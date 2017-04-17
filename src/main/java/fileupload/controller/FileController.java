package fileupload.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import fileupload.model.File;
import fileupload.model.Users;
import fileupload.service.FileService;
import fileupload.service.UserService;
import fileupload.validator.FileValidator;


@Controller
public class FileController {
	

	 @Autowired
	 FileValidator fileValidator;
	 
	 @Autowired
	 FileService fileService;
	 
	 @Autowired
	 UserService userService;
	 
	 final String UPLOAD_DIRECTORY ="/Uploaded_Files"; 
	 
	 @RequestMapping("/dashboard/submitFileUpload") 
	 public String fileUploaded( @ModelAttribute("uploadedFile") File uploadedFile, 
			 BindingResult result,HttpSession session,Principal principal,ModelMap model) { 
		 
		  
		 MultipartFile file = uploadedFile.getFile(); 
		 fileValidator.validate(uploadedFile, result);
		 
		 ServletContext context = session.getServletContext();  
	     String path = context.getRealPath(UPLOAD_DIRECTORY);  
	     String fileName = file.getOriginalFilename();  
		   
		  
		 // If it has error, redirect it to same page 
		 
		 if (result.hasErrors()) {                     
		        return "dashboard"; 
		} 
		 
		 boolean uploadStaus = fileService.uploadFile(principal.getName(),fileName,path,file) ;
		 
		 if(uploadStaus)
		 {
			 model.addAttribute("fileName", fileName); 
			 return "UploadSuccess";
		 }
		 return "redirect:/dashboard";
	 }
		 
	 @RequestMapping(value = "/dashboard/uploaded-files",method = RequestMethod.GET) 
	 public String uploadedFiles(ModelMap model,Principal principal,HttpSession session) { 
		 	String userEmail = principal.getName();
		 	
		 	ServletContext context = session.getServletContext();  
		    String path = context.getRealPath(UPLOAD_DIRECTORY);
		     
		 	List<String> uploadedFiles = fileService.userAccociatedFiles(userEmail,path);
		 	
		 	if(uploadedFiles!=null)
		 		model.addAttribute("uploadedFiles", uploadedFiles);
		 	
		 	Users thisUser = userService.getUserByEmail(userEmail);
		 	
		 	model.addAttribute("name", thisUser.getName());
		 	model.addAttribute("companyName", thisUser.getCompany());
		 	
			return "fileUploadRecord";
	 }

}
