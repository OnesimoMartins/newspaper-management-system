import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AvaliationService {

  private url=environment.apiV1Url+'articles'

  constructor(private http:HttpClient) {}

  avaliateArticle(articleId:Number,mark:Number){

    return  this.http.post<any>(`${this.url}/${articleId}/avaliate`,{
      mark:mark
    })

   }
}
