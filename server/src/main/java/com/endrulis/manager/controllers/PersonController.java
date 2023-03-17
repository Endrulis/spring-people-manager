package com.endrulis.manager.controllers;

import com.endrulis.manager.dto.PersonDTO;
import com.endrulis.manager.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class PersonController {
    private final PersonService personService;

    public PersonController( PersonService personService ) {
        this.personService = personService;
    }
    @PostMapping("/people")
    public ResponseEntity<PersonDTO> createPerson( @Valid @RequestBody PersonDTO personDTO ){
        PersonDTO savedPerson = personService.createPerson(personDTO);
        return ResponseEntity.ok(savedPerson);
    }

    @GetMapping("/people")
    public List<PersonDTO> getAllPeople() {
        return personService.getAllPeople();
    }
    @GetMapping("/people/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Long id){
        PersonDTO personDTO = null;
        personDTO = personService.getPersonById(id);
        return ResponseEntity.ok(personDTO);
    }
    @PutMapping("/people/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id,
                                                  @Valid @RequestBody PersonDTO personDTO){
        personDTO = personService.updatePerson(id, personDTO);
        return ResponseEntity.ok(personDTO);
    }
    @DeleteMapping("/people/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePerson(@PathVariable Long id){
        boolean deleted = false;
        deleted = personService.deletePersonById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/people")
    public ResponseEntity<Map<String, Boolean>> deleteAllPeople() {
        boolean deleted = personService.deleteAllPeople();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }
}
