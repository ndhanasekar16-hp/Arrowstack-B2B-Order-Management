import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { CustomerDashboard } from './components/customer-dashboard/customer-dashboard';
import { AdminDashboard } from './components/admin-dashboard/admin-dashboard';
export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component: Login },
    { path: 'customer', component: CustomerDashboard },
    { path: 'admin', component: AdminDashboard },
    { path: '**', redirectTo: 'login' }
];
