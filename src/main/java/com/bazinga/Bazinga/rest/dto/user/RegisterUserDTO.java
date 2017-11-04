package com.bazinga.Bazinga.rest.dto.user;

import com.bazinga.Bazinga.security.AuthoritiesConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {
    @NotNull
    private String username;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String authority;
}
