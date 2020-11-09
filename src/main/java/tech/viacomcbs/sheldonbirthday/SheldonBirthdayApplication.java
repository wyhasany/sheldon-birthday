package tech.viacomcbs.sheldonbirthday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class SheldonBirthdayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SheldonBirthdayApplication.class, args);
	}

}

@Controller
class SheldonBirthdayController {

	@GetMapping(value = "/birthday", produces = "text/plain")
	@ResponseBody
	String birthday() {
		String start = "";
		// rajesh
		start +=
   			"""
			*               0   0               *
			                |   |
			""";
		// bernardet
		start +=
			"""
			            ____|___|____
			         0  |~ ~ ~ ~ ~ ~|   0
			         |  |           |   |
			""";
		// howard
		start +=
			"""
			      ___|__|___________|___|__
			      |/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/|
			  0   |       H a p p y       |   0
			  |   |/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/|   |
			""";
		// leonard
		start +=
			"""
			 _|___|_______________________|___|__
			|/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/|
			|                                   |
			|         B i r t h d a y! ! !      |
			| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |
			""";
		// penny
		start +=
			"""
			|___________________________________|
			|                                   |
			|             Sheldon  ! ! !        |
			|___________________________________|
			""";
		return start;
	}

}