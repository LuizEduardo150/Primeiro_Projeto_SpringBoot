package br.edu.ifmg.produto.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserInsertDTO {

    @NotBlank
    @Size(min = 2,  max = 50)
    private String password;



}
