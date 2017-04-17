package fileupload.controller;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fileupload.model.Users;
import fileupload.service.UserService;


@Controller
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RestController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/api/v1/user/{id}", method = RequestMethod.GET)
	public @ResponseBody Users getUser(@PathVariable String id) {
		Users user = userService.getUserById(Integer.parseInt(id));
		return user;
	}
}
