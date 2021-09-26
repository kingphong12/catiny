import "./right-chat.scss";
import React, {useEffect, useState} from 'react';
import {useAppDispatch, useAppSelector} from 'app/config/store';
import {createMessageGroup, getAllMessageGroupsJoined, updateStatusUserInGroup,} from 'app/shared/layout/right-chat/right-chat.reducer';
import {IMessageGroup} from 'app/shared/model/message-group.model';
import {simpleCollage3} from 'app/component/simple-component';
import {imageUrl} from 'app/shared/util/image-tools-util';
import {cleanSearchMasterUser, searchMasterUser} from 'app/component/reducer/master-user.reducer';
import {useSelector} from 'react-redux';
import ChatBox from './chat-box';
import Websocket from "app/config/Websocket";

const RightChat = () =>
{
  const dispatch = useAppDispatch();
  const masterUser = useAppSelector(state => state.authentication.masterUser);
  const resultSearchUsers = useSelector((state: any) =>
    state.masterUserComponent.resultSearchUsers.filter(v => v.uuid !== masterUser.uuid)
  );

  const websocket = new Websocket();
  const [userToCreateMessageGroup, setUserToCreateMessageGroup] = useState([]);
  const [groupNameToCreateMessageGroup, setGroupNameToCreateMessageGroup] = useState('');
  const [groupNameIsCustom, setGroupNameIsCustom] = useState(false);

  const [openGroupIds, setOpenGroupIds] = useState([]);


  const allMessageGroupsJoined = useAppSelector(state => state.rightChat.messageGroups);
  useEffect(() =>
  {
    dispatch(getAllMessageGroupsJoined({}));
  }, []);

  useEffect(() =>
  {
    websocket.connect('/websocket/main');
    if (!masterUser.uuid) return;
    websocket.subscribeUserConsumer(`/notifications/message-group.status`, ({body}) =>
    {
      dispatch(updateStatusUserInGroup(JSON.parse(body)));
    });

  }, [masterUser.uuid]);


  const openChatBox = (messageGroup: IMessageGroup) =>
  {
    setOpenGroupIds(prev =>
    {
      const result = [...new Set(prev.concat(messageGroup))];
      result.length > 3 && result.shift();
      return result;
    });
  };
  const [keywordSearchUser, setKeywordSearchUser] = useState("");


  const typingSearchUsers = async event =>
  {
    const a = event.target.value;
    setKeywordSearchUser(a);
    await new Promise(executor => setTimeout(executor, 500));
    if (a === event.target.value) dispatch(searchMasterUser({query: a}));
  };

  const avatarLoader = (messageGroupAvatar, css) =>
  {
    if (!css || css.includes('square')) css = 'square35';
    if (messageGroupAvatar)
    {
      const avatarJsonParsed = JSON.parse(messageGroupAvatar);
      if (avatarJsonParsed && avatarJsonParsed.links && avatarJsonParsed.links.length > 2)
        return simpleCollage3(avatarJsonParsed.links, css);
      if (avatarJsonParsed && avatarJsonParsed.urls && avatarJsonParsed.urls.length > 2) return simpleCollage3(avatarJsonParsed.urls, css);
    }
    return <img src={imageUrl(messageGroupAvatar)} alt='avater' className={`${css} border border-secondary rounded-circle  me-1`} />;
  };

  function handleGroupOnline(idUserOnline)
  {
    return idUserOnline.filter(value => masterUser.uuid !== value).length > 0;
  }

  const rightChatActive = useAppSelector(state => state.setting.rightChatActive);
  const groupJoinedComponent = () =>
    allMessageGroupsJoined.map(messageGroup => (
      // Start Single Demo
      <li
        key={messageGroup.uuid}
        className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'
      >
        <figure className='avatar float-left mb-0 me-2'>{avatarLoader(messageGroup.avatar, '')}</figure>
        <h3 className='fw-700 mb-0 mt-0'>
          <span
            className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer'
            onClick={() => openChatBox(messageGroup)}>
            {messageGroup && messageGroup.groupName ?
              (messageGroup.groupName.length > 20 ?
                (messageGroup.groupName.substring(0, 18) + ' ...') :
                (messageGroup.groupName)) :
              (<del>No Name</del>)}
          </span>
        </h3>
        <span className={`${handleGroupOnline(messageGroup.idUserOnline) ? 'bg-success' : "bg-grey"}  ms-auto btn-round-xss`} />
      </li>
      // End Single Demo
    ))

  const userSearchComponent = () =>
  {
    const addToCreateGroup = user => setUserToCreateMessageGroup(prev =>
    {
      const result =
        prev.filter(v => v.uuid === user.uuid).length < 1 ? prev.concat(user) : prev.filter(v => v.uuid !== user.uuid);
      !groupNameIsCustom && setGroupNameToCreateMessageGroup(() => result.map(v => v.fullName).join(','));
      return result;
    });
    return resultSearchUsers.map(user => (
      <li
        key={user.uuid}
        className='m-1 d-flex rounded-3 bg-white theme-dark-bg'
        onClick={() => addToCreateGroup(user)}
      >
        <figure className='avatar float-left mb-0 me-2'>{avatarLoader(user.avatar, '')}</figure>
        <h3 className='fw-700 mb-0 mt-0'>
          <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer'>
            {user.fullName.substring(0, 20)}
            {user.fullName.length > 20 && '...'}
          </span>
        </h3>
        {/*<h5 className='font-xsssss m-0 '>{user.nickname.substring(0, 25)}</h5>*/}
        {userToCreateMessageGroup.filter(v => v.uuid === user.uuid).length > 0 ?
          <i className='feather-check-circle font-xsss text-grey-600 ms-auto ' /> :
          <i className='feather-x font-xsss text-grey-600 ms-auto' />
        }
      </li>
    ));
  }


  return (
    <div id='main-content-wrap' className={`right-chat nav-wrap mt-2 right-scroll-bar ${rightChatActive ? 'active-sidebar' : ''}`}>
      <div className='middle-sidebar-right-content bg-white shadow-xss rounded-xxl'>
        <div className='section full pe-3 ps-4 pt-2 position-relative feed-body'>
          <h4 className='font-xsssss text-grey-500 text-uppercase '>
            <div className='form-group mb-0 icon-input'>
              <i className='feather-search font-sm text-grey-400' />
              {resultSearchUsers.length > 0 && <i className='feather-x-square font-sm text-grey-600'
                                                  onClick={() =>
                                                  {
                                                    setKeywordSearchUser("");
                                                    dispatch(cleanSearchMasterUser());
                                                  }} />}
              <input
                type='text'
                className='bg-grey border-0 lh-28 ps-5 pe-3 font-xssss fw-500 rounded-xl theme-dark-bg'
                placeholder='Search user ...'
                onChange={typingSearchUsers}
                value={keywordSearchUser}
              />
            </div>
          </h4>
        </div>

        <div className='section full pe-3 ps-4 pt-1 position-relative feed-body'>
          <h4 className='font-xsssss text-grey-500 text-uppercase fw-700 ls-3'>CONTACTS</h4>
          <ul className='position-absolute border rounded-2 border-dark bg-grey theme-light-bg color-theme-cayan' style={{zIndex: 1080}}>
            <div className={'d-flex'}>
              <div>
                {
                  userToCreateMessageGroup.length > 0 &&
                  (<textarea
                    className={'me-auto'}
                    onKeyUp={() => setGroupNameIsCustom(true)}
                    onChange={event => setGroupNameToCreateMessageGroup(event.target.value)}
                    value={groupNameToCreateMessageGroup} />)
                }
              </div>
              <div className={`ms-auto ${resultSearchUsers.length < 1 && 'd-none'}`}>
                {userToCreateMessageGroup.length > 0 &&
                (<><i className={`feather-x font-xl text-grey-600 d-block`} />
                    <i className={`feather-check font-xl text-grey-600 d-block`}
                       onClick={() => dispatch(createMessageGroup({
                         userIds: userToCreateMessageGroup.map(v => v.uuid),
                         desiredName: groupNameToCreateMessageGroup,
                       }))} /></>
                )}
              </div>
            </div>
          </ul>
          <ul className='list-group list-group-flush'>
            {
              userToCreateMessageGroup.length > 0 &&
              (<div><textarea
                className={'me-auto'}
                onKeyUp={() => setGroupNameIsCustom(true)}
                onChange={event => setGroupNameToCreateMessageGroup(event.target.value)}
                value={groupNameToCreateMessageGroup} /></div>)
            }
            {
              resultSearchUsers.length > 0 ? userSearchComponent() : groupJoinedComponent()
            }
          </ul>
        </div>

        <div className='section full pe-3 ps-4 pt-4 pb-4 position-relative feed-body'>
          <h4 className='font-xsssss text-grey-500 text-uppercase fw-700 ls-3'>GROUPS</h4>
          <ul className='list-group list-group-flush'>
            <li className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'>
              <span className='btn-round-sm bg-primary-gradiant me-3 ls-3 text-white font-xssss fw-700'>UD</span>
              <h3 className='fw-700 mb-0 mt-0'>
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={() => openChatBox}>
                  Studio Express
                </span>
              </h3>
              <span className='badge mt-0 text-grey-500 badge-pill pe-0 font-xsssss'>2 min</span>
            </li>
            <li className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'>
              <span className='btn-round-sm bg-gold-gradiant me-3 ls-3 text-white font-xssss fw-700'>AR</span>
              <h3 className='fw-700 mb-0 mt-0'>
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={() => openChatBox}>
                  Armany Design
                </span>
              </h3>
              <span className='bg-warning ms-auto btn-round-xss' />
            </li>
            <li className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'>
              <span className='btn-round-sm bg-mini-gradiant me-3 ls-3 text-white font-xssss fw-700'>UD</span>
              <h3 className='fw-700 mb-0 mt-0'>
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={() => openChatBox}>
                  De fabous
                </span>
              </h3>
              <span className='bg-success ms-auto btn-round-xss' />
            </li>
          </ul>
        </div>

        <div className='section full pe-3 ps-4 pt-0 pb-4 position-relative feed-body'>
          <h4 className='font-xsssss text-grey-500 text-uppercase fw-700 ls-3'>Pages</h4>
          <ul className='list-group list-group-flush'>
            <li className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'>
              <span className='btn-round-sm bg-primary-gradiant me-3 ls-3 text-white font-xssss fw-700'>AB</span>
              <h3 className='fw-700 mb-0 mt-0'>
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={() => openChatBox}>
                  Armany Seary
                </span>
              </h3>
              <span className='bg-success ms-auto btn-round-xss' />
            </li>
            <li className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'>
              <span className='btn-round-sm bg-gold-gradiant me-3 ls-3 text-white font-xssss fw-700'>SD</span>
              <h3 className='fw-700 mb-0 mt-0'>
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={() => openChatBox}>
                  Entropio Inc
                </span>
              </h3>
              <span className='bg-success ms-auto btn-round-xss' />
            </li>
          </ul>
        </div>
      </div>
      <div>
        <div className='right-0 bottom-0 position-fixed d-flex'>
          {openGroupIds && openGroupIds.map((item, index) =>
            <ChatBox close={() => setOpenGroupIds(prev => prev.filter((prevV, prevI) => prevI !== index))} key={index} item={item} />)}
        </div>
      </div>
    </div>
  );
};

export default RightChat;
