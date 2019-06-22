package com.lifujian.webdemo.model;

import java.util.Date;

/**
 * @author itlfj
 * @time 2019/06/22 21:24
 * @description 学生
 */
public class Student {
	private long id;
	private String name;
	private int age;
	private Date createTime;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", createTime=" + createTime + "]";
	}
}
