package com.ventas.ventasapi.controller;

import com.ventas.ventasapi.model.Vendedor;
import com.ventas.ventasapi.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    @Autowired // inyecta instancia VendedorRepository
    private VendedorRepository vendedorRepository;

    // Listar todos los vendedores
    @GetMapping
    public List<Vendedor> listar() {
        return vendedorRepository.findAll();
    }

    // Crear un nuevo vendedor
    @PostMapping
    public Vendedor crear(@RequestBody Vendedor vendedor) {
        vendedor.setId(null);
        return vendedorRepository.save(vendedor);
    }

    // Actualizar un vendedor por ID
    @PutMapping("/{id}")
    public Vendedor actualizar(@PathVariable Long id, @RequestBody Vendedor vendedor) {
        vendedor.setId(id);
        return vendedorRepository.save(vendedor);
    }

    // Eliminar un vendedor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            vendedorRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    "No se puede eliminar el vendedor con ID " + id + " porque tiene ventas asociadas."
            );
        }
    }
}
