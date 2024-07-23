import { Component, OnInit } from '@angular/core';
import { RestaurantService } from '../../services/restaurant.service';
import { Restaurant } from 'src/app/Models/Restaurant';
import { AuthService } from '../../services/auth.service';
import { PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';


@Component({
  selector: 'app-restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['./restaurant-list.component.scss']
})
export class RestaurantListComponent implements OnInit {
  restaurants: Restaurant[] = [];
  filteredRestaurants: Restaurant[] = [];
  paginatedRestaurants: Restaurant[] = [];
  token: string = '';
  searchQuery: string = '';

  length = 0;
  pageSize = 6;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  pageIndex = 0;

  constructor(private restaurantService: RestaurantService, private authService: AuthService,private router: Router) { }

  ngOnInit(): void {
    const token = this.authService.getToken();
    if (token) {
      this.token = token;
      this.getRestaurants();
    } else {
      console.error('No JWT token found');
    }
  }

  getRestaurants(): void {
    this.restaurantService.getRestaurants().subscribe(
      (data: Restaurant[]) => {
        this.restaurants = data;
        this.filteredRestaurants = data; 
        this.length = data.length; 
        this.paginateRestaurants(); 
      },
      (error) => console.error(error)
    );
  }

  onSearch(): void {
    if (this.searchQuery.trim() !== '') {
      this.filteredRestaurants = this.restaurants.filter(restaurant =>
        restaurant.name.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    } else {
      this.filteredRestaurants = this.restaurants; 
    }
    this.length = this.filteredRestaurants.length; 
    this.paginateRestaurants();
  }

  paginateRestaurants(event?: PageEvent): void {
    if (event) {
      this.pageIndex = event.pageIndex;
      this.pageSize = event.pageSize;
    }
    const start = this.pageIndex * this.pageSize;
    const end = start + this.pageSize;
    this.paginatedRestaurants = this.filteredRestaurants.slice(start, end);
  }
  handleClick(id:number)
  { 
    let url: string = "/restaurant_details/"+id;
    this.router.navigate([url]);
  }
  myBooking(): void {
    this.router.navigate(['/my-booking']);
  }
}

