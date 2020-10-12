package com.formacionbdi.app.items.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.app.items.models.Item;
import com.formacionbdi.app.items.models.Producto;
import com.formacionbdi.app.items.models.service.IServiceItem;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class ItemsController {
	
	private static Logger log = org.slf4j.LoggerFactory.getLogger(ItemsController.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	@Qualifier("serviceFeign")
	private IServiceItem serviceItem;
	
	@Value("${configuracion.texto}")
	private String texto;
	
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
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){
		
		log.info(texto);
		Map<String, String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("puerto", puerto);
		
		if(env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}
		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
}
