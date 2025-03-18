package com.example.userservice.controllers;

import com.example.userservice.dtos.LoginRequestDto;
import com.example.userservice.dtos.LogoutRequestDto;
import com.example.userservice.dtos.SignUpRequestDto;
import com.example.userservice.dtos.SignUpResponseDto;
import com.example.userservice.models.User;
import com.example.userservice.models.Token;
import com.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService; //  Declared as final (good practice)

    //  Constructor-based dependency injection
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //  Login Endpoint
//    @PostMapping("/login")
//    public Token login(@RequestBody LoginRequestDto requestDto) {
//        return userService.login(requestDto.getEmail(), requestDto.getPassword());
//    }
    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto requestDto) {
        // check if email and password in db
        // if yes create token (use random string) return token
        // else throw some error

        return userService.login(requestDto.getEmail(), requestDto.getPassword());
    }
    //  Signup Endpoint
    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody SignUpRequestDto requestDto) {
        return toSignUpResponseDto(userService.signUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        ));
    }

    //  Logout Endpoint
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto requestDto) {
        userService.logout(requestDto.getToken());
        return ResponseEntity.ok().build();
    }

    //  Validate Token Endpoint
    @PostMapping("/validate/{token}")
    public User validateToken(@PathVariable("token") String token) {
        return userService.validateToken(token);
    }

    //  Convert User to DTO (Manually handled since no Lombok)
    private SignUpResponseDto toSignUpResponseDto(User user) {
        if (user == null) {
            return null; // Can throw an exception instead if required
        }

        SignUpResponseDto dto = new SignUpResponseDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setEmailVerified(user.isEmailVerified());

        return dto;
    }
}

