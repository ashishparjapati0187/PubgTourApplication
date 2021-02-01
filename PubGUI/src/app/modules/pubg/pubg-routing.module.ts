import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';


import { AuthGuardService } from '../../authGuard.service';
import { TmdbContainerComponent } from './tmdb-container/tmdb-container.component';
import { ViewMatchesComponent } from './view-matches/view-matches.component';
import { MatchDetailsComponent } from './match-details/match-details.component';
import { FavouriteListComponent } from './favourite-list/favourite-list.component';



const pubgRoutes:Routes=[
    {  
        path:'pubg',
        children:[
            {path:'',redirectTo:'/pubg/tournament',pathMatch:'full',canActivate:[AuthGuardService]},  // it will load movies-popular url
            {path:'tournament',
            component:TmdbContainerComponent
            ,canActivate:[AuthGuardService]
        
        },{path:'viewMatch/:id',component:ViewMatchesComponent,canActivate:[AuthGuardService]}
        ,{path:'matchDetails/:id',component:MatchDetailsComponent,canActivate:[AuthGuardService]},
        {path:'favouriteList',component:FavouriteListComponent,canActivate:[AuthGuardService]}
        //     {path:'top_rated',
        //     component:TmdbContainerComponent,canActivate:[AuthGuardService],
        //     data:{movieType:'top_rated'}
        //    },
        //    {path:'watchlist',component:WatchlistComponent,canActivate:[AuthGuardService]},
        //    {path:'search',component:SearchComponent,canActivate:[AuthGuardService]}
              ]
    }
    
]

@NgModule({
    imports: [RouterModule.forChild(pubgRoutes)],
  exports: [RouterModule]    // sending routing configuration by routingModule

})
export class PubgRouterModule{}