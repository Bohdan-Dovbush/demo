package com.example.demo.service.interfaces;

import com.example.demo.entity.user.UserEntity;
import com.example.demo.exeption.InvalidTokenException;
import com.example.demo.exeption.UnkownIdentifierException;
import com.example.demo.exeption.UserAlreadyExistException;
import com.example.demo.validation.UserData;

public interface UserEntityService {

    void register(final UserData user) throws UserAlreadyExistException;
    boolean checkIfUserExist(final String email);
    void sendRegistrationConfirmationEmail(final UserEntity user);
    boolean verifyUser(final String token) throws InvalidTokenException;
    UserEntity getUserById(final String id) throws UnkownIdentifierException;
}
