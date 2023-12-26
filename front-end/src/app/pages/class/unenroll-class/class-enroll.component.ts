import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ClassResDto } from '@dto/class/class.res.dto';
import { AuthService } from '@services/auth.service';
import { ClassService } from '@services/class.service';
import { StudentService } from '@services/student.service';

@Component({
  selector: 'class-enroll',
  templateUrl: './class-enroll.component.html',
})
export class ClassEnrollComponent implements OnInit {
  classes!: ClassResDto[];
  roleCode!: string;

  constructor(
    private classService: ClassService,
    private authService: AuthService,
    private router: Router,
    private fb: NonNullableFormBuilder,
    private studentService: StudentService
  ) {}

  ngOnInit(): void {
    const profile = this.authService.getProfile();
    if (profile?.roleCode) {
      this.roleCode = profile.roleCode;
    }

    this.classService
      .getAllStudentUnenroll()
      .subscribe((res) => (this.classes = res));
  }

  get checkClass() {
    return this.classes?.length > 0 ? false : true;
  }

  enrollClass(id: number) {
    const data = {
      classId: id,
    };
    this.studentService.enrollClass(data).subscribe((res) => {
      this.router.navigateByUrl('/class');
    });
    console.log(data);
  }
}
