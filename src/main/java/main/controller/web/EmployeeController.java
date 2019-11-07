package main.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import main.controller.dao.DepartmentsDao;
import main.controller.dao.EmployeesDao;
import model.Departments;
import model.Employees;

@Controller
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	EmployeesDao empDao;

	@RequestMapping("")
	public String getEmployee(Model model) {
		List<Employees> result = empDao.getAll();
		model.addAttribute("emp", result);
		return "employees/result";
	}
	
	@RequestMapping("all")
	public String getEmployeeData(Model model) {
		List<Employees> result = empDao.getAll();
		model.addAttribute("emp", result);
		return "employees/result";
	}

	@RequestMapping("/inputModel")
	public String inputModel(Model model) {
		model.addAttribute("employeeModel", new Employees());
		return "employees/form";
	}

	@PostMapping("/inputModel")
	public String submitFormModel(@ModelAttribute Employees pm) {
		System.out.println(pm.getFirstName() + " " + pm.getLastName());
		return "employees/formresult";
	}

	@RequestMapping("/inputAdd")
	public String inputAdd(Model model) {
		model.addAttribute("emp", new Employees());
		return "employees/add";
	}

	@PostMapping("/inputAdd")
	public ModelAndView submitFormAdd(@ModelAttribute Employees emp) {
		int n = empDao.insertData(emp);
		if (n > 0) {
			return new ModelAndView("redirect:" + "");
		}
		return null;
	}

	@RequestMapping("delete/{id}")
	public ModelAndView delete(@PathVariable(name = "id") long id) {
		int n = empDao.deleteById(id);
		if (n > 0) {
			return new ModelAndView("redirect:" + "/employee/all");
		}
		return null;
	}

	@RequestMapping("edit/{id}")
	public String getOne(@PathVariable(name = "id") String id, Model model) {
		Employees emp = empDao.getOne(id);
		System.out.println(emp.getEmployeeId() + "." + emp.getFirstName() + " " + emp.getLastName());
		model.addAttribute("emp", emp);
		return "employees/edit";
	}

	@PostMapping("/edit")
	public ModelAndView submitFormUpdate(@ModelAttribute Employees emp) {
		int n = empDao.update(emp);
		if (n > 0) {
			return new ModelAndView("redirect:" + "");
		}
		return null;
	}
}
