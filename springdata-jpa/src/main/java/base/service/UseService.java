package base.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.bean.User;
import base.dao.UserDaoTest;

@Service
public class UseService {

	@Resource
	private UserDaoTest userDaoTest;
	
	@Transactional
	public void saveUser(List<User> list) {
		userDaoTest.save(list);
	}
	
	@Transactional
	public void updateUserName(Integer id,String name) {
		
		userDaoTest.updateUserName(id, name);
	}
}
