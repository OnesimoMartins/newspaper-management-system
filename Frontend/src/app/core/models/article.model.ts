import { Category } from "./category.model"
import { Journalist } from "./journalist.model"
import { Magazine } from "./magazine.model"

export class Article{
  id: Number=0
  tittle: string=''
  summary: string=''
  keywords: string=''
  state: string=''
  body: string=''
  magazine!:Magazine
  journalist_creator:Journalist=new Journalist()
  category:Category=new Category()
  _links:any
}

