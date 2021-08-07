import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IRankGroup } from 'app/shared/model/rank-group.model';
import { IMasterUser } from 'app/shared/model/master-user.model';

export interface IRankUser {
  id?: number;
  uuid?: string;
  ratingPoints?: number | null;
  info?: IBaseInfo | null;
  rankGroup?: IRankGroup | null;
  owner?: IMasterUser | null;
}

export const defaultValue: Readonly<IRankUser> = {};
