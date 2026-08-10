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

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Minio.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ConfigurationProperties(MinioProperties.PREFIX)
public class MinioProperties {

	public static final String PREFIX = "minio";

	/**
	 * Request endpoint. Endpoint is an URL, domain name, IPv4 or IPv6 address.
	 */
	private String endpoint;

	/**
	 * Valid port. It should be in between 1 and 65535. Unused if endpoint is an URL.
	 */
	private int port = -1;

	/**
	 * Access key to access service in endpoint.
	 */
	private String accessKey;

	/**
	 * Secret key to access service in endpoint.
	 */
	private String secretKey;

	/**
	 * Region name to access service in endpoint.
	 */
	private String region;

	/**
	 * If true, access endpoint using HTTPS else access it using HTTP.
	 */
	private boolean secure;

	/**
	 * the partSize, minimum allowed 5MiB, maximum allowed 5GiB, default 0
	 */
	private int partSize;

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public boolean isSecure() {
		return secure;
	}

	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	public int getPartSize() {
		return partSize;
	}

	public void setPartSize(int partSize) {
		this.partSize = partSize;
	}

}
