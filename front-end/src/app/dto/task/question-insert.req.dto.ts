import { QuestionFilesInsertReqDto } from './question-files-insert.req.dto';

export interface QuestionInsertReqDto {
  questionName: string;
  typeId: number;
  questionFiles: QuestionFilesInsertReqDto[];
}
