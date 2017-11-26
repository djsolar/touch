package com.twinflag.touch.respository;

import com.twinflag.touch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

    User findByUsername(String username);
}
