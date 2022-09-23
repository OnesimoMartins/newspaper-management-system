import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JwtHelperService,JWT_OPTIONS } from '@auth0/angular-jwt';
import { HttpClientModule } from '@angular/common/http';
import { AppLoginComponent } from './components/app-login/app-login.component';
import { MaterialModule } from '../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';



@NgModule({
  declarations: [
    AppLoginComponent
  ],
  imports: [
    HttpClientModule,
    FlexLayoutModule,
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers:[
    {provide:JWT_OPTIONS,useValue:JWT_OPTIONS},
    JwtHelperService
  ]
})
export class AuthenticationModule { }
