package com.manjavacas.fence.user;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private ArrayList<User> users = new ArrayList<User>(Arrays.asList(new User(1), new User(2), new User(3)));

	public ArrayList<User> getAllUsers() {
		return users;
	}

	public User getUser(int id) {
		return users.stream().filter(u -> u.getId() == id).findFirst().get();
	}

	public void addUser(User user) {
		users.add(user);
	}

	public void updateUser(int id, User user) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == id) {
				users.set(i, user);
				return;
			}
		}
	}

	public void deleteUser(int id) {
		users.removeIf(u -> u.getId() == id);
	}

}
