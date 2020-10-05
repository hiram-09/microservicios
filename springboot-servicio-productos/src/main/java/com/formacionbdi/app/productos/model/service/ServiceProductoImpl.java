package com.formacionbdi.app.productos.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionbdi.app.productos.model.dao.ProductoDao;
import com.formacionbdi.app.productos.model.entity.Producto;

@Service
public class ServiceProductoImpl implements IServiceProducto{
	
	@Autowired
	private ProductoDao productoDao;

	public List<Producto> listar() {
		return (List<Producto>) productoDao.findAll();
	}

	public Producto detalle(Long id) {
		return productoDao.findById(id).orElse(null);
	}

}
