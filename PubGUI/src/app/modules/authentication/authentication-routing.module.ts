import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';







const authRoutes:Routes=[

    {
        path:'',
        children:[
          {path:'',redirectTo:'/login',pathMatch:'full'},
          {path:'register',component:RegisterComponent},
          {path:'login',component:LoginComponent}
        ]
      }
]
@NgModule({
    imports: [RouterModule.forChild(authRoutes)],
  exports: [RouterModule]    // sending routing configuration by routingModule

})

export class AuthenticationRouterModule{}
