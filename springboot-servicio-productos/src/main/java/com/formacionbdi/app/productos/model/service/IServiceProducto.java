package com.formacionbdi.app.productos.model.service;

import java.util.List;

import com.formacionbdi.app.productos.model.entity.Producto;

public interface IServiceProducto {
	List<Producto> listar();
	Producto detalle(Long id);
}
