package fileupload.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fileupload.model.Users;

@Repository
public class UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	 Session session = null;
	 Transaction tx = null;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	
	public void addUser(Users p) {
		 session = sessionFactory.openSession();
		 tx = session.getTransaction();
		 session.beginTransaction();	
		 session.persist(p);
		 tx.commit();
	}
	
	public void updateUser(Users p,int id) {
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();	
		Users entity = (Users) session.get(Users.class, id);
        if(entity!=null){
            entity.setName(p.getName());
            entity.setCompany(p.getCompany());
            session.save(entity); 
        }
        try{
        	tx.commit();
        }
        catch(javax.validation.ConstraintViolationException e)
        {
        	System.out.println("invalid data");
        }
		
	}
	
	public Users getUserByEmail(String email) {
		
		 List<Users> userList = new ArrayList<Users>();
		 session = sessionFactory.openSession();
		 tx = session.getTransaction();
		 session.beginTransaction();	
		 String hql = "FROM Users u where u.email = :email";
		 Query query = session.createQuery(hql);
		 query.setParameter("email", email);
		 userList = query.list();
		 tx.commit();
		 if (userList.size() > 0)
		 {
	            return userList.get(0);
		 }
        else
        {
            return null;
        }
		
	}
	
	public Users getUserById(Integer id) {
		 session = sessionFactory.openSession();
		 tx = session.getTransaction();
		 session.beginTransaction();	
		 Users p = null;
		 try{
			 p = (Users) session.get(Users.class, id);
			 p.getId();//to throw the exception
		 }
		 catch(org.hibernate.ObjectNotFoundException e)
		 {
			 p = null;
			 System.out.println("we are returning null");
		 }
		 tx.commit();
		 return p;
		
	}
	
	
	
}