package fileupload.controller;
import fileupload.model.Users;
import fileupload.service.UserService;

import java.security.Principal;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {
	
	@RequestMapping(value = "/",method = RequestMethod.GET) 
	public String ohooo(ModelMap model) { 
		
		model.addAttribute("message", "ohooo");
		return "../index"; 
	}
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/dashboard",method = RequestMethod.GET) 
	public ModelAndView dashboard(Principal principal, HttpServletRequest request) { 
		//first time logging in
		if(request.getSession().getAttribute("userId") == null)
		{
			Users loggedInUser = userService.getUserByEmail(principal.getName());
			request.getSession().setAttribute("userId", loggedInUser.getId());
		}
		String loggedInUserName=principal.getName(); 
		return new ModelAndView("dashboard", "userName", loggedInUserName); 
	}
		
	@RequestMapping(value = { "/dashboard/edit-user-{id}" }, method = RequestMethod.GET)
    public String editUserGet(@PathVariable String id, Principal principal, ModelMap model,
    		RedirectAttributes redirectAttributes) {
		
		Users user;
		
		try{
			user = userService.getUserById(Integer.parseInt(id));
		}
        catch(java.lang.NumberFormatException e){
        	user = null;
        }
		
        if(user == null || userService.discrepencyInUserInfo(principal.getName(),user))
        {
        	redirectAttributes.addFlashAttribute("message", "Page not accessible by you");
        	return "redirect:/dashboard";
        }
        model.addAttribute("user", user);
        model.addAttribute("userId", user.getId());
        return "profileUpdate";
    }
	
	@RequestMapping(value = { "/dashboard/edit-user-{id}" }, method = RequestMethod.POST)
    public String editUserPost(@Validated(Users.EditUserValidationStep.class) @ModelAttribute("user") Users user, BindingResult result,
    		@PathVariable String id, Principal principal, 
    		ModelMap model, RedirectAttributes redirectAttributes) {
        
		if(userService.discrepencyInUserInfo(principal.getName(),user))
        {
			redirectAttributes.addFlashAttribute("message", "Profile could not be updated");
        	return "redirect:/dashboard";
        }
		
		if(result.hasErrors())
		{
			model.addAttribute("message","error(s) occured - Profile update not done");
			return "profileUpdate";
			
		}

        userService.updateUser(user,new Integer(id));
        user.setEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userId", id);
        model.addAttribute("message","Your profile is successfully updated");
        return "profileUpdate";
    }
	
	@RequestMapping(value="/login", method = RequestMethod.GET) 
	public String login( Principal principal) { 
		//already logged in user
		if(principal!=null)
		{
			return "redirect:/dashboard";
		}
		return "login"; 
	}

	
	@RequestMapping(value="/loginError", method = RequestMethod.GET) 
	public String loginError(ModelMap model) { 
		model.addAttribute("error", "true"); 
		return "login"; 
	}
	
	@RequestMapping("/logout")
	public String showLoggedout(RedirectAttributes redirectAttributes,ModelMap model){
		model.addAttribute("message","You are successfuly logged out");
	    return "redirect:/";
	}
	
	
		
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String viewRegistration(Model model) {
		
		Users userForm = new Users();		
		model.addAttribute("userForm", userForm);
		return "Registration";
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String processRegistration(@Validated({Users.RegisterUserValidationStep.class,Users.EditUserValidationStep.class}) @ModelAttribute("userForm") Users user,
			BindingResult result,HttpServletRequest httpServletRequest,@RequestParam("confirm_password") 
	String confirmPassword, ModelMap model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		
		
		if(result.hasErrors())
		{
			model.addAttribute("message","error(s) occured - Registration not done");
			return "Registration";
			
		}
		
		if(userService.UserAlreadyExists(user.getEmail())){
			model.addAttribute("message","Account with this email already exists");
			return "Registration";
			
		}
		
		if(!userService.matchPasswords(user.getPassword(), confirmPassword))
		{
			model.addAttribute("message","Passwords didn't match");
			return "Registration";
		}
		else{
				String password = user.getPassword();
				userService.addUser(user);
				try {
					httpServletRequest.login(user.getEmail().toLowerCase(), password);
					request.getSession().setAttribute("userId", user.getId());
					redirectAttributes.addFlashAttribute("message", "Welcome new User!");
					return "redirect:/dashboard";
				} catch (ServletException e) {
					//authentication failed
					redirectAttributes.addFlashAttribute("message", "Sorry! Something bad happened, but you are registered successfuly");
					e.printStackTrace();
				}
		}
		return "redirect:/register";
	}
	
	@RequestMapping(value="/register/check-email-availability", method=RequestMethod.GET)
    public @ResponseBody boolean checkEmailAvailability(HttpServletRequest request) {
		String email = request.getParameter("email").trim();
		boolean available =false;
		if(email.length()>0)
		{
	        available = userService.checkUsernameAvailability(email);
		}
        return available;
    }
	
	
}
