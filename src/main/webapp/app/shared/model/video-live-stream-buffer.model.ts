import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IVideoStream } from 'app/shared/model/video-stream.model';

export interface IVideoLiveStreamBuffer {
  id?: number;
  uuid?: string;
  bufferDataContentType?: string | null;
  bufferData?: string | null;
  info?: IBaseInfo | null;
  videoStream?: IVideoStream | null;
}

export const defaultValue: Readonly<IVideoLiveStreamBuffer> = {};
