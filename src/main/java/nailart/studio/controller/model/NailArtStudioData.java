package nailart.studio.controller.model;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import nailart.studio.entity.Customer;
import nailart.studio.entity.Employee;
import nailart.studio.entity.NailArtStudio;

@Data
@NoArgsConstructor
public class NailArtStudioData {

	private Long nailArtStudioId;
	private String nailArtStudioName;
	private String nailArtStudioAddress;
	private String nailArtStudioCity;
	private String nailArtStudioState;
	private String nailArtStudioZip;
	private String nailArtStudioPhone;
	private Set<NailArtStudioCustomer> customers = new HashSet<>();
	private Set<NailArtStudioEmployee> employees = new HashSet<>();


	public NailArtStudioData(NailArtStudio nailArtStudio) {
		nailArtStudioId = nailArtStudio.getNailArtStudioId();
		nailArtStudioName = nailArtStudio.getNailArtStudioName();
		nailArtStudioAddress = nailArtStudio.getNailArtStudioAddress();
		nailArtStudioCity = nailArtStudio.getNailArtStudioCity();
		nailArtStudioState = nailArtStudio.getNailArtStudioState();
		nailArtStudioZip = nailArtStudio.getNailArtStudioZip();
		nailArtStudioPhone = nailArtStudio.getNailArtStudioPhone();
		
		
		for (Customer customer : nailArtStudio.getCustomers()) {
            //NailArtStudioCustomer nailArtStudioCustomer = new NailArtStudioCustomer(customer);
            customers.add(new NailArtStudioCustomer(customer));
        }
		
		for (Employee employee : nailArtStudio.getEmployees()) {
			//NailArtStudioEmployee nailArtStudioEmployee = new NailArtStudioEmployee(employee);
			employees.add(new NailArtStudioEmployee(employee));
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class NailArtStudioEmployee {
		private Long employeeId;
		private String employeeFirstName;
		private String employeeLastName;
		private String employeePhone;
		private String employeeJobTitle;
		
		public NailArtStudioEmployee(Employee employee) {
			employeeId = employee.getEmployeeId();
			employeeFirstName = employee.getEmployeeFirstName();
			employeeLastName = employee.getEmployeeLastName();
			employeePhone = employee.getEmployeePhone();
			employeeJobTitle = employee.getEmployeeJobTitle();
			
		}
	}
	

	@Data
	@NoArgsConstructor
	public static class NailArtStudioCustomer {
		
		private Long customerId;
		private String customerFirstName;
		private String customerLastName;
		private String customerEmail;
		
		public NailArtStudioCustomer(Customer customer) {
            customerId = customer.getCustomerId();
            customerFirstName = customer.getCustomerFirstName();
            customerLastName = customer.getCustomerLastName();
            customerEmail = customer.getCustomerEmail();
        }
		
		
	}
}


