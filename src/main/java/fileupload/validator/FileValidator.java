package fileupload.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fileupload.model.File;

public class FileValidator implements Validator{
	
	
	public boolean supports(Class clazz) { 
		//just validate the FileUpload instances 
		return File.class.isAssignableFrom(clazz); 
		} 
	
	public void validate(Object target, Errors errors) { 
		File file = (File)target; 
		String fileExt = getFileExtension(file);
		if(file.getFile().getSize()==0){ 
			errors.rejectValue("file", "uploadForm.selectFile", "Please select a file!"); 
			} 
		
		
		else if(!(fileExt.equals("mp3")|| fileExt.equals("mp4") || fileExt.equals("3gp") || fileExt.equals("wav")))
		{
			errors.rejectValue("file", "uploadForm.selectFile", "File is not acceptable!   Only .mp4, .mp3, .3gp,.wav can be uploaded"); 
		}
	}
	private static String getFileExtension(File file) {
        String fileName = file.getFile().getOriginalFilename();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        	return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
	
		
}
