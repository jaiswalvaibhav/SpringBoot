package com.springboot.annotations.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValueAnnotationDemo {

    @Value("XYZ Default Value Name")
    private String defaultName;

    @Value("${mail.host}")
    private String mailHost;

    @Value(("${mail.email}"))
    private String email;

    @Value(("${mail.password}"))
    private String emailPassword;

    @Value("${java.home}")//Pre-defined variable name for complete java home path where jdk is installed
    private String javaHome;

    @Value("${HOME}")//Pre-defined variable name for java home directory: It is your current working directory
    private String homeDirectory;

    public String getDefaultName() {
        return defaultName;
    }

    public String getMailHost() {
        return mailHost;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public String getJavaHome() {
        return javaHome;
    }

    public String getHomeDirectory() {
        return homeDirectory;
    }
}
