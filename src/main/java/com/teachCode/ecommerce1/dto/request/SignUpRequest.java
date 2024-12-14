package com.teachCode.ecommerce1.dto.request;

import com.teachCode.ecommerce1.entities.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String firstName;
    private String lastName ;
    private String email;
    private String password;
    /*@Enumerated(EnumType.STRING)
    private Role role;*/
}
