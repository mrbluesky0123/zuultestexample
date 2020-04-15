package me.mrbluesky.zuultestexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

//@EnableDiscoveryClient
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaServer
public class ZuultestexampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZuultestexampleApplication.class, args);
  }

  @Bean
  public PreZuulFilter preFilter() {
    return new PreZuulFilter();
  }

  @Bean
  public PostZuulFilter postFilter() {
    return new PostZuulFilter();
  }

}
