package com.semestrwork.demo.Service.Interface;

import com.semestrwork.demo.Models.Favorite;
import com.semestrwork.demo.Models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface FavoritesService extends ForAllService {

    boolean addFavorite(Favorite favorite);

    boolean removeFavorite(Long idOfFavorite);
}
