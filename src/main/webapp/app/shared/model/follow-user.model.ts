import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IMasterUser } from 'app/shared/model/master-user.model';

export interface IFollowUser {
  id?: number;
  uuid?: string;
  info?: IBaseInfo | null;
  follow?: IMasterUser | null;
}

export const defaultValue: Readonly<IFollowUser> = {};
