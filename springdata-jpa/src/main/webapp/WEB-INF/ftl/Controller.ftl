package com.liu.aop.controller.${param3};
import java.util.List;

import javax.annotation.Resource;
import com.liu.aop.model.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.liu.aop.model.ServiceResult;
import com.liu.aop.model.${param3}.${param1};
import com.liu.aop.service.${param3}.${param1}Service;
@SuppressWarnings("all")
@Controller
@RequestMapping("/${param2}")
public class ${param1}Controller {
	@Resource
	${param1}Service ${param2}Service;

	@RequestMapping(value="/page/{pagenow}/{pagesize}",method=RequestMethod.POST)
	@ResponseBody
	public ResponseData findRecords(${param1} record,@PathVariable("pagenow") int pagenow,@PathVariable("pagesize") int pagesize){
		
		
		ServiceResult<Object> result = ${param2}Service.findRecords(record, pagenow, pagesize);
		ResponseData responseData = ResponseData.ok();
		if(result.getData() !=null){
			responseData.putDataValue("result", result.getData());
		}
		if(result.getTotal() !=null){
			responseData.putDataValue("count", result.getTotal());
		}
		return responseData;
	}
	
	//保存的方法
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseData addRecord(${param1} record){
		ServiceResult result =${param2}Service.addRecord(record);
		
		ResponseData responseData = ResponseData.ok();
		if(result.getData() !=null){
			responseData.putDataValue("result", result.getData());
		}
		return responseData;
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/del",method=RequestMethod.GET)
	public ResponseData delRecord(@PathVariable("id") Integer record){
		ServiceResult<Object> result = ${param2}Service.delRecord(record);
		ResponseData responseData = ResponseData.ok();
		if(result.getData() !=null){
			responseData.putDataValue("result", result.getData());
		}
		return responseData;
	}
	
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseData updateRecord(${param1} record){
		ServiceResult<Object> result = ${param2}Service.updateRecord(record);
		ResponseData responseData = ResponseData.ok();
		if(result.getData() !=null){
			responseData.putDataValue("result", result.getData());
		}
		return responseData;
	}
	
}
