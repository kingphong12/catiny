import {imageUrl} from "app/shared/util/json-util";
import React from "react";

export const simpleCollage3 = (
  url: { left: string, rightTop: string, rightBottom: string },
  alt: { left: string, rightTop: string, rightBottom: string } | null) =>
{
  return (
    <div className='square50'>
      <div className='square50 image-collections rounded-circle overflow-hidden display-flex'>
        <img alt={alt && alt.left ? alt.left : "Left"} className='collage-3-l' src={imageUrl(url.left)} />
        <img alt={alt && alt.rightTop ? alt.rightTop : "RightTop"} className='collage-3-rt' src={imageUrl(url.rightTop)} />
        <img alt={alt && alt.rightBottom ? alt.rightBottom : "RightBottom"} className='collage-3-rb' src={imageUrl(url.rightBottom)} />
      </div>
    </div>);
}
