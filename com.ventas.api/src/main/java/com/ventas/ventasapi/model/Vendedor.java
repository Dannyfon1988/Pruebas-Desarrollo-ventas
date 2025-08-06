package com.ventas.ventasapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity //
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que id es autoincremental
    private Long id;

    private String nombre;
    private String correo;
}
