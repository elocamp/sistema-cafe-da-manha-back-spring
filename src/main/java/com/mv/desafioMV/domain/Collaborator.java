package com.mv.desafioMV.domain;

import com.mv.desafioMV.dto.CollaboratorDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "collaborator")
@Table(name = "collaborator")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "cpf")
public class Collaborator {

    @Id
    private String cpf;

    private String name;

    public Collaborator(CollaboratorDto dto) {
        this.cpf = dto.cpf();
        this.name = dto.name();
    }
}
