package com.semestrwork.demo.Service.Implementation;

import com.semestrwork.demo.DTO.ProfileDTO;
import com.semestrwork.demo.Models.Role;
import com.semestrwork.demo.Models.User;
import com.semestrwork.demo.Repository.UsersRepository;
import com.semestrwork.demo.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class UserServiceImpl extends ServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void signUp(ProfileDTO profileDTO) {


//            Part p = request.getPart("photo");
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



        User user = User.builder()
                .login(profileDTO.getLogin())
                .password(passwordEncoder.encode(profileDTO.getPassword()))
                .phone_number(profileDTO.getPhone_number())
                .name(profileDTO.getName())
                .serName(profileDTO.getSerName())
                .date_birth(profileDTO.getDate_birth())
                .date_registration("now")
//                .avatar(avatar)
                .city(profileDTO.getCity())
                .role(Role.USER)
                .build();

        usersRepository.save(user);
    }

    @Override
    public boolean editUserInfo(User user) {

        usersRepository.save(user);
        return true;
    }
}
