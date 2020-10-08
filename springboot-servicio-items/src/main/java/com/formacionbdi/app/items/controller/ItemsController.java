package com.formacionbdi.app.items.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.app.items.models.Item;
import com.formacionbdi.app.items.models.Producto;
import com.formacionbdi.app.items.models.service.IServiceItem;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemsController {
	
	@Autowired
	@Qualifier("serviceFeign")
	private IServiceItem serviceItem;
	
	@GetMapping("/listar")
	public List<Item> listar(){
		return serviceItem.listar();
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/detalle/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return serviceItem.detalle(id, cantidad);
	}
	
	public Item metodoAlternativo(Long id, Integer cantidad) {
		Producto producto = new Producto();
		producto.setId(id);
		producto.setCreateAt(new Date());
		producto.setNombre("Camara Sony");
		producto.setPrecio(500.00);
		Item item = new Item(producto, cantidad);
		
		return item;
		
	}
}
