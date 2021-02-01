import { TestBed, fakeAsync,inject } from '@angular/core/testing';

import { AuthenticateService } from './authentication.service';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

const testConfig={
  addUser:{
    positive:{
      firstName:'test',
      lastName:'testlast',
      userId:'testuser',
      password:'testpass'
    }
  },
  loginUser2:{
    positive:{ 
      userId:'testuser',
      password:'testpass'
    }
  }

}

fdescribe('AuthenticateService', () => {

  let authService:AuthenticateService;

  beforeEach(() => {
  TestBed.configureTestingModule({
    imports:[HttpClientModule,HttpClientTestingModule],
    providers:[AuthenticateService]
  });
  authService=TestBed.get(AuthenticateService)
  });

  it('should create Authenticate service',()=>{
    inject([AuthenticateService],(service:AuthenticateService)=>{
      expect(service).toBeTruthy();      // tests whether authenticateService created or not
  })});
    



    it('should register user data',fakeAsync(()=>{

      let data=testConfig.addUser.positive;
      inject([AuthenticateService,HttpTestingController],(backend:HttpTestingController)=>{
        const mockReq=backend.expectOne(authService.springEndPoint);
        expect(mockReq.request.url).toEqual(authService.springEndPoint,'requsted url hould match with the json server api url');
        expect(mockReq.request.method).toBe('POST','should handle requested method type');

        mockReq.flush(data);
        backend.verify();
      })

      authService.registerUser(data).subscribe((res:any)=>{
        expect(res).toBeDefined();
        expect(res._body).toBe(data,'data should be same');
      });
    }));



    it('should login user data',fakeAsync(()=>{

      let data=testConfig.loginUser2.positive;
      inject([AuthenticateService,HttpTestingController],(backend:HttpTestingController)=>{
        const mockReq=backend.expectOne(authService.springEndPoint);
        expect(mockReq.request.url).toEqual(authService.springEndPoint,'requsted url hould match with the json server api url');
        expect(mockReq.request.method).toBe('POST','should handle requested method type');

        mockReq.flush(data);
        backend.verify();
      })

      authService.loginUser(data).subscribe((res:any)=>{
        expect(res).toBeDefined();
        expect(res._body).toBe(data,'data should be same');
      });
    }));
  
});
