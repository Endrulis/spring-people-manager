package com.endrulis.manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "people")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Name should only contain letters and spaces")
    private String name;
    @Column(nullable = false)
    @Min(1)
    @Max(150)
    @Digits(integer = 3, fraction = 0, message = "Age should only contain digits")
    @Positive(message = "Age should be a positive number")
    private int age;
    @Column(nullable = false)
    @Pattern(regexp = "^https?://\\S*", message = "Image should be a valid URL starting with http:// or https:// and should not contain any whitespaces")
    private String image;
}
