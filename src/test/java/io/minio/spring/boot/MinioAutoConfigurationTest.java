/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.minio.spring.boot;

import io.minio.MinioClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link MinioAutoConfiguration}.
 *
 * <p>Verifies the auto-configuration activates under the expected conditions
 * and exposes its declared beans.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("MinioAutoConfiguration Tests")
class MinioAutoConfigurationTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(MinioAutoConfiguration.class));

    @Test
    @DisplayName("Auto-configuration class can be instantiated")
    void testInstantiation() {
        MinioAutoConfiguration configuration = new MinioAutoConfiguration();
        assertThat(configuration).isNotNull();
    }

    @Test
    @DisplayName("MinioClient bean is created when endpoint is configured")
    void testMinioClientCreated() {
        runner.withPropertyValues(
                        "minio.endpoint=http://localhost:9000",
                        "minio.access-key=minioadmin",
                        "minio.secret-key=minioadmin")
                .run(context -> {
                    assertThat(context).hasSingleBean(MinioClient.class);
                    assertThat(context).hasSingleBean(MinioProperties.class);
                });
    }

    @Test
    @DisplayName("MinioClient bean is created with port and region")
    void testMinioClientWithPortAndRegion() {
        runner.withPropertyValues(
                        "minio.endpoint=http://localhost",
                        "minio.port=9000",
                        "minio.access-key=minioadmin",
                        "minio.secret-key=minioadmin",
                        "minio.region=us-east-1")
                .run(context -> {
                    assertThat(context).hasSingleBean(MinioClient.class);
                });
    }

    @Test
    @DisplayName("MinioClient bean is created with port but no region")
    void testMinioClientWithPortNoRegion() {
        runner.withPropertyValues(
                        "minio.endpoint=http://localhost",
                        "minio.port=9000",
                        "minio.access-key=minioadmin",
                        "minio.secret-key=minioadmin")
                .run(context -> {
                    assertThat(context).hasSingleBean(MinioClient.class);
                });
    }

    @Test
    @DisplayName("MinioClient bean is created with region but no port")
    void testMinioClientWithRegionNoPort() {
        runner.withPropertyValues(
                        "minio.endpoint=http://localhost:9000",
                        "minio.access-key=minioadmin",
                        "minio.secret-key=minioadmin",
                        "minio.region=us-east-1")
                .run(context -> {
                    assertThat(context).hasSingleBean(MinioClient.class);
                });
    }

    @Test
    @DisplayName("MinioProperties bean is always created")
    void testMinioPropertiesCreated() {
        runner.withPropertyValues(
                        "minio.endpoint=http://localhost:9000",
                        "minio.access-key=minioadmin",
                        "minio.secret-key=minioadmin")
                .run(context -> {
                    assertThat(context).hasSingleBean(MinioProperties.class);
                    MinioProperties props = context.getBean(MinioProperties.class);
                    assertThat(props.getEndpoint()).isEqualTo("http://localhost:9000");
                    assertThat(props.getAccessKey()).isEqualTo("minioadmin");
                    assertThat(props.getSecretKey()).isEqualTo("minioadmin");
                });
    }

    @Test
    @DisplayName("MinioClient bean respects @ConditionalOnMissingBean")
    void testConditionalOnMissingBean() {
        MinioClient customClient = MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("minioadmin", "minioadmin")
                .build();
        runner.withBean(MinioClient.class, () -> customClient)
                .withPropertyValues(
                        "minio.endpoint=http://localhost:9000",
                        "minio.access-key=minioadmin",
                        "minio.secret-key=minioadmin")
                .run(context -> {
                    assertThat(context).hasSingleBean(MinioClient.class);
                    assertThat(context.getBean(MinioClient.class)).isSameAs(customClient);
                });
    }
}
