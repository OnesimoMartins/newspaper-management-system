import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppMagazineListComponent } from './components/app-magazine-list/app-magazine-list.component';
import { MaterialModule } from '../material/material.module';
import { AppMagazineDetailsComponent } from './components/app-magazine-details/app-magazine-details.component';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';



@NgModule({
  declarations: [
    AppMagazineListComponent,
    AppMagazineDetailsComponent
  ],
  imports: [
    FlexLayoutModule,
    CommonModule,
    MaterialModule,
    RouterModule
  ]
})
export class MagazinesModule { }
