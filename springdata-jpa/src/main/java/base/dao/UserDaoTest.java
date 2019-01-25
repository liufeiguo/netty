package base.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import base.bean.User;

/**
 * @author gcx
 * 1.Repository 是一个空接口，一个标记接口
 * 2.继承了Repository，会被IOC容器识别为Repository Bean
 * 纳入到IOC容器中，进而可以在该接口中定义满足一定规范的方法
 *3.实际上也可以通过@RepositoryDefinition 注解来替代  Repository接口
 *4.在 Repository 子接口中生命方法
 *查询方法以find read get 开头
 *涉及查询条件，条件属性以属性第一个大写祖母开头
 *5.支持级联属性，若使用级联属性，则属性之间用下化线查询
 *
 */
//@RepositoryDefinition(domainClass = User.class, idClass = Integer.class)
//Repository<User, Integer>
//CrudRepository<User, Integer>
//PagingAndSortingRepository
public interface UserDaoTest extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User>,MyUserDao{
      
	/**
	 * @param name
	 * @return
	 */
	User getByName(String name);
	
	
	/**
	 * @param name
	 * @param id
	 * @return
	 * @despriction 以name 开头 ， 比什么小
	 */
	List<User> getByNameStartingWithAndIdLessThan(String name,Integer id);
	
	/**
	 * @param name
	 * @param id
	 * @return
	 */
	List<User> getByNameEndingWithAndIdLessThan(String name,Integer id);
	
	
	/**
	 * @param list
	 * @param id
	 * @return
	 */
	List<User> getByNameInOrIdLessThanEqual(List<String> list,Integer id);


	/**
	 * @param asList
	 * @param i
	 * @return
	 */
	List<User> getByNameInAndIdLessThanEqual(List<String> asList, int i);
	
	
	/**
	 * @param id
	 * @return
	 */
	List<User> getByAddressIdGreaterThanEqual(Integer id);


	/**
	 * @param i
	 * @return
	 */
	List<User> getByAddress_IdGreaterThanEqual(int i);
	
	
	/**
	 * @return
	 * 
	 * 自定义JPQL 语句
	 */
	@Query("SELECT u FROM User u WHERE u.id= (SELECT MAX(u2.id) FROM User u2)")
	User getUserMaxId();
	/**
	 * 
	 * @return
	 * 使用占位符
	 */
	@Query("SELECT u FROM User u WHERE u.id= ?1 AND  u.name = ?2")
	List<User> testQueryAnnotationParams1(Integer id,String name);
	
	/**
	 * 
	 * @return
	 * 使用占位符
	 */
	@Query("SELECT u FROM User u WHERE u.id= :id AND  u.name = :name")
	List<User> testQueryAnnotationParams2(@Param("id")Integer id,@Param("name")String name);
	
	/**
	 * 
	 * @return
	 * 使用占位符
	 */
	@Query("SELECT u FROM User u WHERE u.id= :id AND  u.name LIKE %:name%")
	List<User> testQueryAnnotationLikeParams3(@Param("id")Integer id,@Param("name")String name);
	
	/**
	 * @return
	 * 
	 *设置 nativeQuery=true 使用原声 SQL
	 */
	@Query(value="SELECT COUNT(1) FROM USER",nativeQuery = true)
	long getTotalCount();
	
	/**
	 * 自定义JPQL完成update 和 delete 不支持INSERT 
	 * 
	 *在 @Query 编写必须使用@Modifying并且要加上事务
	 * @return
	 * 
	 * 
	 */
	  @Modifying
	  @Query(value="UPDATE User u SET u.name = :name WHERE id = :id") 
	  void updateUserName(@Param("id") Integer id,@Param("name")String name);
	 
}
