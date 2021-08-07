import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IGroupPost } from 'app/shared/model/group-post.model';

export interface IGroupProfile {
  id?: number;
  uuid?: string;
  info?: IBaseInfo | null;
  group?: IGroupPost | null;
}

export const defaultValue: Readonly<IGroupProfile> = {};
