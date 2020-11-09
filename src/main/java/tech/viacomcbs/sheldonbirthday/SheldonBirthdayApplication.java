package tech.viacomcbs.sheldonbirthday;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
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
@RequiredArgsConstructor
class SheldonBirthdayController {

	private final List<BirthdayTask> birthdayTasks;

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

interface BirthdayTask {
	String perform(String source);
}

@Component
@Order(1)
class Rajesh implements BirthdayTask {

	@Override
	public String perform(String source) {
		return source +=
			"""
         *               0   0               *
                         |   |
         """;
	}
}

@Component
@Order(2)
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
}

@Component
@Order(3)
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
}

@Component
@Order(3)
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
}

@Component
@Order(4)
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
}