package com.example.userservice.services;

import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.repositories.TokenRepo;
import com.example.userservice.repositories.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements UserServices {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepo userRepository;
    private final TokenRepo tokenRepository;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepo userRepository, TokenRepo tokenRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User signUp(String name, String email, String password) {
        User u = new User();
        u.setEmail(email);
        u.setName(name);
        u.setHashedPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(u);
    }

    @Override
    public Token login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        if (bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {
            Token token = new Token();
            token.setUser(user);
            token.setValue(RandomStringUtils.randomAlphabetic(128)); // Kept original logic
            LocalDate today = LocalDate.now();
            LocalDate oneDayLater = today.plusDays(1);
            Date expiryAt = Date.from(oneDayLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
            token.setExpiryAt(expiryAt);
            return tokenRepository.save(token);
        }
        return null;
    }

    @Override
    public void logout(String token) {
        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeleted(token, false);
        if (tokenOptional.isPresent()) {
            Token t = tokenOptional.get();
            t.setDeleted(true);
            tokenRepository.save(t);
        }
    }

    @Override
    public User validateToken(String token) {
        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeleted(token, false);
        if (tokenOptional.isEmpty()) {
            return null;
        }
        Token t = tokenOptional.get();
        if (t.getExpiryAt().before(new Date())) {
            return null;
        }
        return t.getUser();
    }
//
//
//}


}
