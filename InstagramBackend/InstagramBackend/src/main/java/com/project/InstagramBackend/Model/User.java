package com.project.InstagramBackend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long userId;
    @Column(name = "user_first_name")

    private String userFirstName;
    @Column(name = "user_last_name")

    private String userLastName;
    @Column(name = "user_password")


    private String userPassword;
    @Column(name = "user_age")

    private Integer userAge;

    @Email
    @Column(name = "user_email")

    private String userEmail;
    @Column(name = "user_phone_number")


    private String userPhoneNumber;
}
