package main.controller.dao;

import java.util.List;

import model.Departments;

public interface DepartmentsDao {
	public List<Departments> getAll();
	public Departments getOne(String id);
	public int insertData(Departments dpt);
	public int deleteById(long id);
	public int update(Departments dpt);
}
