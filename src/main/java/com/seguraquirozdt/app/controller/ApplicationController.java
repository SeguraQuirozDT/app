package com.seguraquirozdt.app.controller;



import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.seguraquirozdt.app.entity.User;
import com.seguraquirozdt.app.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ApplicationController {

	
	@Autowired
	UserRepository userRepository;
	
		@GetMapping("/")
		public String rootPage() {
			
			return "index";		
		}

		@GetMapping("/login")
		public String loginPage() {
			
			return "login";		
		}
		
		@GetMapping("/home")
		public String homePage(Model model, HttpServletRequest request) {
			if(request.getParameter("status") != null) {
				model.addAttribute("listUsers", userRepository.findUserByStatus(request.getParameter("status").charAt(0)));
			}
			else {
				model.addAttribute("listUsers", userRepository.findAll());
			}
			return "home";		
		}

		
		@GetMapping("/usuario/new")
		public String usuarioNew(Model model, User user) {
			
			
			return "user_new";		
		}
		
		@GetMapping("/usuario/update/{id}")
		public String usuarioUpdate(Model model, @PathVariable Long id) {
			model.addAttribute("user", userRepository.findUserById(id));
			
			return "user_update";		
		}
		
		@GetMapping("/usuario/desactivar/{id}")
		public String usuarioDesactivar(Model model, @PathVariable Long id) {
			User objUser = userRepository.findUserById(id);
			objUser.setStatus('B');
			userRepository.save(objUser);
			
			return "redirect:/home";		
		}
		
		@GetMapping("/usuario/activar/{id}")
		public String usuarioActivar(Model model, @PathVariable Long id) {
			User objUser = userRepository.findUserById(id);
			objUser.setStatus('A');
			userRepository.save(objUser);
			
			return "redirect:/home";		
		}

		
		@PostMapping("/usuario/save")
		public String saveUsers(@ModelAttribute("user") User user) {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
			
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			
			userRepository.save(user);
			
			return "redirect:/home";
		}
		
		

		@PostMapping("/usuario/updateSave")
		public String updateUserSave(@ModelAttribute("user") User user) {
			Date date = new Date(System.currentTimeMillis());
			
			user.setFechamodificacion(date);
			userRepository.save(user);
			
			return "redirect:/home";
		}
		
		@GetMapping("/logout")  
		public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
			if (auth != null){      
			new SecurityContextLogoutHandler().logout(request, response, auth);  
			}  
			return "redirect:/";  
		} 
		

}
