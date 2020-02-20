package com.manjavacas.fence.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.EmployeeSTCMedition;
import com.manjavacas.fence.repository.EmployeeSTCMeditionRepository;

@Service
public class EmployeeSTCMeditionService {

	@Autowired
	EmployeeSTCMeditionRepository employeeSTCMeditionRepository;

	public List<EmployeeSTCMedition> getAllEmployeeSTCMeditions() {
		return employeeSTCMeditionRepository.findAll();
	}

	public EmployeeSTCMedition getEmployeeSTCMedition(ObjectId id) {
		return employeeSTCMeditionRepository.findBy_id(id.toString());
	}

	public List<EmployeeSTCMedition> getEmployeeSTCMeditionsByDni(String dni) {
		return employeeSTCMeditionRepository.findByDni(dni);
	}

	public List<EmployeeSTCMedition> getEmployeeSTCMeditionsByDate(Date date) {
		return employeeSTCMeditionRepository.findByDate(date);
	}

	public void addEmployeeSTCMedition(EmployeeSTCMedition employeeSTCMedition) {
		employeeSTCMeditionRepository.insert(employeeSTCMedition);
	}

	public void updateEmployeeSTCMedition(ObjectId id, EmployeeSTCMedition newEmployeeSTCMedition) {
		EmployeeSTCMedition currentEmployeeSTCMedition = employeeSTCMeditionRepository.findBy_id(id.toString());

		currentEmployeeSTCMedition.setDate(newEmployeeSTCMedition.getDate());
		currentEmployeeSTCMedition.setEmployee(newEmployeeSTCMedition.getEmployee());
		currentEmployeeSTCMedition.setStc(newEmployeeSTCMedition.getStc());

		employeeSTCMeditionRepository.save(currentEmployeeSTCMedition);

	}

	public void deleteEmployeeSTCMedition(ObjectId id) {
		employeeSTCMeditionRepository.deleteBy_id(id.toString());
	}

}
