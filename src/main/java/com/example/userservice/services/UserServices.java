package com.example.userservice.services;

import com.example.userservice.dtos.SignUpRequestDto;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;

public interface UserServices {
   User signUp(SignUpRequestDto requestDto);
   Token login(String email, String password);
   void logout(String token);
   User validateToken(String token);
}
