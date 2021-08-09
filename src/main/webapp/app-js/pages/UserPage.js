import React, {Fragment} from "react";

import ProfileDetail from '../components/ProfileDetail';
import ProfilePhoto from '../components/ProfilePhoto';
import ProfileCardThree from '../components/ProfileCardThree';
import CreatePost from '../components/CreatePost';
import Events from '../components/Events';
import PostView from '../components/PostView';
import Load from '../components/Load';

const UserPage = () => (
  <Fragment>
    <div className="main-content right-chat-active">
      <div className="middle-sidebar-bottom">
        <div className="middle-sidebar-left pe-0">
          <div className="row">
            <div className="col-xl-12 mb-3">
              <ProfileCardThree/>
            </div>
            <div className="col-xl-4 col-xxl-3 col-lg-4 pe-0">
              <ProfileDetail/>
              <ProfilePhoto/>
              <Events/>
            </div>
            <div className="col-xl-8 col-xxl-9 col-lg-8">
              <CreatePost/>
              <PostView id="32" postvideo="" postimage="post.png" avater="user.png" user="Surfiya Zakir" time="22 min ago"
                        des="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus."/>
              <PostView id="31" postvideo="" postimage="post.png" avater="user.png" user="David Goria" time="22 min ago"
                        des="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus."/>
              <PostView id="33" postvideo="" postimage="post.png" avater="user.png" user="Anthony Daugloi" time="2 hour ago"
                        des="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus."/>
              <Load/>
            </div>
          </div>
        </div>
      </div>
    </div>
  </Fragment>
);

export default UserPage;