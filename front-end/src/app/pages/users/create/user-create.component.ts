import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RoleResDto } from '@dto/role/role.res.dto';
import { RoleService } from '@services/role.service';
import { UsersService } from '@services/users.service';
import { Roles } from 'src/app/constant/role.constant';

@Component({
  selector: 'user-create',
  templateUrl: './user-create.component.html',
})
export class UserCreateComponent implements OnInit {
  userInsertReqDto = this.fb.group({
    username: ['', Validators.required],
    roleId: [0, Validators.required],
    fullName: ['', Validators.required],
    phoneNumb: ['', Validators.required],
  });

  sending = false;

  constructor(
    private roleService: RoleService,
    private fb: NonNullableFormBuilder,
    private userService: UsersService,
    private router: Router
  ) {}

  roles: RoleResDto[] = [];

  ngOnInit(): void {
    this.roleService.getAllRole().subscribe((res) => {
      this.roles = res;

      for (let r of this.roles) {
        if (r.roleCode === Roles.TEACHER) {
          this.userInsertReqDto = this.fb.group({
            username: ['', Validators.required],
            roleId: [r.id, Validators.required],
            fullName: ['', Validators.required],
            phoneNumb: ['', Validators.required],
          });
        }
      }
    });
  }

  onCreate() {
    if (this.userInsertReqDto.valid) {
      const data = this.userInsertReqDto.getRawValue();
      this.sending = true;
      this.userService.insert(data).subscribe((res) => {
        this.router.navigateByUrl('/users');
      });
    } else {
      console.log('ISI DULU');
      this.sending = false;
    }
  }
}
