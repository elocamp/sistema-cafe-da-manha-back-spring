package com.mv.desafioMV.service;

import com.mv.desafioMV.domain.Food;
import com.mv.desafioMV.dto.FoodDto;
import com.mv.desafioMV.repository.CollaboratorRepository;
import com.mv.desafioMV.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class FoodService {

    @Autowired
    private FoodRepository repository;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    public void saveFood(Food food) {
        repository.save(food);
    }

    public Food createFood(FoodDto foodDto) throws Exception {
        if (collaboratorRepository.isCPFExistent(foodDto.collaboratorCPF()).isEmpty()) {
            throw new Exception("Collaborator is not registered yet.");
        }

        if (foodDto.date().isBefore(LocalDate.now())) {
            throw new Exception("Invalid date. Please enter a date equal to or later than today.");
        }

        if (repository.existsByDate(foodDto.date()) && repository.existsByName(foodDto.name())) {
            throw new Exception("Food with the same name already exists at this date.");
        }

        Food newFood = new Food(foodDto);
        saveFood(newFood);
        return newFood;
    }

    public List<Food> getAll() throws Exception {
        if (!repository.findAll().isEmpty())
            return repository.findAll();
        else
            throw new Exception(String.valueOf(HttpStatus.NOT_FOUND) + " - No food option has been created yet.");
    }

    public Food getFoodById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Food option not found."));
    }

    public Food updateFoodById(Long id, FoodDto dto) throws Exception {
        Food food = repository.findById(id).orElseThrow(() -> new Exception("Food option not found."));

        if (collaboratorRepository.isCPFExistent(dto.collaboratorCPF()).isEmpty()) {
            throw new Exception("Collaborator is not registered yet.");
        }

        if (dto.date().isBefore(LocalDate.now())) {
            throw new Exception("Invalid date. Please enter a date equal to or later than today.");
        }

        if (repository.existsByDate(dto.date()) && repository.existsByName(dto.name())) {
            if (!(dto.name() == food.getName()) && (dto.date() == food.getDate()) && (dto.brought() != food.getBrought())) {
                throw new Exception("Food with the same name already exists at this date.");
            }
        }

        food.setName(dto.name());
        food.setCollaboratorCPF(dto.collaboratorCPF());
        food.setDate(dto.date());
        food.setBrought(dto.brought());

        saveFood(food);
        return food;
    }

    public List<Food> getAllFoodsByDate(LocalDate date) throws Exception {
        if (!repository.getAllFoodsByDate(date).isEmpty()) {
            return repository.getAllFoodsByDate(date);
        }
        else throw new Exception("There is no food registered for this date.");
    }

    public List<Food> getAllFoodsByCollaboratorCPF(String collaboratorCPF) throws Exception {
        if (!repository.getAllFoodsByCollaboratorCPF(collaboratorCPF).isEmpty()) {
            return repository.getAllFoodsByCollaboratorCPF(collaboratorCPF);
        }
        else throw new Exception("There is no food registered for this Collaborator");
    }

    public void deleteFoodById(Long id) throws Exception {
        Food food = repository.findById(id).orElseThrow(() -> new Exception("Food option not found."));
        repository.delete(food);
    }
}
