import { Administrator } from "src/app/core/models/administrator.model"
import { Page } from "src/app/core/models/page.model"


export class AdministratorCollection{
  _embedded!: _embedded
  page:Page=new Page()
}

class _embedded{
  administratorResponseList:Administrator[]=[]
}


