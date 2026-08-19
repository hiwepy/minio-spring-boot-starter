package io.minio.spring.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;

import io.minio.MinioClient;
import io.minio.errors.MinioException;

@Configuration
@EnableConfigurationProperties({ MinioProperties.class })
/**\n * Auto-configuration for MinioAutoConfiguration.\n *\n * @author <a href="https://github.com/loong10k">Loong Wan</a>\n * @since 1.0.0\n */
public class MinioAutoConfiguration{
	/**
	 * <p>Minio client.</p>
	 * @param properties the properties
	 * @return the minio client
	 * @throws MinioException if an error occurs
	 */

	@Bean
	@ConditionalOnMissingBean
	public MinioClient minioClient(MinioProperties properties) throws MinioException {
		if(properties.getPort() > 0) {
			if(StringUtils.hasText(properties.getRegion())) {
				// Create a minioClient with the MinIO server playground, its access key and secret key.
				return MinioClient.builder()
						.endpoint(properties.getEndpoint(), properties.getPort(), properties.isSecure())
						.credentials(properties.getAccessKey(), properties.getSecretKey())
						.region(properties.getRegion())
						.build();
			}
			return MinioClient.builder()
					.endpoint(properties.getEndpoint(), properties.getPort(), properties.isSecure())
					.credentials(properties.getAccessKey(), properties.getSecretKey())
					.build();
		}
		
		if(StringUtils.hasText(properties.getRegion())) {
			return MinioClient.builder()
					.endpoint(properties.getEndpoint())
					.credentials(properties.getAccessKey(), properties.getSecretKey())
					.region(properties.getRegion())
					.build();
		}
		return MinioClient.builder()
				.endpoint(properties.getEndpoint())
				.credentials(properties.getAccessKey(), properties.getSecretKey())
				.build();
	}

}
