package com.semestrwork.demo.Controller;

import com.semestrwork.demo.Config.UserDetailsImpl;
import com.semestrwork.demo.Models.Chat;
import com.semestrwork.demo.Models.User;
import com.semestrwork.demo.Service.Interface.ChatsService;
import com.semestrwork.demo.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private User userForPage;

    private User user;

    @Autowired
    private ChatsService chatsService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public String showProfile(Authentication authentication, Model model, @RequestParam(value = "id", defaultValue = "false") String id_otherUser) {

        boolean isAnonim = true;

        if (authentication != null) {
            isAnonim = false;
        }

        model.addAttribute("isAnonim", isAnonim);

        user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

        if (!id_otherUser.equals("false")) {
            if (user != null && id_otherUser.equals(Long.toString(user.getId()))) {
                userForPage = user;
                model.addAttribute("other_user", "false");
            }
            else {
                userForPage = userService.getUserFromListOfUsers(Long.parseLong(id_otherUser));
                model.addAttribute("other_user", "true");
            }
        }
        else {
            userForPage = user;
            model.addAttribute("other_user", "false");
        }

        model.addAttribute("user", userForPage);

        return "profile";
    }

    @PostMapping()
    public String createChat() {

//        Chat chat = chatsService.createChat(user, userForPage);

        return "redirect:/chats";//?id=" + chat.getId();
    }
}
