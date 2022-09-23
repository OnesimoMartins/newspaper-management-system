import { Magazine } from "src/app/core/models/magazine.model";

export class MagazineEdition{
  magazine:Magazine=new Magazine()
  edition!:Number
  status!:string
  publication_date!:Date
}
