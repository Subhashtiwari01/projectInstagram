package com.project.InstagramBackend.Service;
import com.project.InstagramBackend.Model.Post;
import com.project.InstagramBackend.Service.UserService;
import com.project.InstagramBackend.Model.AuthenticationToken;
import com.project.InstagramBackend.Model.DataObject.SignInInput;
import com.project.InstagramBackend.Model.DataObject.SignUpOutput;
import com.project.InstagramBackend.Model.User;
import com.project.InstagramBackend.Repository.IAuthenticationRepo;
import com.project.InstagramBackend.Repository.IPostRepo;
import com.project.InstagramBackend.Repository.IUserRepo;
import com.project.InstagramBackend.Service.Utiliti.EmailHandler;
import com.project.InstagramBackend.Service.Utiliti.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    IPostRepo postRepo;

    @Autowired
    IAuthenticationRepo authenticationRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    PostService postService;

    public SignUpOutput signUpUser(User user) throws NoSuchAlgorithmException {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getUserEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(newEmail);
        if(existingUser != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //hash the password: encrypt the password

            String encryptedPassword = PasswordEncrypter.encryptionPassword(user.getUserPassword());

            //saveAppointment the user with the new encrypted password

            user.setUserPassword(encryptedPassword);
            user.setUserEmail(user.getUserEmail());
            user.setUserFirstName(user.getUserFirstName());
            user.setUserLastName(user.getUserLastName());
            user.setUserAge(user.getUserAge());
            user.setUserPhoneNumber(user.getUserPhoneNumber());



            User user1=userRepo.save(user);

            return new SignUpOutput(signUpStatus, "User registered successfully!!!");


    }


    public String signInUser(SignInInput signInInput) throws MessagingException, NoSuchAlgorithmException {

        String signInStatusMessage = null;

        User existingUser = userRepo.findFirstByUserEmail(signInInput.getEmail());


        //check if this user email already exists ??

        if (existingUser == null) {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptionPassword(signInInput.getPassword());
            if (existingUser.getUserPassword().equals(encryptedPassword)) {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken = new AuthenticationToken(existingUser);
                authenticationService.saveAuthToken(authToken);

                EmailHandler.sendEmail("subhashtiwari32096@gmail.com", "email testing", authToken.getTokenValue());
                return "Token sent to your email";
            } else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        } catch (Exception e) {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }
    }


    public String createInstaPost(Post post, String email) {
        User postOwner = userRepo.findFirstByUserEmail(email);
        post.setPostOwner(postOwner);
        return postService.createInstaPost(post);

    }
}
