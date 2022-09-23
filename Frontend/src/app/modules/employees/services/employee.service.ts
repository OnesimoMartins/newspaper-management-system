import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AdministratorCollection } from '../models/administrator-collection.model';
import { JournalistCollection } from '../models/Journalist-collection.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http:HttpClient) { }

  private JournalistsUrl=environment.apiV1Url+'employees/journalists'
  private admUrl=environment.apiV1Url+'employees/administrators'

  getJournalistsPage(number:Number){

    let pageable=`?size=${6}&page=${number}`
    return this.http.get<JournalistCollection>(`${this.JournalistsUrl+pageable}`)
  }

  getAdministratorsPage(number:Number){

    let pageable=`?size=${6}&page=${number}`
    return this.http.get<AdministratorCollection>(`${this.admUrl+pageable}`)
  }

}
