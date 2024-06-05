package org.eduardomaravill.gestionclientes.repository;

import org.eduardomaravill.gestionclientes.dto.ClientDto;
import org.eduardomaravill.gestionclientes.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientRepository extends JpaRepository<Client,Long> {

    @Query("SELECT c FROM Client c WHERE " +
            "(:firstName IS NULL OR c.firstName LIKE %:firstName%) AND " +
            "(:email IS NULL OR c.email LIKE %:email%) AND " +
            "(:phone IS NULL OR c.phone LIKE %:phone%)")
    List<Client> searchClients(@Param("firstName") String firstName,
                              @Param("email") String email,
                              @Param("phone") String phone);
}
