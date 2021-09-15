import './home.scss';

import React from 'react';
import StorySlider from "app/component/story-slider";
import CreatePost from "app/component/create-post";
import PostView from "app/component/post-view";
import MemberSlider from "app/component/member-slider";
import FriendSilder from "app/component/friend-silder";
import Load from "app/component/load";
import Friends from "app/component/friends";
import Contacts from "app/component/contacts";
import Group from "app/component/group";
import Events from "app/component/events";
import ProfilePhoto from "app/component/profile-photo";

export const Home = () =>
{
  return (
    <div>
      <div className='main-content right-chat-active'>
        <div className='middle-sidebar-bottom'>
          <div className='middle-sidebar-left'>
            <div className='row feed-body'>
              <div className='col-xl-8 col-xxl-9 col-lg-8'>
                <StorySlider />
                <CreatePost />
                <PostView id='32' postvideo='' postimage='post.png' avater='user.png' user='Surfiya Zakir' time='22 min ago'
                          des='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus.' />
                <PostView id='31' postvideo='' postimage='post.png' avater='user.png' user='David Goria' time='22 min ago'
                          des='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus.' />
                <PostView id='33' postvideo='' postimage='post.png' avater='user.png' user='Anthony Daugloi' time='2 hour ago'
                          des='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus.' />
                <MemberSlider />
                <PostView id='35' postvideo='' postimage='post.png' avater='user.png' user='Victor Exrixon' time='3 hour ago'
                          des='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus.' />
                <FriendSilder />
                <PostView id='36' postvideo='' postimage='post.png' avater='user.png' user='Victor Exrixon' time='12 hour ago'
                          des='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus.' />
                <Load />
              </div>
              <div className='col-xl-4 col-xxl-3 col-lg-4 ps-lg-0'>
                <Friends />
                <Contacts />
                <Group />
                <Events />
                <ProfilePhoto />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
