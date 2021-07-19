package com.red.exercise.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.red.exercise.filter.EmployeeFilter;
import com.red.exercise.model.Employee;
import com.red.exercise.security.AuthenticationRequest;
import com.red.exercise.services.DepartmentService;
import com.red.exercise.services.EmployeeService;
import com.red.exercise.services.RoleService;
import com.red.exercise.utils.ModelAndViewCreator;

@Controller
public class EmployeeController
{
	private final String VIEW_SHOW_EMPLOYEE = "employeeFolder/showEmployee";
	private final String VIEW_CREATE_EMPLOYEE = "employeeFolder/createEmployee";
	private final String VIEW_MODIFY_EMPLOYEE = "employeeFolder/modifyEmployee";
	private final String VIEW_LIST_OF_EMPLOYEES = "employeeFolder/employeeList";
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping(value= {"/", "/login"})
	public String login(Model model)
	{
		model.addAttribute("authenticationRequest", new AuthenticationRequest());
		//model.addAttribute("employee", new Employee());
		return "generalFolder/login";
	}
	
	@GetMapping("/employee/{id}")
	@ResponseBody
	public ModelAndView showEmployee(@PathVariable("id") long employeeId, HttpServletRequest request)
	{
		return ModelAndViewCreator.createModelAndView("employee", employeeService.getEmployeeById(employeeId), VIEW_SHOW_EMPLOYEE);
	}
	
//	@DeleteMapping("/employee/delete/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(
			  value = "/employee/delete/{id}", 
			  method = {RequestMethod.GET, RequestMethod.DELETE})
	public ModelAndView deleteEmployee(@PathVariable("id") long employeeId, Model model)
	{
		//We could check if the user has admin privileges but since we only let admins access this operations, it isn't really needed.

		employeeService.deleteEmployee(employeeId);
		return listOfAllEmployees(model);
	}

	@GetMapping("/employee/create")
	public ModelAndView createEmployee (Model model)
	{
		model.addAttribute("employee", new Employee());
		return ModelAndViewCreator.createModelAndView("roleList", roleService.getAllRoles(), VIEW_CREATE_EMPLOYEE)
				.addObject("departmentList", departmentService.getAllDepartments());
	}
	
	@PostMapping("/employee/new")
	//We remove the @RequestBody and replace it with @ModelAttribute when working with a web client
	public ModelAndView addNewEmployee (@ModelAttribute("employee") Employee employee)
	{
		//We could check if the user has admin privileges but since we only let admins access this operations, it isn't really needed.
		if (employeeService.createNewEmployee(employee))
			return ModelAndViewCreator.createModelAndView("employee", employeeService.getEmployeeByDNI(employee.getDNI()), VIEW_SHOW_EMPLOYEE);
		else
			//We return to the same page where we are creating the employee, but with and error telling the user what went wrong.
			return ModelAndViewCreator.createModelAndView("employee", employee, VIEW_CREATE_EMPLOYEE)
					.addObject("errorEmployeeExists", true)
					.addObject("roleList", roleService.getAllRoles())
					.addObject("departmentList", departmentService.getAllDepartments());
	}

	@GetMapping("/employee/modify/{id}")
	public ModelAndView modifyEmployee(@PathVariable("id") long employeeId, Model model)
	{
		model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
		return ModelAndViewCreator.createModelAndView("roleList", roleService.getAllRoles(), VIEW_MODIFY_EMPLOYEE)
				.addObject("departmentList", departmentService.getAllDepartments());
	}
	
	@RequestMapping(
			  value = "/employee/update", 
			  method = {RequestMethod.POST, RequestMethod.PUT})
	//We remove the @RequestBody and replace it with @ModelAttribute when working with a web client
	public ModelAndView updateEmployee (@ModelAttribute("employee") Employee employee)
	{
		//We could check if the user has admin privileges but since we only let admins access this operations, it isn't really needed.
		
		if (employeeService.updateEmployee(employee))
			return ModelAndViewCreator.createModelAndView("employee", employeeService.getEmployeeById(employee.getId()), VIEW_SHOW_EMPLOYEE);
		else
			//We return to the same page where we are modifying the employee, but with and error telling the user what went wrong.
			return ModelAndViewCreator.createModelAndView("employeeToModify", employee, VIEW_MODIFY_EMPLOYEE)
					.addObject("errorModifiyingEmployee", true)
					.addObject("roleList", roleService.getAllRoles())
					.addObject("departmentList", departmentService.getAllDepartments());	 
	}

	@GetMapping("/listOfEmployees")
	public ModelAndView listOfAllEmployees(Model model)
	{
		model.addAttribute("employeeFilter", new EmployeeFilter());
		return ModelAndViewCreator.createModelAndView("listOfAllEmployees", employeeService.getAllEmployees(), VIEW_LIST_OF_EMPLOYEES);
	}
	
	@GetMapping("/listOfEmployees/filter")
	public ModelAndView filterEmployees(@ModelAttribute("employeeFilter") EmployeeFilter filter)
	{
		return ModelAndViewCreator.createModelAndView("listOfAllEmployees", employeeService.getEmployeesFiltered(filter), VIEW_LIST_OF_EMPLOYEES);
	}
}
