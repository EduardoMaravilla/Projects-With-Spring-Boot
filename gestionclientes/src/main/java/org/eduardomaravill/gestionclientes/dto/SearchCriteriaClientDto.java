package org.eduardomaravill.gestionclientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteriaClientDto {
    private String firstName;
    private String email;
    private String phone;
}
