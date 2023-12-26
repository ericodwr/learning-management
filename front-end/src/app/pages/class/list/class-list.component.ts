import { Component, OnInit } from '@angular/core';
import { ClassResDto } from '@dto/class/class.res.dto';
import { AuthService } from '@services/auth.service';
import { ClassService } from '@services/class.service';
import { Roles } from 'src/app/constant/role.constant';

@Component({
  selector: 'class-list',
  templateUrl: './class-list.component.html',
})
export class ClassListComponent implements OnInit {
  classes!: ClassResDto[];
  roleCode!: string;

  constructor(
    private classService: ClassService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const profile = this.authService.getProfile();
    if (profile?.roleCode) {
      this.roleCode = profile.roleCode;
    }

    if (this.roleCode === Roles.SUPER_ADMIN) {
      this.classService.getAllClass().subscribe((res) => (this.classes = res));
    } else if (this.roleCode === Roles.TEACHER) {
      this.classService
        .getAllTeacherClass()
        .subscribe((res) => (this.classes = res));
    } else if (this.roleCode === Roles.STUDENT) {
      console.log('this is student');
      this.classService.getAllStudentClass().subscribe((res) => {
        console.log(res);

        this.classes = res;
      });
    }
  }

  get isAdmin() {
    return Roles.SUPER_ADMIN === this.roleCode;
  }

  get checkClass() {
    return this.classes?.length > 0 ? false : true;
  }
}
