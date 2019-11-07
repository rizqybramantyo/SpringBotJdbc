package util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.Employees;

public class EmployeeMapper implements RowMapper<Employees> {

	@Override
	public Employees mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employees emp = new Employees();
		emp.setEmployeeId(rs.getInt("employee_id"));
		emp.setFirstName(rs.getString("first_name"));
		emp.setLastName(rs.getString("last_name"));
		return emp;
	}
}
