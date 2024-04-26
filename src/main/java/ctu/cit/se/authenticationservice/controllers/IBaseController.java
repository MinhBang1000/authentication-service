package ctu.cit.se.authenticationservice.controllers;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBaseController<CreateDTO, UpdateDTO, RetrieveDTO, CommandDTO> {
    ResponseEntity<List<RetrieveDTO>> list();
    ResponseEntity<RetrieveDTO> retrieve(String id);
    ResponseEntity<CommandDTO> create(CreateDTO createDTO);
    ResponseEntity<CommandDTO> update(UpdateDTO updateDTO);
    ResponseEntity<Void> delete(String id);
}
