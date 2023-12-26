import { Component } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { AuthService } from '@services/auth.service';
import { LoginService } from '@services/login.service';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-login',
  // templateUrl: './login.component.html',
  templateUrl: './login.component.html',
})
export class LoginComponent {
  loading = false;

  loginReqDto = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]],
  });

  // Constructor
  constructor(
    private authService: AuthService,
    private loginService: LoginService,
    private fb: NonNullableFormBuilder,
    private router: Router,
    private title: Title
  ) {
    this.title.setTitle('Dashboard | Boottest Erico');
  }

  onLogin() {
    if (this.loginReqDto.valid) {
      this.loading = true;
      this.loginService
        .login(this.loginReqDto.getRawValue())
        .subscribe((result) => {
          localStorage.setItem('data', JSON.stringify(result));
          this.loading = false;
          this.router.navigateByUrl('/dashboard');
        });
    } else {
      this.loading = false;
      console.log('Invalid!');
    }
  }
}
