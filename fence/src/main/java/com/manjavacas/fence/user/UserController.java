package com.manjavacas.fence.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	public ArrayList<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping("/users/{id}")
	public User getUser(@PathVariable int id) {
		return userService.getUser(id);
	}

	@PostMapping(value = "/users")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	@PutMapping(value = "/users/{id}")
	public void updateUser(@PathVariable int id, @RequestBody User user) {
		userService.updateUser(id, user);
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}
}
