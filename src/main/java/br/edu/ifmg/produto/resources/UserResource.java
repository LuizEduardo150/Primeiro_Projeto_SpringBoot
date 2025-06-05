package br.edu.ifmg.produto.resources;

import br.edu.ifmg.produto.dtos.UserDTO;
import br.edu.ifmg.produto.dtos.UserInsertDTO;
import br.edu.ifmg.produto.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserResource {

    UserService userService = new UserService();

    // Link publico
    @PostMapping(value = "/signup", produces = "aplication/json")
    @Operation(description = "sing up", summary = "singup", responses = {
            @ApiResponse(description = "created", responseCode = "200")
    })
    public void signup(@RequestBody UserInsertDTO dto){
        UserDTO user = userService.signup(dto);

    }




}
