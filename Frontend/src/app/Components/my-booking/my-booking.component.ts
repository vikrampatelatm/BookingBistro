import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { AuthService } from 'src/app/services/auth.service';
import * as moment from 'moment';
import { MatTableDataSource } from '@angular/material/table';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-my-booking',
  templateUrl: './my-booking.component.html',
  styleUrls: ['./my-booking.component.scss']
})
export class MyBookingComponent implements OnInit {
  bookings: any[] = [];
  displayedColumns: string[] = ['restaurantName', 'dateAndTime', 'slotTime', 'guestCount', 'bookingStatus', 'daysRemaining', 'actions'];
  dataSource = new MatTableDataSource<any>(this.bookings);
  pageSize = 10;
  pageIndex = 0;
  totalBookings = 0;

  bookingObj: any = {
    "userId": ""
  };

  constructor(private restaurantService: RestaurantService, private authService: AuthService, private http: HttpClient, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.getBookings();
  }

  isCurrentBooking(dateAndTime: string): boolean {
    return moment(dateAndTime).isSameOrAfter(moment(), 'day');
  }

  calculateDaysRemaining(dateAndTime: string): number {
    const bookingDate = moment(dateAndTime);
    return bookingDate.diff(moment(), 'days');
  }

  extractSlotTime(time: string): string {
    return moment(time, 'HH:mm').format('hh:mm A');
  }

  getBookings(): void {
    this.bookingObj.userId = this.authService.getUserId();
    console.log("in my-bookings");
    this.restaurantService.getMyBookingById(this.bookingObj.userId).subscribe(
      (data: any[]) => {
       
        this.bookings = data.reverse().map(booking => ({
          ...booking,
          daysRemaining: this.calculateDaysRemaining(booking.date),
          slotTime: this.extractSlotTime(booking.time)
        }));
        this.dataSource.data = this.bookings;
        this.totalBookings = this.bookings.length;
        this.updatePageData();
        console.log(this.bookings);
      },
      (error) => console.error(error)
    );
  }

  updatePageData(): void {
    const startIndex = this.pageIndex * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.dataSource.data = this.bookings.slice(startIndex, endIndex);
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.updatePageData();
  }

  cancelBooking(bookingId: string): void {
    this.restaurantService.cancelBooking(bookingId).subscribe(
      () => {
        // Update the local state after cancellation
        this.bookings = this.bookings.map(booking =>
          booking.id === bookingId ? { ...booking, bookingStatus: 'cancelled' } : booking
        );
        this.dataSource.data = this.bookings;
        console.log('Booking cancelled successfully');
      },
      (error) => console.error('Error cancelling booking', error)
    );
  }
}
