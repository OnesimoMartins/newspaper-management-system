import { Journalist } from "src/app/core/models/journalist.model"
import { Page } from "src/app/core/models/page.model"


export class JournalistCollection{
  _embedded!: _embedded
  page:Page=new Page()
}

class _embedded{
  journalistResponseList:Journalist[]=[]
}


