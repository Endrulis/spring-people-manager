package com.endrulis.manager.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PersonSeeder personSeeder;

    public DataInitializer( PersonSeeder personSeeder ) {
        this.personSeeder = personSeeder;
    }

    @Override
    public void run( String... args ) throws Exception {
        personSeeder.seed();
    }
}