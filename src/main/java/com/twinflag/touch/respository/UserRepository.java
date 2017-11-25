package com.twinflag.touch.respository;

import com.twinflag.touch.model.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserBean, Integer>{
}
