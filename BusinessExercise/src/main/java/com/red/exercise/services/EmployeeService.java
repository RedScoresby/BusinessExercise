package com.red.exercise.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.red.exercise.DAO.EmployeeDAO;
import com.red.exercise.filter.EmployeeFilter;
import com.red.exercise.model.Employee;

@Service
public class EmployeeService
{
	@Autowired
	public EmployeeDAO employeeDAO;
	
	public boolean createNewEmployee(Employee newEmployee)
	{
		Optional<Employee> employee = employeeDAO.findByDNI(newEmployee.getDNI());
		
		if (employee.isEmpty())
			employeeDAO.save(newEmployee);
		
		return employee.isEmpty();
	}
	
	public boolean updateEmployee(Employee newDataOfEmployee)
	{
		Employee employee = employeeDAO.findById(newDataOfEmployee.getId()).orElse(null);
		Employee employeeByDNI = employeeDAO.findByDNI(newDataOfEmployee.getDNI()).orElse(null);
		
		//We check if the employee exists before modifying it, and if it doesn't have the same DNI as another one in the database
		boolean canUpdate = employee != null && (employeeByDNI == null || employee.getId() == employeeByDNI.getId());
		if (canUpdate)
		{
			employee = newDataOfEmployee;
			employeeDAO.save(employee);
		}
		
		return canUpdate;
	}
	
	public boolean deleteEmployee(long employeeId)
	{
		Optional<Employee> employee = employeeDAO.findById(employeeId);
		
		if (employee.isPresent())
			employeeDAO.delete(employee.get());
		
		return employee.isPresent();
	}
	
	public List<Employee> getAllEmployees()
	{
		return employeeDAO.findAll();
	}
	
	public List<Employee> getEmployeesFiltered(EmployeeFilter filter)
	{
		switch (filter.getFilterType())
		{
			case "name":
				return getEmployeesByName(filter.getFilterString());
			case "firstSurname":
				return getEmployeesByFirstSurname (filter.getFilterString());
			case "secondSurname":
				return getEmployeesBySecondSurName(filter.getFilterString());
			case "address":
				return getEmployeesByAddress(filter.getFilterString());
			case "role":
				return getEmployeesByRole(filter.getFilterString());
			case "department":
				return getEmployeesByDepartment(filter.getFilterString());
			default:
				break;
		}
		return new ArrayList<Employee>();
	}
	
	public Employee getEmployeeById(long employeeId)
	{
		return employeeDAO.findById(employeeId).orElse(null);
	}
	
	public List<Employee> getEmployeesByName (String name)
	{
		return employeeDAO.findByName(name);
	}

	public List<Employee> getEmployeesByFirstSurname (String firstSurname)
	{
		return employeeDAO.findByFirstSurname(firstSurname);
	}
	
	public List<Employee> getEmployeesBySecondSurName (String secondSurname)
	{
		return employeeDAO.findBySecondSurname(secondSurname);
	}

	public List<Employee> getEmployeesByAddress (String address)
	{
		return employeeDAO.findByAddress(address);
	}
	
	public Employee getEmployeeByDNI(String employeeDNI)
	{
		return employeeDAO.findByDNI(employeeDNI).orElse(null);
	}
	
	public List<Employee> getEmployeesByRole (String roleName)
	{
		return employeeDAO.findAllByRole(roleName);
	}
	
	public List<Employee> getEmployeesByDepartment (String departmentName)
	{
		return employeeDAO.findAllByDepartment(departmentName);
	}
	
}
