package com.mv.desafioMV.dto;

import com.mv.desafioMV.domain.Collaborator;

import java.util.Date;

public record FoodDto(
        String name,
        String collaboratorCPF,
        Date date,
        Boolean brought
) {
}
