import { HttpClient } from '@angular/common/http';
import { Injectable, Service } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Auth } from './auth';





export interface CartRequest {
  user: { id: number };
  product: { id: number };
  quantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class Cart {


     private cartUrl = 'http://localhost:8080/customer/carts';
  private orderUrl = 'http://localhost:8080/customer/orders/create';

constructor(private http: HttpClient, private authService: Auth) {}

  // Add item to cart
  saveCart(cart: CartRequest): Observable<any> {
    return this.http.post<any>(this.cartUrl, cart, {
      headers: this.authService.getAuthHeaders()
    });
  }

  // Get user's cart items
  getAllCarts(): Observable<any[]> {
    return this.http.get<any[]>(`${this.cartUrl}/list`, {
      headers: this.authService.getAuthHeaders()
    });
  }

  // Place order and trigger auto-invoice generation
  createOrder(userId: number): Observable<any> {
    return this.http.post<any>(`${this.orderUrl}/${userId}`, {}, {
      headers: this.authService.getAuthHeaders()
    });
  }

   // Get all orders
  getAllOrders(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/customer/orders/list', {
      headers: this.authService.getAuthHeaders()
    });
  }

  // Get all invoices
  getAllInvoices(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/invoices/list', {
      headers: this.authService.getAuthHeaders()
    });
  }





}
