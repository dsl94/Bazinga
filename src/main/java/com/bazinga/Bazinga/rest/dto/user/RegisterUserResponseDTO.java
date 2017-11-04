package com.bazinga.Bazinga.rest.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponseDTO {

    private Long id;
    private String username;
}
