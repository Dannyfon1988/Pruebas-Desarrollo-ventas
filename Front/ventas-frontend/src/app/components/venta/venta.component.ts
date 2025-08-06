import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Venta } from '../../models/venta';
import { VentaService } from '../../services/venta.service';
import { ProductoService } from '../../services/producto.service';
import { VendedorService } from '../../services/vendedor.service';
import { Producto } from '../../models/producto';
import { Vendedor } from '../../models/vendedor';
import { VentaDTO } from '../../models/venta-dto';

@Component({
  selector: 'app-venta',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css']
})
export class VentaComponent {
  private ventaService = inject(VentaService);
  private productoService = inject(ProductoService);
  private vendedorService = inject(VendedorService);

  ventas: Venta[] = [];
  productos: Producto[] = [];
  vendedores: Vendedor[] = [];

  venta: Venta = {
  id: 0,
  producto: { id: 0, nombre: '', precio: 0 },
  vendedor: { id: 0, nombre: '', correo: '' },
  cantidad: 1,
  fecha: new Date().toISOString().split('T')[0],
  total: 0
};

  constructor() {
    this.obtenerVentas();
    this.obtenerProductos();
    this.obtenerVendedores();
  }

  obtenerVentas(): void {
    this.ventaService.getAll().subscribe((data: Venta[]) => {
      this.ventas = data;
    });
  }

  obtenerProductos(): void {
    this.productoService.getAll().subscribe((data: Producto[]) => {
      this.productos = data;
    });
  }

  obtenerVendedores(): void {
    this.vendedorService.getAll().subscribe((data: Vendedor[]) => {
      this.vendedores = data;
    });
  }

  guardarVenta(): void {
  const dto: VentaDTO = {
  productoId: this.venta.producto?.id ?? 0,
  vendedorId: this.venta.vendedor?.id ?? 0,
  cantidad: this.venta.cantidad,
  fecha: this.venta.fecha
  };

  if (this.venta.id === 0) {
    this.ventaService.create(dto).subscribe({
  next: () => {
    this.obtenerVentas();
    this.resetFormulario();
  },
  error: err => {
    console.error('Error al crear venta:', err);
    alert('Error al guardar la venta');
  }
});
  } else {
    this.ventaService.update(this.venta.id, dto).subscribe(() => {
      this.obtenerVentas();
      this.resetFormulario();
    });
  }
  console.log('DTO ENVIADO:', dto);
}

 editarVenta(v: Venta): void {
  this.venta = {
    id: v.id,
    producto: this.productos.find(p => p.id === v.producto.id)!,
    vendedor: this.vendedores.find(vend => vend.id === v.vendedor.id)!,
    cantidad: v.cantidad,
    fecha: v.fecha,
    total: v.total
  };
}

  eliminarVenta(id: number): void {
    this.ventaService.delete(id).subscribe(() => {
      this.obtenerVentas();
    });
  }

  resetFormulario(): void {
  this.venta = {
    id: 0,
    producto: { id: 0, nombre: '', precio: 0 },
    vendedor: { id: 0, nombre: '', correo: '' },
    cantidad: 1,
    fecha: new Date().toISOString().split('T')[0],
    total: 0
  };
  }
}

