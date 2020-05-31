package com.semestrwork.demo.Repository;

import com.semestrwork.demo.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
