package com.openwt.boats.dao;

import com.openwt.boats.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByUserNameAndPassword(String username,String password);
    User findUsersById(Long id);
}
