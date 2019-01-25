package base.dao;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import base.bean.User;
 
/**
 * Created by Administrator on 2017/9/4 0004.
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer>{
    
	User getByName(String name);
}
