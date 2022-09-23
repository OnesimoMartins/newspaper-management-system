import {  Component } from '@angular/core';
import { MediaMatcher,BreakpointObserver } from '@angular/cdk/layout';
// import { NgxLoggerLevel } from 'ngx-logger';

const miniDevice='(max-width: 1000px)'

@Component({
  selector: 'app-root',
  template:`<router-outlet></router-outlet>`
})
export class AppComponent {

  // logger= new NGXLogger()
  mobileQuery!:MediaQueryList

  constructor(media:MediaMatcher,
    deviceObesrver:BreakpointObserver){

    this.mobileQuery=media.matchMedia(miniDevice)

    deviceObesrver.observe(miniDevice).subscribe(()=>{
      this.mobileQuery=media.matchMedia(miniDevice)

    })

  }

}
