package nailart.studio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nailart.studio.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
