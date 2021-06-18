package com.example.demo.service.interfaces.user;

import com.example.demo.entity.user.UserEntity;
import com.example.demo.exeption.InvalidTokenException;
import com.example.demo.exeption.UnkownIdentifierException;
import com.example.demo.exeption.UserAlreadyExistException;
import com.example.demo.validation.UserData;

import java.util.List;
import java.util.Optional;

public interface UserEntityService {

    void register(final UserData user) throws UserAlreadyExistException;
    boolean checkIfUserExist(final String email);
    void sendRegistrationConfirmationEmail(final UserEntity user);
    boolean verifyUser(final String token) throws InvalidTokenException;
    UserEntity getUserById(final String id) throws UnkownIdentifierException;
    Integer findCountUser();

    Optional<UserEntity> findDetailsAndContactById(Long id);
    List<UserEntity> findAll();
}
