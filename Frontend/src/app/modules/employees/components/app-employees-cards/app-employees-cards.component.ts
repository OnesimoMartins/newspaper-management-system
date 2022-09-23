import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Page } from 'src/app/core/models/page.model';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-employees-cards',
  templateUrl: './app-employees-cards.component.html',
  styleUrls: ['./app-employees-cards.component.css']
})
export class AppEmployeesCardsComponent implements OnInit {

  list:any[]=[]
  page:Page=new Page()
  type!:string
  jobTittle:string=''

  constructor(private employeeService:EmployeeService,private activeRoute:ActivatedRoute) {}

  ngOnInit(): void {
  // console.log("trema");

    this. type =this.activeRoute.snapshot.paramMap.get('employeeType')!
    this.loadData(0)
  }


  loadData(page:Number){
    if(this.type=='jornalistas'){
      this.jobTittle='Jornalist'
      this.loadJournalists(page)
     }
      else if(this.type=='administradores'){
      this.jobTittle='Administrador'
      this.loadAdministrators(page)
     }
  }

   loadJournalists(page:Number){
    this.employeeService.getJournalistsPage(page).subscribe(
      it=>{
        this.list=it._embedded.journalistResponseList
        this.page=it.page
        })
   }

   loadAdministrators(page:Number){
    this.employeeService.getAdministratorsPage(page).subscribe(
      it=>{
        console.log(it);

        this.list=it._embedded.administratorResponseList
        this.page=it.page
        })
   }

   onPaging(e:any){
    this.loadData(e.pageIndex);
   }


}
