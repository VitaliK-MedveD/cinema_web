package by.it.medved.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("service.mail")
public class MailProperties {

    private String email;
    private String userName;
    private String password;
}