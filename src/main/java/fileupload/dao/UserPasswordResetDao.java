package fileupload.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fileupload.model.UserPasswordReset;

@Repository
public class UserPasswordResetDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	 Session session = null;
	 Transaction tx = null;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	public void saveResetPasswordToken(UserPasswordReset userPasswordReset){
		 session = sessionFactory.openSession();
		 tx = session.getTransaction();
		 session.beginTransaction();	
		 session.saveOrUpdate(userPasswordReset);
		 tx.commit();
	}
}
