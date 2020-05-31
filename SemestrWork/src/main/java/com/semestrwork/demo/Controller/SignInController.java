package com.semestrwork.demo.Controller;

import com.semestrwork.demo.Service.Interface.UserService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInController {

    @GetMapping("/sign_in")
    public String showSignInForm() {
        return "login";
    }

    @GetMapping("/wants_logout")
    public String wantLogout() {
        return "consentLogout";
    }

//    @PostMapping("/sign_in")
//    public String signIn(Authentication authentication) {
//        if (authentication != null) {
//            return "redirect:/catalog";
//        } else {
//            return "redirect:/login?isCorrect=false";
//        }
//    }
}
