import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PubgRouterModule } from './pubg-routing.module';
import { TmdbContainerComponent } from './tmdb-container/tmdb-container.component';
import { PubgService } from './pubg.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { ContainerComponent } from './container/container.component';
import { Container } from '@angular/compiler/src/i18n/i18n_ast';
import { ThumbnailComponent } from './thumbnail/thumbnail.component';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatCardModule} from '@angular/material/card';
import { ViewMatchesComponent } from './view-matches/view-matches.component';
import { MatchDetailsComponent } from './match-details/match-details.component';
import { FavouriteListComponent } from './favourite-list/favourite-list.component';




@NgModule({
  declarations: [TmdbContainerComponent, ContainerComponent, ThumbnailComponent, ViewMatchesComponent, MatchDetailsComponent, FavouriteListComponent,],
  imports: [
    CommonModule,PubgRouterModule,
    MatCardModule,MatButtonModule,
    MatSnackBarModule,MatDialogModule
    ,MatInputModule,MatDialogModule

  ], providers:[]    ,
  exports:[TmdbContainerComponent,ContainerComponent]
})
export class PubgModule { }
