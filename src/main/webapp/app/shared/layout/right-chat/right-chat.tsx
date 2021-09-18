import React, { useEffect, useState } from 'react';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import {
  createMessageGroup,
  getAllMessageGroupsJoined,
  getMessageContentByMessageGroupId,
  sendContentToGroup,
} from 'app/shared/layout/right-chat/right-chat.reducer';
import { defaultValue as defaultValueMessageGroup, IMessageGroup } from 'app/shared/model/message-group.model';
import { simpleCollage3 } from 'app/component/simple-component';
import { imageUrl } from 'app/shared/util/image-tools-util';
import Websocket from 'app/config/Websocket';
import { searchMasterUser } from 'app/component/reducer/master-user.reducer';
import { useSelector } from 'react-redux';
import ChatBox from './ChatBox';

const RightChat = () => {
  const websocket = new Websocket();
  const dispatch = useAppDispatch();
  const masterUser = useAppSelector(state => state.authentication.masterUser);
  const resultSearchUsers = useSelector((state: any) =>
    state.masterUserComponent.resultSearchUsers.filter(v => v.uuid !== masterUser.uuid)
  );

  const [isOpen, setIsOpen] = useState(false);
  const [width, setWidth] = useState(800);
  const [height, setHeight] = useState(182);
  const [currentMessageGroup, setCurrentMessageGroup] = useState(defaultValueMessageGroup);
  const [currentMessageContents, setCurrentMessageContents] = useState([]);
  const [messageContentTyping, setMessageContentTyping] = useState('');

  const [userToCreateMessageGroup, setUserToCreateMessageGroup] = useState([]);
  const [groupNameToCreateMessageGroup, setGroupNameToCreateMessageGroup] = useState('');
  const [groupNameIsCustom, setGroupNameIsCustom] = useState(false);

  //pagination chat api

  const [page, setPage] = useState(1);
  const [size, setSize] = useState(5);
  const [openGroupIds, setOpenGruopIds] = useState([]);

  /**
   * Calculate & Update state of new dimensions
   * có sẵn em cũng chưa sửa ok đấy chính vì thế nên nhiều lúc k biết được cái nào để ở đâu đâm ra no loạn
   */
  // const updateDimensions = () => {
  //   if (window.innerWidth < 500) {
  //     setWidth(450);
  //     setHeight(102);
  //   } else {
  //     const update_width = window.innerWidth - 100;
  //     const update_height = Math.round(update_width / 4.4);
  //     setWidth(update_width);
  //     setHeight(update_height);
  //   }
  // };

  // // Add event listener
  // // cái này theme nó có sẵn em cũng chưa xem
  // useEffect(() => {
  //   updateDimensions();
  //   window.addEventListener('resize', updateDimensions.bind(this));
  //   // Remove event listener
  //   return () => window.removeEventListener('resize', updateDimensions.bind(this));
  // });
  // load tất cả group
  const allMessageGroupsJoined = useAppSelector(state => state.rightChat.messageGroups);
  useEffect(() => {
    dispatch(getAllMessageGroupsJoined({}));
  }, []);

  // lắng nhe websocket ()  cái này dùng cho mỗi gruop id hay dùng cho cả e cho mỗi gr thôi anh thế thì a xóa bên này bê qua ben kia luôn ok anh
  // useEffect(() => {
  //   websocket.connect('/websocket/main');
  //   if (!masterUser.uuid) return;
  //   websocket.subscribeUserConsumer(`/messages`, data => {
  //     const body = JSON.parse(data.body);
  //     if (body && body.group && body.group.uuid && body.group.uuid === currentMessageGroup.uuid)
  //       setCurrentMessageContents(prev => prev.concat(body));
  //   });
  //   return () => {
  //     websocket.unsubscribe();
  //   };
  // }, [masterUser.uuid, currentMessageGroup]);

  const toggleOpen = (messageGroup: IMessageGroup) => {
    // if (!isOpen) {
    //   setCurrentMessageGroup(messageGroup);
    //   dispatch(getMessageContentByMessageGroupId({ uuidMessageGroups: messageGroup.uuid }))
    //     .unwrap()
    //     .then(action => {
    //       setCurrentMessageContents(action.data);
    //     });
    // }
    // setIsOpen(!isOpen);
    // console.log(messageGroup);
    // const ids = {
    //   id: messageGroup,
    // }; // anh bị sau chỗ này
    // const ids = messageGroup.uuid;
    setOpenGruopIds([...openGroupIds, messageGroup]);

    // opengroupIds = [{abc} , {def},{}]
  };

  useEffect(() => {
    // console.log(openGroupIds);
  }, [openGroupIds]);
  // const menuClass = `${isOpen ? ' d-block ' : ''}`;

  const typingSearchUsers = async event => {
    const a = event.target.value;
    await new Promise(executor => setTimeout(executor, 500));
    if (a === event.target.value) dispatch(searchMasterUser({ query: a }));
  };

  // const sendMessageContent = () => {
  //   if (!messageContentTyping && !(messageContentTyping.replace(' ', '').length > 0)) return;
  //   dispatch(sendContentToGroup({ groupId: currentMessageGroup.uuid, content: messageContentTyping }));
  //   setMessageContentTyping('');
  // };

  const avatarLoader = (messageGroupAvatar, css) => {
    if (!css || css.includes('square')) css = 'square35';
    if (messageGroupAvatar) {
      const avatarJsonParsed = JSON.parse(messageGroupAvatar);
      if (avatarJsonParsed && avatarJsonParsed.links && avatarJsonParsed.links.length > 2)
        return simpleCollage3(avatarJsonParsed.links, css);
      if (avatarJsonParsed && avatarJsonParsed.urls && avatarJsonParsed.urls.length > 2) return simpleCollage3(avatarJsonParsed.urls, css);
    }
    return <img src={imageUrl(messageGroupAvatar)} alt="avater" className={`${css} border border-secondary rounded-circle  me-1`} />;
  };

  // const popupChat = () => {
  //   useEffect(() => {});
  //   const message = messageContent => {
  //     if (messageContent && messageContent.info && messageContent.info.owner) {
  //       const owner = messageContent.info.owner;
  //       if (owner.uuid === masterUser.uuid)
  //         return (
  //           <>
  //             <div className="message self text-right mt-2">
  //               <div className="message-content font-xssss lh-24 fw-500">{messageContent.content}</div>
  //             </div>
  //           </>
  //         );
  //       else
  //         return (
  //           <>
  //             <div className="message">
  //               <div className="message-content font-xssss lh-24 fw-500">{messageContent.content}</div>
  //             </div>
  //             <div className="date-break font-xsssss lh-24 fw-500 text-grey-500 mt-2 mb-2">Mon 10:20am</div>
  //           </>
  //         );
  //     } else return <></>;
  //   };

  //   return (
  //     <div
  //       className={`modal-popup-chat d-block `}
  //       style={{
  //         right: '80px',
  //         bottom: '0',
  //         position: 'relative',
  //         // backgroundColor: 'red',
  //         // height: '500px',
  //         // width: '100px',
  //         marginLeft: '1rem',
  //       }}
  //     >
  //       <div className={`modal-popup-wrap bg-white p-0 shadow-lg rounded-3 ${width < 1000 ? 'w250' : ''} `}>
  //         <div className="modal-popup-header border-bottom">
  //           <div className="card p-3 d-block border-0 d-block">
  //             <figure className="avatar mb-0 float-left me-2">{avatarLoader(currentMessageGroup.avatar, '')}</figure>
  //             <h5 className="fw-700 text-primary font-xssss mt-1 mb-1">
  //               {currentMessageGroup.groupName ? currentMessageGroup.groupName : <del>No Name</del>}
  //             </h5>
  //             <h4 className="text-grey-500 font-xsssss mt-0 mb-0">
  //               <span className="d-inline-block bg-success btn-round-xss m-0" /> Available
  //             </h4>
  //             <div className="font-xssss position-absolute right-0 top-0 mt-3 me-4 pointer" onClick={() => toggleOpen(null)}>
  //               <i className="ti-close text-grey-900 mt-2 d-inline-block" />
  //             </div>
  //           </div>
  //         </div>
  //         <div className="modal-popup-body w-100 right-scroll-bar h300 p-3 ">
  //           {currentMessageContents.map(message)}
  //           <div className="snippet pt-3 ps-4 pb-2 pe-3 mt-2 bg-grey rounded-xl float-right" data-title=".dot-typing">
  //             <div className="stage">
  //               <div className="dot-typing" />
  //             </div>
  //           </div>
  //           <div className="clearfix" />
  //         </div>
  //         <div className="modal-popup-footer w-100 border-top">
  //           <div className="card p-3 d-block border-0 d-block">
  //             <div className="form-group icon-right-input style1-input mb-0">
  //               <input
  //                 type="text"
  //                 placeholder="Start typing.."
  //                 className="form-control rounded-xl bg-greylight border-0 font-xssss fw-500 ps-3"
  //                 onChange={e => setMessageContentTyping(e.target.value)}
  //                 onKeyUp={e => (e.key === 'Enter' ? sendMessageContent() : null)}
  //                 value={messageContentTyping}
  //               />
  //               <i className="feather-send text-grey-500 font-md" onClick={sendMessageContent} />
  //             </div>
  //           </div>
  //         </div>
  //       </div>
  //     </div>
  //   );
  // };

  const rightChatActive = useAppSelector(state => state.setting.rightChatActive);

  return (
    <div id="main-content-wrap" className={`right-chat nav-wrap mt-2 right-scroll-bar ${rightChatActive ? 'active-sidebar' : ''}`}>
      <div className="middle-sidebar-right-content bg-white shadow-xss rounded-xxl">
        <div className="section full pe-3 ps-4 pt-2 position-relative feed-body">
          <h4 className="font-xsssss text-grey-500 text-uppercase ">
            <div className="form-group mb-0 icon-input">
              <i className="feather-search font-sm text-grey-400" />
              <input
                type="text"
                className="bg-grey border-0 lh-28 ps-5 pe-3 font-xssss fw-500 rounded-xl theme-dark-bg"
                placeholder="Search user ..."
                onChange={typingSearchUsers}
              />
            </div>
          </h4>
          <ul className="position-absolute border rounded-2 border-dark bg-grey theme-light-bg color-theme-cayan" style={{ zIndex: 1080 }}>
            <div className={'d-flex'}>
              <div>
                {userToCreateMessageGroup.length > 0 ? (
                  <textarea
                    className={'me-auto'}
                    onKeyUp={() => setGroupNameIsCustom(true)}
                    onChange={event => setGroupNameToCreateMessageGroup(event.target.value)}
                    value={groupNameToCreateMessageGroup}
                  />
                ) : (
                  ''
                )}
              </div>

              <div className={`ms-auto ${resultSearchUsers.length < 1 ? 'd-none' : ''}`}>
                <i className={`feather-x font-xl text-grey-600 d-block`} />
                {userToCreateMessageGroup.length > 0 ? (
                  <i
                    className={`feather-check font-xl text-grey-600 d-block`}
                    onClick={() =>
                      dispatch(
                        createMessageGroup({
                          userIds: userToCreateMessageGroup.map(v => v.uuid),
                          desiredName: groupNameToCreateMessageGroup,
                        })
                      )
                    }
                  />
                ) : (
                  ''
                )}
              </div>
            </div>
            {resultSearchUsers.map(user => (
              <li
                key={user.uuid}
                className="m-1 d-flex rounded-3 bg-white theme-dark-bg"
                onClick={() => {
                  setUserToCreateMessageGroup(prev => {
                    const result =
                      prev.filter(v => v.uuid === user.uuid).length < 1 ? prev.concat(user) : prev.filter(v => v.uuid !== user.uuid);
                    if (!groupNameIsCustom)
                      setGroupNameToCreateMessageGroup(() => {
                        let name = '';
                        result.map(u => (name += u.fullName + ','));
                        if (name.length > 0) return name.substring(0, name.length - 1);
                        return '';
                      });
                    return result;
                  });
                }}
              >
                <div className="d-flex ">{avatarLoader(user.avatar, 'square35')}</div>
                <div className="font-xsssss text-grey-500 fw-700 ls-3 justify-content-xxl-around">
                  <h4 className="font-xssss">
                    {user.fullName.substring(0, 20)}
                    {user.fullName.length > 20 ? '...' : ''}
                  </h4>
                  <h5 className="font-xsssss m-0 ">{user.nickname.substring(0, 25)}</h5>
                </div>
                {userToCreateMessageGroup.filter(v => v.uuid === user.uuid).length > 0 ? (
                  <i className="feather-check-circle font-xsss text-grey-600 ms-auto " />
                ) : (
                  <i className="feather-x font-xsss text-grey-600 ms-auto" />
                )}
              </li>
            ))}
          </ul>
        </div>

        <div className="section full pe-3 ps-4 pt-1 position-relative feed-body">
          <h4 className="font-xsssss text-grey-500 text-uppercase fw-700 ls-3">CONTACTS</h4>
          <ul className="list-group list-group-flush">
            {allMessageGroupsJoined.map(messageGroup => (
              // Start Single Demo
              <li
                key={messageGroup.uuid}
                className="bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center"
              >
                <figure className="avatar float-left mb-0 me-2">{avatarLoader(messageGroup.avatar, '')}</figure>
                <h3 className="fw-700 mb-0 mt-0">
                  <span
                    className="font-xssss text-grey-600 d-block text-dark model-popup-chat pointer"
                    onClick={() => toggleOpen(messageGroup)}
                  >
                    {messageGroup && messageGroup.groupName ? (
                      messageGroup.groupName.length > 20 ? (
                        messageGroup.groupName.substring(0, 18) + ' ...'
                      ) : (
                        messageGroup.groupName
                      )
                    ) : (
                      <del>No Name</del>
                    )}
                  </span>
                </h3>
                <span className={`${/*value.status*/ 'bg-success'} ms-auto btn-round-xss`} />
              </li>
              // End Single Demo
            ))}
          </ul>
        </div>

        <div className="section full pe-3 ps-4 pt-4 pb-4 position-relative feed-body">
          <h4 className="font-xsssss text-grey-500 text-uppercase fw-700 ls-3">GROUPS</h4>
          <ul className="list-group list-group-flush">
            <li className="bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center">
              <span className="btn-round-sm bg-primary-gradiant me-3 ls-3 text-white font-xssss fw-700">UD</span>
              <h3 className="fw-700 mb-0 mt-0">
                <span className="font-xssss text-grey-600 d-block text-dark model-popup-chat pointer" onClick={() => toggleOpen}>
                  Studio Express
                </span>
              </h3>
              <span className="badge mt-0 text-grey-500 badge-pill pe-0 font-xsssss">2 min</span>
            </li>
            <li className="bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center">
              <span className="btn-round-sm bg-gold-gradiant me-3 ls-3 text-white font-xssss fw-700">AR</span>
              <h3 className="fw-700 mb-0 mt-0">
                <span className="font-xssss text-grey-600 d-block text-dark model-popup-chat pointer" onClick={() => toggleOpen}>
                  Armany Design
                </span>
              </h3>
              <span className="bg-warning ms-auto btn-round-xss" />
            </li>
            <li className="bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center">
              <span className="btn-round-sm bg-mini-gradiant me-3 ls-3 text-white font-xssss fw-700">UD</span>
              <h3 className="fw-700 mb-0 mt-0">
                <span className="font-xssss text-grey-600 d-block text-dark model-popup-chat pointer" onClick={() => toggleOpen}>
                  De fabous
                </span>
              </h3>
              <span className="bg-success ms-auto btn-round-xss" />
            </li>
          </ul>
        </div>

        <div className="section full pe-3 ps-4 pt-0 pb-4 position-relative feed-body">
          <h4 className="font-xsssss text-grey-500 text-uppercase fw-700 ls-3">Pages</h4>
          <ul className="list-group list-group-flush">
            <li className="bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center">
              <span className="btn-round-sm bg-primary-gradiant me-3 ls-3 text-white font-xssss fw-700">AB</span>
              <h3 className="fw-700 mb-0 mt-0">
                <span className="font-xssss text-grey-600 d-block text-dark model-popup-chat pointer" onClick={() => toggleOpen}>
                  Armany Seary
                </span>
              </h3>
              <span className="bg-success ms-auto btn-round-xss" />
            </li>
            <li className="bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center">
              <span className="btn-round-sm bg-gold-gradiant me-3 ls-3 text-white font-xssss fw-700">SD</span>
              <h3 className="fw-700 mb-0 mt-0">
                <span className="font-xssss text-grey-600 d-block text-dark model-popup-chat pointer" onClick={() => toggleOpen}>
                  Entropio Inc
                </span>
              </h3>
              <span className="bg-success ms-auto btn-round-xss" />
            </li>
          </ul>
        </div>
      </div>
      <div>
        <div style={{ right: '0', bottom: '0', position: 'fixed', display: 'flex' }}>
          {openGroupIds.length &&
            openGroupIds.map((item, index) => {
              return <ChatBox key={index} item={item} />;
            })}
        </div>
      </div>
    </div>
  );
};

export default RightChat;
