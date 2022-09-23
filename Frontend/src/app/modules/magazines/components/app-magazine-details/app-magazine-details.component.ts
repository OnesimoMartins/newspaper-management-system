import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Magazine } from 'src/app/core/models/magazine.model';
import { SnackBarService } from 'src/app/core/services/snack-bar-service.service';
import { ArticleService } from 'src/app/modules/articles/services/article.service';
import { AuthenticationService } from 'src/app/modules/authorization/services/authentication.service';
import { ArticleTableProvider } from 'src/app/modules/shared/article-table-provider';
import { MagazineService } from '../../services/magazine.service';

@Component({
  selector: 'app-magazine-details',
  templateUrl: './app-magazine-details.component.html',
  styleUrls: ['./app-magazine-details.component.css'],
})
export class AppMagazineDetailsComponent extends ArticleTableProvider{

  constructor(
    private activeRoute: ActivatedRoute,
    private magazineService: MagazineService,
    private authService:AuthenticationService,
    private notificationService:SnackBarService,
    articleService: ArticleService
  ) {
    super(articleService);
    super.setheaderColumns(['id', 'tittle', 'actions']);
  }

  magazine: Magazine = new Magazine();
  editions: Number[] = [];
  choosenEdition: Number = 0;

  override ngOnInit(): void {


    this.magazineService
      .getMagazineById(this.activeRoute.snapshot.paramMap.get('id'))
      .subscribe((value) => {
        this.magazine = value;

        for (let i = 1; i <= value.last_edition; i++) this.editions.push(i);

        this.choosenEdition = value.last_edition;

        this.fillArticleDatasource();
      });
  }

  publishMagazine(){

    this.magazineService.pubishMagazine(this.magazine.id).subscribe(it=>{

      this.magazine=it.magazine
      this.notificationService.openSnackBar(`Edição '${it.edition}' do magazine
      '${this.magazine.name}' publicada com sucesso.`)
    },
    error=>{
      this.notificationService.openSnackBar(`A edição do magazine deve ter no mínimo 1 artigo para ser pubilcado.`)
     });
  }

  chooseEdition() {
    this.fillArticleDatasource();
  }

  newMagazineEdition(){

    this.magazineService.newEdition( this.magazine.id).subscribe(it=>{
      this.magazine=it.magazine
      this.editions.push(it.edition)
      this.notificationService.openSnackBar(`nova edição do magazine '${this.magazine.name}' criada com sucesso. `)
    })

  }


  override fillArticleDatasource() {
    super.fillArticleDatasource(() =>
      this.magazineService.getMagzineEditionArticlePage(
        this.magazine.id,
        this.choosenEdition
      )
    );
  }

  cantPublish(){
    !this.authService.getCurrentUser().isPublisher() || !this.authService.getCurrentUser().isCEO()
  }
  cantCreateEdition(){
    return this.authService.getCurrentUser().isJournalist()
  }

}
