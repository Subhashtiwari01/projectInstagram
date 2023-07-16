package com.project.InstagramBackend.Controller;
import com.project.InstagramBackend.Model.Post;
import com.project.InstagramBackend.Service.UserService;
import com.project.InstagramBackend.Model.DataObject.SignInInput;
import com.project.InstagramBackend.Model.DataObject.SignUpOutput;
import com.project.InstagramBackend.Model.User;
import com.project.InstagramBackend.Service.AuthenticationService;
import com.project.InstagramBackend.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("user/signup")
    public SignUpOutput signUpInstaUser(@RequestBody User user) throws NoSuchAlgorithmException {

        return userService.signUpUser(user);
    }

    @PostMapping("user/signIn")
    public String sigInInstaUser(@RequestBody @Valid SignInInput signInInput) throws MessagingException, NoSuchAlgorithmException {
        return userService.signInUser(signInInput);
    }
    @PostMapping("post")
    public String createInstaPost(@RequestBody Post post, @RequestParam String email, @RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.createInstaPost(post,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }


}


