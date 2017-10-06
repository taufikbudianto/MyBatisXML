package com.taufik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.taufik.model.Employee;
import com.taufik.service.EmployeeService;

@Controller
public class MyBatisController {
	
	@Autowired
	EmployeeService empserv;
	
	@RequestMapping(value="/home")
	private ModelAndView homeScreen() {
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Boolean> saveData(@RequestBody Employee emplo){
		try {
			boolean result = empserv.save(emplo);
			return new ResponseEntity<Boolean>(result,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployee(){
		try {
			List<Employee> list = empserv.getAllCategori();
			return new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<Employee>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getDataById", method = RequestMethod.GET)
	public String getData() {
		try {
			Employee emp = empserv.getSingleCategori(1);
			System.out.println(emp.getName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "test= ";
	}
	
	@RequestMapping(value = "/deleteById", method = RequestMethod.GET)
	public ResponseEntity<Boolean> deleteById(){
		try {
			boolean res = empserv.delete(2);
			return new ResponseEntity<Boolean>(res,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
					
		}
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseEntity<Boolean> update(@RequestBody Employee emp){
		try {
			boolean result = empserv.update(emp);
			return new ResponseEntity<Boolean>(result,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
