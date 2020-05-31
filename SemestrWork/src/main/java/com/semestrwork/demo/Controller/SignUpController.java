package com.semestrwork.demo.Controller;

import com.semestrwork.demo.DTO.ProfileDTO;
import com.semestrwork.demo.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;

@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping("/sign_up")
    public String showFormSignUp(Authentication authentication, Model model, @RequestParam(value = "type", defaultValue = "false") String type) {

        boolean isAnonim = true;

        if (authentication != null) {
            isAnonim = false;
        }

        model.addAttribute("isAnonim", isAnonim);

        if (type.equals("ok")) {
            return "registrationOK";
        }

        return "registration";
    }

    @PostMapping("/sign_up")
    public String signUp(ProfileDTO profileDTO) throws NoSuchAlgorithmException {

        userService.signUp(profileDTO);

        return "redirect:/sign_up?type=ok";
    }
}
