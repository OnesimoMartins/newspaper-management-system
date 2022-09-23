
export class User{

  user_id!: Number
  newspaper_id!: Number
  user_name!: string
  authorities!:string[]

  isJournalist():boolean{
    return this.authorities.includes('JRT')
  }
  isPublisher():boolean{
    return this.authorities.includes('ADM_PUBLISHER')
  }
  isCEO() {
    return this.authorities.includes('CEO')
  }

  isJournalistSenior() {
    return this.authorities.includes('JRT_SENIOR')
  }

}

// "ADM_SENIOR",
//     "ADM",
//     "ADM_PUBLISHER"
