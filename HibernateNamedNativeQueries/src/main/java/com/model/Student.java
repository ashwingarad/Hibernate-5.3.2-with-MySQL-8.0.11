package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedNativeQueries({
		@NamedNativeQuery(name = "displayAll", query = "Select * from Student", resultClass = Student.class),
		@NamedNativeQuery(name = "insert", query = "insert into student (id, name, marks) values(?,?,?)", resultClass = Student.class),
		@NamedNativeQuery(name = "selectClause", query = "select name, marks from student"),
		@NamedNativeQuery(name = "whereClause", query = "select * from student where id=?"),
		@NamedNativeQuery(name = "max", query = "select max(marks) from student"),
		@NamedNativeQuery(name = "avg", query = "select avg(marks) from student"),
		@NamedNativeQuery(name = "distinct", query = "select count(distinct marks) from student"),
		@NamedNativeQuery(name = "orderClause", query = "SELECT name, marks FROM student ORDER BY marks ASC"),
		@NamedNativeQuery(name = "countStudent", query = "SELECT COUNT(marks) FROM student") })
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private Integer marks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

}
