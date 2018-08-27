package com.rabbit.samples.kubeconfig.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration("poolSizeConfig")
@ConfigurationProperties(prefix = "poolsize")
public class PoolSizeConfig {

	String name;

	int core;

	int max;

}
