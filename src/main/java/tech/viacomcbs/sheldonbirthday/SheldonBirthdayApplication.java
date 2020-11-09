package tech.viacomcbs.sheldonbirthday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@SpringBootApplication
public class SheldonBirthdayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SheldonBirthdayApplication.class, args);
	}

}

@Controller
class SheldonBirthdayController {

	private final List<BirthdayTask> birthdayTasks;

	SheldonBirthdayController(List<BirthdayTask> birthdayTasks) {
		// Collections.sort(birthdayTasks);
		this.birthdayTasks = birthdayTasks;
	}

	@GetMapping(value = "/birthday", produces = "text/plain")
	@ResponseBody
	String birthday() {
		String start = "";
		for (BirthdayTask birthdayTask : birthdayTasks) {
			start = birthdayTask.perform(start);
		}
		return start;
	}
}

interface BirthdayTask extends Comparable<BirthdayTask> {
	String perform(String source);

	int getOrder();

	@Override
	default int compareTo(BirthdayTask o) {
		return getOrder() - o.getOrder();
	}
}

@Component
class Rajesh implements BirthdayTask {

	@Override
	public String perform(String source) {
		return source +=
			"""
         *               0   0               *
                         |   |
         """;
	}

	@Override
	public int getOrder() {
		return 1;
	}
}

@Component
class Bernardette implements BirthdayTask {

	@Override
	public String perform(String source) {
		return source +=
			"""
			            ____|___|____
			         0  |~ ~ ~ ~ ~ ~|   0
			         |  |           |   |
			""";
	}

	@Override
	public int getOrder() {
		return 2;
	}
}

@Component
class Howard implements BirthdayTask {

	@Override
	public String perform(String source) {
		return source +=
			"""
			      ___|__|___________|___|__
			      |/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/|
			  0   |       H a p p y       |   0
			  |   |/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/|   |
			""";
	}

	@Override
	public int getOrder() {
		return 3;
	}
}

@Component
class Leonard implements BirthdayTask {

	@Override
	public String perform(String source) {
		return source +=
			"""
			 _|___|_______________________|___|__
			|/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/|
			|                                   |
			|         B i r t h d a y! ! !      |
			| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |
			""";
	}

	@Override
	public int getOrder() {
		return 4;
	}
}

@Component
class Penny implements BirthdayTask {

	@Override
	public String perform(String source) {
		return source +=
			"""
			|___________________________________|
			|                                   |
			|             Sheldon  ! ! !        |
			|___________________________________|
			""";
	}


	@Override
	public int getOrder() {
		return 5;
	}
}