import {  Router } from '@angular/router';
import {CanActivate} from '@angular/router/src/interfaces'
import { Injectable } from '@angular/core';
import { AuthenticateService } from './modules/authentication/authentication.service';


@Injectable()
export class AuthGuardService implements CanActivate{
    constructor(private router:Router,private authenticationService:AuthenticateService){

    }

    canActivate(){
        if(!this.authenticationService.isTokenExpired()){
            return true;
        }
        this.router.navigate(['/login']);
        return false;
    }
    
 }