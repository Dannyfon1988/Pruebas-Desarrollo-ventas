import { Producto } from "./producto";
import { Vendedor } from "./vendedor";


export interface Venta {
  id: number;
  producto: Producto;
  vendedor: Vendedor;
  cantidad: number;
  fecha: string;
  total: number;
}
