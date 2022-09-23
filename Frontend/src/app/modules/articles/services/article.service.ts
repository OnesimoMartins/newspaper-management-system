import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from 'src/app/core/models/article.model';
import { environment } from 'src/environments/environment';
import { ArticleCollection } from '../models/article-collection.model';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private url=environment.apiV1Url+'articles'

  constructor(private http:HttpClient) {}

  putArticleText(textBody:any,id:Number){

    const formData=new FormData()

    formData.append('file',textBody)

    return  this.http.put<any>(`${this.url}/${id}/upload-body`,formData)
   }

  postArticle(body:any){
   return  this.http.post<Article>(this.url,body)
  }

  getArticleById(id:any):Observable< Article>{
    return this.http.get<Article>(`${this.url}/${id}`)
  }

  getArticlePage(number: Number, size: Number, queryFilter?: string):Observable<ArticleCollection> {

    let url=`${this.url}?size=${size}&page=${number}`

    if(queryFilter){
       url=`${this.url}?size=${size}&page=${number}&state=${queryFilter}`
       console.log(url);
  }

   return  this.http.get<ArticleCollection>(url)
  }


}
