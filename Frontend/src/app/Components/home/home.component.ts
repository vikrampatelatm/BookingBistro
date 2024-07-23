import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
//import { film } from 'src/app/userDetails';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  movieList: any[]=[]
  

  constructor(private formBuilder: FormBuilder, private http: HttpClient, private router: Router) {
    
  }

  ngOnInit(): void {
  }
 

}
