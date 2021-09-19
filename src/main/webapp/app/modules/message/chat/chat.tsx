import React, {Fragment, useEffect, useRef, useState} from "react";
import Websocket from "app/config/Websocket";

const Chat = (props) =>
{
  const ITEMS_PER_PAGE = 5;
  const {messageContentList} = props;
  const [groupIdCurrent, setGroupIdCurrent] = useState(props.match.params.groupId);

  const [contentTyping, setContentTyping] = useState("");
  const [pageCurrent, setPageCurrent] = useState(0);
  const messageContentRef = useRef(null);
  const [messageContentScrollCurrent, setMessageContentScrollCurrent] = useState(Number.MAX_SAFE_INTEGER);
  const websocket = new Websocket();


  // useEffect(() =>
  // {
  //   websocket.connect();
  //   websocket.subscribe()
  // }, []);

  useEffect(() =>
  {
    const scrollCurrent = messageContentRef.current.scrollHeight - messageContentScrollCurrent;
    if (messageContentRef.current.scrollTop < 500)
      messageContentRef.current.scrollTo(0, scrollCurrent);
    else
      messageContentRef.current.scrollTo(0, Number.MAX_SAFE_INTEGER);
    setMessageContentScrollCurrent(messageContentRef.current.scrollHeight);
  }, [messageContentList]);

  useEffect(() =>
  {
    setPageCurrent(0);
    setGroupIdCurrent(props.match.params.groupId);
    props.getContentInGroup(props.match.params.groupId, 0, ITEMS_PER_PAGE * 3);
    setPageCurrent(prev => prev + 3);
  }, [props.match.params.groupId]);


  // function support

  // const time = (messageContent: IMessageContent) =>
  // {
  //   return dayjs(new Date(Date.now() - new Date(messageContent.createdDate).getTime())).utc().format("HH:MM");
  // }

  // Handle

  const handleLoadMore = (event) =>
  {
    const target = event.target;
    if (event.target.scrollTop === 0 && target.scrollHeight > target.offsetHeight)
    {
      props.getContentInGroup(groupIdCurrent, pageCurrent, ITEMS_PER_PAGE);
      setPageCurrent(prev => ++prev);
    }
  };

  const handleSend = () =>
  {
    // const body = {content: contentTyping, groupId: groupIdCurrent};
    // if (contentTyping && /[^ ]+/.exec(contentTyping))
    //   wsSendData("message/send", body, {});
    // else
    //   window.console.log("nhếch nhêch");
    // setContentTyping("");
  }
// Component TSX


  const messageContent = (content, avatarLink, username, time, status, outgoing) =>
  {
    // const isSender = props.account.login === messageContent.sender;

    let iconStatus
    if (status === "seen")
      iconStatus = <i className='ti-double-check text-info' />;
    else if (status === "send")
      iconStatus = <i className='ti-double-check text-info' />;
    else if (status === "received")
      iconStatus = <i className='ti-double-check text-info' />;
    else
      iconStatus = <></>
    return (
      <div className={`message-item ${outgoing}`}>
        <div className='message-user'>
          <figure className='avatar'>
            <img src={avatarLink} alt='avater' />
          </figure>
          <div>
            <h5>{username}</h5>
            <div className='time'>{time}{iconStatus}</div>
          </div>
        </div>
        {/*<figure>*/}
        {/*  <img src='assets/images/product.png' className='w-25 img-fluid rounded-3' alt='avater' />*/}
        {/*</figure>*/}
        <div className='message-wrap'>{content}</div>
      </div>
    );
  }
  return (
    <Fragment>
      <div className='main-content right-chat-active'>
        <div className='middle-sidebar-bottom'>
          <div className='middle-sidebar-left pe-0' style={{maxWidth: "100%"}}>
            <div className='row'>
              <div className='col-lg-12 position-relative'>
                <div className='chat-wrapper pt-0 w-100 position-relative scroll-bar bg-white theme-dark-bg'>
                  <div className='chat-body p-3 '>
                    <div className='messages-content pb-5'>
                      {messageContent("aa", 'assets/images/user.png', "Mèo Máy", "12h30m", "seen", "outgoing-message")}
                      {messageContent("aa", 'https://i.pinimg.com/564x/e1/24/04/e124041a63e3a93437603fbb93255169.jpg', "Mèo Máy", "12h30m", "seen", "")}
                      {messageContent("aa", 'assets/images/user.png', "Mèo Máy", "12h30m", "seen", "outgoing-message")}
                      {messageContent("aa", 'assets/images/user.png', "Mèo Máy", "12h30m", "seen", "outgoing-message")}
                      {messageContent("aa", 'assets/images/user.png', "Mèo Máy", "12h30m", "seen", "")}
                      <div className='clearfix' />
                    </div>
                  </div>
                </div>
                <div className='chat-bottom dark-bg p-3 shadow-none theme-dark-bg' style={{width: "98%"}}>
                  <form className='chat-form'>
                    <button className='bg-grey float-left'><i className='ti-microphone text-grey-600' /></button>
                    <div className='form-group'><input type='text' placeholder='Start typing..' /></div>
                    <button className='bg-current'><i className='ti-arrow-right text-white' /></button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </Fragment>
  );
}

export default Chat;