import { QuestionInsertReqDto } from './question-insert.req.dto';

export interface TaskInsertReqDto {
  taskName: string;
  taskCode: string;
  learningId: number;
  startTime: string;
  endTime: string;
  questionList: QuestionInsertReqDto[];
}
