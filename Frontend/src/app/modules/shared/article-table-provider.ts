import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Article } from 'src/app/core/models/article.model';
import { Page } from 'src/app/core/models/page.model';
import { ArticleService } from '../articles/services/article.service';
import { Injectable, ViewChild } from '@angular/core';
import { ArticleCollection } from '../articles/models/article-collection.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ArticleTableProvider {
  constructor(protected articleSerice: ArticleService) {}

  datasource: MatTableDataSource<Article> = new MatTableDataSource<Article>();

  page: Page = new Page();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;


  headerColumns = ['id', 'tittle', 'magazine', 'keywords', 'actions'];

  queryFilter=''

  public setheaderColumns(v: string[]) {
    this.headerColumns = v;
  }

  ngOnInit(): void {
    this.loadData(0, 6);
  }

  ngAfterViewInit(): void {
    this.datasource.paginator = this.paginator;
  }

  loadData(number: Number, size: Number) {


    this.articleSerice .getArticlePage(number, size, this.queryFilter)
      .subscribe( it => {
        this.datasource = new MatTableDataSource<Article>( it?._embedded?.articleResponseList);
        this.page = it.page;
      });
  }

  fillArticleDatasource(fun: () => Observable<ArticleCollection>) {
    fun().subscribe((it) => {
      this.datasource = new MatTableDataSource<Article>(it as any);
      this.page = it.page
    });
  }

  onPaging(e: PageEvent) {
    this.loadData(e.pageIndex, e.pageSize);
  }

  hasArticles() {
    return this.datasource.data.length > 0;
  }

}
