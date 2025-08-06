package com.ventas.ventasapi.repository;

import com.ventas.ventasapi.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

