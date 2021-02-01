import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Match } from '../UserMatch';
import { PubgService } from '../pubg.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatchDetails } from '../MatchDetails';

@Component({
  selector: 'pubg-view-matches',
  templateUrl: './view-matches.component.html',
  styleUrls: ['./view-matches.component.css']
})
export class ViewMatchesComponent implements OnInit {
  tournamentId:string;
matches:Array<Match>;
data:any;
data2:any;
match:Match;
snackMessege:string;
  constructor(private activateRoute:ActivatedRoute,private pubgService:PubgService,private router:Router,private snackBar:MatSnackBar) { 
    this.matches=[];
  }

  ngOnInit() {
    this.tournamentId =this.activateRoute.snapshot.paramMap.get('id');
     console.log("touranamentId="+this.tournamentId);

          this.pubgService.getMatchesOfTournament(this.tournamentId).subscribe((res)=>{
            this.data=res.included;
            console.log(this.data);
            this.match=new Match();
            for(var i=0;i< this.data.length;i++){
      this.match.type=this.data[i].type;
          this.match.id=this.data[i].id;
          this.match.createdAt=this.data[i].attributes.createdAt;
          this.matches.push(this.match);
            }
      
          });
      
  }

  viewMatches(matchId:string){

    this.router.navigate(['/pubg/matchDetails/',matchId]);
    
 }
 
}
