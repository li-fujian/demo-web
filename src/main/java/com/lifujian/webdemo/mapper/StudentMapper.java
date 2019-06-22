package com.lifujian.webdemo.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifujian.webdemo.model.Student;

/**
 * @author itlfj
 * @time 2019/06/22 21:27
 * @description StudentMapper
 */
@Repository
public interface StudentMapper {
	
	Student getById(@Param(value = "id") long id);

}
