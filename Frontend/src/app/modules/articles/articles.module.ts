import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppArticleListComponent } from './components/app-article-list/app-article-list.component';
import { MaterialModule } from '../material/material.module';
import { AppArticleDetailsComponent } from './components/app-article-details/app-article-details.component';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { AppArticleFormComponent } from './components/app-article-form/app-article-form.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [AppArticleListComponent, AppArticleDetailsComponent, AppArticleFormComponent],
  imports: [
    ReactiveFormsModule,
    FlexLayoutModule,
    MaterialModule,
    RouterModule,
    CommonModule
  ]
})
export class ArticlesModule { }
