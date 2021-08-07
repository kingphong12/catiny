import dayjs from 'dayjs';
import { IHistoryUpdate } from 'app/shared/model/history-update.model';
import { IMasterUser } from 'app/shared/model/master-user.model';
import { IClassInfo } from 'app/shared/model/class-info.model';
import { IPermission } from 'app/shared/model/permission.model';
import { ProcessStatus } from 'app/shared/model/enumerations/process-status.model';

export interface IBaseInfo {
  id?: number;
  uuid?: string;
  processStatus?: ProcessStatus | null;
  modifiedClass?: string | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
  notes?: string | null;
  deleted?: boolean | null;
  priorityIndex?: number | null;
  countUse?: number | null;
  histories?: IHistoryUpdate[] | null;
  createdBy?: IMasterUser | null;
  modifiedBy?: IMasterUser | null;
  owner?: IMasterUser | null;
  classInfo?: IClassInfo | null;
  permissions?: IPermission[] | null;
}

export const defaultValue: Readonly<IBaseInfo> = {
  deleted: false,
};
