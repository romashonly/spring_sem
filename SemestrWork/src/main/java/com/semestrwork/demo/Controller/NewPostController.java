package com.semestrwork.demo.Controller;

import com.semestrwork.demo.Config.UserDetailsImpl;
import com.semestrwork.demo.DTO.PostDTO;
import com.semestrwork.demo.Models.Car;
import com.semestrwork.demo.Models.User;
import com.semestrwork.demo.Service.Interface.CarService;
import com.semestrwork.demo.Service.Interface.ForAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/post_car")
public class NewPostController {

    @Autowired
    private CarService carService;

    @GetMapping()
    public String showNewPostForm(Authentication authentication, Model model) {

        boolean isAnonim = true;

        if (authentication != null) {
            isAnonim = false;
        }

        model.addAttribute("isAnonim", isAnonim);

        return "addNewPost";
    }

    @PostMapping()
    public String addNewPost(PostDTO postDTO, Authentication authentication) {

        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

//        Part p = request.getPart("image");
//        String localdir = "uploads/cars";
//        String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
//        File dir = new File(pathDir);
//        if (!dir.exists()) {
//            dir.mkdir();
//        }
//
//        String[] filename_data = p.getSubmittedFileName().split("\\.");
//        String filename = Math.random() + "." + filename_data[filename_data.length - 1];
//        String fullpath = pathDir + File.separator + filename;
//        p.write(fullpath);
//
//        String image = "/" + localdir + "/" + filename;

        if (
                postDTO.getBrand_car() != null
                        && postDTO.getModel_car() != null
                        && postDTO.getYear_issue() != null
                        && postDTO.getColor() != null
                        && postDTO.getMileage() != null
                        && postDTO.getEngine() != null
                        && postDTO.getBody_type() != null
                        && postDTO.getGearBox_type() != null
                        && postDTO.getDriveUnit_type() != null
                        && postDTO.getRudder_type() != null
                        && postDTO.getCondition_type() != null
                        && postDTO.getCost() != null
        ) {
            Car newCar = Car.builder()
                    .owner(user)
                    .brandCar(postDTO.getBrand_car())
                    .modelCar(postDTO.getModel_car())
                    .year_issue(postDTO.getYear_issue())
                    .date_posting("now")
                    .color(postDTO.getColor())
                    .mileage(postDTO.getMileage())
                    .engine(postDTO.getEngine())
                    .body_type(postDTO.getBody_type())
                    .gearBox_type(postDTO.getGearBox_type())
                    .driveUnit_type(postDTO.getDriveUnit_type())
                    .rudder_type( postDTO.getRudder_type())
                    .condition_type(postDTO.getCondition_type())
//                    .image(image)
                    .cost(Integer.parseInt(postDTO.getCost()))
                    .build();

            carService.addCar(newCar);
            return "redirect:/my_posts";
        }
        else {
            return  "redirect:/post_car?completed=false";
        }
    }
}
