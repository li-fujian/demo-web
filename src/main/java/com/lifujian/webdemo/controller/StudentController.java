package com.lifujian.webdemo.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lifujian.webdemo.model.Student;
import com.lifujian.webdemo.service.StudentService;
import com.lifujian.webdemo.utils.JsonResponse;

/**
 * @author itlfj
 * @time 2019/06/22 21:39
 * @description 
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {
	
    public static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    
    @Resource
    private StudentService studentService;

    // http://localhost:8080/student/get?id=1
	@GetMapping("get")
	public String get(@RequestParam(value = "id") long id) {
		logger.info("get student by id. id is [{}]", id);
		Student student = studentService.getById(id);
		return JsonResponse.toJsonResponse(student);
	}
}

