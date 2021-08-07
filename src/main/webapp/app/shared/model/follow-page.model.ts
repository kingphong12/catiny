import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPagePost } from 'app/shared/model/page-post.model';

export interface IFollowPage {
  id?: number;
  uuid?: string;
  info?: IBaseInfo | null;
  pageDetails?: IPagePost | null;
}

export const defaultValue: Readonly<IFollowPage> = {};
