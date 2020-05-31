package com.semestrwork.demo.Controller;

import com.semestrwork.demo.Config.UserDetailsImpl;
import com.semestrwork.demo.Models.Favorite;
import com.semestrwork.demo.Models.User;
import com.semestrwork.demo.Service.Interface.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @GetMapping()
    public String getFavorites(Authentication authentication, Model model) {

        boolean isAnonim = true;

        if (authentication != null) {
            isAnonim = false;
        } else {
            return "redirect:/sign_in";
        }

        model.addAttribute("isAnonim", isAnonim);

        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

        model.addAttribute("favorites", user.getFavorites());
        return "favorites";
    }

    @PostMapping()
    public String removeFavorite(@RequestParam("idOfFavorite") String id) {
        favoritesService.removeFavorite(Long.parseLong(id));
        return "redirect:/favorites";
    }
}
