package util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.Departments;

public class DepartmentMapper implements RowMapper<Departments> {

	@Override
	public Departments mapRow(ResultSet rs, int rowNum) throws SQLException {
		Departments dpt = new Departments();
		dpt.setDepartmentId(rs.getInt("department_id"));
		dpt.setDepartmentName(rs.getString("department_name"));
		dpt.setManagerId(rs.getString("manager_id"));
		dpt.setLocationId(rs.getString("location_id"));
		return dpt;
	}
}
