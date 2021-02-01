import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent} from './login.component';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';
import { Location } from '@angular/common';
import { Observable, of } from 'rxjs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatCardModule } from '@angular/material/card';
import { AppComponent } from '../../../app.component';
import { AuthenticateService } from '../authentication.service';

const testConfig={
  userdata:{
    firstName:'test',
    lastName:'testlast',
    userId:'testuser',
    password:'testpass'
  }
}

fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authService: AuthenticateService;
  let spyUser:any;
  let loginComponent:LoginComponent;
  let routes:Router;
  let location:Location;

  class AthServiceStub{
    currentUser:any;
    constructor(){

    }
    login(credentials){
      if(credentials.userId == testConfig.userdata.userId){
        return of(false);
      }
    }
    }

    class dummy{

    }

    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [ LoginComponent ],
        imports: [FormsModule, MatFormFieldModule, MatInputModule,
          MatButtonModule, BrowserAnimationsModule, HttpClientModule,MatCardModule,
          RouterTestingModule.withRoutes(
            [{path: '', component: dummy}]
          )
        ],
        providers: [AppComponent, {provide: AuthenticateService, useClass: AthServiceStub}]
      })
      .compileComponents();
    }));

    beforeEach(() => {
      routes = TestBed.get(Router);
      fixture = TestBed.createComponent(LoginComponent);
      location = TestBed.get(Location);
      component = fixture.componentInstance;
      fixture.detectChanges();
      fixture.debugElement.injector.get(AppComponent);
      fixture.debugElement.injector.get(AuthenticateService);
    });
    it('should create login component', async(() => {
      const app = fixture.debugElement.componentInstance;
      expect(app).toBeTruthy();
    }));
  
  
    it('should contain two input field', () => {
      let userId=fixture.debugElement.query(By.css('.userId'))
      let password=fixture.debugElement.query(By.css('.password'))
      let userButton=fixture.debugElement.query(By.css('.login-user'))
      let registerButton=fixture.debugElement.query(By.css('.register-button'))
  
      let userIdInput=userId.nativeElement;
      let passwordInput=password.nativeElement;
      let userButtonInput=userButton.nativeElement
      let registerButtonInput=registerButton.nativeElement;
      
      expect(userIdInput).toBeTruthy();
      expect(passwordInput).toBeTruthy();
      expect(userButtonInput).toBeTruthy();
      expect(registerButtonInput).toBeTruthy();
    });
  
  
    it('should redirect to login if registered successfully', async(() => {
      let userId=fixture.debugElement.query(By.css('.userId'))
      let password=fixture.debugElement.query(By.css('.password'))
      let userButton=fixture.debugElement.query(By.css('.login-user'))
      
      let userIdInput=userId.nativeElement;
      let passwordInput=password.nativeElement;
      let userButtonInput=userButton.nativeElement;
  
      fixture.detectChanges();
      fixture.whenStable().then(()=>{
        userIdInput.value='testUser';
        passwordInput.value='testpass';
        userIdInput.dispatchEvent(new Event('input'));
        passwordInput.dispatchEvent(new Event('input'));
        userButtonInput.click();
      }).then(()=>{
        expect(location.path()).toBe('');
      })
  
    }));
});
