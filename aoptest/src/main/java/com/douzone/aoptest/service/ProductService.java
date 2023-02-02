package com.douzone.aoptest.service;

import org.springframework.stereotype.Service;

import com.douzone.aoptest.vo.ProductVo;

@Service
public class ProductService {
	public ProductVo find(String name) {
		System.out.println("[ProductSerice] finding...");
		return new ProductVo(name);
	}
}
