import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateMaterialComponent } from './create/create-material.component';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialDetailComponent } from './details/material-details.component';
import { UrlPipe } from 'src/app/pipes/url.pipe';

const routes: Routes = [
  {
    path: ':learningId/create',
    component: CreateMaterialComponent,
  },
  {
    path: ':learningId/detail/:materialId',
    component: MaterialDetailComponent,
  },
];

@NgModule({
  declarations: [CreateMaterialComponent, MaterialDetailComponent],
  imports: [
    RouterModule.forChild(routes),
    ReactiveFormsModule,
    CommonModule,
    UrlPipe,
  ],
  exports: [RouterModule],
})
export class MaterialRouting {}
