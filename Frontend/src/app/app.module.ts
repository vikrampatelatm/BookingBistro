import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './Components/register/register.component';
import { LoginComponent } from './Components/login/login.component';
import { HomeComponent } from './Components/home/home.component';
import { RestaurantListComponent } from './Components/restaurant-list/restaurant-list.component';
import { AuthInterceptor } from './AuthInterceptor';
import { FooterComponent } from './Components/footer/footer.component';

import { HeaderComponent } from './Components/header/header.component';

import { LocationComponent } from './Components/location/location.component';
import { BannerComponent } from './Components/banner/banner.component';


//angualr matrial 

import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { RestaurantDetailsComponent } from './Components/restaurant-details/restaurant-details.component';
import { BookingComponent } from './Components/booking/booking.component';
import { MyBookingComponent } from './Components/my-booking/my-booking.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    RestaurantListComponent,
    FooterComponent,
    HeaderComponent,
    LocationComponent,
    BannerComponent,
    RestaurantDetailsComponent,
    BookingComponent,
    MyBookingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatSlideToggleModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatPaginatorModule,
    MatTableModule,
    MatIconModule ,
    MatButtonModule

  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
