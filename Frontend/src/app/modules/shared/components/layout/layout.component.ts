import { BreakpointObserver, MediaMatcher } from '@angular/cdk/layout';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SpinnerService } from 'src/app/core/services/spinner.service';
import { AuthenticationService } from 'src/app/modules/authorization/services/authentication.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css'],
})
export class LayoutComponent {
  mobileQuery!: MediaQueryList;

  constructor(media: MediaMatcher,
     deviceObesrver: BreakpointObserver,
     private authService:AuthenticationService,
     private router:Router,
     public spinnerService:SpinnerService ) {

    const miniDevice = '(max-width: 1000px)';
    this.mobileQuery = media.matchMedia(miniDevice);

    deviceObesrver.observe(miniDevice).subscribe(() => {
      this.mobileQuery = media.matchMedia(miniDevice);

    });
  }


  logout(){
    this.authService.logout().subscribe( it=>
     this.router.navigateByUrl('auth/login')
  )}

  canAccessEmployess():boolean{
    return this.authService.getCurrentUser().isJournalist()
  }
}
