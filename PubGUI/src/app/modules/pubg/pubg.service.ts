import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map ,retry} from 'rxjs/operators';
import { Observable } from 'rxjs';

////import { map ,retry} from 'rxjs/operators';
//import { Observable } from 'rxjs';

import * as jwt_decode from 'jwt-decode';
import { Tournament } from 'src/app/modules/pubg/Tournament';



import { MatchDetails } from './MatchDetails';
import { AuthenticateService } from '../authentication/authentication.service';
export const TOKEN_NAME:string='jwt-decode';  //npm install --save jwt-decode

@Injectable({
  providedIn: 'root'
})
//     const headers = new HttpHeaders();
//return this.http.get(this.url,{ headers: {'Authorization': this.api_token,'accept':'application/vnd.api+json'}})

export class PubgService {

    movieApi:string;
  imagePrefix:string;
  apiKey:string;
  tmdbEndpoint:string;
watchListEndPoint:string;
favouriteEndPoint:string;
searchEndPoints:string;
tournamentEndPoints:string;
matchesEndPoints:string;
pubgApi:string;
matchDetails:string;
    inj:string="https://api.themoviedb.org/3/movie/popular?api_key=3961612ceffc2d22d83a13105133968d&page=1";
    constructor(private http:HttpClient,private authService:AuthenticateService) {
      this.tmdbEndpoint="https://api.themoviedb.org/3/movie"
      this.apiKey="api_key=3961612ceffc2d22d83a13105133968d";
     
   this.favouriteEndPoint="http://localhost:8089/favourite/"; // local host link of server   
   this.searchEndPoints="https://api.themoviedb.org/3/search/movie?";
   this.tournamentEndPoints="https://api.pubg.com/tournaments";
   this.pubgApi="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiN2M1ZTVlMC0zMWQ2LTAxMzctNDIwMC0wMDU3NDUzNGQzNjMiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNTUzNTkyMTk5LCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6Ii0xY2E1ODQ2ZS0xM2ZjLTQxODctYjYyYi04MDYwOGM4NGE2MzcifQ.DMIfxrpVIhUPVfPLc9XyNfNy0qnNiejIr7sveJinjBQ";
this.matchDetails="https://api.pubg.com/shards/tournament/matches";
  
  
     }
  
     getTournament():any{
      const headers = new HttpHeaders();
      return this.http.get(this.tournamentEndPoints,{ headers: {'Authorization':'Bearer '+this.pubgApi,'accept':'application/vnd.api+json'}});
  
     }
    
  getMatchesOfTournament(id:string):any{
    const headers = new HttpHeaders();
    
    const url=`${this.tournamentEndPoints}/${id}`;
    return this.http.get(url,{ headers: {'Authorization':'Bearer '+this.pubgApi,'accept':'application/vnd.api+json'}});
  }

  viewMatchDetails(matchId:string):any{

    const headers = new HttpHeaders();
    const url=`${this.matchDetails}/${matchId}`;
    return this.http.get(url,{ headers: {'Authorization':'Bearer '+this.pubgApi,'accept':'application/vnd.api+json'}});
  }
  

     
  getWatchListedMovies():Observable<Array<MatchDetails>>{
    const headers = new HttpHeaders();
    const getWatchListUrl=`${this.favouriteEndPoint}allMatches`;
    return this.http.get<Array<MatchDetails>>(getWatchListUrl,{ headers: {'Authorization':'Bearer '+this.authService.getToken()}});
  }
  // }
  AddToFavourite(matchDetails:MatchDetails):any{
    const headers = new HttpHeaders();
    const saveUrl=`${this.favouriteEndPoint}match`;
    console.log(matchDetails);
   return this.http.post(saveUrl,matchDetails,{ headers: {'Authorization':'Bearer '+this.authService.getToken()}});
  }
  deleteFromMyFavList(id:string){
    const headers = new HttpHeaders();
    const url=`${this.favouriteEndPoint}${id}`;
    return this.http.delete(url,
      {headers: {'Authorization':'Bearer '+this.authService.getToken(),
      responseType:'text'}});
  
   }

 
}
