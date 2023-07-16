package com.project.InstagramBackend.Repository;

import com.project.InstagramBackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends CrudRepository<User,Long> {
    User findFirstByUserEmail(User user);

    User findFirstByUserEmail(String email);
}
