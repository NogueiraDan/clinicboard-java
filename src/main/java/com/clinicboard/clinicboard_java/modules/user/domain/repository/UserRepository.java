package com.clinicboard.clinicboard_java.modules.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import com.clinicboard.clinicboard_java.modules.user.domain.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByEmail(String email);
}
