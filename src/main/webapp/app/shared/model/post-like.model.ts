import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPost } from 'app/shared/model/post.model';
import { IPostComment } from 'app/shared/model/post-comment.model';

export interface IPostLike {
  id?: number;
  uuid?: string;
  info?: IBaseInfo | null;
  post?: IPost | null;
  comment?: IPostComment | null;
}

export const defaultValue: Readonly<IPostLike> = {};
