package org.eduardomaravill.gestionclientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    private List<String> to;
    private String from;
    private String subject;
    private String body;
    private List<String> cc;
    private List<String> bcc;
}
