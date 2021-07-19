package com.red.exercise.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.red.exercise.model.Department;
import com.red.exercise.services.DepartmentService;
import com.red.exercise.utils.ModelAndViewCreator;

@Controller
public class DepartmentController
{
	private final String VIEW_SHOW_DEPARTMENT = "departmentFolder/showDepartment";
	private final String VIEW_CREATE_DEPARTMENT = "departmentFolder/createDepartment";
	private final String VIEW_MODIFY_DEPARTMENT = "departmentFolder/modifyDepartment";
	private final String VIEW_LIST_OF_DEPARTMENTS = "departmentFolder/departmentList";
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/department/{id}")
	public ModelAndView showDepartment (@PathVariable("id") long id) 
	{
		return ModelAndViewCreator.createModelAndView("department", departmentService.getDepartment(id), VIEW_SHOW_DEPARTMENT);
	}
	
	@GetMapping("/allDepartments")
	public ModelAndView allDepartments ()
	{
		return ModelAndViewCreator.createModelAndView("departments", departmentService.getAllDepartments(), VIEW_LIST_OF_DEPARTMENTS);
	}

	@GetMapping("/department/create")
	public ModelAndView createDepartment()
	{
		return ModelAndViewCreator.createModelAndView("department", new Department(), VIEW_CREATE_DEPARTMENT);
	}
	
	@PostMapping("/department/new")
	public ModelAndView addNewDepartment (@ModelAttribute("department") Department department)
	{
		Department newDepartment = departmentService.createDepartment(department);
		
		if (newDepartment != null)
			return ModelAndViewCreator.createModelAndView("department", departmentService.getDepartment(newDepartment.getId()), VIEW_SHOW_DEPARTMENT);
		else
			return ModelAndViewCreator.createModelAndView("department", department, VIEW_CREATE_DEPARTMENT)
					.addObject("errorDepartmentExists", true);
	}

//	@DeleteMapping("/department/delete/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(
			  value = "/department/delete/{id}", 
			  method = {RequestMethod.GET, RequestMethod.DELETE})
	public ModelAndView deleteDepartment (@PathVariable("id") long id)
	{
		try
		{
			departmentService.deleteDepartment(id);
			return allDepartments();
		}
		catch (Exception e)
		{
			return allDepartments()
					.addObject("errorDeletingDepartment", true)
					.addObject("errorDepartmentName", departmentService.getDepartment(id).getName());
		}
	}

	@GetMapping("/department/modify/{id}")
	public ModelAndView modifyDepartment(@PathVariable("id") long id)
	{
		return ModelAndViewCreator.createModelAndView("department", departmentService.getDepartment(id), VIEW_MODIFY_DEPARTMENT);
	}
	
	@RequestMapping(
			  value = "/department/update", 
			  method = {RequestMethod.POST, RequestMethod.PUT})
	public ModelAndView updateDepartment (@ModelAttribute("department") Department department)
	{
		if (departmentService.updateDepartment(department))
			return ModelAndViewCreator.createModelAndView("department", departmentService.getDepartment(department.getId()), VIEW_SHOW_DEPARTMENT);
		else
			return ModelAndViewCreator.createModelAndView("department", department, VIEW_MODIFY_DEPARTMENT)
					.addObject("errorModifyingDepartment", true);
	}

}
