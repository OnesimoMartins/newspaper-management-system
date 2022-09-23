import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { SnackBarService } from 'src/app/core/services/snack-bar-service.service';
import { Login } from '../../models/login.model';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-app-login',
  templateUrl: './app-login.component.html',
  styleUrls: ['./app-login.component.css'],
})
export class AppLoginComponent implements OnInit {
  loginForm!: FormGroup;
  loading!: boolean;
  invalidCredentialsMessage!:string

  constructor(
    private router: Router,
    private titleService: Title,
    private authenticationService: AuthenticationService,
    private notificationService:SnackBarService
  ) {}

  ngOnInit() {
    this.titleService.setTitle('Newpaper Gest | login');
    this.createForm();
  }

  private createForm() {

    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
    });
  }

  login() {
    const email = this.loginForm.get('email')?.value;
    const password = this.loginForm.get('password')?.value;

    this.loading = true;

    this.authenticationService.login(new Login(email, password)).subscribe(
      data => {
        this.notificationService.openSnackBar('Sessão iniciada com sucesso.')
        this.router.navigate(['/']);
      },
      error => {
        this.loading = false;
        this.invalidCredentialsMessage='As suas credenciais estão incorrectas.'
        this.loginForm.get('password')?.reset('')

      }
    );
  }


}
