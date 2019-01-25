package com.liu.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;

import base.bean.User;
import base.controller.ExportExcel;
import base.dao.UserDao;
import base.dao.UserDaoTest;
import base.service.UseService;

//@RunWith(value=SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations= {"classpath:spring.xml"})
public class SpringDataTest {

	// @Autowired
	private ApplicationContext ctx;
	// @Autowired
	private UserDaoTest userDaoTest;
	// @Autowired
	private UseService useService;

	{
		ctx = new ClassPathXmlApplicationContext("spring.xml");

		userDaoTest = ctx.getBean(UserDaoTest.class);

		useService = ctx.getBean(UseService.class);
	}

	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

	@Test
	public void testJpa() {

	}

	@Test
	public void testHello() {
		UserDao userDao = ctx.getBean(UserDao.class);
		User user = userDao.getByName("zs");
		System.out.println(user);
	}

	@Test
	public void testHello1() {
		// UserDaoTest userDao = ctx.getBean(UserDaoTest.class);
		System.out.println(userDaoTest.getClass().getName());

		User user = userDaoTest.getByName("zs");
		System.out.println(user);
	}

	@Test
	public void testKeyWords() {

		// List<User> list = userDaoTest.getByNameStartingWithAndIdLessThan("z", 3);

		// List<User> list = userDaoTest.getByNameEndingWithAndIdLessThan("s", 3);

		List<User> list = userDaoTest.getByNameInAndIdLessThanEqual(Arrays.asList("zs", "lisi"), 2);

		System.out.println(list);
	}

	@Test
	public void testKeyWords2() {

		// List<User> list = userDaoTest.getByNameStartingWithAndIdLessThan("z", 3);

		// List<User> list = userDaoTest.getByNameEndingWithAndIdLessThan("s", 3);

		List<User> list = userDaoTest.getByAddress_IdGreaterThanEqual(1);

		System.out.println(list);
	}

	@Test
	public void testQueryAnnotation() {

		// List<User> list = userDaoTest.getByNameStartingWithAndIdLessThan("z", 3);

		// List<User> list = userDaoTest.getByNameEndingWithAndIdLessThan("s", 3);

		User userMaxId = userDaoTest.getUserMaxId();

		System.out.println(userMaxId);
	}

	@Test
	public void testQueryAnnotationParams1() {

		// List<User> list = userDaoTest.getByNameStartingWithAndIdLessThan("z", 3);

		// List<User> list = userDaoTest.getByNameEndingWithAndIdLessThan("s", 3);

		List<User> list = userDaoTest.testQueryAnnotationParams1(1, "zs");

		System.out.println(list);
	}

	@Test
	public void testQueryAnnotationParams2() {

		// List<User> list = userDaoTest.getByNameStartingWithAndIdLessThan("z", 3);

		// List<User> list = userDaoTest.getByNameEndingWithAndIdLessThan("s", 3);

		List<User> list = userDaoTest.testQueryAnnotationParams2(1, "zs");

		System.out.println(list);
	}

	@Test
	public void testQueryAnnotationLikeParams3() {

		// List<User> list = userDaoTest.getByNameStartingWithAndIdLessThan("z", 3);

		// List<User> list = userDaoTest.getByNameEndingWithAndIdLessThan("s", 3);

		List<User> list = userDaoTest.testQueryAnnotationLikeParams3(1, "z");

		System.out.println(list);
	}

	@Test
	public void getTotalCount() {

		// List<User> list = userDaoTest.getByNameStartingWithAndIdLessThan("z", 3);

		// List<User> list = userDaoTest.getByNameEndingWithAndIdLessThan("s", 3);

		long totalCount = userDaoTest.getTotalCount();

		System.out.println(totalCount);
	}

	@Test
	public void testMiodifying() {

		// List<User> list = userDaoTest.getByNameStartingWithAndIdLessThan("z", 3);

		// List<User> list = userDaoTest.getByNameEndingWithAndIdLessThan("s", 3);

		// userDaoTest.updateUserName(1, "zzss" );
		useService.updateUserName(1, "zsssss");

	}

	@Test
	public void testCurdReposiory() {

		List<User> list = new ArrayList<>();

		for (int i = 'a'; i < 'z'; i++) {
			User user = new User();
			user.setName("zs" + i);
			user.setAddressId(i);
			list.add(user);
		}
		useService.saveUser(list);

	}

	@Test
	public void testPagingAndSortingRepository() {
		// page 从零开始
		// 分页
		int page = 3;
		int size = 5;

		// 排序
		Order order1 = new Order(Direction.DESC, "id");
		Order order2 = new Order(Direction.ASC, "name");
		Sort sort = new Sort(order1, order2);

		PageRequest pageable = new PageRequest(page, size, sort);

		Page<User> list = userDaoTest.findAll(pageable);

		System.out.println(list.getTotalElements() + "---" + list.getNumber() + "-----" + list.getTotalPages());
		System.out.println(list.getContent());
		System.out.println(list.getNumberOfElements());

	}

	/**
	 * 带查询条件的分页
	 */
	@Test
	public void testJpaSpecificationExecutor() {

		// 分页
		int page = 3 - 1;
		int size = 5;

		// 排序
		Order order1 = new Order(Direction.DESC, "id");
		Order order2 = new Order(Direction.ASC, "name");
		Sort sort = new Sort(order1, order2);

		PageRequest pageable = new PageRequest(page, size, sort);

		Specification<User> specification = new Specification<User>() {
			/**
			 * @param root  代表查询的实体
			 * @param query 添加查询条件
			 * @param cb    CriteriaBuilder 对象 用于创建Criteria 相关对象从中获取Predicate对象
			 * @return Predicate 查询条件
			 */
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				Path<Integer> path = root.get("id");
				Predicate predicate = cb.gt(path, 5);
				return predicate;
			}

		};

		Page<User> list = userDaoTest.findAll(specification, pageable);

		System.out.println(list.getTotalElements() + "---" + list.getNumber() + "-----" + list.getTotalPages());
		System.out.println(list.getContent());
		System.out.println(list.getNumberOfElements());

	}

	@Test
	public void testCustomRepositotyMethod() {

		userDaoTest.test();
	}

	@Test
	public void outPartitionExcel(User user, HttpServletResponse response) throws Exception {
		List<User> list = userDaoTest.findAll();

		String title = "管理分区";
		String[] rowsName = new String[] { "序号", "ID" ,"姓名" ,"密码"};
		List<Object[]> dataList = new ArrayList<>();
		Object[] objs = null;
		for (int i = 0; i < list.size(); i++) {
			User main = list.get(i);
			objs = new Object[rowsName.length];
			objs[0] = i;
			objs[1] = main.getId();
			objs[2] = main.getName();
			objs[3] = main.getPassword();
			/*
			 * objs[1] = main.getSortingCode(); objs[2] = main.getZoneCode(); objs[3] =
			 * main.getProvince(); objs[4] = main.getCity(); objs[5] = main.getCounty();
			 * objs[6] = main.getKeyword(); objs[7] = main.getStartNumber(); objs[8] =
			 * main.getTerminateNumber(); objs[9] = main.getsDNumber(); objs[10] =
			 * main.getEmp().getEmpName(); objs[11] = main.getSyUnits().getName();
			 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String
			 * date = df.format(main.getOperationTime()); objs[12] = date;
			 */
			dataList.add(objs);
		}

		 ExportExcel ex = new ExportExcel(title, rowsName, dataList);
		 ex.export(response);
	}

}
