package com.manjavacas.fence.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.Admin;
import com.manjavacas.fence.controller.AdminController;

@RestController
public class LoginManager {

	@Autowired
	AdminController adminController;

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/login", consumes="application/json", produces = "application/json")
	public String login(@RequestBody Map<String, String> json) throws Exception {
		
		String username = json.get("username");
		String password = json.get("password");
		
		Admin admin = adminController.getAdmin(username);

		// Response
		JSONObject response = new JSONObject();

		if(admin.getPassword().equals(password)) {
			response.put("type", "OK");
		} else {
			response.put("type", "ERROR");
		}

		return response.toString();
	}
}
