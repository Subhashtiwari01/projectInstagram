package com.project.InstagramBackend.Service;

import com.project.InstagramBackend.Model.Post;
import com.project.InstagramBackend.Repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo;

    public String createInstaPost(Post post) {
        post.setPostCreatedTimeStamp(LocalDateTime.now());
        postRepo.save(post);
        return "Post uploaded!!!!";
    }
    }

