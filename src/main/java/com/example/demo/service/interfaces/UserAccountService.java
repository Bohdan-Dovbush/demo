package com.example.demo.service.interfaces;

import com.example.demo.exeption.InvalidTokenException;
import com.example.demo.exeption.UnkownIdentifierException;

public interface UserAccountService {

    void forgottenPassword(final String userName) throws UnkownIdentifierException;
    void updatePassword(final String password, final String token) throws InvalidTokenException, UnkownIdentifierException;
    boolean loginDisabled(final String username);
}
