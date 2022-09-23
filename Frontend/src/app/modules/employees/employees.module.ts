import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../material/material.module';
import { AppEmployeesCardsComponent } from './components/app-employees-cards/app-employees-cards.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [AppEmployeesCardsComponent],
  imports: [
    CommonModule,
    FlexLayoutModule,
    MaterialModule,
    HttpClientModule
  ]
})
export class EmployeesModule { }
