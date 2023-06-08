package com.haitaos.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

  @Bean
  public RestTemplate restTemplate(ClientHttpRequestFactory requestFactory) {
    return new RestTemplate(requestFactory);
  }

  @Bean
  public ClientHttpRequestFactory httpRequestFactory() {
    return new HttpComponentsClientHttpRequestFactory(httpClient());
  }

  public HttpClient httpClient() {
    Registry<ConnectionSocketFactory> registry =
        RegistryBuilder.<ConnectionSocketFactory>create()
            .register("http", PlainConnectionSocketFactory.getSocketFactory())
            .register("https", SSLConnectionSocketFactory.getSocketFactory())
            .build();
    PoolingHttpClientConnectionManager connectionManager =
        new PoolingHttpClientConnectionManager(registry);

    // Set the connection pool to a maximum of 500 connections
    connectionManager.setMaxTotal(500);

    // MaxPerRoute is a breakdown of maxtotal, the maximum concurrency per host is 300, and route is
    // the domain name
    connectionManager.setDefaultMaxPerRoute(300);

    RequestConfig requestConfig =
        RequestConfig.custom()
            // Timeout for returning data from the server
            .setResponseTimeout(Timeout.ofDays(20000))
            // Timeout time to connect to the server
            .setConnectTimeout(Timeout.ofDays(10000))
            // Get the timeout of the connection from the connection pool
            .setConnectionRequestTimeout(Timeout.ofDays(1000))
            .build();

    CloseableHttpClient closeableHttpClient =
        HttpClientBuilder.create()
            .setDefaultRequestConfig(requestConfig)
            .setConnectionManager(connectionManager)
            .build();
    return closeableHttpClient;
  }
}
