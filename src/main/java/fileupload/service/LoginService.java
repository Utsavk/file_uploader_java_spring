package fileupload.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fileupload.dao.UserDao;
import fileupload.model.Users;

@Service("loginService")
public class LoginService implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	
	@SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users user = userDao.getUserByEmail(email);
		
		List authorities = buildUserAuthority("ROLE_USER");
		try
		{
			return new User(user.getEmail().toLowerCase(), user.getPassword(),
			    true, true, true, true, authorities);
		}
		catch(java.lang.NullPointerException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private List buildUserAuthority(String userRole) {

		  Set setAuths = new HashSet();
		  // Build user's authorities
		  setAuths.add(new SimpleGrantedAuthority(userRole));
		  

		  List Result = new ArrayList(setAuths);

		  return Result;
	}

}
