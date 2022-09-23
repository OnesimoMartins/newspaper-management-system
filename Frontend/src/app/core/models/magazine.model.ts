import { Administrator } from "./administrator.model"
import { Newspaper } from "./newspaper.model"

export class Magazine {
  id: Number=0
  name: string=''
  newspaper: Newspaper=new Newspaper()
  administrator: Administrator=new Administrator()
  publisher: Administrator=new Administrator()
  last_edition: Number=0
  is_last_edition_published!: boolean
}
