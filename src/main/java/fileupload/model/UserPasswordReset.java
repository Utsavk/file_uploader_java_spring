package fileupload.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER_PASSWORD_RESET_TOKENS")
public class UserPasswordReset {
	
	  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Integer id;
	  
	  @OneToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "user_id")
	  private Users user;
	  
	  @Column(name=" reset_password_token")
	  private String resetPasswordToken;
	  
	  @Column(name=" reset_password_expires")
	  private Date resetPasswordExpires;
 
	 
	  public Users getUser() {
	    return user;
	  }
	  
	  public void setUser(Users user) {
	    this.user = user;
	  }
	  
	  public String getResetPasswordToken() {
	    return resetPasswordToken;
	  }
	  public void setResetPasswordToken(String resetPasswordToken) {
	    this.resetPasswordToken = resetPasswordToken;
	  }
	  public Date getResetPasswordExpires() {
	    return resetPasswordExpires;
	  }
	  public void setResetPasswordExpires(Date resetPasswordExpires) {
	    this.resetPasswordExpires = resetPasswordExpires;
	  }

}
