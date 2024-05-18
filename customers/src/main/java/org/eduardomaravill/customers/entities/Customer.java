package org.eduardomaravill.customers.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "customers")
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String address;
}
