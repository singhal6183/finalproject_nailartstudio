package nailart.studio.entity;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class NailArtStudio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nailArtStudioId;
	private String nailArtStudioName;
	private String nailArtStudioAddress;
	private String nailArtStudioCity;
	private String nailArtStudioState;
	private String nailArtStudioZip;
	private String nailArtStudioPhone;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "nailart_studio_customer", joinColumns = @JoinColumn(name = "nail_studio_id"), 
	inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private Set<Customer> customers = new HashSet<>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "nailArtStudio", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Employee> employees = new HashSet<>();

	
}
