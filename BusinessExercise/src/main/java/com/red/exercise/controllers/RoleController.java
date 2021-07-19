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

import com.red.exercise.model.Role;
import com.red.exercise.services.RoleService;
import com.red.exercise.utils.ModelAndViewCreator;

@Controller
public class RoleController
{
	private final String VIEW_SHOW_ROLE = "roleFolder/showRole";
	private final String VIEW_CREATE_ROLE = "roleFolder/createRole";
	private final String VIEW_MODIFY_ROLE = "roleFolder/modifyRole";
	private final String VIEW_LIST_OF_ROLES = "roleFolder/roleList";
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/role/{id}")
	public ModelAndView showRole (@PathVariable("id") long id) 
	{
		return ModelAndViewCreator.createModelAndView("role", roleService.getRole(id), VIEW_SHOW_ROLE);
	}
	
	@GetMapping("/allRoles")
	public ModelAndView allRoles ()
	{
		return ModelAndViewCreator.createModelAndView("roles", roleService.getAllRoles(), VIEW_LIST_OF_ROLES);
	}
	
	@GetMapping("/role/create")
	public ModelAndView createRole()
	{
		return ModelAndViewCreator.createModelAndView("role", new Role(), VIEW_CREATE_ROLE);
	}
	
	@PostMapping("/role/new")
	public ModelAndView addNewRole (@ModelAttribute("role") Role role)
	{
		//We could check if the user has admin privileges but since we only let admins access this operations, it isn't really needed.
		Role newRole = roleService.createRole(role);
		
		if (newRole != null)
			return ModelAndViewCreator.createModelAndView("role", roleService.getRole(newRole.getId()), VIEW_SHOW_ROLE);
		else
			return ModelAndViewCreator.createModelAndView("role", new Role(), VIEW_CREATE_ROLE)
					.addObject("errorRoleExists", true);
	}

//	@DeleteMapping("/role/delete/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(
			  value = "/role/delete/{id}", 
			  method = {RequestMethod.GET, RequestMethod.DELETE})
	public ModelAndView deleteRole (@PathVariable("id") long id)
	{
		//We could check if the user has admin privileges but since we only let admins access this operations, it isn't really needed.
		
		try
		{
			roleService.deleteRole(id);
			return allRoles();
		} 
		catch (Exception e)
		{
			return allRoles()
					.addObject("errorDeletingRole", true)
					.addObject("errorRoleName", roleService.getRole(id).getName());
		}
	}

	@GetMapping("/role/modify/{id}")
	public ModelAndView modifyRole(@PathVariable("id") long id)
	{
		return ModelAndViewCreator.createModelAndView("role", roleService.getRole(id), VIEW_MODIFY_ROLE);
	}
	
	@RequestMapping(
			  value = "/role/update", 
			  method = {RequestMethod.POST, RequestMethod.PUT})
	public ModelAndView updateRole (@ModelAttribute("role") Role role)
	{
		//We could check if the user has admin privileges but since we only let admins access this operations, it isn't really needed.
		if (roleService.updateRole(role))
			return ModelAndViewCreator.createModelAndView("role", roleService.getRole(role.getId()), VIEW_SHOW_ROLE);
		else
			return ModelAndViewCreator.createModelAndView("role", role, VIEW_MODIFY_ROLE)
					.addObject("errorModifyingRole", true);
	}
}
