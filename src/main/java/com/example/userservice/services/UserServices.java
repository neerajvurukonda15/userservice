package com.example.userservice.services;

import com.example.userservice.models.Token;
import com.example.userservice.models.User;

public interface UserServices {
   User signUp(String name, String email, String password);
   Token login(String email, String password);
   void logout(String token);
   User validateToken(String token);
}
