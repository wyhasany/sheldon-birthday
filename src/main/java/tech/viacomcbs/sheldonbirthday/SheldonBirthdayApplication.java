package tech.viacomcbs.sheldonbirthday;

import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SheldonBirthdayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SheldonBirthdayApplication.class, args);
	}

}

@Controller
class SheldonBirthdayController {

	private final Map<Class<?>, BirthdayTask<?,?>> birthdayTaskMap;

	SheldonBirthdayController(List<BirthdayTask<?, ?>> birthdayTasks) {
		birthdayTaskMap = new HashMap<>();
		for (BirthdayTask<?,?> birthdayTask : birthdayTasks) {
			birthdayTaskMap.put(birthdayTask.handles(), birthdayTask);
		}
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@GetMapping(value = "/birthday", produces = "text/plain")
	@ResponseBody
	String birthday() {
		Object chainObject = "";
		List<Class<?>> cycleRecorder = new ArrayList<>();
		cycleRecorder.add(chainObject.getClass());
		while (birthdayTaskMap.get(chainObject.getClass()) != null) {
			BirthdayTask birthdayTask = birthdayTaskMap.get(chainObject.getClass());
			chainObject = birthdayTask.perform(chainObject);
			if (cycleRecorder.contains(chainObject.getClass())) {
				throw new IllegalStateException("Birthday tasks has cycle " + cycleRecorder.toString());
			} else {
				cycleRecorder.add(chainObject.getClass());
			}
		}
		return chainObject.toString();
	}
}

interface BirthdayTask<T, U> {
	U perform(T source);

	default Class<T> handles() {
		return (Class<T>)(((ParameterizedType) this.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0]);
	}
}

@Component
class Rajesh implements BirthdayTask<String, RajeshResult> {

	@Override
	public RajeshResult perform(String source) {
		return new RajeshResult(source +=
			"""
         *               0   0               *
                         |   |
         """);
	}
}

@Component
class Bernardette implements BirthdayTask<RajeshResult, BernardetteResult> {

	@Override
	public BernardetteResult perform(RajeshResult source) {
		String result = source.getResult();
		return new BernardetteResult(result +=
			"""
			            ____|___|____
			         0  |~ ~ ~ ~ ~ ~|   0
			         |  |           |   |
			""");
	}
}

@Component
class Howard implements BirthdayTask<BernardetteResult, HowardResult> {

	@Override
	public HowardResult perform(BernardetteResult source) {
		String result = source.getResult();
		return new HowardResult(result +=
			"""
			      ___|__|___________|___|__
			      |/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/|
			  0   |       H a p p y       |   0
			  |   |/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/|   |
			""");

	}
}

@Component
class Leonard implements BirthdayTask<HowardResult, LeonardResult> {

	@Override
	public LeonardResult perform(HowardResult source) {
		String result = source.getResult();
		return new LeonardResult(result +=
			"""
			 _|___|_______________________|___|__
			|/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/|
			|                                   |
			|         B i r t h d a y! ! !      |
			| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |
			""");
	}
}

@Component
class Penny implements BirthdayTask<LeonardResult, PennyResult> {

	@Override
	public PennyResult perform(LeonardResult source) {
		String result = source.getResult();
		return new PennyResult(result +=
			"""
			|___________________________________|
			|                                   |
			|             Sheldon  ! ! !        |
			|___________________________________|
			""");
	}
}


@Value
class RajeshResult {
	String result;
}

@Value
class BernardetteResult {
	String result;
}

@Value
class HowardResult {
	String result;
}

@Value
class LeonardResult {
	String result;
}

@Value
class PennyResult {
	String result;

	@Override
	public String toString() {
		return result;
	}
}
