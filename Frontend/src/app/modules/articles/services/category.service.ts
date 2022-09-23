import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from 'src/app/core/models/category.model';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private url=environment.apiV1Url+'categories'

  constructor(private http:HttpClient) {}

  getCategories():Observable< Category[]>{
    return this.http.get<Category[]>(this.url)
  }

}
