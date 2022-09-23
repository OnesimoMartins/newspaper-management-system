import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SpinnerService {
  constructor() {}

  processing = new BehaviorSubject(false);

  show() {
    this.processing.next(true);
  }

  hide() {
    this.processing.next(false);
  }
}
