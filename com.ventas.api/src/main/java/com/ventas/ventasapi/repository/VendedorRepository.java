package com.ventas.ventasapi.repository;

import com.ventas.ventasapi.model.Vendedor; // hereda JpaRepository y sus atributos
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}
