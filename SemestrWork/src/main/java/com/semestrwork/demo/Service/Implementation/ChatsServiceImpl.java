package com.semestrwork.demo.Service.Implementation;

import com.semestrwork.demo.Models.Chat;
import com.semestrwork.demo.Models.Message;
import com.semestrwork.demo.Models.User;
import com.semestrwork.demo.Repository.ChatRepository;
import com.semestrwork.demo.Repository.MessageRepository;
import com.semestrwork.demo.Service.Interface.ChatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatsServiceImpl extends ServiceImpl implements ChatsService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Chat createChat(User user_first, User user_second) {

        List<User> users = new ArrayList<>();

        users.add(user_first);
        users.add(user_second);

        Chat chat = null;// chatRepository.findAllByUsers(users).get(0);

        if (chat == null) {
            chat = Chat.builder().users(users).date_creating("now").build();
            chatRepository.save(chat);
        }

        return chat;
    }

    @Override
    public Chat getChat(Long id_chat) {
        return chatRepository.findById(id_chat).get();
    }

    @Override
    public boolean addMessage(Message message) {
        messageRepository.save(message);
        return true;
    }
}
