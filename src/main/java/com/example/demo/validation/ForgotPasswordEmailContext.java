package com.example.demo.validation;

import com.example.demo.entity.user.UserEntity;
import lombok.Getter;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
public class ForgotPasswordEmailContext extends AbstractEmailContext{

    private String token;

    @Override
    public <T> void init(T context){
        //we can do any common configuration setup here
        // like setting up some base URL and context
        UserEntity customer = (UserEntity) context; // we pass the customer information
        put("firstName", customer.getFirstName());
        setTemplateLocation("emails/forgot-password");
        setSubject("Forgotten Password");
        setFrom("no-reply@gmail.com");
        setTo(customer.getEmail());
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/password/change").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}
