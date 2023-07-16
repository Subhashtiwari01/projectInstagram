package com.project.InstagramBackend.Repository;

import com.project.InstagramBackend.Model.AuthenticationToken;
import com.project.InstagramBackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByUser(User user);
}
