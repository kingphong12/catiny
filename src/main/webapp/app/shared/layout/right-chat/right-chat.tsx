import React, {useEffect, useState} from 'react';
import {useAppDispatch, useAppSelector} from "app/config/store";
import {getAllMessageGroupsJoined, getMessageContentByMessageGroupId} from "app/shared/layout/right-chat/right-chat.reducer";
import {defaultValue as defaultValueMessageGroup, IMessageGroup} from "app/shared/model/message-group.model";
import {simpleCollage3} from 'app/component/simple-component';
import {imageUrl} from "app/shared/util/image-tools-util";


const chatMember = [
  {
    imageUrl: 'user.png',
    name: 'Hurin Seary',
    status: 'bg-success'
  },
  {
    imageUrl: 'user.png',
    name: 'Victor Exrixon',
    status: 'bg-success'
  },
  {
    imageUrl: 'user.png',
    name: 'Surfiya Zakir',
    status: 'bg-warning'
  },
  {
    imageUrl: 'user.png',
    name: 'Goria Coast',
    status: 'bg-danger'
  },
  {
    imageUrl: 'user.png',
    name: 'Hurin Seary',
    status: 'bg-success'
  },
  {
    imageUrl: 'user.png',
    name: 'David Goria',
    status: 'bg-success'
  },
  {
    imageUrl: 'user.png',
    name: 'Seary Victor',
    status: 'bg-success'
  },
  {
    imageUrl: 'user.png',
    name: 'Ana Seary',
    status: 'bg-success'
  },
]

const RightChat = () =>
{
  const dispatch = useAppDispatch();
  const [isOpen, setIsOpen] = useState(false);
  const [width, setWidth] = useState(800);
  const [height, setHeight] = useState(182);
  const [currentMessageGroup, setCurrentMessageGroup] = useState(defaultValueMessageGroup);
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
  }

  // Add event listener
  useEffect(() =>
  {
    updateDimensions();
    window.addEventListener("resize", updateDimensions.bind(this));
    // Remove event listener
    return () => window.removeEventListener("resize", updateDimensions.bind(this));
  });
  const allMessageGroupsJoined = useAppSelector((state) => state.rightChat.messageGroups);
  useEffect(() =>
  {
    dispatch(getAllMessageGroupsJoined({}));
  }, [])


  const toggleOpen = (messageGroup: IMessageGroup) =>
  {
    if (!isOpen)
    {
      setCurrentMessageGroup(messageGroup);
      dispatch(getMessageContentByMessageGroupId({uuidMessageGroups: messageGroup.uuid}));
    }
    setIsOpen(!isOpen);
  }
  const menuClass = `${isOpen ? " d-block " : ""}`;


  const popupChat = () =>
  {
    return (
      <div className={`modal-popup-chat ${menuClass}`}>
        <div className='modal-popup-wrap bg-white p-0 shadow-lg rounded-3'>
          <div className='modal-popup-header w-100 border-bottom'>
            <div className='card p-3 d-block border-0 d-block'>
              <figure className='avatar mb-0 float-left me-2'>
                {avatar(currentMessageGroup.avatar)}
              </figure>
              <h5 className='fw-700 text-primary font-xssss mt-1 mb-1'>
                {currentMessageGroup.groupName ? currentMessageGroup.groupName : <del>No Name</del>}
              </h5>
              <h4 className='text-grey-500 font-xsssss mt-0 mb-0'><span className='d-inline-block bg-success btn-round-xss m-0' /> Available
              </h4>
              <div className='font-xssss position-absolute right-0 top-0 mt-3 me-4 pointer' onClick={() => toggleOpen(null)}>
                <i className='ti-close text-grey-900 mt-2 d-inline-block' /></div>
            </div>
          </div>
          <div className='modal-popup-body w-100 p-3 h-auto'>
            <div className='message'>
              <div className='message-content font-xssss lh-24 fw-500'>Hi, how can I help you?</div>
            </div>
            <div className='date-break font-xsssss lh-24 fw-500 text-grey-500 mt-2 mb-2'>Mon 10:20am</div>
            <div className='message self text-right mt-2'>
              <div className='message-content font-xssss lh-24 fw-500'>I want those files for you. I want you to send 1 PDF and 1 image file.</div>
            </div>
            <div className='snippet pt-3 ps-4 pb-2 pe-3 mt-2 bg-grey rounded-xl float-right' data-title='.dot-typing'>
              <div className='stage'>
                <div className='dot-typing' />
              </div>
            </div>
            <div className='clearfix' />
          </div>
          <div className='modal-popup-footer w-100 border-top'>
            <div className='card p-3 d-block border-0 d-block'>
              <div className='form-group icon-right-input style1-input mb-0'>
                <input type='text' placeholder='Start typing..' className='form-control rounded-xl bg-greylight border-0 font-xssss fw-500 ps-3' />
                <i className='feather-send text-grey-500 font-md' />
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }

  const avatar = messageGroupAvatar =>
  {
    if (messageGroupAvatar)
    {
      const avatarJsonParsed = JSON.parse(messageGroupAvatar)
      if (avatarJsonParsed && avatarJsonParsed.links && avatarJsonParsed.links.length > 2)
        return simpleCollage3(avatarJsonParsed.links);
      if (avatarJsonParsed && avatarJsonParsed.urls && avatarJsonParsed.urls.length > 2)
        return simpleCollage3(avatarJsonParsed.urls);
    }
    return <img src={imageUrl(messageGroupAvatar)} alt='avater' className='w35 me-1' />
  }

  return (
    <div id='main-content-wrap' className={`right-chat nav-wrap mt-2 right-scroll-bar ${width > 1500 ? "active-sidebar" : " "}`}>
      <div className='middle-sidebar-right-content bg-white shadow-xss rounded-xxl'>

        <h4 className='font-xsssss text-grey-500 text-uppercase fw-700 ls-3'>
          <form action='#' className='float-left ms-3'>
            <div className='form-group mb-0 icon-input'>
              <i className='feather-search font-sm text-grey-400' />
              <input type='text' placeholder='Start typing to search..'
                     className='bg-grey border-0 lh-28 ps-5 pe-3 font-xssss fw-500 rounded-xl theme-dark-bg' />
            </div>
          </form>
        </h4>
        <div className='section full pe-3 ps-4 pt-4 position-relative feed-body'>
          <h4 className='font-xsssss text-grey-500 text-uppercase fw-700 ls-3'>CONTACTS</h4>
          <ul className='list-group list-group-flush'>
            {allMessageGroupsJoined.map(messageGroup => (
              // Start Single Demo
              <li key={messageGroup.id} className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'>
                <figure className='avatar float-left mb-0 me-2'>
                  {avatar(messageGroup.avatar)}
                </figure>
                <h3 className='fw-700 mb-0 mt-0'>
                  <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer'
                        onClick={() => toggleOpen(messageGroup)}>{messageGroup && messageGroup.groupName ? messageGroup.groupName :
                    <del>No Name</del>}</span>
                </h3>
                <span className={`${ /*value.status*/"bg-success"} ms-auto btn-round-xss`} />
              </li>
              // End Single Demo
            ))}
          </ul>
        </div>

        <div className='section full pe-3 ps-4 pt-4 pb-4 position-relative feed-body'>
          <h4 className='font-xsssss text-grey-500 text-uppercase fw-700 ls-3'>GROUPS</h4>
          <ul className='list-group list-group-flush'>
            <li className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'>
              <span className='btn-round-sm bg-primary-gradiant me-3 ls-3 text-white font-xssss fw-700'>UD</span>
              <h3 className='fw-700 mb-0 mt-0'>
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={() => toggleOpen}>Studio Express</span>
              </h3>
              <span className='badge mt-0 text-grey-500 badge-pill pe-0 font-xsssss'>2 min</span>
            </li>
            <li className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'>
              <span className='btn-round-sm bg-gold-gradiant me-3 ls-3 text-white font-xssss fw-700'>AR</span>
              <h3 className='fw-700 mb-0 mt-0'>
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={() => toggleOpen}>Armany Design</span>
              </h3>
              <span className='bg-warning ms-auto btn-round-xss' />
            </li>
            <li className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'>
              <span className='btn-round-sm bg-mini-gradiant me-3 ls-3 text-white font-xssss fw-700'>UD</span>
              <h3 className='fw-700 mb-0 mt-0'>
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={() => toggleOpen}>De fabous</span>
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
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={() => toggleOpen}>Armany Seary</span>
              </h3>
              <span className='bg-success ms-auto btn-round-xss' />
            </li>
            <li className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'>
              <span className='btn-round-sm bg-gold-gradiant me-3 ls-3 text-white font-xssss fw-700'>SD</span>
              <h3 className='fw-700 mb-0 mt-0'>
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={() => toggleOpen}>Entropio Inc</span>
              </h3>
              <span className='bg-success ms-auto btn-round-xss' />
            </li>
          </ul>
        </div>
      </div>
      {toggleOpen ? popupChat() : <></>}
    </div>
  );
}

export default RightChat;