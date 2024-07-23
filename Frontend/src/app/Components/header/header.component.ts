import { Component, OnInit } from '@angular/core';
import { Restaurant } from 'src/app/Models/Restaurant';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import * as moment from 'moment';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  
})
export class HeaderComponent implements OnInit {
  
  restaurants: Restaurant[] = [];
  token: string = '';
  filteredRestaurants: Restaurant[] = [];
  searchQuery: string = '';


  constructor(private restaurantService: RestaurantService,private http: HttpClient, private router: Router,private authService: AuthService) { }


  ngOnInit(): void {
  
  }

  getRestaurants(): void {
    this.restaurantService.getRestaurants().subscribe(
      (data) => this.restaurants = data,
      (error) => console.error(error)
    );
}


onSearch(): void {
  this.restaurantService.getRestaurants().subscribe(
    (data) =>  this.filteredRestaurants = data.filter(restaurant =>
      restaurant.name.toLowerCase().includes(this.searchQuery.toLowerCase())
    ),
    (error) => console.error(error)
  );
  console.log(this.filteredRestaurants);
}

logout(): void {
  this.authService.logout();
  this.router.navigate(['/login']);
}
}




