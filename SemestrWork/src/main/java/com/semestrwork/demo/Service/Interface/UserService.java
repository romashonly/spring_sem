package com.semestrwork.demo.Service.Interface;

import com.semestrwork.demo.DTO.ProfileDTO;
import com.semestrwork.demo.Models.User;

import java.security.NoSuchAlgorithmException;

public interface UserService extends ForAllService {

    void signUp(ProfileDTO profileDTO) throws NoSuchAlgorithmException;

    boolean editUserInfo(User user);
}
