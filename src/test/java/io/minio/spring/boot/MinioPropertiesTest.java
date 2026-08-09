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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link MinioProperties}.
 *
 * <p>Verifies default values, getters/setters and POJO contract.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("MinioProperties Tests")
class MinioPropertiesTest {

    @Test
    @DisplayName("Default constructor creates non-null instance with expected defaults")
    void testDefaultInstance() {
        MinioProperties props = new MinioProperties();
        assertThat(props).isNotNull();
        assertThat(props.getEndpoint()).isNull();
        assertThat(props.getPort()).isEqualTo(-1);
        assertThat(props.getAccessKey()).isNull();
        assertThat(props.getSecretKey()).isNull();
        assertThat(props.getRegion()).isNull();
        assertThat(props.isSecure()).isFalse();
        assertThat(props.getPartSize()).isEqualTo(0);
    }

    @Test
    @DisplayName("Public constant 'PREFIX' has expected value")
    void testPREFIXConstant() {
        assertThat(MinioProperties.PREFIX).isEqualTo("minio");
    }

    @Test
    @DisplayName("endpoint getter/setter works correctly")
    void testEndpoint() {
        MinioProperties props = new MinioProperties();
        props.setEndpoint("http://localhost:9000");
        assertThat(props.getEndpoint()).isEqualTo("http://localhost:9000");
    }

    @Test
    @DisplayName("port getter/setter works correctly")
    void testPort() {
        MinioProperties props = new MinioProperties();
        assertThat(props.getPort()).isEqualTo(-1);
        props.setPort(9000);
        assertThat(props.getPort()).isEqualTo(9000);
    }

    @Test
    @DisplayName("accessKey getter/setter works correctly")
    void testAccessKey() {
        MinioProperties props = new MinioProperties();
        props.setAccessKey("minioadmin");
        assertThat(props.getAccessKey()).isEqualTo("minioadmin");
    }

    @Test
    @DisplayName("secretKey getter/setter works correctly")
    void testSecretKey() {
        MinioProperties props = new MinioProperties();
        props.setSecretKey("miniosecret");
        assertThat(props.getSecretKey()).isEqualTo("miniosecret");
    }

    @Test
    @DisplayName("region getter/setter works correctly")
    void testRegion() {
        MinioProperties props = new MinioProperties();
        props.setRegion("us-east-1");
        assertThat(props.getRegion()).isEqualTo("us-east-1");
    }

    @Test
    @DisplayName("secure getter/setter works correctly")
    void testSecure() {
        MinioProperties props = new MinioProperties();
        assertThat(props.isSecure()).isFalse();
        props.setSecure(true);
        assertThat(props.isSecure()).isTrue();
    }

    @Test
    @DisplayName("partSize getter/setter works correctly")
    void testPartSize() {
        MinioProperties props = new MinioProperties();
        props.setPartSize(10485760);
        assertThat(props.getPartSize()).isEqualTo(10485760);
    }
}
