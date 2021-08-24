import React, {useEffect, useState} from 'react';
import PopupChat from "app/shared/layout/right-chat/popup-chat";
import {useAppDispatch, useAppSelector} from "app/config/store";
import {getAllMessageGroupsJoined} from "app/shared/layout/right-chat/right-chat.reducer";

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
  const [isOpen, setIsOpen] = useState(false);
  const [width, setWidth] = useState(800);
  const [height, setHeight] = useState(182);
  const dispatch = useAppDispatch();


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


  const toggleOpen = () => setIsOpen(!isOpen);
  const menuClass = `${isOpen ? " d-block " : ""}`;
  return (
    <div id='main-content-wrap' className={`right-chat nav-wrap mt-2 right-scroll-bar ${width > 1500 ? "active-sidebar" : " "}`}>
      <div className='middle-sidebar-right-content bg-white shadow-xss rounded-xxl'>

        <div className='section full pe-3 ps-4 pt-4 position-relative feed-body'>
          <h4 className='font-xsssss text-grey-500 text-uppercase fw-700 ls-3'>CONTACTS</h4>
          <ul className='list-group list-group-flush'>
            {allMessageGroupsJoined.map(messageGroup => (
              // Start Single Demo
              <li key={messageGroup.id} className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'>
                <figure className='avatar float-left mb-0 me-2'>
                  <img src={JSON.parse(messageGroup.avatar).url} alt='avater' className='w35' />
                </figure>
                <h3 className='fw-700 mb-0 mt-0'>
                  <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer'
                        onClick={toggleOpen}>{messageGroup.groupName}</span>
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
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={toggleOpen}>Studio Express</span>
              </h3>
              <span className='badge mt-0 text-grey-500 badge-pill pe-0 font-xsssss'>2 min</span>
            </li>
            <li className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'>
              <span className='btn-round-sm bg-gold-gradiant me-3 ls-3 text-white font-xssss fw-700'>AR</span>
              <h3 className='fw-700 mb-0 mt-0'>
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={toggleOpen}>Armany Design</span>
              </h3>
              <span className='bg-warning ms-auto btn-round-xss' />
            </li>
            <li className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'>
              <span className='btn-round-sm bg-mini-gradiant me-3 ls-3 text-white font-xssss fw-700'>UD</span>
              <h3 className='fw-700 mb-0 mt-0'>
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={toggleOpen}>De fabous</span>
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
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={toggleOpen}>Armany Seary</span>
              </h3>
              <span className='bg-success ms-auto btn-round-xss' />
            </li>
            <li className='bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'>
              <span className='btn-round-sm bg-gold-gradiant me-3 ls-3 text-white font-xssss fw-700'>SD</span>
              <h3 className='fw-700 mb-0 mt-0'>
                <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer' onClick={toggleOpen}>Entropio Inc</span>
              </h3>
              <span className='bg-success ms-auto btn-round-xss' />
            </li>
          </ul>
        </div>
      </div>
      {toggleOpen ? <PopupChat menuClass={menuClass} toggleOpen={toggleOpen} /> : <></>}
    </div>
  );
}

export default RightChat;