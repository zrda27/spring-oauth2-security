package com.cmrh.auth.app.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@EnableOAuth2Sso
public class OAuth2ClientConfig {
}
