import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoopAnimationsModule, BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {LoggerModule,NgxLoggerLevel} from 'ngx-logger'
import { ArticlesModule } from './modules/articles/articles.module';
import { AuthenticationModule } from './modules/authorization/authentication.module';
import { SharedModule } from './modules/shared/shared.module';
import { environment } from 'src/environments/environment';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppHttpInterceptor } from './core/interceptors/http-interceptor.interceptor';
import { MagazinesModule } from './modules/magazines/magazines.module';
import { EmployeesModule } from './modules/employees/employees.module';

@NgModule({
  declarations: [
    AppComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthenticationModule,
    ArticlesModule,
    SharedModule,
    EmployeesModule,
    MagazinesModule,
    NoopAnimationsModule,
    BrowserAnimationsModule,
    LoggerModule.forRoot({
      level:NgxLoggerLevel[ environment.logLevel ]
    } as any)

  ],
  providers:[
    {provide:HTTP_INTERCEPTORS,useClass:AppHttpInterceptor,multi:true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor() {
  }
}
