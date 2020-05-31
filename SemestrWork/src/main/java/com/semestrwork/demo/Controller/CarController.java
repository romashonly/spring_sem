package com.semestrwork.demo.Controller;

import com.semestrwork.demo.Config.UserDetailsImpl;
import com.semestrwork.demo.Models.Car;
import com.semestrwork.demo.Models.Comment;
import com.semestrwork.demo.Models.Favorite;
import com.semestrwork.demo.Models.User;
import com.semestrwork.demo.Service.Interface.CarService;
import com.semestrwork.demo.Service.Interface.CommentsService;
import com.semestrwork.demo.Service.Interface.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/catalog/car")
public class CarController {

    private Car car;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CarService carService;

    @Autowired
    private FavoritesService favoritesService;

    @GetMapping()
    @ResponseBody
    public String getConcreteUserPage(Authentication authentication, @RequestParam("id") String carId, Model model) {

        boolean isAnonim = true;

        if (authentication != null) {
            isAnonim = false;
        }

        model.addAttribute("isAnonim", isAnonim);

        car = carService.getCarFromAllCars(Long.parseLong(carId));

        model.addAttribute("car", car);
        model.addAttribute("comments", car.getComments());

        return "detailCar";
    }

    @PostMapping()
    @ResponseBody
    public String addComment(Authentication authentication, @RequestParam("textOfComment") String textOfComment,
                             @RequestParam("addToFavorites") String addToFavorites) {

        if (authentication == null) {
            return "redirect:/sign_in";
        }

        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

        if (addToFavorites != null && addToFavorites.equals("true")) {

            Favorite favorite = Favorite.builder()
                    .car(carService.getCarFromAllCars(car.getId()))
                    .user(user)
                    .date_adding("now")
                    .build();

            favoritesService.addFavorite(favorite);

            return "redirect:/favorites";
        }
        else if (textOfComment != null) {

            Comment comment = Comment.builder()
                    .sender(user)
                    .car(car)
                    .text(textOfComment)
                    .date_sending("now")
                    .build();

            commentsService.addComment(comment);

            return "redirect:/catalog/car?id=" + car.getId();
        }
        else {
            return "redirect:/catalog/car?id=" + car.getId();
        }
    }
}
