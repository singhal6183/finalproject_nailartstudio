package nailart.studio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nailart.studio.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}