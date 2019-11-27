package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.ProcessSH;
import com.example.demo.service.PowerSHService;

@Controller
@RequestMapping("/processes")
public class MainController {
	@Autowired
	private PowerSHService powerSHService;

	
	@GetMapping("/")
	public String defaulte(Model model) {
List<ProcessSH> list = powerSHService.getProcesses();
		
		model.addAttribute("Processes", list);
		
		return "processList";
	}
	
	@GetMapping("/all")
	public String getAllProcesses(Model model) {
		
		List<ProcessSH> list = powerSHService.getProcesses();
		
		model.addAttribute("Processes", list);
		
		return "processList";
	}
	
	@PostMapping("/stop/{id}")
	public ModelAndView stopProcess(@PathVariable("id") Long id, Model model) {
		
		powerSHService.stopProcess(id.toString());
		
		return new ModelAndView("redirect:/processes/all", "modelName", model);
	}
	
	
}
