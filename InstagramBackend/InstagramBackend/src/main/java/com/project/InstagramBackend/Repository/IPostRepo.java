package com.project.InstagramBackend.Repository;

import com.project.InstagramBackend.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post,Long> {
}
