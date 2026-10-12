package com.bharath.springdata.idgenerators.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import com.bharath.springdata.idgenerators.CustomRandomId;

@Entity
public class Employee {

	//@TableGenerator(name = "employee_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val",allocationSize=100)
	@Id
	@CustomRandomId
	//@GeneratedValue(strategy = GenerationType.TABLE,generator="employee_gen")
	private Long id;
	private String name;

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

}
