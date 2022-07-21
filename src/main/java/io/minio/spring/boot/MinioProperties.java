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

import io.minio.ObjectWriteArgs;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(MinioProperties.PREFIX)
@Data
public class MinioProperties {

	public static final String PREFIX = "minio";
	
	/**
	 * Request endpoint. Endpoint is an URL, domain name, IPv4 or IPv6 address.<pre>
	 *              Valid endpoints:
	 *              * https://s3.amazonaws.com
	 *              * https://s3.amazonaws.com/
	 *              * https://play.min.io:9000
	 *              * http://play.min.io:9010/
	 *              * localhost
	 *              * localhost.localdomain
	 *              * play.min.io
	 *              * 127.0.0.1
	 *              * 192.168.1.60
	 *              * ::1</pre>
	 */
	String endpoint; 
	
	/**
	 * Valid port.  It should be in between 1 and 65535.  Unused if endpoint is an URL.
	 */
	int port = -1; 
	
	/**
	 * Access key to access service in endpoint.
	 */
	String accessKey;
	/**
	 * Secret key to access service in endpoint.
	 */
	String secretKey; 
	
	/**
	 * Region name to access service in endpoint.
	 */
	String region;
	
	/**
	 * If true, access endpoint using HTTPS else access it using HTTP.
	 */
	boolean secure;

	/**
	 * the partSize, minimum allowed 5MiB, maximum allowed 5GiB, default 0
	 */
	long partSize = ObjectWriteArgs.MIN_MULTIPART_SIZE;

}

