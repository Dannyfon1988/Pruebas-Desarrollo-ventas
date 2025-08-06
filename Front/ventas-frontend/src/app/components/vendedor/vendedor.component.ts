import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Vendedor } from '../../models/vendedor';
import { VendedorService } from '../../services/vendedor.service';

@Component({
  selector: 'app-vendedor',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './vendedor.component.html',
  styleUrls: ['./vendedor.component.css']
})
export class VendedorComponent {
  private vendedorService: VendedorService = inject(VendedorService);

  vendedores: Vendedor[] = [];
  vendedor: Vendedor = { id: 0, nombre: '', correo: '' };

  constructor() {
    this.obtenerVendedores();
  }

  obtenerVendedores(): void {
    this.vendedorService.getAll().subscribe((data: Vendedor[]) => {
      this.vendedores = data;
    });
  }

  guardarVendedor(): void {
    if (this.vendedor.id === 0) {
      // Clonamos el objeto y eliminamos el campo id
      const nuevoVendedor = { ...this.vendedor };
      delete nuevoVendedor.id;

      this.vendedorService.create(nuevoVendedor).subscribe(() => {
        this.obtenerVendedores();
        this.resetFormulario();
      });
    } else {
      this.vendedorService.update(this.vendedor).subscribe(() => {
        this.obtenerVendedores();
        this.resetFormulario();
      });
    }
  }

  editarVendedor(v: Vendedor): void {
    this.vendedor = { ...v };
  }

  eliminarVendedor(id?: number): void {
  if (id !== undefined) {
    this.vendedorService.delete(id).subscribe(() => {
      this.obtenerVendedores();
    });
  }
}
  resetFormulario(): void {
    this.vendedor = { id: 0, nombre: '', correo: '' };
  }
}
