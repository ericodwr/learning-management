import { MaterialResDto } from '@dto/material/material.res.dto';
import { TaskResDto } from '@dto/task/task.res.dto';

export interface LearningResDto {
  id: number;
  learningName: string;
  learningCode: string;
  startTime: string;
  endTime: string;
  materials: MaterialResDto[];
  tasks: TaskResDto[];
}
