import { Component, OnInit } from '@angular/core';
import { MatchDetails } from '../MatchDetails';
import { PubgService } from '../pubg.service';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'pubg-favourite-list',
  templateUrl: './favourite-list.component.html',
  styleUrls: ['./favourite-list.component.css']
})
export class FavouriteListComponent implements OnInit {

  snackMessege:string;
  allFavMatchDetails:Array<MatchDetails>;
  constructor(private pubgService:PubgService,private snackBar:MatSnackBar) {
  this.allFavMatchDetails=[];
  }

  ngOnInit() {

    this.pubgService.getWatchListedMovies().subscribe((allFavMatchDetails)=>{  
      //gets all watched movie from service
      console.log(allFavMatchDetails);
      this.allFavMatchDetails.push(...allFavMatchDetails);
      
       });
  }
  deleteMatch(matchDetails:MatchDetails){
    this.pubgService.deleteFromMyFavList(matchDetails.id).subscribe((res)=>{
      this.snackMessege=`${matchDetails.title} is deleted`;
      console.log(res);
      this.snackBar.open(this.snackMessege,'',{
        duration:2000
      });

    })
    const index=this.allFavMatchDetails.indexOf(matchDetails);
this.allFavMatchDetails.splice(index,1);
  }


}
