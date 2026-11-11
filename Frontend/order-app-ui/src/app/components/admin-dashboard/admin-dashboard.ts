import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Product, ProductModel } from '../../services/product';
import { Auth } from '../../services/auth';

@Component({
  imports: [CommonModule,FormsModule],
  selector: 'app-admin-dashboard',
  styleUrl: './admin-dashboard.css',
  templateUrl: './admin-dashboard.html',
})

export class AdminDashboard implements OnInit  {

 products: ProductModel[] = [];
  newProduct: ProductModel = {
    productName: '', 
    price: 0, 
    stockQuantity: 0, 
    description: ''
  };
  message = '';

  constructor(
    private productService: Product,
    private authService: Auth,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.getAllProducts().subscribe({
      next: (data) => this.products = data as unknown as ProductModel[],
      error: (err) => console.error('Failed to load products', err)
    });
  }

  onAddProduct() {
    this.productService.addProduct(this.newProduct as unknown as Product).subscribe({
      next: (saved) => {
        this.message = 'Product added successfully!';
        this.products.push(saved as unknown as ProductModel);
        this.newProduct = { productName: '', price: 0, stockQuantity: 0, description: '' };
      },
      error: (err) => {
        this.message = 'Error adding product!';
        console.error(err);
      }
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
  
  





}