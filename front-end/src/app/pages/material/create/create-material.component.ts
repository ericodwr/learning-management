import { Component, OnInit } from '@angular/core';
import { FormArray, NonNullableFormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { MaterialFilesInsertReqDto } from '@dto/material/material-files-insert.req.dto';
import { MaterialInsertReqDto } from '@dto/material/material-insert.req.dto';
import { MaterialService } from '@services/material.service';
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
  selector: 'create-material',
  templateUrl: './create-material.component.html',
})
export class CreateMaterialComponent implements OnInit {
  classId!: number;
  learningId!: number;
  materialInsertReqDtoArr: MaterialInsertReqDto[] = [];
  materialFilesInsertReqDtoArr: MaterialFilesInsertReqDto[] = [];

  materialInsertReqDto = this.fb.group({
    data: this.fb.array(this.materialInsertReqDtoArr),
  });

  constructor(
    private fb: NonNullableFormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private materialService: MaterialService
  ) {}

  ngOnInit(): void {
    getParams(this.activatedRoute, 1).subscribe((res) => {
      this.classId = Number(res['classId']);
    });
  }

  get data() {
    return this.materialInsertReqDto.get('data') as FormArray;
  }

  onAddMaterial() {
    this.activatedRoute.paramMap.subscribe((param) => {
      this.learningId = Number(param.get('learningId'));
      this.data.push(
        this.fb.group({
          materialName: ['', Validators.required],
          materialTitle: ['', Validators.required],
          materialText: ['', Validators.required],
          learningId: [Number(param.get('learningId')), Validators.required],
          materialFilesReq: this.fb.array(this.materialFilesInsertReqDtoArr),
        })
      );
    });
  }

  onMaterialRemove(i: number) {
    this.data.removeAt(i);
  }

  materialFiles(i: number) {
    return this.data.at(i).get('materialFilesReq') as FormArray;
  }

  fileUpload(event: any, i: number) {
    const toBase64 = (file: File) =>
      new Promise<string>((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
          if (typeof reader.result === 'string') resolve(reader.result);
        };
        reader.onerror = (error) => reject(error);
      });

    this.materialFiles(i).clear();
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
        this.materialFiles(i).push(
          this.fb.group({
            fileName: [resultBase64, Validators.required],
            fileExtens: [resultExtension, Validators.required],
          })
        );
        // this.classInsertReqDto.get('fileName')?.setValue(resultBase64);
        // this.classInsertReqDto.get('fileExt')?.setValue(resultExtension);
      });
    }
  }

  onSubmit() {
    if (this.materialInsertReqDto.valid) {
      const data = this.materialInsertReqDto.getRawValue().data;
      if (data.length > 0) {
        this.materialService.insert(data).subscribe((res) => {
          this.router.navigateByUrl(`/learning/${this.classId}`);
        });
      } else {
        console.log('please input material');
      }
    } else {
      console.log('errors');
    }
  }
}
