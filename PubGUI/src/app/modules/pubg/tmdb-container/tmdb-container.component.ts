import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PubgService } from '../pubg.service';
import { Tournament } from 'src/app/modules/pubg/Tournament';

@Component({
  selector: 'movie-tmdb-container',
  templateUrl: './tmdb-container.component.html',
  styleUrls: ['./tmdb-container.component.css']
})
export class TmdbContainerComponent implements OnInit {

  tournaments:Array<Tournament>;  //will be send to container component
  data:any; 
  tournament:Tournament
  //will get movies
 
  constructor(private pubgService:PubgService,private route:ActivatedRoute) { 

    // this.tournament.type="hellofsafsf";
    //  this.tournaments.push(this.tournament);  
    this.tournaments=[];  
    this.tournament=new Tournament();
  }



  ngOnInit() {      //getting all movies from link
    
    this.pubgService.getTournament().subscribe((res)=>{
      this.data=res.data;
      console.log(this.data);
      for(var i=0;i<this.data.length;i++){
        this.tournament=new Tournament();
          console.log(this.data[i].type)
          this.tournament.type=this.data[i].type;
          this.tournament.tournamentId=this.data[i].id;
          this.tournament.createdAt=this.data[i].attributes.createdAt;
          this.tournaments.push(this.tournament);
      }
    });


      
  }

 
}
