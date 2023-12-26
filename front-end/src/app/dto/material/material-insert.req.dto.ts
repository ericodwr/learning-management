import { MaterialFilesInsertReqDto } from './material-files-insert.req.dto';

export interface MaterialInsertReqDto {
  materialName: string;
  materialTitle: string;
  materialText: string;
  learningId: number;
  materialFilesReq: MaterialFilesInsertReqDto[];
}
