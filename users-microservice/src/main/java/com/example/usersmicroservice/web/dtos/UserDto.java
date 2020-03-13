package com.example.usersmicroservice.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Positive
    private Long id;

    @Email(message = "Field must be an email")
    @NotBlank(message = "Email is required")
    private String email;

}
