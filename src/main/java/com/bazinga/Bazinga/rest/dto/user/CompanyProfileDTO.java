package com.bazinga.Bazinga.rest.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lazap on 11/5/2017.
 */
@Data
@NoArgsConstructor
public class CompanyProfileDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
}
