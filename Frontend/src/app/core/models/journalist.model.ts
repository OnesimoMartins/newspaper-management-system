import { EmployeeDetails } from "./employee.interface"


export class Journalist {


  getFullName():string{
    return this.first_name+' '+this.last_name
  }


  id: Number=0
  email: string=''
  first_name: string=''
  last_name: string=''
  recruitment_date!:Date
  experience_level!:string


  // full_Name: string=this.first_name+' '+this.last_name
  // job_tittle: string='Journalist'


 }
