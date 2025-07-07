# Spring Security Auto Configuration Documentation

## Overview

This document provides comprehensive information about Spring Security auto-configuration in Spring Boot applications, including the latest changes in Spring Security 6 with Spring Boot 3.

## Table of Contents

- [Spring Boot Auto Configuration for Spring Security](#spring-boot-auto-configuration-for-spring-security)
- [Spring Security 6 with Spring Boot 3](#spring-security-6-with-spring-boot-3)
- [Key Features](#key-features)
- [Security Endpoints](#security-endpoints)
- [Deprecated Components](#deprecated-components)

---

## Spring Boot Auto Configuration for Spring Security

Spring Boot provides comprehensive auto-configuration for Spring Security, streamlining the setup process and providing secure defaults out of the box.

### Key Features

#### 1. **Starter Dependency**
- **`spring-boot-starter-security`** starter aggregates all Spring Security-related dependencies
- Simplifies dependency management for Spring Security components

#### 2. **Default Configuration**
- Enables Spring Security's default configuration automatically
- Creates a servlet Filter as a bean named `springSecurityFilterChain`
- Provides a default login form with username field set to "user"

#### 3. **Default User Creation**
- Creates a default user with username: `user`
- Generates a random password that is logged to the console
- **Example password format**: `8e557245-73e2-4286-969a-ff57fe326336`

#### 4. **Customizable User Credentials**
- Spring Boot provides properties to customize the default user's username and password
- Can be configured through `application.properties` or `application.yml`

#### 5. **Default Login Page**
- Provides a default login page accessible at `localhost:8080`
- Prompts for username and password authentication

#### 6. **Password Security**
- Protects password storage using the **BCrypt algorithm**
- Ensures secure password hashing and storage

#### 7. **Logout Functionality**
- Default logout feature available at `localhost:8080/logout`
- Handles session invalidation and security cleanup

#### 8. **CSRF Protection**
- **Cross-Site Request Forgery (CSRF) attack prevention** enabled by default
- Provides built-in protection against CSRF vulnerabilities

#### 9. **Automatic HTTP Endpoint Security**
- When Spring Security is on the classpath, Spring Boot automatically secures all HTTP endpoints
- Uses **"basic" authentication** as the default security mechanism

---

## Spring Security 6 with Spring Boot 3

### Framework Overview

Spring Security 6 represents a significant evolution from previous versions, introducing major architectural changes and modernizations when paired with Spring Boot 3.

#### Evolution Comparison: Spring Security 6 vs Previous Versions

| Aspect | Spring Security 5.x & Earlier | Spring Security 6 with Spring Boot 3 |
|--------|-------------------------------|-------------------------------------|
| **Java Version** | Java 8+ support | **Java 17+ minimum requirement** |
| **Spring Boot Version** | Spring Boot 2.x | **Spring Boot 3.x** (with Spring Framework 6) |
| **Configuration Approach** | `WebSecurityConfigurerAdapter` based | **Component-based configuration** with `@Bean` methods |
| **Method Security** | `@EnableGlobalMethodSecurity` | **`@EnableMethodSecurity`** with improved annotations |
| **Path Matching** | `antMatchers()` | **`requestMatchers()`** with enhanced pattern matching |
| **Authorization** | `authorizeRequests()` | **`authorizeHttpRequests()`** with lambda DSL |
| **Servlet API** | Servlet 4.0 | **Servlet 5.0+** (Jakarta EE) |
| **Namespace Migration** | `javax.*` packages | **`jakarta.*` packages** |

#### Key Architectural Changes

##### **Before (Spring Security 5.x)**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/public/**").permitAll()
            .anyRequest().authenticated();
    }
}
```

##### **Now (Spring Security 6)**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .build();
    }
}
```

#### Core Capabilities

1. **Enhanced Authentication and Authorization**
    - **Modern OAuth 2.1 support** with improved PKCE flow
    - **JWT token handling** with better validation and parsing
    - **Reactive security** fully integrated with WebFlux applications

2. **Advanced Attack Protection**
    - **Improved CSRF protection** with better SameSite cookie handling
    - **Enhanced CORS configuration** with more flexible origin patterns
    - **Content Security Policy (CSP)** integration for XSS protection

3. **Multi-Application Support**
    - **First-class reactive support** for WebFlux applications
    - **Unified security model** across servlet and reactive stacks
    - **Better microservices security** with improved service-to-service authentication

4. **Performance & Observability**
    - **Micrometer integration** for security metrics
    - **Improved performance** with reduced memory footprint
    - **Better debugging** with enhanced security event publishing

#### Migration Benefits

- **Cleaner Configuration**: Component-based approach eliminates adapter inheritance
- **Lambda DSL**: More readable and maintainable security configurations
- **Type Safety**: Better compile-time checks and IDE support
- **Modern Standards**: Full Jakarta EE compliance and latest security protocols
- **Future-Proof**: Built for long-term maintainability and extensibility

#### Dependency Management

- **`spring-boot-starter-security`** starter continues to aggregate Spring Security-related dependencies
- Optimized for Spring Boot 3 compatibility and performance

---

## Security Endpoints

| Endpoint | Purpose | Default Access |
|----------|---------|----------------|
| `localhost:8080` | Default login page | Public (login required) |
| `localhost:8080/logout` | Logout functionality | Authenticated users |
| All HTTP endpoints | Application endpoints | Basic authentication required |

---

## Deprecated Components

⚠️ **Important**: The following components have been deprecated in Spring Security 6:

### Deprecated Classes and Annotations

- **`WebSecurityConfigurerAdapter`** - Legacy configuration approach
- **`@EnableGlobalMethodSecurity`** - Replaced with newer method security annotations
- **`antMatchers()`** - Path matching method for security configuration
- **`authorizeRequests()`** - Request authorization configuration method
---
