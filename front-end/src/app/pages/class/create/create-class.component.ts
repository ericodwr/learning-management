import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsersResDto } from '@dto/user/users.res.dto';
import { ClassService } from '@services/class.service';
import { UsersService } from '@services/users.service';

@Component({
  selector: 'create-class',
  templateUrl: './create-class.component.html',
})
export class CreateClassComponent implements OnInit {
  teachers!: UsersResDto[];

  classInsertReqDto = this.fb.group({
    className: ['', Validators.required],
    classCode: ['', Validators.required],
    teacherId: [0, Validators.required],
    fileName: ['', Validators.required],
    fileExt: ['', Validators.required],
  });

  constructor(
    private classService: ClassService,
    private fb: NonNullableFormBuilder,
    private userService: UsersService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userService.getAllTeacher().subscribe((res) => (this.teachers = res));
  }

  fileUpload(event: any) {
    const toBase64 = (file: File) =>
      new Promise<string>((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
          if (typeof reader.result === 'string') resolve(reader.result);
        };
        reader.onerror = (error) => reject(error);
      });

    for (let file of event.target.files) {
      toBase64(file).then((result) => {
        const resultBase64 = result.substring(
          result.indexOf(',') + 1,
          result.length
        );
        const resultExtension = file.name.substring(
          file.name.indexOf('.') + 1,
          file.name.length
        );

        this.classInsertReqDto.get('fileName')?.setValue(resultBase64);
        this.classInsertReqDto.get('fileExt')?.setValue(resultExtension);
      });
    }
  }

  onCreate() {
    if (this.classInsertReqDto.valid) {
      const data = this.classInsertReqDto.getRawValue();
      this.classService.insert(data).subscribe((res) => {
        this.router.navigateByUrl('/class');
      });
    } else {
      console.log('Error!');
    }
  }
}
