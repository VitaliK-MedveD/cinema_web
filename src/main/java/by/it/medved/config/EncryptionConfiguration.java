package by.it.medved.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("service.encrypt")
public class EncryptionConfiguration {

    private String cryptographicHashingAlgorithm;
    private String keyGenerationAlgorithm;
    private Integer saltLength;
    private Integer derivedKeyLength;
    private Integer iterations;
}