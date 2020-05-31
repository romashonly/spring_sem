package com.semestrwork.demo.Controller;

import com.semestrwork.demo.Config.UserDetailsImpl;
import com.semestrwork.demo.DTO.ProfileDTO;
import com.semestrwork.demo.Models.User;
import com.semestrwork.demo.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Part;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;

@Controller
@RequestMapping("/edit_profile")
public class ProfileEditController {

    private User user;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping()
    public String showFormForEditingProfile(Authentication authentication, Model model) {

        boolean isAnonim = true;

        if (authentication != null) {
            isAnonim = false;
        }

        model.addAttribute("isAnonim", isAnonim);

        user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

        model.addAttribute("user", user);

        return "editProfile";
    }

    @PostMapping()
    public String saveChangesOfProfile(ProfileDTO profileDTO) {

//        Part p = request.getPart("photo");
//        if (p.getSubmittedFileName() != null && !p.getSubmittedFileName().equals("")) {
//            String localdir = "uploads/avatars";
//            String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
//            File dir = new File(pathDir);
//            if (!dir.exists()) {
//                dir.mkdir();
//            }
//
//            String[] filename_data = p.getSubmittedFileName().split("\\.");
//            String filename = Math.random() + "." + filename_data[filename_data.length - 1];
//            String fullpath = pathDir + File.separator + filename;
//            p.write(fullpath);
//
//            String avatar = "/" + localdir + "/" + filename;
//            user.setAvatar(avatar);
//        }

        user.setName(profileDTO.getName());
        user.setSerName(profileDTO.getSerName());
        user.setDate_birth(profileDTO.getDate_birth());
        user.setCity(profileDTO.getCity());
        user.setPhone_number(profileDTO.getPhone_number());
        user.setLogin(profileDTO.getLogin());

        user.setPassword(passwordEncoder.encode(profileDTO.getPassword()));

        userService.editUserInfo(user);

        return "redirect:/profile";
    }
}
