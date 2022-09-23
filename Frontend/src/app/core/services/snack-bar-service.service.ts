import { Injectable } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class SnackBarService {

  constructor(private snackBar:MatSnackBar) { }

  openSnackBar(msg:string){
    this.snackBar.open(msg,'Fechar',{
      duration:5000
    });
  }
}
