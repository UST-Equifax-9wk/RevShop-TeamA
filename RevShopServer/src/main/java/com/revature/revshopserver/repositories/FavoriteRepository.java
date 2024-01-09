package com.revature.revshopserver.repositories;

import com.revature.revshopserver.entities.Favorite;
import com.revature.revshopserver.entities.FavoriteKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteKey> {

}
