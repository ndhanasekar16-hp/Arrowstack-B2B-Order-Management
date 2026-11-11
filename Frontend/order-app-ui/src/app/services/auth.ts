import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core'; 
import { Observable, tap } from 'rxjs';

interface User {
  role: any;
  id: number;
}


@Injectable({
  providedIn: 'root'
})
export class Auth {

  private baseUrl = 'http://localhost:8080/users'; 

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<User> {
    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa(`${username}:${password}`)
    });

    return this.http.get<User>(`${this.baseUrl}/username/${username}`, { headers }).pipe(
      tap(user => {
        if (user) {
          localStorage.setItem('currentUser', JSON.stringify(user));
          localStorage.setItem('authCredentials', btoa(`${username}:${password}`));
        }
      })
    );
  }

  getAuthHeaders(): HttpHeaders {
    const credentials = localStorage.getItem('authCredentials');
    return new HttpHeaders({
      'Authorization': credentials ? `Basic ${credentials}` : '',
      'Content-Type': 'application/json'
    });
  }

  getCurrentUserId(): number | null {
    const userJson = localStorage.getItem('currentUser');
    if (userJson) {
      const user = JSON.parse(userJson) as User;
      return user.id;
    }
    return null;
  }

  logout() {
    localStorage.clear();
  }
}
