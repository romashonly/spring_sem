package com.semestrwork.demo.Service.Implementation;

import com.semestrwork.demo.Models.Car;
import com.semestrwork.demo.Models.Comment;
import com.semestrwork.demo.Repository.CommentRepository;
import com.semestrwork.demo.Service.Interface.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl extends ServiceImpl implements CommentsService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public boolean addComment(Comment comment) {
        commentRepository.save(comment);
        return true;
    }
}
