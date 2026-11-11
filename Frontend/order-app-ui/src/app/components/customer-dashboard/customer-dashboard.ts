import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Product, ProductModel } from '../../services/product';
import { Cart, CartRequest } from '../../services/cart';
import { Auth } from '../../services/auth';
import { Router } from '@angular/router';

@Component({
  imports: [CommonModule,FormsModule],
  selector: 'app-customer-dashboard',
  styleUrl: './customer-dashboard.css',
  templateUrl: './customer-dashboard.html',
})
export class CustomerDashboard implements OnInit {

  products: ProductModel[] = [];
  cartItems: any[] = [];
  quantities: { [productId: number]: number } = {};
  
  // 1. Added arrays for Orders and Invoices
  myOrders: any[] = [];
  myInvoices: any[] = [];

  successMessage = '';
  errorMessage = '';
  userId: number | null = null;

  constructor(
    private productService: Product,
    private cartService: Cart,
    private authService: Auth,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.userId = this.authService.getCurrentUserId();
    this.loadProducts();
    this.loadCart();
    this.loadHistory(); // 2. Load history on page load
  }

  loadProducts() {
    this.productService.getAllProducts().subscribe({
      next: (data) => {
        this.products = data as unknown as ProductModel[];
        this.products.forEach(p => {
          if (p.id) this.quantities[p.id] = 1;
        });
        this.cdr.detectChanges();
      },
      error: (err) => console.error('Error loading products', err)
    });
  }

  loadCart() {
    this.cartService.getAllCarts().subscribe({
      next: (data) => {
        this.cartItems = data.filter(item => item.user?.id === this.userId);
        this.cdr.detectChanges();
      },
      error: (err) => console.error('Error loading cart', err)
    });
  }

  // 3. Added method to fetch Orders and Invoices
  
    loadHistory() {
    this.cartService.getAllOrders().subscribe({
      next: (orders) => {
        this.myOrders = orders.filter(o => o.user?.id === this.userId);
        const myOrderIds = this.myOrders.map(o => o.id);
        this.cdr.detectChanges();

        // Fetch invoices and match using user's Order IDs
        this.cartService.getAllInvoices().subscribe({
          next: (invoices) => {
            this.myInvoices = invoices.filter(inv => myOrderIds.includes(inv.order?.id));
            this.cdr.detectChanges();
          },
          error: (err) => console.error('Error loading invoices', err)
        });
      },
      error: (err) => console.error('Error loading orders', err)
    });
  }
  


  addToCart(product: ProductModel) {
    this.clearMessages();
    if (!this.userId || !product.id) return;

    const qty = this.quantities[product.id] || 1;
    const request: CartRequest = {
      user: { id: this.userId },
      product: { id: product.id },
      quantity: qty
    };

    this.cartService.saveCart(request).subscribe({
      next: () => {
        this.successMessage = `${product.productName} added to cart!`;
        this.loadCart();
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.errorMessage = err.error?.message || 'Failed to add item to cart.';
        this.cdr.detectChanges();
      }
    });
  }

  placeOrder() {
    this.clearMessages();
    if (!this.userId) return;

    this.cartService.createOrder(this.userId).subscribe({
      next: (order: any) => {
        this.successMessage = `Order #${order.id} placed successfully! Invoice generated automatically.`;
        this.cartItems = []; 
        this.loadProducts(); 
        this.loadHistory(); // 4. Refresh orders and invoices after placing order
        this.cdr.detectChanges();
      },
      error: (err: any) => {
        this.errorMessage = err.error?.message || err.error?.error || 'Order placement failed.';
        this.cdr.detectChanges();
      }
    });
  }

  clearMessages() {
    this.successMessage = '';
    this.errorMessage = '';
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}