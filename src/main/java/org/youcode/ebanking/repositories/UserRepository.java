package org.youcode.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.ebanking.models.AppUser;
import org.youcode.ebanking.models.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
    List<AppUser> findByRoleName(String roleName);
    Optional<AppUser> findByUsernameAndRole_Name(String username, String roleName);
}
