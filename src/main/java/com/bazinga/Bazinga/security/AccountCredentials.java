package com.bazinga.Bazinga.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountCredentials {

    private String username;
    private String password;

}
