import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PubgService } from '../pubg.service';
import { MatchDetails } from '../MatchDetails';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'pubg-match-details',
  templateUrl: './match-details.component.html',
  styleUrls: ['./match-details.component.css']
})
export class MatchDetailsComponent implements OnInit {

  matchDetailsId:string;
  data:any;
  tempData:any;
  matchDetails:MatchDetails;
  snackMessege:string;
  totalPLayerArray=[];
  constructor(private activateRouter:ActivatedRoute,private pubgService:PubgService,private snackBar:MatSnackBar) { 
    this.matchDetails=new MatchDetails();
  }

  ngOnInit() {
    this.matchDetailsId =this.activateRouter.snapshot.paramMap.get('id');
    console.log("matchdetailsId= matchdetailscomponent"+this.matchDetailsId);

    // "data": {
    //   "type": "match",
    //   "id": "82821ad4-1b53-48e0-b116-1e33b89bb6c3",
    //   "attributes": {
    //       "isCustomMatch": false,
    //       "seasonState": "progress",
    //       "tags": null,
    //       "mapName": "Desert_Main",
    //       "createdAt": "2019-03-20T11:17:08Z",
    //       "duration": 548,
    //       "stats": null,
    //       "gameMode": "normal",
    //       "titleId": "bluehole-pubg",
    //       "shardId": "tournament"
    //   }
    
    this.pubgService.viewMatchDetails(this.matchDetailsId).subscribe((res)=>{
      this.data=res.data;    
      this.matchDetails.id=this.data.id;
      this.matchDetails.title=this.data.attributes.titleId;
      this.matchDetails.mapName=this.data.attributes.mapName;
      this.matchDetails.createdAt=this.data.attributes.createdAt;
this.matchDetails.totalPlayers=res['included'].length;
       
    });
  }


  addToFavourite(match:MatchDetails){
    this.snackMessege=`${match.title} is added to favourite list`;
    this.pubgService.AddToFavourite(match).subscribe((res)=>{
   console.log("successfully added to favourite"+res);
    });
    this.snackBar.open(this.snackMessege,'',{
      duration:2000
    });
  }
}
