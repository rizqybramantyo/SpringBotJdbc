package main.controller.dao;

import java.util.List;

import model.Employees;

public interface EmployeesDao {
	public List<Employees> getAll();

	public Employees getOne(String id);

	public int insertData(Employees emp);

	public int deleteById(long id);

	public int update(Employees emp);
}
