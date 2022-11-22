package com.qqls.youxiangousys.pj.common.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node implements Serializable{
	private static final long serialVersionUID = 7646167094495580989L;
	private Integer id;//菜单id
	private String name;//菜单名
	private Integer parentId;//父菜单id
	
}
