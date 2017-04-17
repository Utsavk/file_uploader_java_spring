package fileupload.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fileupload.dao.UserDao;
import fileupload.model.UserPasswordReset;
import fileupload.model.Users;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserDao userDao;
	

	public void addUser(Users p) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String unsafePassword = p.getPassword();
		String hashedPassword = passwordEncoder.encode(unsafePassword);
		p.setPassword(hashedPassword);
		userDao.addUser(p);
	}
	
	
	
	public Users getUserByEmail(String email)
	{
		return userDao.getUserByEmail(email);
	}
	
	public Users getUserById(Integer id)
	{
		return userDao.getUserById(id);
	}
	
	public boolean checkUsernameAvailability(String email)
	{
		if(userDao.getUserByEmail(email)==null)
			return true;
		return false;
	}
	
	public void updateUser(Users user,Integer id) {
		userDao.updateUser(user,id);
    }
	
	public boolean discrepencyInUserInfo(String userEmailFromRequest,Users userFromUrl)
	{
		if(userEmailFromRequest.equals(userFromUrl.getEmail()))
				return false;
				
		return true;
	}
	
	public boolean UserAlreadyExists(String email)
	{
		
		Users alreadyPresent = null;
		try{
			alreadyPresent = userDao.getUserByEmail(email);
		}
		catch(org.hibernate.ObjectNotFoundException e)
		{
			//user is not registered previously
			alreadyPresent = null;
		}
		
		if(alreadyPresent != null)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean matchPasswords(String p1,String p2)
	{
		return p1.equals(p2);
	}
	
	
	
	
	
}
