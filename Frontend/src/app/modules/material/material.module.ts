import { NgModule } from '@angular/core';
import { MatToolbarModule  } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTabsModule} from '@angular/material/tabs';
import { MatTooltipModule} from '@angular/material/tooltip';
import { MatSelectModule} from '@angular/material/select';
import { MatSnackBarModule} from '@angular/material/snack-bar';



@NgModule({
  exports:[
    MatSnackBarModule,
    MatSelectModule,
    MatTooltipModule,
    MatTabsModule,
    MatPaginatorModule,
    MatInputModule,
    MatSlideToggleModule,
    MatTableModule,
    MatCardModule,
    MatButtonModule,
    MatListModule,
    MatDividerModule,
    MatProgressBarModule,
    MatSidenavModule,
    MatMenuModule,
    MatIconModule,
    MatToolbarModule,
    MatFormFieldModule
  ]
})
export class MaterialModule {

}
