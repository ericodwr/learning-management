import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { MaterialResDto } from '@dto/material/material.res.dto';
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
  selector: 'material-detail',
  templateUrl: './material-details.component.html',
})
export class MaterialDetailComponent implements OnInit {
  classId!: number;
  learningId!: number;
  materialId!: number;

  material?: MaterialResDto;

  constructor(
    private activatedRoute: ActivatedRoute,
    private materialService: MaterialService
  ) {}

  ngOnInit(): void {
    getParams(this.activatedRoute, 0).subscribe((res) => {
      this.learningId = Number(res['learningId']);
      this.materialId = Number(res['materialId']);
    });

    getParams(this.activatedRoute, 1).subscribe((res) => {
      this.classId = Number(res['classId']);
    });

    this.materialService.getById(this.materialId).subscribe((res) => {
      this.material = res;
    });
  }
}
