package com.red.exercise.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length=30)
	private String name;

	@ManyToMany(mappedBy = "departmentsWorkingIn", fetch = FetchType.EAGER)
	@Column(nullable = false)
	private Set<Employee> employeesWhoWorkHere;
	
	public Department()
	{
		
	}
	
	public Department(long id, String name, Set<Employee> employeesWhoWorkHere)
	{
		super();
		this.id = id;
		this.name = name;
		this.employeesWhoWorkHere = employeesWhoWorkHere;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Set<Employee> getEmployeesWhoWorkHere()
	{
		return employeesWhoWorkHere;
	}

	public void setEmployeesWhoWorkHere(Set<Employee> employeesWhoWorkHere)
	{
		this.employeesWhoWorkHere = employeesWhoWorkHere;
	}

	
}
