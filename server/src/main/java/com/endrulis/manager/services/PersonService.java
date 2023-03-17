package com.endrulis.manager.services;

import com.endrulis.manager.dto.PersonDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    PersonDTO createPerson (@NotNull @Valid PersonDTO person );
    List<PersonDTO> getAllPeople();
    PersonDTO getPersonById(@NotNull Long id);
    PersonDTO updatePerson(@NotNull Long id, @NotNull @Valid PersonDTO person);
    boolean deletePersonById(@NotNull Long id);
    boolean deleteAllPeople();

}
