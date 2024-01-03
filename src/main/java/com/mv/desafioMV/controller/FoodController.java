package com.mv.desafioMV.controller;

import com.mv.desafioMV.domain.Food;
import com.mv.desafioMV.dto.CollaboratorDto;
import com.mv.desafioMV.dto.FoodDto;
import com.mv.desafioMV.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService service;

    @PostMapping
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Food> createFood(@RequestBody FoodDto foodDto) throws Exception {
        Food newFood = service.createFood(foodDto);
        return new ResponseEntity<>(newFood, HttpStatus.CREATED);
    }

    @GetMapping
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<List<Food>> getAll() throws Exception {
        var foods = service.getAll();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Object> getFoodById(@PathVariable(value = "id") Long id) throws Exception {
        var food = service.getFoodById(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Object> getAllFoodsByDate(@PathVariable(value = "date") String stringDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(stringDate);
        var foods = service.getAllFoodsByDate(date);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/collaboratorCPF/{collaboratorCPF}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Object> getAllFoodsByCollaboratorCPF(@PathVariable(value = "collaboratorCPF") String collaboratorCPF) throws Exception {
        var foods = service.getAllFoodsByCollaboratorCPF(collaboratorCPF);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<String> updateFoodById(@PathVariable(value = "id") Long id,
                                                   @RequestBody FoodDto foodDto) throws Exception {
        service.updateFoodById(id, foodDto);
        return ResponseEntity.status(HttpStatus.OK).body("Food option has been successfully updated.");
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<String> deleteFoodById(@PathVariable(value = "id") Long id) throws Exception {
        service.deleteFoodById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Food option has been successfully deleted.");
    }
}
