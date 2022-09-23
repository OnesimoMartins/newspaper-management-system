import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginGuard } from './core/guards/login.guard';
import { AuthenticationGuard } from './core/guards/authentication.guard';
import { AppArticleListComponent } from './modules/articles/components/app-article-list/app-article-list.component';
import { LayoutComponent } from './modules/shared/components/layout/layout.component';
import { AppLoginComponent } from './modules/authorization/components/app-login/app-login.component';
import { AppMagazineListComponent } from './modules/magazines/components/app-magazine-list/app-magazine-list.component';
import { AppMagazineDetailsComponent } from './modules/magazines/components/app-magazine-details/app-magazine-details.component';
import { AppArticleDetailsComponent } from './modules/articles/components/app-article-details/app-article-details.component';
import { AppArticleFormComponent } from './modules/articles/components/app-article-form/app-article-form.component';
import { AppEmployeesCardsComponent } from './modules/employees/components/app-employees-cards/app-employees-cards.component';
import { AdministratorGuard } from './core/guards/administrator.guard';

const routes: Routes = [

  {path:'auth/login',component:AppLoginComponent, canActivate:[LoginGuard]},

  {path:'',component:LayoutComponent,canActivate:[AuthenticationGuard],
    children:[
    {path:'', redirectTo:'/artigos',pathMatch:'full'},
    {path:'artigos/novo',component:AppArticleFormComponent},
    {path:'artigos',component:AppArticleListComponent},
    {path:'artigos/:id',component:AppArticleDetailsComponent},
    {path:'magazines',component:AppMagazineListComponent},
    {path:'magazines/:id',component:AppMagazineDetailsComponent},
    {path:'empregados/:employeeType',component:AppEmployeesCardsComponent,canActivate:[AdministratorGuard]},
    {path:'**',redirectTo:'/artigos',pathMatch:'full'}
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
