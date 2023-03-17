package com.endrulis.manager.services;

import com.endrulis.manager.dto.PersonDTO;
import com.endrulis.manager.entities.PersonEntity;
import com.endrulis.manager.repositories.PersonRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository;

    public PersonServiceImpl( PersonRepository personRepository ) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonDTO createPerson( @NotNull @Valid PersonDTO personDTO ) {
        PersonEntity personEntity = new PersonEntity();
        BeanUtils.copyProperties(personDTO, personEntity);
        personRepository.save(personEntity);
        return personDTO;
    }

    @Override
    public List<PersonDTO> getAllPeople() {
        List<PersonEntity> personEntities = personRepository.findAll();
        List<PersonDTO> personDTOList = personEntities.stream()
                .map(person -> new PersonDTO(
                        person.getId(),
                        person.getName(),
                        person.getAge(),
                        person.getImage()
                )).collect(Collectors.toList());
        return personDTOList;
    }

    @Override
    public PersonDTO getPersonById( Long id ) {
        PersonEntity personEntity
                = personRepository.findById(id).get();
        PersonDTO personDTO = new PersonDTO();
        BeanUtils.copyProperties(personEntity, personDTO);
        return personDTO;
    }

    @Override
    public PersonDTO updatePerson( @NotNull Long id, @NotNull @Valid PersonDTO personDTO ) {
        PersonEntity personEntity =
                personRepository.findById(id).get();
        personEntity.setName(personDTO.getName());
        personEntity.setAge(personDTO.getAge());
        personEntity.setImage(personDTO.getImage());
        personRepository.save(personEntity);
        return personDTO;
    }

    @Override
    public boolean deletePersonById( Long id ) {
        PersonEntity person = personRepository.findById(id).get();
        personRepository.delete(person);
        return true;
    }

    @Override
    public boolean deleteAllPeople() {
        personRepository.deleteAll();
        return true;
    }
}
