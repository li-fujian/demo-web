package com.lifujian.webdemo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.lifujian.webdemo.mapper.StudentMapper;
import com.lifujian.webdemo.model.Student;
import com.lifujian.webdemo.service.StudentService;

/**
 * @author itlfj
 * @time 2019/06/22 21:36
 * @description 
 */
@Service
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentMapper studentMapper;
	
	@Override
	public Student getById(long id) {
		if (id <= 0) {
			return null;
		}

		return studentMapper.getById(id);
	}

}
