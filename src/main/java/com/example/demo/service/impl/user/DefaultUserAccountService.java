package com.example.demo.service.impl.user;

import com.example.demo.entity.user.SecureToken;
import com.example.demo.entity.user.UserEntity;
import com.example.demo.exeption.InvalidTokenException;
import com.example.demo.exeption.UnkownIdentifierException;
import com.example.demo.repository.interfaces.user.SecureTokenRepository;
import com.example.demo.repository.interfaces.user.UserEntityRepository;
import com.example.demo.service.interfaces.user.EmailService;
import com.example.demo.service.interfaces.user.SecureTokenService;
import com.example.demo.service.interfaces.user.UserAccountService;
import com.example.demo.service.interfaces.user.UserEntityService;
import com.example.demo.validation.ForgotPasswordEmailContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Objects;

@Service("UserAccountService")
public class DefaultUserAccountService implements UserAccountService {

    @Value("${site.base.url.http}")
    private String baseURL;

    final UserEntityService userService;
    final SecureTokenRepository secureTokenRepository;
    private final SecureTokenService secureTokenService;
    private final EmailService emailService;
    private final UserEntityRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultUserAccountService(UserEntityService userService, SecureTokenRepository secureTokenRepository, SecureTokenService secureTokenService, EmailService emailService, UserEntityRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.secureTokenRepository = secureTokenRepository;
        this.secureTokenService = secureTokenService;
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void forgottenPassword(String userName) throws UnkownIdentifierException {
        UserEntity user= userService.getUserById(userName);
        sendResetPasswordEmail(user);
    }

    @Override
    public void updatePassword(String password, String token) throws InvalidTokenException, UnkownIdentifierException {
        SecureToken secureToken = secureTokenService.findByToken(token);
        if(Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()){
            throw new InvalidTokenException("Token is not valid");
        }
        UserEntity user = userRepository.getOne(secureToken.getUser().getId());
        if(Objects.isNull(user)){
            throw new UnkownIdentifierException("unable to find user for the token");
        }
        secureTokenService.removeToken(secureToken);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public boolean loginDisabled(String username) {
        UserEntity userEntity = userRepository.findByEmail(username);
        return userEntity !=null  ? userEntity.isLoginDisabled() : false;
    }

    protected void sendResetPasswordEmail(UserEntity user) {
        SecureToken secureToken = secureTokenService.createSecureToken();
        secureToken.setUser(user);
        secureTokenRepository.save(secureToken);
        ForgotPasswordEmailContext emailContext = new ForgotPasswordEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println(emailContext);
        }
    }
}
