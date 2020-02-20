package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.Admin;
import com.manjavacas.fence.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;

	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	public Admin getAdmin(String username) {
		return adminRepository.findByUsername(username);
	}

	public void addAdmin(Admin admin) {
		adminRepository.insert(admin);
	}

	public void updateAdmin(String username, Admin newAdmin) {
		Admin currentAdmin = adminRepository.findByUsername(username);

		currentAdmin.setUsername(newAdmin.getUsername());
		currentAdmin.setPassword(newAdmin.getPassword());
		currentAdmin.setEmail(newAdmin.getEmail());
		currentAdmin.setCreate_time(newAdmin.getCreate_time());

		adminRepository.save(currentAdmin);
	}

	public void deleteAdmin(String username) {
		adminRepository.deleteByUsername(username);
	}

}
