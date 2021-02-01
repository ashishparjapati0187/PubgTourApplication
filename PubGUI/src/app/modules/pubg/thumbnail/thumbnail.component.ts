import { Component, OnInit, Input } from '@angular/core';
import {MatSnackBarModule, MatSnackBar} from '@angular/material/snack-bar';
import{MatDialogModule,MatDialogRef,MAT_DIALOG_DATA, MatDialog} from '@angular/material/dialog'
import { PubgService } from '../pubg.service';
import { Match } from '../UserMatch';
import { Router } from '@angular/router';
import { Tournament } from 'src/app/modules/pubg/Tournament';



@Component({
  selector: 'pubg-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {


  @Input()
  tournament:Tournament;
  match:Match;
  data:any;
  matches:Array<Match>;
  constructor(private pubgService:PubgService,private router:Router) { 
    this.matches=[];
  }

  ngOnInit() {
   
  }
  viewMatches(tournamentId:string){
    // {
    //   "type": "match",
    //   "id": "b320884c-efb1-4e3a-93c1-e8113c2b8ff8",
    //   "attributes": {
    //       "createdAt": "2019-03-24T12:33:55Z"
    //   }


    console.log("tourID="+tournamentId)
    this.router.navigate(['/pubg/viewMatch/',tournamentId]);

//     this.pubgService.getMatchesOfTournament(tournamentId).subscribe((res)=>{
//       this.data=res.included;
//       console.log(this.data);
//       this.match=new Match();
//       for(var i=0;i< this.data.length;i++){
// this.match.type=this.data[i].type;
//     this.match.id=this.data[i].id;
//     this.match.createdAt=this.data[i].attributes.createdAt;
//     this.matches.push(this.match);
//       }

//     });


  }

}
