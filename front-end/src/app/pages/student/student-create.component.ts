import { Component } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsersService } from '@services/users.service';

@Component({
  selector: 'create-student',
  templateUrl: './student-create.component.html',
})
export class StudentComponent {
  loading!: boolean;

  userInsertReqDto = this.fb.group({
    username: ['', Validators.required],
    roleId: [0, Validators.required],
    fullName: ['', Validators.required],
    phoneNumb: ['', Validators.required],
  });

  constructor(
    private fb: NonNullableFormBuilder,
    private userService: UsersService,
    private router: Router
  ) {}

  onCreate() {
    if (this.userInsertReqDto.valid) {
      this.loading = true;
      const data = this.userInsertReqDto.getRawValue();
      this.userService.insertStudent(data).subscribe((res) => {
        console.log(res);
        this.router.navigateByUrl('/login');
      });
    } else {
      this.loading = false;
    }
  }
}
