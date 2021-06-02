package com.example.demo.security;

import com.example.demo.service.interfaces.user.BruteForceProtectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AuthenticationSuccessLogoutListener implements ApplicationListener<LogoutSuccessEvent> {

    private static final Logger LOG  = LoggerFactory.getLogger(AuthenticationSuccessLogoutListener.class);

    @Resource(name="BruteForceProtectionService")
    private BruteForceProtectionService bruteForceProtectionService;

    @Override
    public void onApplicationEvent(LogoutSuccessEvent logoutSuccessEvent) {
        String username = logoutSuccessEvent.getAuthentication().getName();
        LOG.info("********* logout successful for user {} ", username);
        bruteForceProtectionService.isBruteForceAttack(username);
    }
}
