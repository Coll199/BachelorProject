package com.transport.smart.repository;

import com.transport.smart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String username);

    boolean existsById(UUID id);

    User findByEmail(String username);

    @Transactional
    void deleteByEmail(String username);

}
