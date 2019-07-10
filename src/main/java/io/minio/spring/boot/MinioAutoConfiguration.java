package io.minio.spring.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import io.minio.MinioClient;
import io.minio.errors.MinioException;

@Configuration
@ConditionalOnClass(MinioClient.class)
@ConditionalOnProperty(prefix = MinioProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ MinioProperties.class })
public class MinioAutoConfiguration{

	@Bean
	@ConditionalOnMissingBean
	public MinioClient minioClient(MinioProperties properties) throws MinioException {
		
		if(properties.getPort() > 0) {
			if(StringUtils.hasText(properties.getRegion())) {
				return new MinioClient(properties.getEndpoint(), properties.getPort(), properties.getAccessKey(), properties.getSecretKey(), properties.getRegion(), properties.isSecure());
			}
			return new MinioClient(properties.getEndpoint(), properties.getPort(), properties.getAccessKey(), properties.getSecretKey(), properties.isSecure());
		}
		
		if(StringUtils.hasText(properties.getRegion())) {
			return new MinioClient(properties.getEndpoint(), properties.getAccessKey(), properties.getSecretKey(), properties.getRegion());
		}
		return new MinioClient(properties.getEndpoint(), properties.getAccessKey(), properties.getSecretKey(), properties.isSecure());
	}

}
