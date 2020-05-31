package com.semestrwork.demo.Service.Implementation;

import com.semestrwork.demo.Models.User;
import com.semestrwork.demo.Repository.UsersRepository;
import com.semestrwork.demo.Service.Interface.ForAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements ForAllService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User getUserFromListOfUsers(Long idOfUser) {
        return usersRepository.findById(idOfUser).get();
    }
}
