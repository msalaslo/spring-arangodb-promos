package com.msl.data.arangodb.promo.loader;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Marca;
import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.entity.ProductoMarca;
import com.msl.data.arangodb.promo.repository.MarcaRepository;
import com.msl.data.arangodb.promo.repository.ProductoMarcaRepository;
import com.msl.data.arangodb.promo.repository.ProductoRepository;
import com.msl.data.arangodb.promo.util.Util;


@Component
public class ProductoMarcaRelationsLoader {
	@Autowired
	private MarcaRepository marcaRepo;
	
	@Autowired
	private ProductoRepository productoRepo;
	
	@Autowired
	private ProductoMarcaRepository marcaProductoRepo;

	public void fullLoad() {
		// first create some relations for the marcas and productos
		Iterable<Marca> marcas = marcaRepo.findAll();
		Iterable<Producto> productos = productoRepo.findAll();
		for (Producto producto : productos) {
			for (Marca marca : marcas) {
				marcaProductoRepo.save(new ProductoMarca(producto, marca));
			}	
		}
		productos = productoRepo.findAll();
		for (Producto productoConMarca : productos) {
			Collection<Marca> marcasProd = productoConMarca.getMarcas();
			System.out.println("producto:" + productoConMarca + "marca:" + marcasProd);
		}
	}
	
	public void oneMarcaLoad() {
		// first create some relations for the marcas and productos
		Iterable<Marca> marcas = marcaRepo.findAll();
		Iterable<Producto> productos = productoRepo.findAll();
		Marca marca = marcas.iterator().next();
		for (Producto producto : productos) {		
				marcaProductoRepo.save(new ProductoMarca(producto, marca));
		}	
				
		productos = productoRepo.findAll();
		for (Producto productoConMarca : productos) {
			Collection<Marca> marcasProd = productoConMarca.getMarcas();
			System.out.println("producto:" + productoConMarca + "marca:" + marcasProd);
		}
	}
	
	public void shareMarcasLoad() {
		// first create some relations for the marcas and productos
		Iterable<Marca> marcas = marcaRepo.findAll();
		Iterable<Producto> productos = productoRepo.findAll();
		Iterator<Marca> iteMarcas = marcas.iterator();
		int numProductos = Util.getSize(productos);
		int numMarcas = Util.getSize(marcas);
		int section = numProductos/numMarcas;
		int cont = 0;
		Marca marca = iteMarcas.next();
		for (Producto producto : productos) {			
			//Vamos avanzando la marcas a un conjunto equitativo de productos
			if(cont == section && numProductos > numMarcas && iteMarcas.hasNext()) {
				marca = iteMarcas.next();
				cont = 0;
			}else {
				cont++;
			}			
			System.out.println("Asociando el producto " + producto + " a la marca " + marca );
			marcaProductoRepo.save(new ProductoMarca(producto, marca));
		}	
				
		productos = productoRepo.findAll();
		for (Producto productoConMarca : productos) {
			Collection<Marca> marcasProd = productoConMarca.getMarcas();
			System.out.println("producto:" + productoConMarca + "marca:" + marcasProd);
		}
	}
	
	public void deleteAll() {
		marcaProductoRepo.deleteAll();
	}
}
