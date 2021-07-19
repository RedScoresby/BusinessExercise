package com.red.exercise.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.red.exercise.DAO.DepartmentDAO;
import com.red.exercise.model.Department;

@Service
public class DepartmentService
{
	@Autowired
	private DepartmentDAO departmentDAO;

	public Department getDepartment (long id)
	{
		return departmentDAO.findById(id).orElse(null);
	}
	
	public Department createDepartment (Department department)
	{
		if (departmentDAO.findByName(department.getName()).isEmpty())
			return departmentDAO.save(department);
		else 
			return null;
	}
	
	public boolean updateDepartment (Department department)
	{
		Optional<Department> optionalDepartment = departmentDAO.findById(department.getId());

		//We check if the department exists before modifying it, and if it doesn't have the same name as another one in the database
		boolean canUpdate = optionalDepartment.isPresent() && departmentDAO.findByName(department.getName()).isEmpty();
		
		if (canUpdate)
			departmentDAO.save(department);
		
		return canUpdate;
	}
	
	public boolean deleteDepartment (long id) throws Exception
	{
		Optional<Department> optionalDepartment = departmentDAO.findById(id);

		if (optionalDepartment.isPresent())
			departmentDAO.delete(optionalDepartment.get());
		
		return optionalDepartment.isPresent();
	}
	
	public List<Department> getAllDepartments ()
	{
		return departmentDAO.findAll();
	}
	
	public Department getDepartmentByName (String name)
	{
		return departmentDAO.findByName(name).orElse(null);
	}
}
