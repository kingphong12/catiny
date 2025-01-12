import { IVideo } from 'app/shared/model/video.model';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IVideoLiveStreamBuffer } from 'app/shared/model/video-live-stream-buffer.model';

export interface IVideoStream {
  id?: number;
  uuid?: string;
  isLivestreaming?: boolean | null;
  video?: IVideo | null;
  info?: IBaseInfo | null;
  videoLiveStreamBuffers?: IVideoLiveStreamBuffer[] | null;
}

export const defaultValue: Readonly<IVideoStream> = {
  isLivestreaming: false,
};
