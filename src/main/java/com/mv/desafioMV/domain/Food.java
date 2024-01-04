package com.mv.desafioMV.domain;

import com.mv.desafioMV.dto.FoodDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "food")
@Table(name = "food")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String collaboratorCPF;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    private Boolean brought;

    public Food(FoodDto dto) {
        this.name = dto.name();
        this.collaboratorCPF = dto.collaboratorCPF();
        this.date = dto.date();
        this.brought = dto.brought();
    }
}
