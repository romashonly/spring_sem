package com.semestrwork.demo.Service.Interface;

import com.semestrwork.demo.Models.Car;
import com.semestrwork.demo.Models.Comment;

import java.util.List;

public interface CommentsService extends ForAllService {

    boolean addComment(Comment comment);
}
