import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ArticleService } from '../../services/article.service';
import { ArticleTableProvider } from 'src/app/modules/shared/article-table-provider';
import { AuthenticationService } from 'src/app/modules/authorization/services/authentication.service';

@Component({
  selector: 'app-article-list',
  templateUrl: './app-article-list.component.html',
  styleUrls: ['./app-article-list.component.css'],
})
export class AppArticleListComponent extends ArticleTableProvider {

  filter: string = 'Todos';

  constructor(articleSerice: ArticleService,private authService:AuthenticationService) {
    super(articleSerice);
  }

  alterFilter() {

    if (this.filter == 'Reprovados')
      super.queryFilter = 'Refused';

    else if (this.filter == 'Aprovados')
      super.queryFilter = 'Approved';

    else if (this.filter == 'Submetidos')
      super.queryFilter = 'Applied';

    else
      super.queryFilter=''

    super.loadData(0, 6);

  }

  getSubtittle() {
    if (this.filter == 'Aprovados')
      return 'A lista abaixo contém todos os artigos aprovados do jornal.';

    else if (this.filter == 'Reprovados')
      return   'A lista abaixo contém todos os artigos reprovados do jornal.';

      else if (this.filter == 'submetidos')
      return   'A lista abaixo contém todos os artigos submetidos do jornal, dispostos para avaliação';

    return 'A lista abaixo contém todos os artigos do jornal.';
  }

  isJournalist(){
    return this.authService.getCurrentUser().isJournalist()
  }

}
