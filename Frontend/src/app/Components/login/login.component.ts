import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  title = 'huangular2022';
 
  loginObj : any = {
    "email":"",
    "password": ""
  }

  LoginForm!: FormGroup;

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
 
  constructor(private http: HttpClient, private router: Router, private formBuilder: FormBuilder,private authService: AuthService) {
  
  }
 
  ngOnInit(): void{
    this.LoginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }
  
 
  onLogin() {
    this.loginObj.email = this.LoginForm.get('email')?.value;
    this.loginObj.password = this.LoginForm.get('password')?.value;
    //console.log(this.loginObj);
    this.http.post('http://localhost:8080/auth/login',this.loginObj).subscribe((res:any)=>{
      localStorage.setItem('eventUser', JSON.stringify(res.data))
      if(res) {
        alert('Login Success');
       // console.log(res.token);
        const token = res.token;
        this.authService.setToken(token);
        this.router.navigate(['home']);
      } else {
        console.log(res);
        alert(res.message);
      }
    })
  }
}
