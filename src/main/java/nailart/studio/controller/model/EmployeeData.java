package nailart.studio.controller.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import nailart.studio.entity.NailArtStudio;
import nailart.studio.entity.Employee;

@Data
@NoArgsConstructor
public class EmployeeData {
	private Long employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeePhone;
	private String employeeJobTitle;
	//NailArtStudio nailArtStudio;
	
//	private MassageStudio massageStudio;
//	Long massageStudioId = TherapistData.getMassageStudioId(massageStudio);
	
	
	public EmployeeData(Employee employee) {
		employeeId = employee.getEmployeeId();
		employeeFirstName = employee.getEmployeeFirstName();
		employeeLastName = employee.getEmployeeLastName();
		employeePhone = employee.getEmployeePhone();
		employeeJobTitle = employee.getEmployeeJobTitle();
	//	massageStudioId = therapist.getMassageStudioId(massageStudio);
		}
	

}
