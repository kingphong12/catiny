import './footer.scss';

import React from 'react';
import {Link} from 'react-router-dom';
import {imageUrl} from "app/shared/util/image-tools-util";
import {useAppDispatch, useAppSelector} from "app/config/store";
import {rightChatActive} from "app/modules/setting/setting.reducer";

const Footer = props =>
{
  const dispatch = useAppDispatch();
  const userAvatarUrl = useAppSelector(state => imageUrl(state.authentication.masterUser.avatar))
  return (
    <div className='app-footer border-0 shadow-lg bg-primary-gradiant'>
      <Link to='/home' className='nav-content-bttn nav-center'><i className='feather-home' /></Link>
      <Link to='/defaultvideo' className='nav-content-bttn'><i className='feather-package' /></Link>
      <Link to='/defaultlive' className='nav-content-bttn' data-tab='chats'><i className='feather-layout' /></Link>
      <div className='nav-content-bttn' onClick={() => dispatch(rightChatActive())}><i className='feather-message-square' /></div>
      <Link to='/defaultsettings' className='nav-content-bttn'>
        <img src={userAvatarUrl} alt='user' className='w30 shadow-xss' /></Link>
    </div>
  );
};

export default Footer;
