import { Pipe, PipeTransform } from '@angular/core';
import { BASE_URL } from '../constant/api.constant';
import { LearningService } from '@services/learning.service';
import { LearningResDto } from '@dto/learning/learning.res.dto';
import { MaterialService } from '@services/material.service';

@Pipe({
  name: 'material',
  standalone: true,
})
export class MaterialPipe implements PipeTransform {
  constructor(
    private learning: LearningService,
    private material: MaterialService
  ) {}

  dataArr!: any[];

  transform(value: any): any[] {
    let dataArr!: any[];
    // this.learning.getByClassId(value).subscribe((res) => {
    //   dataArr.push(res[0]);
    //   console.log(res);
    //   for (let r of res) {
    //     dataArr.push(res);
    //   }
    // });

    this.material.getByLearningId(value).subscribe((res) => {
      console.log(res);
      dataArr = res;
      dataArr.push(res[0]);
      // dataArr = res;
      // console.log(res);
      // for (let r of res) {
      // console.log(res);
      // console.log(r);
      //   this.dataArr = res;
      // }
    });

    // Object.keys(data).forEach((res) => dataArr.push(res));

    console.log(dataArr);

    return [];
  }
}
