package com.model;

import java.util.Set;

public class Book {
	private Long id;
	private String bname;
	private Integer price;

	private Set<Author> authorSet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Set<Author> getAuthorSet() {
		return authorSet;
	}

	public void setAuthorSet(Set<Author> authorSet) {
		this.authorSet = authorSet;
	}
}
