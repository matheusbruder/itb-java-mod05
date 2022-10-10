package br.com.mpbruder.teste02.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    @NotBlank(message = "Name must not be null or blank")
    private String name;

    @NotBlank(message = "Email must not be null or blank")
    @Email(message = "Email must be valid")
    private String email;

    @Min(value = 18, message = "Age must be 18 or over")
    private int age;
}
