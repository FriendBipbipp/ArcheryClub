package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping("/")
    public String dashboard() {
        return "adminDashboard"; // ชี้ไปที่ register.html (หรือ register.jsp / register.blade)
    }
	
	
}
