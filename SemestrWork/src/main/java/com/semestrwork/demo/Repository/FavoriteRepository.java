package com.semestrwork.demo.Repository;

import com.semestrwork.demo.Models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
