package com.semestrwork.demo.Controller;

import com.semestrwork.demo.Config.UserDetailsImpl;
import com.semestrwork.demo.Models.User;
import com.semestrwork.demo.Service.Interface.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/my_posts")
public class MyPostsController {

    @Autowired
    private CarService carService;

    @GetMapping()
    public String getMyPosts(Authentication authentication, Model model) {

        if (authentication == null) {
            return "redirect:/sign_in";
        }

        model.addAttribute("isAnonim", false);

        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

        model.addAttribute("myCars", user.getCars());

        return "myPosts";
    }

    @PostMapping()
    public String removePost(@RequestParam("idOfCar") String idOfCar) {
        carService.removeCar(Long.parseLong(idOfCar));
        return "redirect:/my_posts";
    }
}
