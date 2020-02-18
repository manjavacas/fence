package com.manjavacas.fence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.Admin;
import com.manjavacas.fence.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/Admins")
	public List<Admin> getAllAdmins() {
		return adminService.getAllAdmins();
	}

	@RequestMapping("/Admins/{username}")
	public Admin getAdmin(@PathVariable String username) {
		return adminService.getAdmin(username);
	}

	@PostMapping(value = "/Admins")
	public void addAdmin(@RequestBody Admin Admin) {
		adminService.addAdmin(Admin);
	}

	@PutMapping(value = "/Admins/{username}")
	public void updateAdmin(@PathVariable String username, @RequestBody Admin admin) {
		adminService.updateAdmin(username, admin);
	}

	@DeleteMapping("/Admins/{username}")
	public void deleteAdmin(@PathVariable String username) {
		adminService.deleteAdmin(username);
	}
}
