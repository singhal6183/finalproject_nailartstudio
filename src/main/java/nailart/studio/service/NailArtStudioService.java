package nailart.studio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import nailart.studio.controller.model.NailArtStudioData;
import nailart.studio.controller.model.NailArtStudioData.NailArtStudioCustomer;
import nailart.studio.controller.model.NailArtStudioData.NailArtStudioEmployee;
import nailart.studio.dao.NailArtStudioDao;
import nailart.studio.dao.CustomerDao;
import nailart.studio.dao.EmployeeDao;
import nailart.studio.entity.Customer;
import nailart.studio.entity.Employee;
import nailart.studio.entity.NailArtStudio;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.LinkedList;
import java.util.List;

@Service
public class NailArtStudioService {

    private final NailArtStudioDao NailArtStudioDao;
    private final EmployeeDao employeeDao;
	private final CustomerDao customerDao;

    @Autowired
    public NailArtStudioService(NailArtStudioDao NailArtStudioDao, EmployeeDao employeeDao, CustomerDao customerDao) {
        this.NailArtStudioDao = NailArtStudioDao;
        this.employeeDao = employeeDao;
		this.customerDao = customerDao;
		
    }
    
    // for NailArtStudio data
   // @Transactional(readOnly = false)
    public NailArtStudioData saveNailArtStudio(NailArtStudioData NailArtStudioData) {
        NailArtStudio NailArtStudio = findOrCreateNailArtStudio(NailArtStudioData.getNailArtStudioId());
        copyNailArtStudioFields(NailArtStudio, NailArtStudioData);
        NailArtStudio savedNailArtStudio = NailArtStudioDao.save(NailArtStudio);
        return new NailArtStudioData(savedNailArtStudio);
    }

    public NailArtStudioData updateNailArtStudio(Long NailArtStudioId, NailArtStudioData NailArtStudioData) {
        NailArtStudio NailArtStudio = findNailArtStudioById(NailArtStudioId);
        if (NailArtStudio == null) {
            throw new NoSuchElementException("NailArt studio not found with ID: " + NailArtStudioId);
        }
        copyNailArtStudioFields(NailArtStudio, NailArtStudioData);
        NailArtStudio updatedNailArtStudio = NailArtStudioDao.save(NailArtStudio);
        return new NailArtStudioData(updatedNailArtStudio);
    }

    private NailArtStudio findOrCreateNailArtStudio(Long NailArtStudioId) {
        if (NailArtStudioId == null) {
            return new NailArtStudio();
        } else {
            Optional<NailArtStudio> NailArtStudioOptional = NailArtStudioDao.findById(NailArtStudioId);
            return NailArtStudioOptional.orElseThrow(() -> new NoSuchElementException("NailArt studio not found with ID: " + NailArtStudioId));
        }
    }

    private NailArtStudio findNailArtStudioById(Long NailArtStudioId) {
        Optional<NailArtStudio> NailArtStudioOptional = NailArtStudioDao.findById(NailArtStudioId);
        return NailArtStudioOptional.orElse(null);
    }

    private void copyNailArtStudioFields(NailArtStudio NailArtStudio, NailArtStudioData NailArtStudioData) {
        NailArtStudio.setNailArtStudioId(NailArtStudioData.getNailArtStudioId());
        NailArtStudio.setNailArtStudioName(NailArtStudioData.getNailArtStudioName());
        NailArtStudio.setNailArtStudioAddress(NailArtStudioData.getNailArtStudioAddress());
        NailArtStudio.setNailArtStudioCity(NailArtStudioData.getNailArtStudioCity());
        NailArtStudio.setNailArtStudioState(NailArtStudioData.getNailArtStudioState());
        NailArtStudio.setNailArtStudioZip(NailArtStudioData.getNailArtStudioZip());
        NailArtStudio.setNailArtStudioPhone(NailArtStudioData.getNailArtStudioPhone());

    
    }
    // For Employee data
    @Transactional(readOnly = false)
	public NailArtStudioEmployee saveEmployee(Long nailArtStudioId, NailArtStudioEmployee nailArtStudioEmployee) {
		NailArtStudio nailArtStudio = findNailArtStudioById(nailArtStudioId);
		Long employeeId = nailArtStudioEmployee.getEmployeeId();
		Employee employee = findOrCreateEmployee(nailArtStudioId, employeeId);
		
		copyEmployeeFields(employee, nailArtStudioEmployee);
		
		employee.setNailArtStudio(nailArtStudio);
		nailArtStudio.getEmployees().add(employee);
		
		Employee dbEmployee = employeeDao.save(employee);
		
		return new NailArtStudioEmployee(dbEmployee);
	}
	private Employee findOrCreateEmployee(Long NailArtStudioId, Long employeeId) {
		if(employeeId == null) {
			return new Employee();
		} 
		return findEmployeeById(NailArtStudioId, employeeId);
	}


	private Employee findEmployeeById(Long NailArtStudioId, Long employeeId) {
		 Employee employee = employeeDao.findById(employeeId).orElseThrow(
				 () -> new NoSuchElementException(
				 "Employee with ID=" + employeeId + " was not found."));
		 
		 if(employee.getNailArtStudio().getNailArtStudioId() != NailArtStudioId) {
			 throw new IllegalArgumentException("The employee with ID=" + employeeId 
					 + " is not employed by the nailart studio with ID=" + NailArtStudioId + ".");
		 }
		 return employee;
	}
	
	private void copyEmployeeFields(Employee employee, NailArtStudioEmployee nailArtStudioEmployee) {
		
		employee.setEmployeeId(nailArtStudioEmployee.getEmployeeId());
		employee.setEmployeeFirstName(nailArtStudioEmployee.getEmployeeFirstName());
		employee.setEmployeeJobTitle(nailArtStudioEmployee.getEmployeeJobTitle());
		employee.setEmployeeLastName(nailArtStudioEmployee.getEmployeeLastName());
		employee.setEmployeePhone(nailArtStudioEmployee.getEmployeePhone());
	}
	
	// for Customer data
	@Transactional(readOnly = false)
	public NailArtStudioCustomer saveCustomer(Long nailArtStudioId, NailArtStudioCustomer nailArtStudioCustomer) {
		NailArtStudio nailArtStudio = findNailArtStudioById(nailArtStudioId);
		Long customerId = nailArtStudioCustomer.getCustomerId();
		Customer customer = findOrCreateCustomer(nailArtStudioId, customerId);
		
		copyCustomerFields(customer, nailArtStudioCustomer);
		
		customer.getNailArtStudio().add(nailArtStudio);
		nailArtStudio.getCustomers().add(customer);
		
		Customer dbCustomer = customerDao.save(customer);
		
		return new NailArtStudioCustomer(dbCustomer);
	}
	
	private Customer findOrCreateCustomer(Long NailArtStudioId, Long customerId) {
		if(customerId == null) {
			return new Customer();
		} 
		return findCustomerById(NailArtStudioId, customerId);
	}


	private Customer findCustomerById(Long NailArtStudioId, Long customerId) {
		 Customer customer = customerDao.findById(customerId).orElseThrow(
				 () -> new NoSuchElementException(
				 "Customer with ID=" + customerId + " was not found."));
		 
		 boolean found = false;
		 
		 for(NailArtStudio NailArtStudio : customer.getNailArtStudio()) {
			 if(NailArtStudio.getNailArtStudioId() == NailArtStudioId) {
				 found = true;
				 break;
			 }
		 }
		 if(!found) {
			 throw new IllegalArgumentException("The customer with ID=" + customerId 
					 + " is not a member of the nailart studio with ID=" + NailArtStudioId + ".");
		 }
		 return customer;
	}
	
	private void copyCustomerFields(Customer customer, NailArtStudioCustomer NailArtStudioCustomer) {
		customer.setCustomerFirstName(NailArtStudioCustomer.getCustomerFirstName());
		customer.setCustomerId(NailArtStudioCustomer.getCustomerId());
		customer.setCustomerLastName(NailArtStudioCustomer.getCustomerLastName());
		customer.setCustomerEmail(NailArtStudioCustomer.getCustomerEmail());
	}
	
	@Transactional(readOnly = true)
	public List<NailArtStudioData> retrieveAllNailArtStudios() {
		List<NailArtStudio> NailArtStudios = NailArtStudioDao.findAll();
		
		List<NailArtStudioData> response = new LinkedList<>();
		
		for(NailArtStudio NailArtStudio : NailArtStudios) {
			NailArtStudioData psd = new NailArtStudioData(NailArtStudio);
			
			psd.getCustomers().clear();
			psd.getEmployees().clear();
			
			response.add(psd);
		}
		return response;
	}

	@Transactional(readOnly = true)
	public NailArtStudioData retrieveNailArtStudioById(Long NailArtStudioId) {
		return new NailArtStudioData(findNailArtStudioById(NailArtStudioId));
	}

	@Transactional(readOnly = false)
	public void deleteNailArtStudioById(Long NailArtStudioId) {
		NailArtStudio NailArtStudio = findNailArtStudioById(NailArtStudioId);
		NailArtStudioDao.delete(NailArtStudio);
	}

}