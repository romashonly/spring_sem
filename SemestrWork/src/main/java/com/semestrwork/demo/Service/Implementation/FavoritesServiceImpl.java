package com.semestrwork.demo.Service.Implementation;

import com.semestrwork.demo.Models.Favorite;
import com.semestrwork.demo.Models.User;
import com.semestrwork.demo.Repository.FavoriteRepository;
import com.semestrwork.demo.Service.Interface.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesServiceImpl extends ServiceImpl implements FavoritesService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public boolean addFavorite(Favorite favorite) {

        favoriteRepository.save(favorite);
        return true;
    }

    @Override
    public boolean removeFavorite(Long idOfFavorite) {
        favoriteRepository.delete(favoriteRepository.findById(idOfFavorite).get());
        return true;
    }
}
