import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Restaurant } from 'src/app/Models/Restaurant';
import { RestaurantService } from '../../services/restaurant.service';
import * as moment from 'moment';



@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.scss']
})
export class RestaurantDetailsComponent implements OnInit {

  constructor(private router: Router,private restaurantService: RestaurantService, private http: HttpClient, private route: ActivatedRoute,) {
  }
  restaurant!:Restaurant;
  
  ngOnInit(): void {
    this.getRestaurantById(this.route.snapshot.params["id"]);
  }

  getRestaurantById(id: string): void {
    this.restaurantService.getRestaurantById(id).subscribe(
      (data: Restaurant) => {
        this.restaurant=data
        console.log(data);
      },
      (error) => console.error(error)
    );
  }

}
