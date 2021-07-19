package com.red.exercise.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.red.exercise.DAO.RoleDAO;
import com.red.exercise.model.Role;

@Service
public class RoleService
{
	@Autowired
	private RoleDAO roleDAO;

	public Role getRole (long id)
	{
		return roleDAO.findById(id).orElse(null);
	}
	
	public Role createRole (Role role)
	{
		if (roleDAO.findByName(role.getName()).isEmpty())
			return roleDAO.save(role);
		else
			return null;
	}
	
	public boolean updateRole (Role role)
	{
		Optional<Role> optionalRole = roleDAO.findById(role.getId());

		//We check if the role exists before modifying it, and if it doesn't have the same name as another one in the database
		boolean canUpdate = optionalRole.isPresent() && roleDAO.findByName(role.getName()).isEmpty();
		
		if (canUpdate)
			roleDAO.save(role);
		
		return canUpdate;
	}
	
	public boolean deleteRole (long id) throws Exception
	{
		Optional<Role> optionalRole = roleDAO.findById(id);
		
		if (optionalRole.isPresent())
			roleDAO.delete(optionalRole.get());
		
		return optionalRole.isPresent();
	}
	
	public List<Role> getAllRoles ()
	{
		return roleDAO.findAll();
	}
	
	public Role getRoleByName (String name)
	{
		return roleDAO.findByName(name).orElse(null);
	}
}
