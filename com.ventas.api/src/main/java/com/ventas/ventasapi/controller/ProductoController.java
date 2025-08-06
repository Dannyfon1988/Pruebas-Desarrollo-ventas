package com.ventas.ventasapi.controller;

import com.ventas.ventasapi.model.Producto;
import com.ventas.ventasapi.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // devuelve el json
@RequestMapping("/api/productos") // rutas del controlador
public class ProductoController {

    @Autowired //inyecta automáticamente una instancia del repositorio

    private ProductoRepository productoRepository; //hacer operaciones con la base de datos sin escribir código SQL


    // Listar todos los productos
    @GetMapping
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    // Crear un nuevo producto
    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // Actualizar un producto por ID
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return productoRepository.save(producto);
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }
}
