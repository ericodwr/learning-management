import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { AttendanceResDto } from '@dto/student/attendance.res.dto';
import { EnrollService } from '@services/enroll.service';
import { Observable } from 'rxjs';

function getParams(
  activatedRoute: ActivatedRoute,
  parentLevel?: number
): Observable<Params> {
  let route = activatedRoute;
  if (parentLevel) {
    for (let i = 0; i < parentLevel; i++) {
      if (route.parent) {
        route = route.parent;
      }
    }
  }
  return route.params;
}

@Component({
  selector: 'attendance-detail',
  templateUrl: './attendance-detail.component.html',
})
export class AttendanceComponet implements OnInit {
  classId!: number;
  learningId!: number;
  studentId!: number;
  students: AttendanceResDto[] = [];

  updateAttendace!: any;

  updateAttendance = this.fb.group({
    studentId: this.studentId,
    learningId: this.learningId,
    approvement: false,
  });

  constructor(
    private enrollService: EnrollService,
    private fb: NonNullableFormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    getParams(this.activatedRoute, 0).subscribe((res) => {
      this.classId = Number(res['classId']);
      this.learningId = Number(res['learningId']);
    });

    this.enrollService
      .getStudentAttendance(this.learningId)
      .subscribe((res) => {
        this.students = res;
      });

    this.updateAttendace = this.fb.group({
      studentId: 0,
      learningId: this.learningId,
      approvement: false,
    });
  }

  studentIdClick(i: number) {
    this.studentId = i;
    this.updateAttendace.get('studentId').setValue(this.studentId);
  }

  onChange(b: boolean) {
    this.updateAttendace.get('approvement').setValue(b);
    console.log(this.updateAttendace.value);
  }

  onUpdate() {
    const data = this.updateAttendace.getRawValue();
    this.enrollService.updateAttend(data).subscribe((res) => {
      console.log('updated success!');
    });
  }
}
