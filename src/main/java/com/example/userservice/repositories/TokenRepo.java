package com.example.userservice.repositories;

import com.example.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {

    Optional<Token> findByValueAndDeleted(String value, boolean deleted);
    //SELECT * FROM tokens WHERE value = ? AND deleted = ?;

//    // ✅ Alternative: Use a JPQL Query if the above method doesn't work
//    @Query("SELECT t FROM Token t WHERE t.value = :value AND t.deleted = false")
//    Optional<Token> findByValueAndDeletedJPQL(@Param("value") String value);
   // Optional<Token> findByValueAndDeletedEquals(String value, boolean isDeleted);

}
