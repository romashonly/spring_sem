package com.semestrwork.demo.Controller;

import com.semestrwork.demo.Service.Interface.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CatalogController {

    @Autowired
    private CarService carService;

    @GetMapping("/")
    public String getMain(Model model) {
        return "redirect:/catalog";
    }

    @GetMapping("/catalog")
    public String getCatalog(Authentication authentication, Model model,
                             @RequestParam(value = "brandCar", defaultValue = "false") String brand,
                             @RequestParam(value = "modelCar", defaultValue = "false") String modelCar,
                             @RequestParam(value = "minCost",defaultValue = "false") String minCost,
                             @RequestParam(value = "maxCost", defaultValue = "false") String maxCost) {

        boolean isAnonim = true;

        if (authentication != null) {
            isAnonim = false;
        }

        model.addAttribute("isAnonim", isAnonim);

        if (!brand.equals("false")) {
            model.addAttribute("cars", carService.getFilterCars(brand, modelCar, minCost, maxCost));
        }
        else {
            model.addAttribute("cars", carService.getAllCars());
        }

       return "catalog";
    }
}
