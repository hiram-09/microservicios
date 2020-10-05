package com.formacionbdi.app.items.models.service;

import java.util.List;

import com.formacionbdi.app.items.models.Item;

public interface IServiceItem {
	List<Item> listar();
	Item detalle(Long id, Integer cantidad);
}
