package com.mv.desafioMV.controller;

import com.mv.desafioMV.domain.Collaborator;
import com.mv.desafioMV.dto.CollaboratorDto;
import com.mv.desafioMV.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collaborators")
public class CollaboratorController {

    @Autowired(required = true)
    private CollaboratorService service;

    @PostMapping
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Collaborator> createCollaborator(@RequestBody CollaboratorDto collaboratorDto) throws Exception {
        Collaborator newCollaborator = service.createCollaborator(collaboratorDto);
        return new ResponseEntity<>(newCollaborator, HttpStatus.CREATED);
    }

    @GetMapping
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<List<Collaborator>> getAll() throws Exception {
        var collaborators = service.getAll();
        return new ResponseEntity<>(collaborators, HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Object> getCollaboratorByCPF(@PathVariable(value = "cpf") String cpf) throws Exception {
        var collaborator = service.getCollaboratorByCPF(cpf);
        return new ResponseEntity<>(collaborator, HttpStatus.OK);
    }

    @PutMapping("/{cpf}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<String> updateCollaboratorByCPF(@PathVariable(value = "cpf") String cpf,
                                                         @RequestBody CollaboratorDto collaboratorDto) throws Exception {
        service.updateCollaboratorByCPF(cpf, collaboratorDto);
        return ResponseEntity.status(HttpStatus.OK).body("Collaborator has been successfully updated");
    }

    @DeleteMapping("/{cpf}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<String> deleteCollaboratorByCPF(@PathVariable(value = "cpf") String cpf) throws Exception {
        service.deleteCollaboratorByCPF(cpf);
        return ResponseEntity.status(HttpStatus.OK).body("Collaborator has been successfully deleted.");
    }
}
