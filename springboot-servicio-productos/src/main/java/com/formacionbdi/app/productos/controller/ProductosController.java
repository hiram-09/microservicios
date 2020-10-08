package com.formacionbdi.app.productos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.app.productos.model.entity.Producto;
import com.formacionbdi.app.productos.model.service.IServiceProducto;

@RestController
public class ProductosController {
	
	@Value("${server.port}")
	private Integer port;
	@Autowired
	private IServiceProducto serviceProducto;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return serviceProducto.listar().stream().map(producto -> {
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/detalle/{id}")
	public Producto detalle(@PathVariable Long id) throws Exception {
		Producto producto = serviceProducto.detalle(id);
		producto.setPort(port);
		
		//Prueba de Time out
		//Thread.sleep(2000L);
		
		return producto;
	}
}
