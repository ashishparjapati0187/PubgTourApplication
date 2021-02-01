import { Component, OnInit, Input } from '@angular/core';
import { Tournament } from 'src/app/modules/pubg/Tournament';

@Component({
  selector: 'pubg-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {


  @Input()
  tournaments:Array<Tournament>; // from tmdb container

  constructor() { }

  ngOnInit() {
  }

}
