package com.example.demo.service.interfaces;

import com.example.demo.entity.user.SecureToken;

public interface SecureTokenService {

    SecureToken createSecureToken();
    void saveSecureToken(final SecureToken token);
    SecureToken findByToken(final String token);
    void removeToken(final SecureToken token);
    void removeTokenByToken(final String token);
}
