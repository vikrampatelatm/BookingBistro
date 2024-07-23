import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private tokenKey = 'auth-token';
  private userId = 'user-id';

  constructor(private router: Router) { }

  
  logout(): void {
    
    localStorage.removeItem('auth-token');
    localStorage.removeItem('userId');

  }


  setToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
    
    //console.log("set"+localStorage.getItem(this.tokenKey));
  }

  setUserId(userId: number): void {
    localStorage.setItem('userId', userId.toString());
    console.log(userId);
  }

  getUserId():number | null{
    const userIdString = localStorage.getItem('userId');
    return userIdString ? parseInt(userIdString, 10) : null;
  }

  getToken(): string | null {
    //console.log("get"+localStorage.getItem(this.tokenKey));
    return localStorage.getItem(this.tokenKey);
  }

  removeToken(): void {
    localStorage.removeItem(this.tokenKey);
  }
}

