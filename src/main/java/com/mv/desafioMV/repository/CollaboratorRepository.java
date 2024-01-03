package com.mv.desafioMV.repository;

import com.mv.desafioMV.domain.Collaborator;
import com.mv.desafioMV.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CollaboratorRepository extends JpaRepository<Collaborator, String> {

    @Query(value = "SELECT * FROM collaborator c WHERE c.cpf = :cpf", nativeQuery = true)
    Optional<Collaborator> isCPFExistent(@Param(("cpf")) String collaboratorCPF);

    Optional<Collaborator> findByCpf(String cpf);
}
