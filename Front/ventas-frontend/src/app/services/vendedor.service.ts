import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Vendedor } from '../models/vendedor';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VendedorService {
  private API_URL = 'http://localhost:8080/api/vendedores';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Vendedor[]> {
    return this.http.get<Vendedor[]>(this.API_URL);
  }

  getById(id: number): Observable<Vendedor> {
    return this.http.get<Vendedor>(`${this.API_URL}/${id}`);
  }

  create(vendedor: Vendedor): Observable<Vendedor> {
    return this.http.post<Vendedor>(this.API_URL, vendedor);
  }

  update(vendedor: Vendedor): Observable<Vendedor> {
    return this.http.put<Vendedor>(`${this.API_URL}/${vendedor.id}`, vendedor);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }
}
