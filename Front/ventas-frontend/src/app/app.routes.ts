import { Routes } from '@angular/router';
import { ProductoComponent } from './components/producto/producto.component';
import { VendedorComponent } from './components/vendedor/vendedor.component';
import { VentaComponent } from './components/venta/venta.component';

export const routes: Routes = [
  { path: '', redirectTo: 'productos', pathMatch: 'full' },
  { path: 'productos', component: ProductoComponent },
  { path: 'vendedores', component: VendedorComponent },
  { path: 'ventas', component: VentaComponent }
];

