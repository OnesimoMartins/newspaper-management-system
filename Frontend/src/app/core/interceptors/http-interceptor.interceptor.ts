import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable,tap } from 'rxjs';
import { SpinnerService } from '../services/spinner.service';


@Injectable()
export class AppHttpInterceptor implements HttpInterceptor {

  constructor(private spinnerService:SpinnerService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    this.spinnerService.show()

    console.log(this.spinnerService.processing.value);


    if (!req.url.includes('/oauth/token') ) {

      req = req.clone({
            setHeaders: {
              Authorization: `Bearer ${localStorage.getItem('token')}`,
            }
        });

    }

    return next.handle(req).pipe( tap(()=>{
      this.spinnerService.hide()
    },
    error=>{
      this.spinnerService.hide()
    }
    ));

  }


}
