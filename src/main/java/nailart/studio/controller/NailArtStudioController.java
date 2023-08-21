package nailart.studio.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nailart.studio.controller.model.NailArtStudioData;
import nailart.studio.controller.model.CustomerData;
import nailart.studio.controller.model.EmployeeData;
import nailart.studio.controller.model.NailArtStudioData.NailArtStudioCustomer;
import nailart.studio.controller.model.NailArtStudioData.NailArtStudioEmployee;
import nailart.studio.service.NailArtStudioService;
import nailart.studio.service.CustomerService;
import nailart.studio.service.EmployeeService;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/nail_art_studio")
@Slf4j

public class NailArtStudioController {
	
	@Autowired
	private NailArtStudioService nailArtStudioService;
	
	@Autowired 
	private EmployeeService employeeService;
	
	@Autowired
	private CustomerService customerService;
   
 /* For NailArt Studio */
	
    @PostMapping    //•	Create new NailArtStudio (POST on Nail Art Studio)
    @ResponseStatus(code = HttpStatus.CREATED)
    public NailArtStudioData createNailArtStudio(@RequestBody NailArtStudioData nailArtStudioData) {
        
    	log.info("Creating NailArt Studio: {}", nailArtStudioData);
        return nailArtStudioService.saveNailArtStudio(nailArtStudioData);
    }
    
    @GetMapping    //•	Browse all NailArtStudios (GET on Studios) – without listing all customers and employees.
	public List<NailArtStudioData> getAllNailArtStudio() {
		
		log.info("Retrieving all NailArt Studio");
	    List<NailArtStudioData> nailArtStudios = nailArtStudioService.retrieveAllNailArtStudios();
	    return nailArtStudios;
	}
    
    @GetMapping("{id}") //•	Browse specific NailArtStudios (GET on Studio specified) – with listing all customers and employees.
	public NailArtStudioData getNailArtStudioById(@PathVariable Long id) {
		
		log.info("Retrieving NailArt Studio with ID= {}", id);
		
	    NailArtStudioData nailArtStudio = nailArtStudioService.retrieveNailArtStudioById(id);
	    return nailArtStudio;
	}
	

    @PutMapping("{id}") //•	Update NailArtStudio (PUT on a specific MassageStudio)
    public NailArtStudioData updateNailArtStudio(@PathVariable Long id, @RequestBody NailArtStudioData nailArtStudioData) {
    	nailArtStudioData.setNailArtStudioId(id);
    	log.info("Updating NailArt Studio with ID {}", id, nailArtStudioData);
        return nailArtStudioService.updateNailArtStudio(id, nailArtStudioData);
    }
    
   
    @PostMapping("{id}/employee") //•	Add new Employee to a NailArtStudio (POST on Employee with NailArtStudio specified) 
    @ResponseStatus(code = HttpStatus.CREATED)
    public NailArtStudioEmployee addEmployeeToNailArtStudio(@PathVariable Long id, @RequestBody NailArtStudioEmployee nailArtStudioEmployee) {
    	log.info("Adding new employee to NailArt Studio with ID=" + id, nailArtStudioEmployee);
        return nailArtStudioService.saveEmployee(id, nailArtStudioEmployee);
    }

    @PostMapping("{id}/customer") //•	Add new Customer to a NailArtStudio (POST on Customer with NailArtStudio specified)
	@ResponseStatus(code = HttpStatus.CREATED)
	public NailArtStudioCustomer addCustomerToNailArtStudio(@PathVariable Long id, @RequestBody NailArtStudioCustomer nailArtStudioCustomer) {
		log.info("Adding new customer to NailArt Studio with ID=" + id, nailArtStudioCustomer, id);
		return nailArtStudioService.saveCustomer(id, nailArtStudioCustomer);
	}
    
   	
	@DeleteMapping("{id}")
	public Map<String, String> deleteNailArtStudioById(@PathVariable Long id) {
		log.info("Deleting pet store with ID={}", id);
		
	    nailArtStudioService.deleteNailArtStudioById(id);
	    return Map.of("message", "Deletion of Nail Art Studio with ID=" + id + " was successful.");
	}
	
    @GetMapping("/employee")  //•	GET all Employees
	public List<EmployeeData> retrieveAllEmployees(){
		log.info("Retrieve all employees.");
		return employeeService.retrieveAllEmployees();
	}
    
    @GetMapping("/customer")   //•	GET all Customers
	public List<CustomerData> retrieveAllCustomers(){
		log.info("Retrieve all customers.");
		return customerService.retrieveAllCustomers();
	}
    
  //retrieve all employee associated with specific NailArtStudioId 
  	@GetMapping("{id}/employee")
  	public Set<NailArtStudioEmployee> retrieveEmployeesbyNailArtStudioId(@PathVariable long id){
  		log.info("Retrieve all employees at NailArt Studio with ID=" + id);
  		return nailArtStudioService.retrieveNailArtStudioById(id).getEmployees();
  	}
  	
  	//retrieve all customers associated with specific NailArtStudioId 
  	@GetMapping("{id}/customer")
  	public Set<NailArtStudioCustomer> retrieveCustomersbyNailArtStudioId(@PathVariable long id){
  		log.info("Retrieve all customers at NailArt Studio with ID=" + id);
  		return nailArtStudioService.retrieveNailArtStudioById(id).getCustomers();
  	}

}