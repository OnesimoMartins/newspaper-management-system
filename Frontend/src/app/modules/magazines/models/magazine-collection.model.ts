import { Article } from "src/app/core/models/article.model"
import { Magazine } from "src/app/core/models/magazine.model"
import { Page } from "src/app/core/models/page.model"



export class MagazineCollection{
  _embedded!: _embedded
  page:Page=new Page()
}

class _embedded{
  magazineResponseList:Magazine[]=[]
}


