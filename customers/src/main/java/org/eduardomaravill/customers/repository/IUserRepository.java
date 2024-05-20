package org.eduardomaravill.customers.repository;


import org.eduardomaravill.customers.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends CrudRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE email LIKE %:email% OR address LIKE %:address%")
    List<User> findByEmailOrAddress(@Param("email") String email, @Param("address") String address);

    @Query("SELECT u FROM User u WHERE email LIKE :email AND password LIKE :password")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
