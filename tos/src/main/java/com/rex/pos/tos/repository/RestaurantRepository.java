package com.rex.pos.tos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rex.pos.models.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

}
