import { Component, OnInit } from '@angular/core';
import { User } from '../User';
import { AuthenticateService } from '../authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'authentication-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser:User;
  constructor(private authService:AuthenticateService,private router:Router) {
    this.newUser=new User();
   }

  ngOnInit() {
  }

  registerUser(){
    console.log("USer form registerpage>>:"+this.newUser);
    this.authService.registerUser(this.newUser).subscribe(

      (data)=>{
        console.log("user is:>>"+data);
        
        this.router.navigate(["/login"]);
      }
    );
  }
  resetInput()
  {
    this.newUser=new User();
  }
}
