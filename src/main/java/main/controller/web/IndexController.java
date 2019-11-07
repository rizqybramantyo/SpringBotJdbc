package main.controller.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Employees;

@Controller
@RequestMapping("menu")
public class IndexController {

	@RequestMapping("")
	public String getIndex() {
		return "index";
	}
	
}
