package fileupload.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import fileupload.model.UserPasswordReset;
import fileupload.model.Users;
import fileupload.service.UserPasswordResetService;
import fileupload.service.UserService;

@Controller
public class ForgotPasswordController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserPasswordResetService userPasswordResetService;
	
	@RequestMapping(value = "/login/forget-password", method = RequestMethod.GET)
	  public String resetPasswordView(HttpServletRequest request, final Model model) {
		String heardeName = request.getHeader("x-requested-with");
		if(heardeName == null)//not an ajax
			return "redirect:/login";
	    model.addAttribute("userForgotPasswordForm", new Users());
	    return "forget-password";
	  }
	
	
	  @RequestMapping(value = "/login/forget-password", method = RequestMethod.POST)
	  public String forgetPassword(@ModelAttribute("userForgotPasswordForm") Users user, 
			  final Model model,RedirectAttributes redirectAttributes) throws MessagingException, IOException {
	    //model.addAttribute("userForgotPasswordForm", user);
		  
	    Users foundUser = userService.getUserByEmail(user.getEmail());
	    UserPasswordReset userPasswordReset =null;
	    if (foundUser != null) {
		      String secureToken = UUID.randomUUID().toString();
		      userPasswordReset = new UserPasswordReset();
		      userPasswordReset.setResetPasswordToken(secureToken);
		      
		      /*
		       Give token one hour expiration delay
		      */
		      Date currentDate = new Date();
		      Calendar calendar = Calendar.getInstance();
		      calendar.setTime(currentDate);
		      calendar.add(Calendar.HOUR_OF_DAY, 1); 
		      Date expirationDate = calendar.getTime();
		      
		      userPasswordReset.setResetPasswordExpires(expirationDate);     
		      userPasswordReset.setUser(foundUser);
		      userPasswordResetService.saveResetPasswordToken(userPasswordReset);
		      
		      
				 String text = "You are receiving this because you (or someone else) have requested the reset of the password for your account.\n\n"
				+ "Please click on the following link, or paste into your browser to complete the reset password process :\n\n"
				 + ServletUriComponentsBuilder.fromCurrentContextPath().path("/login/reset-password").queryParam("_key", secureToken).build().toUriString()
				+ "\n\n If you did not request this, please ignore this email and your password will remain unchanged.";
				      
				 //userPasswordResetService.sendEmailForResetPassword(text, foundUser.getEmail());
				 String responseMessage = "A mail has been sent to your mail box";
				 redirectAttributes.addFlashAttribute("message", responseMessage);
	    }
	    else
	    {
			    String responseMessage = "Invalid address mail.This account doesn't exist";
			    redirectAttributes.addFlashAttribute("invalidEMailMessage", responseMessage);
	    }
	    return "redirect:/login";
	 }
	  
	  
	  @RequestMapping(value = "/login/reset-password", method = RequestMethod.GET)
	  public String resetpasswordView( @RequestParam(value = "_key") String resetPasswordToken, final Model model) {
		UserPasswordReset userPasswordResetObj = userPasswordResetService.findByResetPasswordToken(resetPasswordToken);
	    Date expirationDate;
	    Users user = new Users();
		if (userPasswordResetObj != null) {
			expirationDate = userPasswordResetObj.getResetPasswordExpires();
			if (expirationDate.after(new Date())) {
				user=userPasswordResetObj.getUser();
				//model.addAttribute("resetPasswordToken", resetPasswordToken);
			    
			}
			else{
			model.addAttribute("message", "Token expired");
			}
		}
		
		else{
			model.addAttribute("message", "Password Reset access denied");
		}
		model.addAttribute("user", user);
	    return "resetPassword";
	  }
		  
		@RequestMapping(value = "/login/reset-password", method = RequestMethod.POST)
		  public String resetPassword( @RequestParam(value = "_key") String resetPasswordToken, @ModelAttribute("user") Users user,
		      final Model model, RedirectAttributes redirectAttributes) {
			
//			if(!userService.matchPasswords(user.getPassword(), confirmPassword))
//			{
//				model.addAttribute("message","Passwords didn't match");
//				return "Registration";
//			}
			
			UserPasswordReset userToUpdate = userPasswordResetService.findByResetPasswordToken(resetPasswordToken);
		    String uptadedPassword = user.getPassword();
		    userToUpdate.getUser().setPassword(new BCryptPasswordEncoder().encode(uptadedPassword));
		    userToUpdate.setResetPasswordToken(null);
		    userToUpdate.setResetPasswordExpires(null);
		    userPasswordResetService.updateTokenAsNull(userToUpdate);
		    
		    String responseMessage = "Your password was successfully updated";

		    redirectAttributes.addFlashAttribute("message", responseMessage);
		    return "redirect:/login";
		}
}
