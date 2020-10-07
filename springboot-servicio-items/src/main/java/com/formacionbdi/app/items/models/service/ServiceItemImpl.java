package com.formacionbdi.app.items.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacionbdi.app.items.models.Item;
import com.formacionbdi.app.items.models.Producto;

@Service("serviceRest")
public class ServiceItemImpl implements IServiceItem {
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Item> listar() {
		System.out.println("restTemplate");
		List<Producto> productos = Arrays.asList(restTemplate.getForObject("http://localhost:8080/listar", Producto[].class));
		return productos.stream().map(p -> new Item(p, 5)).collect(Collectors.toList());
	}

	@Override
	public Item detalle(Long id, Integer cantidad) {
		HashMap<String, String> params = new HashMap<>();
		params.put("id", id.toString());
		Producto producto = restTemplate.getForObject("http://localhost:8080/detalle/{id}", Producto.class, params);
		return new Item(producto, 2);
	}

}
