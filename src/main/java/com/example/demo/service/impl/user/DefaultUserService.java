package com.example.demo.service.impl.user;

import com.example.demo.entity.user.Group;
import com.example.demo.entity.user.SecureToken;
import com.example.demo.entity.user.UserEntity;
import com.example.demo.exeption.InvalidTokenException;
import com.example.demo.exeption.UnkownIdentifierException;
import com.example.demo.exeption.UserAlreadyExistException;
import com.example.demo.repository.user.GroupRepository;
import com.example.demo.repository.user.SecureTokenRepository;
import com.example.demo.repository.user.UserEntityRepository;
import com.example.demo.service.interfaces.user.EmailService;
import com.example.demo.service.interfaces.user.SecureTokenService;
import com.example.demo.service.interfaces.user.UserEntityService;
import com.example.demo.validation.AccountVerificationEmailContext;
import com.example.demo.validation.UserData;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Objects;

@Service("UserService")
public class DefaultUserService implements UserEntityService {

    @Value("${site.base.url.http}")
    private String baseURL;

    private final UserEntityRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final SecureTokenService secureTokenService;
    final SecureTokenRepository secureTokenRepository;
    final GroupRepository groupRepository;

    public DefaultUserService(UserEntityRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, SecureTokenService secureTokenService, SecureTokenRepository secureTokenRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.secureTokenService = secureTokenService;
        this.secureTokenRepository = secureTokenRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void register(UserData user) throws UserAlreadyExistException {
        if(checkIfUserExist(user.getEmail())){
            throw new UserAlreadyExistException("User already exists for this email");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        encodePassword(user, userEntity);
        updateUserGroup(userEntity);
        userRepository.save(userEntity);
        sendRegistrationConfirmationEmail(userEntity);
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public void sendRegistrationConfirmationEmail(UserEntity user) {
        SecureToken secureToken= secureTokenService.createSecureToken();
        secureToken.setUser(user);
        secureTokenRepository.save(secureToken);
        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifyUser(String token) throws InvalidTokenException {
        SecureToken secureToken = secureTokenService.findByToken(token);
        if(Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()){
            throw new InvalidTokenException("Token is not valid");
        }
        UserEntity user = userRepository.getOne(secureToken.getUser().getId());
        user.setAccountVerified(true);
        user.setLoginDisabled(false);
        userRepository.save(user); // let's same user details

        // we don't need invalid password now
        secureTokenService.removeToken(secureToken);
        return true;
    }

    @Override
    public UserEntity getUserById(String id) throws UnkownIdentifierException {
        UserEntity user= userRepository.findByEmail(id);
        if(user == null || BooleanUtils.isFalse(user.isAccountVerified())){
            // we will ignore in case account is not verified or account does not exists
            throw new UnkownIdentifierException("unable to find account or account is not active");
        } return user;
    }

    @Override
    public Integer findCountUser() {
        return userRepository.findCountUser();
    }

    private void updateUserGroup(UserEntity userEntity) {
        Group group = groupRepository.findByCode("customer");
        userEntity.addUserGroups(group);
    }

    private void encodePassword(UserData user, UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
