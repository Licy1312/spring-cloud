package org.spring.cloud.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class ServerClientApplication {

	 @Value("${server.port}")
	 private String port;

	private List<User> userList =
			Arrays.asList(new User(1001,"Licy","Shanghai"),new User(1002,"Yage","Henan"),new User(1003,"Jinmu","Shanghai"));

	@RequestMapping("/user/{name}")
	public String home(@PathVariable("name") String name) {
		return "hi "+name+",i am from port:" +port;
	}

	@RequestMapping("/userId/{id}")
	public Optional<User> getUser(@PathVariable("id") int id) {
		return userList.stream().filter(user -> user.getId()==id).findFirst();
	}

	@RequestMapping("/id/{id}")
	public User getOneUser(@PathVariable("id") int id) {
		return userList.stream().filter(user -> user.getId()==id).findFirst().get();
	}

	public static void main(String[] args) {
		SpringApplication.run(ServerClientApplication.class, args);
	}


}
