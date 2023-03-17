package com.endrulis.manager.seed;

import com.endrulis.manager.entities.PersonEntity;
import com.endrulis.manager.repositories.PersonRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PersonSeeder{
    private final PersonRepository personRepository;

    public PersonSeeder( PersonRepository personRepository ) {
        this.personRepository = personRepository;
    }

    public void seed() {
        List<PersonEntity> personEntities = Stream.of(
                PersonEntity.builder().name("James James").age(29).image("https://i.ibb.co/jVKkzvr/portfolio1.png").build(),
                PersonEntity.builder().name("John John").age(32).image("https://i.ibb.co/kSTZDRv/portfolio2.png").build(),
                PersonEntity.builder().name("Jane Jane").age(36).image("https://i.ibb.co/r49DGss/portfolio3.png").build(),
                PersonEntity.builder().name("David David").age(34).image("https://i.ibb.co/tb5n1wJ/portfolio4.png").build(),
                PersonEntity.builder().name("Splint Splint").age(29).image("https://i.ibb.co/52PM9Mh/portfolio5.png").build()
        ).collect(Collectors.toList());
        personRepository.saveAll(personEntities);
    }
}
