import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';



@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  
})
export class RegisterComponent implements OnInit {
  RegisterForm!: FormGroup;

  pwdHide1 = true;
 
  visibilityToggle1() {
      this.pwdHide1 = !this.pwdHide1;
  }
  getIputType1() {
      return this.pwdHide1 ? 'password' : 'text';
  }
  getVisibility1() {
      return this.pwdHide1 ? 'visibility_off' : 'visibility';
  }
 

  constructor(private http: HttpClient, private router: Router, private formBuilder: FormBuilder, private authService: AuthService) { }

  ngOnInit(): void {
    this.RegisterForm = this.formBuilder.group({
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$')]],
      mobile: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      password: ['', Validators.required]
    });
  }

  onRegister(): void {
    if (!this.validateForm()) {
      return;
    }
   console.log(this.RegisterForm.value);
    this.http.post('http://localhost:8080/auth/signup', this.RegisterForm.value).subscribe((res: any) => {
      if (res) {
        alert('Registration Success');
        this.authService.setUserId(res.id);
        this.router.navigate(['login']);
      } else {
        console.log(res);
      }
    });
  }

  validateForm(): boolean {

    if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(this.RegisterForm.value.email)) {
      alert('Please enter a valid email address.');
      return false;
    }
    if (this.RegisterForm.value.mobile.length !== 10 || isNaN(parseInt(this.RegisterForm.value.mobile))) {
      alert('Phone number must be 10 digits long.');
      return false;
    }
    return true;

  }  

 
}