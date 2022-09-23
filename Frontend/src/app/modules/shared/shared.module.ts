import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutComponent } from './components/layout/layout.component';
import { MaterialModule } from '../material/material.module';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    LayoutComponent
  ],
  imports: [
    FlexLayoutModule,
    RouterModule,
    // AppRoutingModule,
    CommonModule,
    MaterialModule
  ],
  exports:[
    LayoutComponent
  ]
})
export class SharedModule { }
