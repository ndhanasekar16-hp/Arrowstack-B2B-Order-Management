import { HttpClient } from '@angular/common/http';
import { Injectable, Service } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Auth } from './auth';

export interface ProductModel {
  id?: number;
  productName: string;
  price: number;
  stockQuantity: number;
  description?: string;
}

@Injectable({
  providedIn: 'root'
})

export class Product {

 private apiUrl = 'http://localhost:8080/admin/products';

 constructor(private http: HttpClient, private authService: Auth) {}

  // Get all products
  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}/list`, {
      headers: this.authService.getAuthHeaders()
    });
  }

  // Add a new product (Admin only)
  addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.apiUrl, product, {
      headers: this.authService.getAuthHeaders()
    });
  }

}
