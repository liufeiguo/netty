package com.liu.aop.service.${param3}.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liu.aop.dao.${param3}.${param1}Mapper;
import com.liu.aop.model.ServiceResult;
import com.liu.aop.model.${param3}.${param1};
import com.liu.aop.service.${param3}.${param1}Service;

@Service("${param2}Service")
public class ${param1}ServiceImpl implements ${param1}Service{

	@Autowired
	private ${param1}Mapper ${param2}Dao;
	
	@Override
	public ServiceResult<Object> findRecords(${param1} record, int pagenow, int pagesize) {
		int limitStart = (pagenow-1)*pagesize;
		int limitEnd = pagesize;
		
		List<${param1}> list = ${param2}Dao.findByRecord(record, limitStart, limitEnd);
		int count = ${param2}Dao.findByRecordCount(record);
		
		ServiceResult<Object> result = new ServiceResult<Object>();
		result.setData(list);
		result.setTotal(count);
		
		return result;
	}

	@Override
	public ServiceResult<Object> addRecord(${param1} record) {
		
		${param2}Dao.insertSelective(record);
		
		ServiceResult<Object> result = new ServiceResult<Object>();
		result.setData(null);
		
		return result;
	}

	@Override
	public ServiceResult<Object> delRecord(int record) {
		${param2}Dao.deleteLogicById(1, record);//1表示逻辑删除
		
		ServiceResult<Object> result = new ServiceResult<Object>();
		result.setData(null);
		
		return result;
	}

	@Override
	public ServiceResult<Object> updateRecord(${param1} record) {
		${param2}Dao.updateByPrimaryKeySelective(record);
		
		ServiceResult<Object> result = new ServiceResult<Object>();
		result.setData(null);
		
		return result;
	}

}
