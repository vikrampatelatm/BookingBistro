import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as moment from 'moment';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { Route } from '@angular/router';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
 


@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.scss']
})
export class BookingComponent implements OnInit {
  dates: string[] = [];
  selectedDate: string = '';
  guests: number[] = [];
  selectedGuests: number = 2;
  slots: string[] = [];
  availableSlots: string[] = [];
  selectedSlot: string = '';

  bookingObj: any = {
    "userId":"",
    "restaurantId": "",
    "bookingDate": "",
    "numberOfGuests": "",
    "timeSlot":""
  }

  constructor(private http: HttpClient,private restaurantService: RestaurantService,private route: ActivatedRoute,private router: Router,private authService: AuthService) {
    
  }
  
  getSlotById(id: string): void {
    this.restaurantService.getSlots(id).subscribe(
      (data) => {
        this.slots=data
       //console.log("slot"+data);
       //console.log("in arr"+this.slots);
      },
      (error) => console.error(error)
    );
  }

  ngOnInit(): void {
    this.generateDateOptions();
    this.generateGuestOptions();
    this.getSlotById(this.route.snapshot.params["id"]);
    this.selectedDate = moment().format('YYYY-MM-DD');
    this.onDateChange();
  }

  generateDateOptions(): void {
    const startDate = moment();
    const endDate = moment().add(2, 'months');

    const dateOptions = [];
    let currentDate = startDate;

    while (currentDate <= endDate) {
      dateOptions.push(currentDate.format('YYYY-MM-DD'));
      currentDate = currentDate.add(1, 'day');
    }

    this.dates = dateOptions;
  }

  generateGuestOptions(): void {
    this.guests = Array.from({ length: 20 }, (_, i) => i + 1);
  }

  onDateChange(): void {
  //  this.getSlotById("1");
  }


  filterSlots(): void {
    const currentTime = moment().format('HH:mm');
    const isToday = this.selectedDate === moment().format('YYYY-MM-DD');

    this.availableSlots = this.slots.filter(slot => {
      if (isToday) {
        return slot > currentTime;
      }
      return true;
    });

    

    if (this.availableSlots.length > 0) {
      this.selectedSlot = this.availableSlots[0];
    }
  }

  onGuestsChange(): void {
    this.onDateChange();
  }

  onSlotSelect(slot: string): void {
    this.selectedSlot = slot;
  }

  onBook(): void {
    if (this.selectedDate && this.selectedGuests && this.selectedSlot) {
      this.bookingObj.userId=this.authService.getUserId();
      console.log(this.bookingObj.userId);
      this.bookingObj.restaurantId= this.route.snapshot.params["id"];
      this.bookingObj.bookingDate=this.selectedDate;
      this.bookingObj.timeSlot=this.selectedSlot;
      this.bookingObj.numberOfGuests=this.selectedGuests;
      console.log('Selected Date:', this.selectedDate);
      console.log('Number of Guests:', this.selectedGuests);
      console.log('Selected Slot:', this.selectedSlot);
      this.http.post('http://localhost:8080/booking/create', this.bookingObj)
         .subscribe(
           response => {
             console.log('Booking successful:', response);
           },
           error => {
             console.error('Booking failed:', error);
             
           }
         );
      this.router.navigate(['my-booking']);
    } else {
      console.error('Date, guests, or slot not selected');
    }
  }
}




