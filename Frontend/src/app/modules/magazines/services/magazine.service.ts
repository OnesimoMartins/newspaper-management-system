import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { MagazineCollection } from '../models/magazine-collection.model';
import {Observable} from 'rxjs'
import { AuthenticationService } from '../../authorization/services/authentication.service';
import { Magazine } from 'src/app/core/models/magazine.model';
import { ArticleCollection } from '../../articles/models/article-collection.model';
import { MagazineEdition } from '../models/magazine-edition.model';
import { Article } from 'src/app/core/models/article.model';

@Injectable({
  providedIn: 'root'
})
export class MagazineService {


  private url=environment.apiV1Url+'magazines'

  constructor(private http:HttpClient){  }

  getMagazineById(id:any){
    return this.http.get<Magazine>(`${this.url}/${id}`)
  }

  pubishMagazine(id: Number) {
    return this.http.put<MagazineEdition>(`${this.url}/${id}/publish`,{})
  }

  addArticle(magazineId: Number, id: Number) {
  return  this.http.put<Article>(`${this.url}/${magazineId}/articles/${id}`,{})
  }

  getNewspaperMagazinePage(number: Number, size: Number):Observable<MagazineCollection> {
   return  this.http.get<MagazineCollection>(`${this.url}?size=${size}&page=${number}`)
  }

  newEdition(id: Number) {
   return  this.http.put<MagazineEdition>(`${this.url}/${id}/editions`,{});
  }

  getMagzineEditionArticlePage( magazineId:Number,edition:Number)
             :Observable<ArticleCollection> {

    return  this.http.get<ArticleCollection>(`${this.url}/${magazineId}/editions/${edition}/articles`)
   }


}
