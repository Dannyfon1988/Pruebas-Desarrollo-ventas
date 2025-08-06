import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Producto } from '../../models/producto';
import { ProductoService } from '../../services/producto.service';

@Component({
  selector: 'app-producto',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent {
  private productoService: ProductoService = inject(ProductoService);

  productos: Producto[] = [];
  producto: Producto = { nombre: '', precio: 0 };

  constructor() {
    this.obtenerProductos();
  }

  obtenerProductos(): void {
    this.productoService.getAll().subscribe((data: Producto[]) => {
      this.productos = data;
    });
  }

  guardarProducto(): void {
    if (!this.producto.id || this.producto.id === 0) {
      const nuevoProducto = { ...this.producto };
      delete nuevoProducto.id;

      this.productoService.create(nuevoProducto).subscribe(() => {
        this.obtenerProductos();
        this.resetFormulario();
      });
    } else {
      this.productoService.update(this.producto).subscribe(() => {
        this.obtenerProductos();
        this.resetFormulario();
      });
    }
  }

  editarProducto(p: Producto): void {
    this.producto = { ...p };
  }

  eliminarProducto(id?: number): void {
    if (id !== undefined) {
      this.productoService.delete(id).subscribe(() => {
        this.obtenerProductos();
      });
    }
  }

  resetFormulario(): void {
    this.producto = { nombre: '', precio: 0 };
  }
}
