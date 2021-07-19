package com.red.exercise.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.red.exercise.model.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, Long>
{
	@Override
	public List<Employee> findAll ();

	public List<Employee> findByName (String name);
	
	public List<Employee> findByFirstSurname (String firstSurname);
	
	public List<Employee> findBySecondSurname (String secondSurname);

	public List<Employee> findByAddress (String address);
	
	public Optional<Employee> findByDNI (String DNI);
	
	@Query("select e from Employee e where e.role.name = ?1")
	public List<Employee> findAllByRole (String roleName);
	
	@Query("select e from Employee e join fetch e.departmentsWorkingIn w where w.name = ?1")
	public List<Employee> findAllByDepartment (String departmentName);
}
