package com.model;

public class Student implements java.io.Serializable {

	private Long id;
	private String name;
	private String addr;

	public Student() {
	}

	public Student(String name, String addr) {
		this.name = name;
		this.addr = addr;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
