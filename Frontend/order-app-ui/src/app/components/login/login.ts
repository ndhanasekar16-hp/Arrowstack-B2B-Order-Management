import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Auth } from '../../services/auth';
import { Router } from '@angular/router';

@Component({
  imports: [CommonModule, FormsModule],
  selector: 'app-login',
  styleUrl: './login.css',
  templateUrl: './login.html',
})
export class Login {

  username = '';
  password = '';
  errorMessage = '';

  constructor(private authService: Auth, private router: Router) {}

  onLogin() {
    this.errorMessage = '';
    
    this.authService.login(this.username, this.password).subscribe({
      next: (user) => {

         const roleName = user.role?.roleName?.toUpperCase() || 'CUSTOMER';


 if (roleName === 'ADMIN') {
          this.router.navigate(['/admin']);
        } else {
          this.router.navigate(['/customer']);
        }
      },
      error: (err) => {
        this.errorMessage = 'Invalid username or password. Please try again.';
        console.error(err);
      }
    });


        }
      }
