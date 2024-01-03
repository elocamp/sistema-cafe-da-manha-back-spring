package com.mv.desafioMV.service;

import com.mv.desafioMV.domain.Collaborator;
import com.mv.desafioMV.dto.CollaboratorDto;
import com.mv.desafioMV.repository.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollaboratorService {

    @Autowired(required = true)
    private CollaboratorRepository repository;

    public void saveCollaborator(Collaborator collaborator) {
        repository.save(collaborator);
    }

    public Collaborator createCollaborator(CollaboratorDto collaboratorDto) throws Exception {
        if (collaboratorDto.cpf().length() != 11) {
            throw new Exception("Invalid CPF for Collaborator. CPF must have contain exactly 11 characters.");
        }

        if (!repository.findById(collaboratorDto.cpf()).isEmpty()) {
            throw new Exception("Collaborator already registered. Try another one.");
        }

        Collaborator newCollaborator = new Collaborator(collaboratorDto);
        saveCollaborator(newCollaborator);
        return newCollaborator;
    }

    public List<Collaborator> getAll() throws Exception {
        if (!repository.findAll().isEmpty())
            return repository.findAll();
        else
            throw new Exception(String.valueOf(HttpStatus.NOT_FOUND) + " - No collaborator has been created yet.");
    }

    public Collaborator getCollaboratorByCPF(String cpf) throws Exception {
        return repository.findById(cpf).orElseThrow(() -> new Exception("Collaborator not found."));
    }

    public Collaborator updateCollaboratorByCPF(String cpf, CollaboratorDto dto) throws Exception {
        Collaborator collaborator = repository.findById(cpf).orElseThrow(() -> new Exception("Collaborator not found."));

        collaborator.setCpf(dto.cpf());
        collaborator.setName(dto.name());

        saveCollaborator(collaborator);
        return collaborator;
    }

    public void deleteCollaboratorByCPF(String cpf) throws Exception {
        Collaborator collaborator = repository.findById(cpf).orElseThrow(() -> new Exception("Collaborator not found."));
        repository.delete(collaborator);
    }
}
