package com.ventas.ventasapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // venta está asociada a un producto
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne // venta está asociada a un vendedor
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    private LocalDate fecha;
    private Integer cantidad;
    private Double total;
}
