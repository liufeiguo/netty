package base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import base.bean.User;
import base.dao.UserDao;
import base.dao.UserDaoTest;

@Controller
@RequestMapping("/test")
public class UserController {
	@Autowired
    UserDao userDao;
	@Autowired
	private UserDaoTest userDaoTest;
 
    @RequestMapping("/index")
    public ModelAndView test(){
        ModelAndView modelAndView =new ModelAndView("index");
        modelAndView.addObject(userDao.findOne(2015));
        return modelAndView;
    }
 
    @RequestMapping("/getdata")
    @ResponseBody
    public User getdata(){
        return userDao.findOne(2015);
    }
    /**
     * 这是入门程序，先睹为快，展示thymeleaf能做什么
     * 经过程序的执行，跟我们以前使用的JSP中EL表达式非常相像
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/myThymeleaf")
    public String myThymeleaf(HttpServletRequest request, Model model){
        model.addAttribute("model","初认识Thymeleaf模板引擎");
        return "thymeleaf";
    }
    /**
     * 入门-thymeleaf基础语法：基础表达式 之 变量表达式
     * 语法：${conten}
     * @param model
     * @return
     */
    @RequestMapping("/basicExpression")
    public String basicExpression(Model model){
    	User user = new User();
    	user.setName("刘国飞");
    	user.setPassword("123456");
        model.addAttribute("person",user);
        return "thymeleaf";
    }
    @RequestMapping("/export")
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
