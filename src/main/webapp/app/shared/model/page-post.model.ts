import { IPageProfile } from 'app/shared/model/page-profile.model';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPost } from 'app/shared/model/post.model';
import { IFollowPage } from 'app/shared/model/follow-page.model';
import { ITopicInterest } from 'app/shared/model/topic-interest.model';

export interface IPagePost {
  id?: number;
  uuid?: string;
  name?: string;
  avatar?: string | null;
  quickInfo?: string | null;
  profile?: IPageProfile | null;
  info?: IBaseInfo | null;
  posts?: IPost[] | null;
  followeds?: IFollowPage[] | null;
  topicInterests?: ITopicInterest[] | null;
}

export const defaultValue: Readonly<IPagePost> = {};
