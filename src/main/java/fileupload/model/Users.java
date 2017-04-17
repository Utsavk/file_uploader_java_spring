package fileupload.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import fileupload.validator.ValidEmail;

@JsonAutoDetect
@Entity
@Table(name="USER")
public class Users {
	

	public interface RegisterUserValidationStep{}
	 public interface EditUserValidationStep{}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

	@NotNull
	@ValidEmail(groups = { RegisterUserValidationStep.class })
	@NotEmpty(message = "Please provide your email.",groups = { EditUserValidationStep.class })
	private String email;

	@NotNull
	@NotEmpty(message = "Please enter a password.",groups = { RegisterUserValidationStep.class })
	@Size(min = 3, max = 15, message = "Your password must between 3 and 15 characters",groups = { RegisterUserValidationStep.class })
	private String password;
	
	@NotNull
	@NotEmpty(message = "Please provide your name.",groups = { EditUserValidationStep.class })
	private String name;
	
	@NotNull
	@NotEmpty(message = "Please provide your company name.",groups = { EditUserValidationStep.class })
	private String company;
	
	@JsonProperty
	public String getName() {
        return name;
    }
	
	@JsonProperty
	public Integer getId() {
        return id;
    }
	
	@JsonProperty
	public String getEmail() {
        return email;
    }
	
	@JsonProperty
	public String getPassword() {
        return password;
    }
	
	@JsonProperty
	public String getCompany() {
        return company;
    }
	
	public void setId(Integer id) {
		this.id = id;
    }
	

	public void setName(String name) {
        this.name = name;
    }
	
	public void setEmail(String email) {
        this.email = email.toLowerCase();
    }
	
	public void setPassword(String password) {
        this.password = password;
    }
	
	public void setCompany(String company) {
        this.company = company;
    }

}
