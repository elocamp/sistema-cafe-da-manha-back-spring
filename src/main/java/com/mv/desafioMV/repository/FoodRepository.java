package com.mv.desafioMV.repository;

import com.mv.desafioMV.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    boolean existsByName(String foodName);

    boolean existsByDate(Date date);

    @Query(value = "SELECT * FROM food f WHERE f.date = :date", nativeQuery = true)
    List<Food> getAllFoodsByDate(@Param("date") Date date);

    @Query(value = "SELECT * FROM food f WHERE f.collaboratorCPF = :collaboratorCPF", nativeQuery = true)
    List<Food> getAllFoodsByCollaboratorCPF(@Param("collaboratorCPF") String collaboratorCPF);
}
