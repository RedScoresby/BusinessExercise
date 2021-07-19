package com.red.exercise.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="role")
public class Role
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length=20)
	private String name;
	
	@OneToMany(mappedBy = "role")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Employee> employeesWithThisRole;

	public Role()
	{
		
	}
	
	public Role(long id, String name, List<Employee> employeesWithThisRole)
	{
		super();
		this.id = id;
		this.name = name;
		this.employeesWithThisRole = employeesWithThisRole;
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
	
	public List<Employee> getEmployeesWithThisRole()
	{
		return employeesWithThisRole;
	}
	
	public void setEmployeesWithThisRole(List<Employee> employeesWithThisRole)
	{
		this.employeesWithThisRole = employeesWithThisRole;
	}
}
