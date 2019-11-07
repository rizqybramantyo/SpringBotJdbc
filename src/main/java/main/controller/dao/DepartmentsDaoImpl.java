package main.controller.dao;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Departments;

import util.DepartmentMapper;


@Repository
public class DepartmentsDaoImpl implements DepartmentsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Departments> getAll(){
		// TODO Auto-generated method stub
		List<Departments> result = jdbcTemplate.query("SELECT department_id, department_name, manager_id, location_id FROM copy_dptcopy", new DepartmentMapper());
		
		return result;
	}

	@Override
	public Departments getOne(String id) {
		// TODO Auto-generated method stub
		Departments result = jdbcTemplate.queryForObject("SELECT department_id, department_name, manager_id, location_id FROM copy_dptcopy WHERE department_id =?", new Object[] {id}, new BeanPropertyRowMapper<Departments>(Departments.class));
		return result;
	}

	@Override
	public int insertData(Departments dpt) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("insert into copy_dptcopy (department_id, department_name, manager_id, location_id)"+"values(?,?,?,?)",new Object[] {
				dpt.getDepartmentId(),dpt.getDepartmentName(),dpt.getManagerId(),dpt.getLocationId()
		});
	}

	@Override
	public int deleteById(long id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("delete from copy_dptcopy where department_id=?",new Object[] {id});
	}

	@Override
	public int update(Departments dpt) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("update copy_dptcopy SET department_name = ? ,manager_id = ? ,location_id = ? WHERE department_id = ?",new Object[] {
				dpt.getDepartmentName(),dpt.getManagerId(),dpt.getLocationId(),dpt.getDepartmentId()
		});
	}
	
}
