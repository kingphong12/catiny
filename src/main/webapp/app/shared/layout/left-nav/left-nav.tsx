import React, {useState} from 'react';
import {Link} from 'react-router-dom';


const LeftNav = () =>
{

  const [isOpen, setIsOpen] = useState(false);
  const [isActive, setIsActive] = useState(false);
  // const [isNoti, setIsNoti] = useState(false);

  const toggleOpen = () => setIsOpen(!isOpen);
  const toggleActive = () => setIsActive(!isActive);
  // const toggleisNoti = () => setIsNoti(!isNoti);

  const navClass = `${isOpen ? " nav-active" : ""}`;
  // const buttonClass = `${isOpen ? " active" : ""}`;
  const searchClass = `${isActive ? " show" : ""}`;
  // const notiClass = `${isNoti ? " show" : ""}`;
  return (
    <>
      <nav className={`navigation scroll-bar ${navClass}`} style={{zIndex: 1080}}>
        <div className='container ps-0 pe-0'>
          <div className='nav-content'>
            <div className='nav-wrap bg-white bg-transparent-card rounded-xxl shadow-xss pt-3 pb-1 mb-2 mt-2'>
              <div className='nav-caption fw-600 font-xssss text-grey-500'><span>New </span>Feeds</div>
              <ul className='mb-1 top-content'>
                <li className='logo d-none d-xl-block d-lg-block' />
                <li><Link to='/home' className='nav-content-bttn open-font'>
                  <i className='feather-tv btn-round-md bg-blue-gradiant me-3' />
                  <span>Newsfeed</span></Link></li>
                <li><Link to='/defaultbadge' className='nav-content-bttn open-font'>
                  <i className='feather-award btn-round-md bg-red-gradiant me-3' />
                  <span>Badges</span></Link></li>
                <li><Link to='/defaultstorie' className='nav-content-bttn open-font'>
                  <i className='feather-globe btn-round-md bg-gold-gradiant me-3' />
                  <span>Explore Stories</span></Link></li>
                <li><Link to='/defaultgroup' className='nav-content-bttn open-font'>
                  <i className='feather-zap btn-round-md bg-mini-gradiant me-3' />
                  <span>Popular Groups</span></Link></li>
                <li><Link to='/userpage' className='nav-content-bttn open-font'>
                  <i className='feather-user btn-round-md bg-primary-gradiant me-3' />
                  <span>Author Profile </span></Link></li>
              </ul>
            </div>

            <div className='nav-wrap bg-white bg-transparent-card rounded-xxl shadow-xss pt-3 pb-1 mb-2'>
              <div className='nav-caption fw-600 font-xssss text-grey-500'>
                <span>More </span>Pages
              </div>
              <ul className='mb-3'>
                <li><Link to='/defaultemailbox' className='nav-content-bttn open-font'>
                  <i className='font-xl text-current feather-inbox me-3' />
                  <span>Email Box</span>
                  <span className='circle-count bg-warning mt-1'>584</span></Link></li>
                <li><Link to='/defaulthotel' className='nav-content-bttn open-font'>
                  <i className='font-xl text-current feather-home me-3' />
                  <span>Near Hotel</span></Link>
                </li>
                <li><Link to='/defaultevent' className='nav-content-bttn open-font'>
                  <i className='font-xl text-current feather-map-pin me-3' />
                  <span>Latest Event</span></Link></li>
                <li><Link to='/defaultlive' className='nav-content-bttn open-font'>
                  <i className='font-xl text-current feather-youtube me-3' />
                  <span>Live Stream</span></Link></li>
              </ul>
            </div>
            <div className='nav-wrap bg-white bg-transparent-card rounded-xxl shadow-xss pt-3 pb-1'>
              <div className='nav-caption fw-600 font-xssss text-grey-500'><span></span> Account</div>
              <ul className='mb-1'>
                <li className='logo d-none d-xl-block d-lg-block' />
                {/*<li><Link to='/defaultsettings' className='nav-content-bttn open-font h-auto pt-2 pb-2'>*/}
                {/*  <i className='font-sm feather-settings me-3 text-grey-500' />*/}
                {/*  <span>Settings</span></Link></li>*/}
                <li><Link to='/defaultanalytics' className='nav-content-bttn open-font h-auto pt-2 pb-2'>
                  <i className='font-sm feather-pie-chart me-3 text-grey-500' />
                  <span>Analytics</span></Link></li>
                {/*<li><Link to='/defaultmessage' className='nav-content-bttn open-font h-auto pt-2 pb-2'>*/}
                {/*  <i className='font-sm feather-message-square me-3 text-grey-500' />*/}
                {/*  <span>Chat</span>*/}
                {/*  <span className='circle-count bg-warning mt-0'>23</span></Link></li>*/}
              </ul>
            </div>
          </div>
        </div>
      </nav>
      <div className={`app-header-search ${searchClass}`}>
        <form className='search-form'>
          <div className='form-group searchbox mb-0 border-0 p-1'>
            <input type='text' className='form-control border-0' placeholder='Search...' />
            <i className='input-icon'>
              {/*<ion-icon name='search-outline' role='img' className='md hydrated' aria-label='search outline' />*/}
            </i>
            <span className='ms-1 mt-1 d-inline-block close searchbox-close'>
              <i className='ti-close font-xs' onClick={toggleActive} />
            </span>
          </div>
        </form>
      </div>
    </>
  );

}

export default LeftNav;