import { Component } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsersService } from '../../../services/users.service';

@Component({
  selector: 'user-change-password',
  templateUrl: './user-changepassword.component.html',
})
export class UserChangePasswordComponent {
  userPasswordDto = this.fb.group({
    oldPassword: ['', Validators.required],
    newPassword: ['', Validators.required],
    confirmNewPassword: ['', Validators.required],
  });

  constructor(
    private userService: UsersService,
    private fb: NonNullableFormBuilder,
    private router: Router
  ) {}

  onSubmit() {
    if (this.userPasswordDto.valid) {
      const data = this.userPasswordDto.getRawValue();
      this.userService.updatePassword(data).subscribe((res) => {
        localStorage.clear();
        this.router.navigateByUrl('/login');
      });
    } else {
    }
  }
}
