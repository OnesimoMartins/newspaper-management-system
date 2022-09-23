import { Component, OnInit } from '@angular/core';
import { MatTabChangeEvent } from '@angular/material/tabs';
import { ActivatedRoute } from '@angular/router';
import { Article } from 'src/app/core/models/article.model';
import { User } from 'src/app/core/models/authUser.model';
import { Magazine } from 'src/app/core/models/magazine.model';
import { SnackBarService } from 'src/app/core/services/snack-bar-service.service';
import { AuthenticationService } from 'src/app/modules/authorization/services/authentication.service';
import { MagazineService } from 'src/app/modules/magazines/services/magazine.service';
import { ArticleService } from '../../services/article.service';
import { AvaliationService } from '../../services/avaliation.service';

@Component({
  selector: 'app-app-article-details',
  templateUrl: './app-article-details.component.html',
  styleUrls: ['./app-article-details.component.css']
})
export class AppArticleDetailsComponent implements OnInit {

constructor(private activeRoute:ActivatedRoute,
    private articleService:ArticleService,
    private avaliationService:AvaliationService,
    private notificationService:SnackBarService,
    private magazineService:MagazineService,
    private authService:AuthenticationService) {}

   article:Article=new Article()

   user:User=this.authService.getCurrentUser()

   alreadyAvaliated=false;

   magazineId!:Number;

   magazines:Magazine[]=[]

 ngOnInit(): void {

  const id= this.activeRoute.snapshot.paramMap.get("id")
   this.articleService.getArticleById(id).subscribe(it=>{
   this.article=it

   })

  }

  canAvaliateArticle(){
    return this.user.isJournalist() &&!(this.article.journalist_creator.id==this.user.user_id)
  }

  avaliateArticle(mark:Number){

   this.avaliationService.avaliateArticle(this.article.id,mark).subscribe(it=>{
    this.notificationService.openSnackBar("Avaliação feita com sucesso.")
    this.alreadyAvaliated=true
   },
   err=>{
    this.alreadyAvaliated=true
    this.notificationService.openSnackBar("Este artigo já foi avaliado por si.")
   })

  }

  matTabChange(event:MatTabChangeEvent){

    if(event.tab.textLabel=="Adicionar ao magazine")
    this.loadMagazines()

  }

  loadMagazines(){
    this.magazineService.getNewspaperMagazinePage(0,30).subscribe(
      it=>{
     this.magazines=
      it._embedded.magazineResponseList.filter(m=>!m.is_last_edition_published)
    })

  }

  addArticleToMagazine(){

    this.magazineService.addArticle(this.magazineId,this.article.id).subscribe(it=>{
      this.article=it
      this.notificationService.openSnackBar("Artigo adicionado com sucesso.")
    },
    error=>{
      this.notificationService.openSnackBar("Este artigo já pertence a um magazine.")
    });

  }

  hasNotMagazine(){
    return !(this.article.magazine?.id==null)
  }
  cantAddToMagazine(){
    !this.authService.getCurrentUser().isJournalistSenior() ||
    this.authService.getCurrentUser().isCEO()
  }

  setMagazineId(value:any){
  this.magazineId=value
  }

}
