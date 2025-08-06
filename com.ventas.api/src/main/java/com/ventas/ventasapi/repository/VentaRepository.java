package com.ventas.ventasapi.repository;

import com.ventas.ventasapi.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}