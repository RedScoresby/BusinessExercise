package com.red.exercise.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.red.exercise.model.Role;

public interface RoleDAO extends JpaRepository<Role, Long>
{
	@Override
	public List<Role> findAll ();

	public Optional<Role> findByName(String name);
	
	@Query("select r from Role r where UPPER(r.name) like '%ADMIN%'")
	public Role findAdmin();
}
