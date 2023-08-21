package nailart.studio.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nailart.studio.controller.model.EmployeeData;
import nailart.studio.dao.EmployeeDao;
import nailart.studio.entity.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;

	@Transactional(readOnly = true)
	public List<EmployeeData> retrieveAllEmployees() {
		List<Employee> employees = employeeDao.findAll();
		List<EmployeeData> response = new LinkedList<>();
		
		for(Employee employee : employees) {
			EmployeeData psd = new EmployeeData(employee);
			response.add(psd);
		}
		return response;
	}

}
