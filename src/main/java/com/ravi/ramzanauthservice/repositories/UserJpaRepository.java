package com.ravi.ramzanauthservice.repositories;

import com.ravi.ramzanauthservice.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
