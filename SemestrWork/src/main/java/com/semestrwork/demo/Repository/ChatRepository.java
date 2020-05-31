package com.semestrwork.demo.Repository;

import com.semestrwork.demo.Models.Chat;
import com.semestrwork.demo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {

//    List<Chat> findAllByUsers(List<User> users);

    Optional<Chat> findById(Long id);
}
