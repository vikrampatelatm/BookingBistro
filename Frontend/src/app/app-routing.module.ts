import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './Components/register/register.component';
import { LoginComponent } from './Components/login/login.component';
import { HomeComponent } from './Components/home/home.component';
import { RestaurantDetailsComponent } from './Components/restaurant-details/restaurant-details.component';
import { MyBookingComponent } from './Components/my-booking/my-booking.component';

const routes: Routes = [
  {path:'', component: RegisterComponent },
  {path:'login', component:LoginComponent},
  {path:'home', component:HomeComponent},
  {path: 'restaurant_details/:id',component: RestaurantDetailsComponent},
  {path:'my-booking',component:MyBookingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
