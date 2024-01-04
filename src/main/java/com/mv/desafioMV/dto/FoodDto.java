package com.mv.desafioMV.dto;

import com.mv.desafioMV.domain.Collaborator;

import java.time.LocalDate;
import java.util.Date;

public record FoodDto(
        String name,
        String collaboratorCPF,
        LocalDate date,
        Boolean brought
) {
}
