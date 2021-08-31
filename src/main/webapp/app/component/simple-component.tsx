import {imageUrl} from "app/shared/util/image-tools-util";
import React from "react";

export const simpleCollage3 = (urls: string[] | null, css) =>
{
  return (
    <div className={`${css}`}>
      <div className={`${css} image-collections border border-secondary rounded-circle overflow-hidden display-flex`}>
        <img alt='Left' className='collage-3-l' src={imageUrl(urls[0])} />
        <img alt='RightTop' className='collage-3-rt  border-start border-bottom border-secondary' src={imageUrl(urls[1])} />
        <img alt='RightBottom' className='collage-3-rb  border-start border-secondary' src={imageUrl(urls[2])} />
      </div>
    </div>);
}
