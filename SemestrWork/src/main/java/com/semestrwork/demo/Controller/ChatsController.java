package com.semestrwork.demo.Controller;

import com.semestrwork.demo.Config.UserDetailsImpl;
import com.semestrwork.demo.Config.UserDetailsServiceImpl;
import com.semestrwork.demo.Models.Chat;
import com.semestrwork.demo.Models.Message;
import com.semestrwork.demo.Models.User;
import com.semestrwork.demo.Service.Implementation.UserServiceImpl;
import com.semestrwork.demo.Service.Interface.ChatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chats")
public class ChatsController {

    private User user;

    @Autowired
    private ChatsService chatsService;

    @GetMapping()
    public String getChats(Authentication authentication, Model model, @RequestParam(value = "id", defaultValue = "false") String id_chat) {

        boolean isAnonim = true;

        if (authentication != null) {
            isAnonim = false;
        }

        model.addAttribute("isAnonim", isAnonim);

        user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

        model.addAttribute("user", user);

        if (!id_chat.equals("false")) {

            model.addAttribute("messages", chatsService.getChat(Long.parseLong(id_chat)).getMessages());
            model.addAttribute("chat", chatsService.getChat(Long.parseLong(id_chat)));

            return  "chatsBetweenUsers";
        }
        else {
            model.addAttribute("chats", user.getChats());
            return "chats";
        }
    }

    @PostMapping()
    public String sendMessage(@RequestParam("newText") String textOfNewMessage, @RequestParam("id_chat") String id_chat) {

        Chat chat = chatsService.getChat(Long.parseLong(id_chat));

        Message newMessage = Message.builder()
                .sender(user)
                .chat(chat)
                .text(textOfNewMessage)
                .date_sending("now")
                .build();

        chatsService.addMessage(newMessage);

        return "redirect:/chats?id=" + chat.getId();
    }
}
