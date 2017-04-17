package fileupload.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("fileService")
public class FileService {
	
	
		public boolean uploadFile(String username, String fileName,String path, MultipartFile uploadedfile)
		{
			File folder = new File(path+File.separator+username);
	        if (!folder.exists()) 
	            folder.mkdir();
	        
			InputStream inputStream = null; 
			OutputStream outputStream = null; 
			try { 
				 inputStream = uploadedfile.getInputStream(); 
				 File newFile = new File(folder.getPath() + File.separator + fileName); 
				 if (!newFile.exists()) { 
					 newFile.createNewFile(); 
				 } 
				 
				 outputStream = new FileOutputStream(newFile); 
				 int read = 0; byte[] bytes = new byte[1024]; 
				 while ((read = inputStream.read(bytes)) != -1) { 
					 outputStream.write(bytes, 0, read); 
					 } 
				 
				 outputStream.flush();  
				 outputStream.close();
			 } 
		 catch (IOException e) { 
			 // TODO Auto-generated catch block 
			 e.printStackTrace(); 
			 return false;
		 } 
		
		return true;
	}
		
	public List<String> userAccociatedFiles(String userEmail, String path)
	{
		List<String> uploadedFiles = new ArrayList<String>();
		
		if(!(Files.isDirectory(Paths.get(path+File.separator+userEmail))))
		{
			return null;
		}
		
		File[] files = new File(path+File.separator+userEmail).listFiles();
		

		for (File file : files) {
		    if (file.isFile()) {
		    	uploadedFiles.add(file.getName());
		    }
		}
		return uploadedFiles;
	}

}
