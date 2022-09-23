import { Component, OnInit,ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Magazine } from 'src/app/core/models/magazine.model';
import { Page } from 'src/app/core/models/page.model';
import { MagazineService } from '../../services/magazine.service';

@Component({
  selector: 'app-app-magazine-list',
  templateUrl: './app-magazine-list.component.html',
  styleUrls: ['./app-magazine-list.component.css']
})
export class AppMagazineListComponent implements OnInit {

constructor(private magazineService:MagazineService,
    private router:Router
    ) {  }

datasource: MatTableDataSource<Magazine>= new MatTableDataSource<Magazine>()

page:Page=new Page()

queryFilter=''

@ViewChild (MatPaginator)
paginator!:MatPaginator


headerColumns=['id','name','publisher','last_edition','actions']

ngOnInit(): void {
this.loadData(0,6)
}

ngAfterViewInit(): void {
this.datasource.paginator=this.paginator;
}

loadData(number:Number,size:Number){

this.magazineService.getNewspaperMagazinePage(number,size).subscribe(it=>{

  this.datasource=new MatTableDataSource<Magazine>(it?._embedded?.magazineResponseList)
this.page=it.page
})

}

onPaging(e:PageEvent){
this.loadData(e.pageIndex,e.pageSize)
}

openMagazineDetails(magazine:Magazine){
this.router.navigateByUrl('magazines/'+magazine.id)
}


getTittle(){
if(this.queryFilter=='aprovados')
return 'Artigos aprovados'

else if(this.queryFilter=='reprovados')
return 'Artigos reprovados'

return "Todos artigos"
}

getSubtittle(){

if(this.queryFilter=='aprovados')
return "A lista abaixo contém a lista de artigos já aprovados."

else  if(this.queryFilter=='reprovados')
return "A lista abaixo contém a lista de artigos reprovados."

return "A lista abaixo contém a lista de todos os artigos ."
}

hasArticles(){
return this.datasource.data.length>0
}

}
