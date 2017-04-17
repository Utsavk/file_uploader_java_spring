package fileupload.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import fileupload.common.EmailSenderClass;import fileupload.dao.UserPasswordResetDao;
import fileupload.model.UserPasswordReset;
import fileupload.model.Users;

@Service("userPasswordResetService")
public class UserPasswordResetService {
	
	@Autowired
	private UserPasswordResetDao userPasswordResetDao;
	
	@Autowired
	private EmailSenderClass emailSenderObject;
	
	public void saveResetPasswordToken(UserPasswordReset userPasswordReset){
			
		userPasswordResetDao.saveResetPasswordToken(userPasswordReset);
	}
	
	public void updateTokenAsNull(UserPasswordReset userPasswordReset)
	{
		
	}
	
	public void sendEmailForResetPassword(String text,String email)
	{
		try {
			sendResetPasswordLink(email,text);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendResetPasswordLink(String email, String text) throws MessagingException, IOException {
	       
        String mailSubject = "Password reset request";
        emailSenderObject.sendMail("uktesting007@gmail.com", email, mailSubject, text);
    }
	
	public UserPasswordReset findByResetPasswordToken(String resetToken)
	{
		return null;
	}
}
