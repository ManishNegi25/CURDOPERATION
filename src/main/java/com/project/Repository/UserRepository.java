package com.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.model.User;


public interface UserRepository extends JpaRepository<User ,Integer>{
	
}



