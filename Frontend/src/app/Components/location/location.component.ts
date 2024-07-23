import { Component, OnInit } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';



interface Car {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.scss'],
  
})
export class LocationComponent implements OnInit {
  
  selectedValue: string | undefined;
  selectedCar: string | undefined;


  cars: Car[] = [
    {value: 'volvo', viewValue: 'Volvo'},
    {value: 'saab', viewValue: 'Saab'},
    {value: 'mercedes', viewValue: 'Mercedes'},
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
