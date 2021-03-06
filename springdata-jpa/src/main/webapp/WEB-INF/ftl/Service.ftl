package com.liu.aop.service.${param3};



import com.liu.aop.model.ServiceResult;
import com.liu.aop.model.${param3}.${param1};


public interface ${param1}Service {

	ServiceResult<Object> findRecords(${param1} record, int pagenow, int pagesize);
	
	public ServiceResult<Object> addRecord(${param1} record);
	
	public ServiceResult<Object> delRecord(int record);
	
	public ServiceResult<Object> updateRecord(${param1} record);
}
