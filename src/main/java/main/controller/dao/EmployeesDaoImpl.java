package main.controller.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Employees;
import util.EmployeeMapper;

@Repository
public class EmployeesDaoImpl implements EmployeesDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Employees> getAll() {
		List<Employees> result = jdbcTemplate.query("SELECT employee_id, first_name, last_name FROM copy_empcopy",
				new EmployeeMapper());

		return result;
	}

	public Employees getOne(String id) {
		Employees result = jdbcTemplate.queryForObject(
				"SELECT employee_id, first_name, last_name FROM copy_empcopy WHERE employee_id =?", new Object[] { id },
				new BeanPropertyRowMapper<Employees>(Employees.class));
		return result;
	}

	@Override
	public int insertData(Employees emp) {
//		Employees result = jdbcTemplate.queryForObject("INSERT INTO employees values " + "(employees_seq.NEXTVAL,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?)", new EmployeeMapper());
		return jdbcTemplate.update("insert into copy_empcopy (employee_id,first_name,last_name)" + "values(?,?,?)",
				new Object[] { emp.getEmployeeId(), emp.getFirstName(), emp.getLastName() });
	}

	public int deleteById(long id) {
		return jdbcTemplate.update("delete from copy_empcopy where employee_id=?", new Object[] { id });
	}

	public int update(Employees emp) {
		return jdbcTemplate.update("update copy_empcopy SET first_name = ? ,last_name = ? WHERE employee_id = ?",
				new Object[] { emp.getFirstName(), emp.getLastName(), emp.getEmployeeId() });
	}
}
