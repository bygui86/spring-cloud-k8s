package com.rabbit.samples.kubeconfig.controllers;

import com.rabbit.samples.kubeconfig.config.PoolSizeConfig;
import com.rabbit.samples.kubeconfig.dtos.InfoDto;
import com.rabbit.samples.kubeconfig.dtos.PoolSizeDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(value = AccessLevel.PROTECTED)
@RestController("infoRestController")
@RequestMapping("/info")
public class InfoRestController {

	int idCounter = 0;

	@Value("${spring.application.name}")
	String appName;

	@Resource(name = "poolSizeConfig")
	PoolSizeConfig poolSizeConfig;

	@GetMapping
	public InfoDto getInfo() {

		log.debug("Getting info...");

		idCounter++;
		return createInfoDto();
	}

	protected InfoDto createInfoDto() {

		return InfoDto.builder()
				.id(getIdCounter())
				.name(getAppName())
				.dateTime(LocalDateTime.now())
				.poolSize(createPoolSizeDto())
				.build();
	}

	protected PoolSizeDto createPoolSizeDto() {

		return PoolSizeDto.builder()
				.name(getPoolSizeConfig().getName())
				.core(getPoolSizeConfig().getCore())
				.max(getPoolSizeConfig().getMax())
				.build();
	}

}
