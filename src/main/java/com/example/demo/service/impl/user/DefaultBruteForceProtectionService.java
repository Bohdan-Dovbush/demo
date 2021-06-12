package com.example.demo.service.impl.user;

import com.example.demo.entity.user.UserEntity;
import com.example.demo.repository.user.UserEntityRepository;
import com.example.demo.service.interfaces.user.BruteForceProtectionService;
import com.example.demo.validation.FailedLogin;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service("BruteForceProtectionService")
@Getter
@Setter
public class DefaultBruteForceProtectionService implements BruteForceProtectionService {

    @Value("${jdj.security.failedlogin.count}")
    private int maxFailedLogins;

    final UserEntityRepository userEntityRepository;

    @Value("${jdj.brute.force.cache.max}")
    private int cacheMaxLimit;

    private final ConcurrentHashMap<String, FailedLogin> cache;

    public DefaultBruteForceProtectionService(UserEntityRepository userEntityRepository) {
        this.cache = new ConcurrentHashMap<>(cacheMaxLimit); //setting max limit for cache
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public void registerLoginFailure(String username) {

        UserEntity user = getUser(username);
        if(user != null && !user.isLoginDisabled()){
            int failedCounter = user.getFailedLoginAttempts();
            if(maxFailedLogins < failedCounter + 1){
                user.setLoginDisabled(true); //disabling the account
            }
            else{
                //let's update the counter
                user.setFailedLoginAttempts(failedCounter+1);
            }
            userEntityRepository.save(user);
        }
    }

    @Override
    public void resetBruteForceCounter(String username) {
        UserEntity user = getUser(username);
        if(user != null) {
            user.setFailedLoginAttempts(0);
            user.setLoginDisabled(false);
            userEntityRepository.save(user);
        }
    }

    @Override
    public boolean isBruteForceAttack(String username) {
        UserEntity user = getUser(username);
        if(user != null){
            return user.getFailedLoginAttempts() >= maxFailedLogins;
        }
        return false;
    }

    private UserEntity getUser(final String username){
        return  userEntityRepository.findByEmail(username);
    }
}
