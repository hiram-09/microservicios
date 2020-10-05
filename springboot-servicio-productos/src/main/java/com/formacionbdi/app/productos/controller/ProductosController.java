package com.formacionbdi.app.productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.app.productos.model.entity.Producto;
import com.formacionbdi.app.productos.model.service.IServiceProducto;

@RestController
public class ProductosController {
	
	@Autowired
	private IServiceProducto serviceProducto;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return serviceProducto.listar();
	}
	
	@GetMapping("/detalle/{id}")
	public Producto detalle(@PathVariable Long id) {
		return serviceProducto.detalle(id);
	}
}
