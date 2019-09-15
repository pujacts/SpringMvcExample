package com.howtodoinjava.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.howtodoinjava.demo.model.EmployeeVO;
import com.howtodoinjava.demo.service.EmployeeManager;

import jdk.nashorn.internal.objects.annotations.Getter;

@Controller
//@RequestMapping("/employee-module/addNew")
@SessionAttributes("employee")
public class EmployeeController {
	@Autowired
	EmployeeManager manager;

	@RequestMapping(method = RequestMethod.GET, value = "/employees")
	public String getAllEMployees(Model model) {
		model.addAttribute("employees", manager.getAllEmployees());
		return "employeeListDisplay";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/showForm")
	public String addEmployeeForm(Model model) {
		EmployeeVO employee = new EmployeeVO();
		model.addAttribute("employee", employee);
		return "addEmployee";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/showForm")
	public String submitForm(@ModelAttribute("employee") EmployeeVO employee, BindingResult result,
			SessionStatus status) {
			boolean error=false;
		System.out.println(employee);
		if (employee.getFirstName().isEmpty()) {
			result.rejectValue("firstName", "error.firstName");
			error =true;
		}
		if(employee.getLastName().isEmpty())
		{
			result.rejectValue("lastName", "error.lastName");
			error =true;
			
		}
		if(employee.getEmail().isEmpty())
		{
			result.rejectValue("email", "error.email");
			error=true;
		}
		if(error)
		{
			return "addEmployee";
		}
		status.setComplete();
		// return "addSuccess";
		return "redirect:success";

	}

	@RequestMapping(method = RequestMethod.GET, value = "/success")
	public String success() {
		return "addSuccess";
	}
}