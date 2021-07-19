package com.red.exercise.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length=30)
	private String name;

	@Column(length=30)
	private String firstSurname;

	@Column(length=30)
	private String secondSurname;

	@Column(length=9)
	private String DNI;

	@Column(length=150)
	private String address;
	
	@ManyToMany 
	@JoinTable(
			name = "employees_working_in_department",
			joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "department_id")
			)
	private Set<Department> departmentsWorkingIn;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	public Employee ()
	{
	}
	
	public Employee(long id, String name, String firstSurname, String secondSurname, String dNI, String address,
			Set<Department> departmentsWorkingIn, Role role)
	{
		super();
		this.id = id;
		this.name = name;
		this.firstSurname = firstSurname;
		this.secondSurname = secondSurname;
		DNI = dNI;
		this.address = address;
		this.departmentsWorkingIn = departmentsWorkingIn;
		this.role = role;
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
	
	public String getFirstSurname()
	{
		return firstSurname;
	}
	
	public void setFirstSurname(String firstSurname)
	{
		this.firstSurname = firstSurname;
	}
	
	public String getSecondSurname()
	{
		return secondSurname;
	}
	
	public void setSecondSurname(String secondSurname)
	{
		this.secondSurname = secondSurname;
	}
	
	public String getDNI()
	{
		return DNI;
	}
	
	public void setDNI(String dNI)
	{
		DNI = dNI;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public Set<Department> getDepartmentsWorkingIn()
	{
		return departmentsWorkingIn;
	}

	public void setDepartmentsWorkingIn(Set<Department> departmentsWorkingIn)
	{
		this.departmentsWorkingIn = departmentsWorkingIn;
	}

	public Role getRole()
	{
		return role;
	}
	
	public void setRole(Role role)
	{
		this.role = role;
	}
}
