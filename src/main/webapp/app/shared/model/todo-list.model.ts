import { IBaseInfo } from 'app/shared/model/base-info.model';

export interface ITodoList {
  id?: number;
  uuid?: string;
  title?: string | null;
  content?: string | null;
  info?: IBaseInfo | null;
}

export const defaultValue: Readonly<ITodoList> = {};
