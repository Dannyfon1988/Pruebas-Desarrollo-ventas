package com.ventas.ventasapi.controller;

import com.ventas.ventasapi.model.Venta;
import com.ventas.ventasapi.model.VentaDTO;
import com.ventas.ventasapi.repository.ProductoRepository;
import com.ventas.ventasapi.repository.VendedorRepository;
import com.ventas.ventasapi.repository.VentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController // Indica que esta clase manejará peticiones REST y devolverá JSON.
@RequestMapping("/api/ventas") // Ruta base para todas las peticiones
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    // GET /api/ventas - Listar todas las ventas
    @GetMapping
    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    // GET /api/ventas/{id} - Obtener venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerPorId(@PathVariable Long id) {
        Optional<Venta> venta = ventaRepository.findById(id);
        if (venta.isPresent()) {
            return ResponseEntity.ok(venta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/ventas - Crear nueva venta
    @PostMapping
    public ResponseEntity<Venta> crear(@RequestBody VentaDTO dto) {

        if (dto.getProductoId() == null || dto.getVendedorId() == null) {
            System.out.println("Producto ID: " + dto.getProductoId());
            System.out.println("Vendedor ID: " + dto.getVendedorId());
            return ResponseEntity.badRequest().body(null);
        }
        var productoOpt = productoRepository.findById(dto.getProductoId());
        var vendedorOpt = vendedorRepository.findById(dto.getVendedorId());

        if (productoOpt.isEmpty() || vendedorOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var producto = productoOpt.get();
        var vendedor = vendedorOpt.get();

        // Validar que el producto tenga un precio
        if (producto.getPrecio() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        double total = producto.getPrecio() * dto.getCantidad();

        Venta venta = Venta.builder()
                .producto(producto)
                .vendedor(vendedor)
                .cantidad(dto.getCantidad())
                .fecha(dto.getFecha() != null ? dto.getFecha() : LocalDate.now())
                .total(total)
                .build();

        return ResponseEntity.ok(ventaRepository.save(venta));
    }

    // PUT /api/ventas/{id} - Actualizar una venta
    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(@PathVariable Long id, @RequestBody VentaDTO dto) {
        if (!ventaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        var productoOpt = productoRepository.findById(dto.getProductoId());
        var vendedorOpt = vendedorRepository.findById(dto.getVendedorId());

        if (productoOpt.isEmpty() || vendedorOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var producto = productoOpt.get();
        var vendedor = vendedorOpt.get();

        Venta venta = Venta.builder()
                .id(id)
                .producto(producto)
                .vendedor(vendedor)
                .cantidad(dto.getCantidad())
                .fecha(dto.getFecha() != null ? dto.getFecha() : LocalDate.now())
                .total(producto.getPrecio() * dto.getCantidad())
                .build();

        return ResponseEntity.ok(ventaRepository.save(venta));
    }



    // DELETE /api/ventas/{id} - Eliminar una venta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!ventaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        ventaRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 sin cuerpo
    }
}
