import { Component, OnInit } from '@angular/core';
import { User } from '../User';
import { AuthenticateService } from '../authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'authentication-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  newUser:User;
  constructor(private authService:AuthenticateService,private router:Router) { 
    this.newUser=new User();
  }

  ngOnInit() {
  }
  LoginUser(){
    console.log("userdetails=",this.newUser.userId,this.newUser.password);
    this.authService.loginUser(this.newUser).subscribe((data)=>{
      console.log("login details="+data)
      if(data['token']){
        this.authService.setToken(data['token']);
        console.log('token',data['token']);
        this.router.navigate(['/pubg/tournament']);
      }
    })
  }
}
