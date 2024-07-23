import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Restaurant } from '../Models/Restaurant';
import { Booking } from '../Models/Booking';


@Injectable({
  providedIn: 'root'
})
export class RestaurantService {
  private apiUrl = 'http://localhost:8080/api/restaurants/All'; 

  private apiUrl2 = 'http://localhost:8080/api/restaurants/1'

  private apiUrl3= 'http://localhost:8080/api/restaurants/time/2';

  
  constructor(private http: HttpClient) { }

  getRestaurants(): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>(this.apiUrl);
  }


  
  getRestaurantById(id: string): Observable<Restaurant> {
    
    return this.http.get<Restaurant>('http://localhost:8080/api/restaurants/'+id);
    
  }


  getSlots(id: string): Observable<string []> {
    return this.http.get<string []>('http://localhost:8080/api/restaurants/time/'+id);
  }


  getMyBookingById(id: number): Observable<Booking []> {
    return this.http.get<Booking []>('http://localhost:8080/booking/'+id);
  }


  cancelBooking(bookingId: string): Observable<void> {
   // console.log("in cancel service");
    return this.http.post<void>(`http://localhost:8080/booking/cancel/${bookingId}`, {});

}

}
