//package com.example.userservice.controllers;
//
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/ui/auth")
//public class AuthUIController {
//
//    // Show the Login Page
//    @GetMapping("/login")
//    public String loginPage(Model model) {
//        return "auth/login"; // Resolves to src/main/resources/templates/auth/login.html
//    }
//
//    // Show the Signup Page
//    @GetMapping("/signup")
//    public String signupPage(Model model) {
//        return "auth/signup"; // Resolves to src/main/resources/templates/auth/signup.html
//    }
//
//    // Show the Dashboard (Only if logged in)
//    @GetMapping("/dashboard")
//    public String dashboardPage(HttpSession session) {
//        String token = (String) session.getAttribute("authToken");
//        if (token == null) {
//            return "redirect:/ui/auth/login"; // Redirect to login if no token is found
//        }
//        return "auth/dashboard"; // Resolves to src/main/resources/templates/auth/dashboard.html
//    }
//
//    // Logout Functionality (Invalidate session and redirect)
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate(); // Clear session
//        return "redirect:/ui/auth/login"; // Redirect back to login page
//    }
//}
