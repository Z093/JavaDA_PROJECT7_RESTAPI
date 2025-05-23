package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
	@GetMapping("/")
	public String home(Model model)
	{
		return "home";
	}

	@GetMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/bidList/list";
	}

	@GetMapping("/home")
	public String redirect(Model model) {
		return "redirect:/bidList/list";
	}
}
