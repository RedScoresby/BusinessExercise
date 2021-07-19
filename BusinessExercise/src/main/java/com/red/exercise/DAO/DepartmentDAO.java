package com.red.exercise.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.red.exercise.model.Department;


public interface DepartmentDAO extends JpaRepository<Department, Long>
{
	@Override
	public List<Department> findAll ();

	public Optional<Department> findByName(String name);
}
