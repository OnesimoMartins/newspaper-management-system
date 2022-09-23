import { Article } from "src/app/core/models/article.model"
import { Page } from "src/app/core/models/page.model"



export class ArticleCollection{
  _embedded!: _embedded
  page:Page=new Page()
}

class _embedded{
  articleResponseList:Article[]=[]
}


