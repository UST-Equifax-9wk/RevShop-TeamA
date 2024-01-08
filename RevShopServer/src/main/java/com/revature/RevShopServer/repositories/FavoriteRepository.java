package com.revature.RevShopServer.repositories;

import com.revature.RevShopServer.entities.Favorite;
import com.revature.RevShopServer.entities.FavoriteKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteKey> {

}
