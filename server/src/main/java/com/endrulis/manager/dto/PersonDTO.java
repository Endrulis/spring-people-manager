package com.endrulis.manager.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PersonDTO {
    private Long id;
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Name should only contain letters and spaces")
    private String name;
    @Min(1)
    @Max(150)
    @Digits(integer = 3, fraction = 0, message = "Age should only contain digits")
    @Positive(message = "Age should be a positive number")
    private int age;
    @Pattern(regexp = "^https?://\\S*", message = "Image should be a valid URL starting with http:// or https:// and should not contain any whitespaces")
    private String image;
}
