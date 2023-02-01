package com.seguraquirozdt.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.seguraquirozdt.app.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>  {
 
	 User findUserById(Long id);
	
    User findUserByEmail(String email);
    
    List<User> findUserByStatus(char status);
 
    @Query("SELECT e FROM usuario e WHERE e.email = ?1 AND e.fecha_vigencia <= current_date ")
    User findUserActivo(String email);
}
