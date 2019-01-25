package base.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import base.bean.User;

public class UserDaoTestImpl implements MyUserDao{
    
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void test() {
		 
		User user = entityManager.find(User.class, 11);
		System.out.println("-->"+ user);
		
	}

}
