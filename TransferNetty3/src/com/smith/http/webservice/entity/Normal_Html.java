package com.smith.http.webservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "normal_html")
public class Normal_Html {
	private int id;
	private String name;
	private int weight;
	private String content;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Column
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Normal_Html(String name, int weight, String content) {
		this.name = name;
		this.weight = weight;
		this.content = content;
	}

	public Normal_Html() {
	}
}
