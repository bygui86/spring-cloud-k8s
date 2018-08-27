package com.rabbit.samples.kubeconfig.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PoolSizeDto {

	String name;

	int core;

	int max;

}
