import React, {useEffect, useRef, useState} from 'react';
import {useAppDispatch, useAppSelector} from 'app/config/store';
import {getMessageContentByMessageGroupId, sendContentToGroup,} from 'app/shared/layout/right-chat/right-chat.reducer';
import {simpleCollage3} from 'app/component/simple-component';
import {imageUrl} from 'app/shared/util/image-tools-util';
import Websocket from 'app/config/Websocket';


const ChatBox = props =>
{
  const {item} = props;
  const [websocket] = useState(new Websocket());
  const dispatch = useAppDispatch();

  const load = useRef(null);
  const scroll = useRef(null);

  const masterUser = useAppSelector(state => state.authentication.masterUser);
  const [currentMessageContents, setCurrentMessageContents] = useState([]);

  const [isOpen, setIsOpen] = useState(true);
  const [width, setWidth] = useState(800);
  const [height, setHeight] = useState(182);
  const [messageContentTyping, setMessageContentTyping] = useState('');


  const [page, setPage] = useState(0);
  const [size] = useState(5);
  const [loadding, setLoadding] = useState(false);

  /**
   * Calculate & Update state of new dimensions
   */
  const updateDimensions = () =>
  {
    if (window.innerWidth < 500)
    {
      setWidth(450);
      setHeight(102);
    }
    else
    {
      const update_width = window.innerWidth - 100;
      const update_height = Math.round(update_width / 4.4);
      setWidth(update_width);
      setHeight(update_height);
    }
  };

  // Add event listener
  useEffect(() =>
  {
    updateDimensions();
    window.addEventListener('resize', updateDimensions.bind(this));
    // Remove event listener
    return () => window.removeEventListener('resize', updateDimensions.bind(this));
  });

  useEffect(() =>
  {
    websocket.connect('/websocket/main');
    if (!masterUser.uuid) return;
    websocket.subscribeUserConsumer(`/messages`, data =>
    {
      const body = JSON.parse(data.body);
      if (body && body.group && body.group.uuid && body.group.uuid === item.uuid)
      {
        setCurrentMessageContents(prev => prev.concat(body));
        if (scroll.current.scrollHeight - scroll.current.scrollTop - 300 < 200)
          scroll.current.scrollTo(0, scroll.current.scrollHeight);
      }
    });
    return () =>
    {
      websocket.unsubscribe();
    };
  }, [masterUser.uuid]);

  useEffect(() =>
  {
    dispatch(getMessageContentByMessageGroupId({page, size, uuidMessageGroups: item.uuid}))
      .unwrap()
      .then(action =>
      {
        const scrollHeightOld = scroll.current.scrollHeight;
        setCurrentMessageContents(m => action.data.concat(m));
        setLoadding(true);
        scroll.current.scrollTop = scroll.current.scrollHeight - scrollHeightOld
      });
  }, [page]);

  const messagesEndRef = useRef(null);

  useEffect(() =>
  {
    if (page === 0)
      scroll.current.scrollTo(0, scroll.current.scrollHeight);
  }, [currentMessageContents]);

  useEffect(() =>
  {
    if (loadding)
    {
      const observer = new IntersectionObserver(entries =>
        {
          if (entries[0].isIntersecting)
            loadMore();
        },
        {
          threshold: 0,
        }
      );
      observer.observe(load.current);
    }
  }, [loadding]);

  const loadMore = () =>
  {
    setPage(p => p + 1);
  };

  const handleClose = () =>
  {
    setIsOpen(false);
    websocket.unsubscribe();
    websocket.disconnect();
    props.close();
  };

  const sendMessageContent = () =>
  {
    if (!messageContentTyping && (messageContentTyping.replace(' ', '').length <= 0)) return;
    dispatch(sendContentToGroup({groupId: item.uuid, content: messageContentTyping}));
    scroll.current.scrollTo(0, scroll.current.scrollHeight);
    setMessageContentTyping('');
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

  const message = messageContent =>
  {
    if (messageContent && messageContent.info && messageContent.info.owner)
    {
      const owner = messageContent.info.owner;
      if (owner.uuid === masterUser.uuid)
        return (
          <>
            <div className='message self text-right mt-2'>
              <div className='message-content font-xssss lh-24 fw-500 text-break'>{messageContent.content}</div>
            </div>
          </>
        );
      else
        return (
          <>
            <div className='message'>
              <div className='message-content font-xssss lh-24 fw-500'>{messageContent.content}</div>
            </div>
            <div className='date-break font-xsssss lh-24 fw-500 text-grey-500 mt-2 mb-2'>Mon 10:20am</div>
          </>
        );
    }
    else return <></>;
  };

  const menuClass = `${isOpen ? ' d-block ' : ''}`;

  return (
    <div className={`modal-popup-chat position-relative ${menuClass}`}>
      <div className={`modal-popup-wrap bg-white p-0 shadow-lg rounded-3 ${width < 1000 ? 'w250' : ''} `}>
        <div className='modal-popup-header border-bottom'>
          <div className='card p-3 d-block border-0 d-block'>
            <figure className='avatar mb-0 float-left me-2'>{avatarLoader(item.avatar, '')}</figure>
            <h5 className='fw-700 text-primary font-xssss mt-1 mb-1'>{item.groupName ? item.groupName : <del>No Name</del>}</h5>
            <h4 className='text-grey-500 font-xsssss mt-0 mb-0'>
              <span className='d-inline-block bg-success btn-round-xss m-0' /> Available
            </h4>
            <div className='font-xssss position-absolute right-0 top-0 mt-3 me-4 pointer' onClick={handleClose}>
              <i className='ti-close text-grey-900 mt-2 d-inline-block' />
            </div>
          </div>
        </div>
        <div ref={scroll} className='modal-popup-body w-100 right-scroll-bar h300 p-3' id='chat'>
          <div ref={load}>đang tải...</div>
          {currentMessageContents.map(message)}
          <div className='snippet pt-3 ps-4 pb-2 pe-3 mt-2 bg-grey rounded-xl float-right' data-title='.dot-typing'>
            <div className='stage'>
              <div className='dot-typing' />
            </div>
          </div>
          <div className='clearfix' />
          <div ref={messagesEndRef} />
        </div>
        <div className='modal-popup-footer w-100 border-top'>
          <div className='card p-3 d-block border-0 d-block'>
            <div className='form-group icon-right-input style1-input mb-0'>
              <input
                type='text'
                placeholder='Start typing..'
                className='form-control rounded-xl bg-greylight border-0 font-xssss fw-500 ps-3 pe-5'
                onChange={e => setMessageContentTyping(e.target.value)}
                onKeyUp={e => (e.key === 'Enter' ? sendMessageContent() : null)}
                value={messageContentTyping}
              />
              <i className='feather-send text-grey-500 font-md' onClick={sendMessageContent} />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ChatBox;
