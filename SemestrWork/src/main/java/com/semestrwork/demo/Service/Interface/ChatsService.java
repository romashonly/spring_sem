package com.semestrwork.demo.Service.Interface;

import com.semestrwork.demo.Models.Chat;
import com.semestrwork.demo.Models.Message;
import com.semestrwork.demo.Models.User;

import java.util.List;

public interface ChatsService extends ForAllService {

    Chat createChat(User user_first, User user_second);

    Chat getChat(Long id_chat);

    boolean addMessage(Message message);
}
