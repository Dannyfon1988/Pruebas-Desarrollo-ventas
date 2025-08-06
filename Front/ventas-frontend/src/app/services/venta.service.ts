import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Venta } from '../models/venta';
import { Observable } from 'rxjs';
import { VentaDTO } from '../models/venta-dto';

@Injectable({
  providedIn: 'root'
})
export class VentaService {
  private API_URL = 'http://localhost:8080/api/ventas';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Venta[]> {
    return this.http.get<Venta[]>(this.API_URL);
  }

  getById(id: number): Observable<Venta> {
    return this.http.get<Venta>(`${this.API_URL}/${id}`);
  }

  create(dto: VentaDTO): Observable<Venta> {
    return this.http.post<Venta>(this.API_URL, dto);
  }

  update(id: number, dto: VentaDTO): Observable<Venta> {
    return this.http.put<Venta>(`${this.API_URL}/${id}`, dto);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }
}
