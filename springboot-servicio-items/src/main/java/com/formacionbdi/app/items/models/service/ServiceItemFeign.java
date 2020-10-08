package com.formacionbdi.app.items.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionbdi.app.items.clientes.ProductoClienteRest;
import com.formacionbdi.app.items.models.Item;

@Service("serviceFeign")
public class ServiceItemFeign implements IServiceItem {

	@Autowired
	private ProductoClienteRest productoRest;
	
	@Override
	public List<Item> listar() {
		System.out.println("serviceFeign");
		return productoRest.listar().stream().map(p -> new Item(p, 5)).collect(Collectors.toList());
	}

	@Override
	public Item detalle(Long id, Integer cantidad) {
		return new Item(productoRest.detalle(id), cantidad);
	}

}
