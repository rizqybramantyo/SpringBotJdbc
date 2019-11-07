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
import model.Departments;
import model.Employees;


@Controller
@RequestMapping("department")
public class DepartmentController {

	@Autowired
	DepartmentsDao dptDao;
	
	@RequestMapping("")
	public String getDepartment(Model model) {
		List<Departments> result = dptDao.getAll();
		model.addAttribute("dpt", result);
		return "departments/result";
	}
	
	@RequestMapping("all")
	public String getDepartmentData(Model model) {
		List<Departments> result = dptDao.getAll();
		model.addAttribute("dpt", result);
		return "departments/result";
	}
	
	@RequestMapping("/inputModel")
	public String inputModel(Model model) {
		model.addAttribute("departmentModel", new Departments());
		return "departments/form";
	}
	
	@PostMapping("/inputModel")
	public String submitFormModel(@ModelAttribute Departments dp) {
		System.out.println(dp.getDepartmentName()+" "+dp.getManagerId()+" "+dp.getLocationId());
		return "departments/formresult";
	}
	
	@RequestMapping("/inputAdd")
	public String inputAdd(Model model) {
		model.addAttribute("dpt", new Departments());
		return "departments/add";
	}

	@PostMapping("/inputAdd")
	public ModelAndView submitFormAdd(@ModelAttribute Departments dpt) {
		int n = dptDao.insertData(dpt);
		if(n>0) {
			return new ModelAndView("redirect:"+"");
		}
		return null;
	}
	
	@RequestMapping("delete/{id}")
	public ModelAndView delete(@PathVariable(name="id")long id) {
		int n = dptDao.deleteById(id);
		if(n>0) {
			return new ModelAndView("redirect:"+"/department/all");
		}
		return null;
	}
	
	@RequestMapping("edit/{id}")
	public String getOne(@PathVariable(name="id")String id, Model model) {
		Departments dpt = dptDao.getOne(id);
		System.out.println(dpt.getDepartmentId()+"."+dpt.getDepartmentName()+" "+dpt.getManagerId()+" "+dpt.getLocationId());
		model.addAttribute("dpt", dpt);
		return "departments/edit";
	}
	
	@PostMapping("/edit")
	public ModelAndView submitFormUpdate(@ModelAttribute Departments dpt) {
		int n = dptDao.update(dpt);
		if(n>0) {
			return new ModelAndView("redirect:"+"");
		}
		return null;
	}
	}
