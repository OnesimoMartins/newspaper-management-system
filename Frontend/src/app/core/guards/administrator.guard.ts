import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from 'src/app/modules/authorization/services/authentication.service';
import { SnackBarService } from '../services/snack-bar-service.service';

@Injectable({
  providedIn: 'root',
})
export class AdministratorGuard implements CanActivate {
  constructor(private auth: AuthenticationService
    ,private notificationSevice:SnackBarService,
    private router:Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {



    if (!this.auth.getCurrentUser().isJournalist())
      return true;

      this.notificationSevice.openSnackBar("Apenas administradores estão autorizados a ver todos os empregados.")

      this.router.navigateByUrl('/')

    return false;
  }
}
