import './header.scss';

import React, {Fragment, useEffect, useRef, useState} from 'react';
import {Translate} from 'react-jhipster';

import {Brand} from './header-components';
import Darkbutton from "app-js/components/DarkMode";
import {Link, NavLink} from 'react-router-dom';
import {hideComponent} from "app/modules/setting/setting.reducer";
import {useDispatch} from 'react-redux';
import {imageUrl} from "app/shared/util/image-tools-util";
import {useAppSelector} from "app/config/store";
import {cleanResultSearchMasterUser, searchMasterUser} from "app/shared/layout/header/header.reducer";


export interface IHeaderProps
{
  isAuthenticated: boolean;
  isAdmin: boolean;
  ribbonEnv: string;
  isInProduction: boolean;
  isOpenAPIEnabled: boolean;
  currentLocale: string;
}

const Header = (props: IHeaderProps) =>
{
  const dispatch = useDispatch();
  const masterUser = useAppSelector(state => state.authentication.masterUser);

  /* jhipster-needle-add-element-to-menu - JHipster will add new menu items here */


  const [isOpen, setIsOpen] = useState(false);
  const [isActive, setIsActive] = useState(false);
  const [isNoti, setIsNoti] = useState(false);

  const toggleOpen = () => setIsOpen(!isOpen);
  const toggleActive = () => setIsActive(!isActive);
  const toggleisNoti = () => setIsNoti(!isNoti);

  const navClass = `${isOpen ? " nav-active" : ""}`;
  const buttonClass = `${isOpen ? " active" : ""}`;
  const searchClass = `${isActive ? " show" : ""}`;
  const notiClass = `${isNoti ? " show" : ""}`;

  const renderDevRibbon = () =>
    !props.isInProduction && (
      <div className='ribbon dev'>
        <a href=''>
          <Translate contentKey={`global.ribbon.${props.ribbonEnv}`} />
        </a>
      </div>
    );


  const leftNav = () =>
    <>
      <nav className={`navigation scroll-bar ${navClass}`}>
        <div className='container ps-0 pe-0'>
          <div className='nav-content'>
            <div className='nav-wrap bg-white bg-transparent-card rounded-xxl shadow-xss pt-3 pb-1 mb-2 mt-2'>
              <div className='nav-caption fw-600 font-xssss text-grey-500'><span>New </span>Feeds</div>
              <ul className='mb-1 top-content'>
                <li className='logo d-none d-xl-block d-lg-block' />
                <li><Link to='/home' className='nav-content-bttn open-font'><i
                  className='feather-tv btn-round-md bg-blue-gradiant me-3' /><span>Newsfeed</span></Link></li>
                <li><Link to='/defaultbadge' className='nav-content-bttn open-font'><i
                  className='feather-award btn-round-md bg-red-gradiant me-3' /><span>Badges</span></Link></li>
                <li><Link to='/defaultstorie' className='nav-content-bttn open-font'><i
                  className='feather-globe btn-round-md bg-gold-gradiant me-3' /><span>Explore Stories</span></Link></li>
                <li><Link to='/defaultgroup' className='nav-content-bttn open-font'><i
                  className='feather-zap btn-round-md bg-mini-gradiant me-3' /><span>Popular Groups</span></Link></li>
                <li><Link to='/userpage' className='nav-content-bttn open-font'><i
                  className='feather-user btn-round-md bg-primary-gradiant me-3' /><span>Author Profile </span></Link></li>
              </ul>
            </div>

            <div className='nav-wrap bg-white bg-transparent-card rounded-xxl shadow-xss pt-3 pb-1 mb-2'>
              <div className='nav-caption fw-600 font-xssss text-grey-500'><span>More </span>Pages</div>
              <ul className='mb-3'>
                <li><Link to='/defaultemailbox' className='nav-content-bttn open-font'><i className='font-xl text-current feather-inbox me-3' /><span>Email Box</span><span
                  className='circle-count bg-warning mt-1'>584</span></Link></li>
                <li><Link to='/defaulthotel' className='nav-content-bttn open-font'><i className='font-xl text-current feather-home me-3' /><span>Near Hotel</span></Link>
                </li>
                <li><Link to='/defaultevent' className='nav-content-bttn open-font'><i className='font-xl text-current feather-map-pin me-3' /><span>Latest Event</span></Link>
                </li>
                <li><Link to='/defaultlive' className='nav-content-bttn open-font'><i className='font-xl text-current feather-youtube me-3' /><span>Live Stream</span></Link>
                </li>
              </ul>
            </div>
            <div className='nav-wrap bg-white bg-transparent-card rounded-xxl shadow-xss pt-3 pb-1'>
              <div className='nav-caption fw-600 font-xssss text-grey-500'><span /> Account</div>
              <ul className='mb-1'>
                <li className='logo d-none d-xl-block d-lg-block' />
                <li><Link to='/defaultsettings' className='nav-content-bttn open-font h-auto pt-2 pb-2'><i
                  className='font-sm feather-settings me-3 text-grey-500' /><span>Settings</span></Link></li>
                <li><Link to='/defaultanalytics' className='nav-content-bttn open-font h-auto pt-2 pb-2'><i
                  className='font-sm feather-pie-chart me-3 text-grey-500' /><span>Analytics</span></Link></li>
                <li><Link to='/defaultmessage' className='nav-content-bttn open-font h-auto pt-2 pb-2'><i
                  className='font-sm feather-message-square me-3 text-grey-500' /><span>Chat</span><span
                  className='circle-count bg-warning mt-0'>23</span></Link></li>
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
              {/*<ion-icon name="search-outline" role="img" className="md hydrated" aria-label="search outline"></ion-icon>*/}
            </i>
            <span className='ms-1 mt-1 d-inline-block close searchbox-close'>
              <i className='ti-close font-xs' onClick={toggleActive} /></span>
          </div>
        </form>
      </div>
    </>

  const header = () =>
  {
    const resultSearchMasterUser = useAppSelector(state => state.header.resultSearchMasterUser);

    const [isOnSearch, setIsOnSearch] = useState(false);
    const [keywordSearch, setKeywordSearch] = useState("");

    const refSearchField = useRef(null);

    useEffect(() =>
    {
      function handleClickOutside(event)
      {
        if (refSearchField.current && !refSearchField.current.contains(event.target))
        {
          handleCloseSearch();
          setIsOnSearch(false);
        }
      }

      document.addEventListener("mousedown", handleClickOutside);
      return () =>
      {
        document.removeEventListener("mousedown", handleClickOutside);
      };
    }, [refSearchField]);

    const handleChangeSearch = async (event) =>
    {
      const value = event.target.value;
      setKeywordSearch(value);
      await new Promise(executor => setTimeout(executor, 500));
      if (value === event.target.value) dispatch(searchMasterUser({query: value}));
    }

    const handleCloseSearch = () =>
    {
      setKeywordSearch("");
      dispatch(cleanResultSearchMasterUser());
    }

    const handleElementSearch = (mu) =>
    {
      //  console.log("làm cái gì đó với thằng handleElementSearch này đi");
      //  console.log(mu);
    }

    const handleAdvanceSearch = () =>
    {
      // console.log("làm cái gì đó với thằng handleAdvanceSearch này đi");
    }

    return <>
      {renderDevRibbon()}
      <div className='nav-top'>
        <Link to='/'>
          {/*<i className='feather-zap text-success display2-size me-3 ms-0' />*/}
          <span className='d-inline-block fredoka-font ls-3 fw-600 text-current font-xxl logo-text mb-0'>
            <Brand />
          </span>
        </Link>
        <Link to='/defaultmessage' className='mob-menu ms-auto me-2 chat-active-btn'>
          <i className='feather-message-circle text-grey-900 font-sm btn-round-md bg-greylight' /></Link>
        <Link to='/defaultvideo' className='mob-menu me-2'>
          <i className='feather-video text-grey-900 font-sm btn-round-md bg-greylight' /></Link>
        <span onClick={toggleActive} className='me-2 menu-search-icon mob-menu'>
          <i className='feather-search text-grey-900 font-sm btn-round-md bg-greylight' /></span>
        <button onClick={toggleOpen} className={`nav-menu me-0 ms-2 ${buttonClass}`} />
      </div>

      <div className='float-left header-search ms-3' ref={refSearchField}>
        <div className='form-group mb-0 icon-input'>
          {!isOnSearch && <i className='feather-search font-sm text-grey-400' />}
          <input type='text'
                 placeholder={!isOnSearch && 'Start typing to search..'}
                 className={`bg-grey border-0 lh-32 pt-2 pb-2 pe-3 font-xssss fw-500 rounded-xl w350 theme-dark-bg ${isOnSearch ? "ps-3" : "ps-5"}`}
                 value={keywordSearch}
                 onChange={handleChangeSearch}
                 onClick={() => setIsOnSearch(true)} />
        </div>
        <div className='position-absolute z-index-1 bg-grey rounded-3 w350'>
          {resultSearchMasterUser.length > 0 &&
          <ul className='list-group list-group-flush theme-dark-bg p-2'>
            {resultSearchMasterUser.map(mu =>
              <a key={mu.uuid}>
                <li
                  className='btn-outline-light rounded-xxl  list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center'
                  onClick={() => handleElementSearch(mu)}>
                  <span className='btn-round-sm bg-primary-gradiant ms-1 me-3 ls-3 text-white font-xssss fw-700'>UD</span>
                  <h3 className='fw-700 mb-0 mt-0'>
                  <span className='font-xssss text-grey-600 d-block text-dark model-popup-chat pointer'>
                    {mu.fullName}
                  </span>
                  </h3>
                  {/*<button className='badge mt-0 text-grey-500 badge-pill pe-0 font-xsssss'>thêm bạn</button>*/}
                </li>
              </a>
            )}
            {resultSearchMasterUser.length > 0 &&
            <a><span className='btn-outline-light rounded-xxl p-2 font-xs text-grey-800 d-block text-dark' onClick={handleAdvanceSearch}>
              Tìm kiếm với: <b className='front-sm'>{keywordSearch}</b>
            </span></a>}
          </ul>}
        </div>
      </div>
      <NavLink activeClassName='active' to='/home' className='p-2 text-center ms-3 menu-icon center-menu-icon'>
        <i className='feather-home font-lg bg-greylight btn-round-lg theme-dark-bg text-grey-500 ' /></NavLink>
      <NavLink activeClassName='active' to='/defaultstorie' className='p-2 text-center ms-0 menu-icon center-menu-icon'>
        <i className='feather-zap font-lg bg-greylight btn-round-lg theme-dark-bg text-grey-500 ' /></NavLink>
      <NavLink activeClassName='active' to='/defaultvideo' className='p-2 text-center ms-0 menu-icon center-menu-icon'>
        <i className='feather-video font-lg bg-greylight btn-round-lg theme-dark-bg text-grey-500 ' /></NavLink>
      <NavLink activeClassName='active' to='/defaultgroup' className='p-2 text-center ms-0 menu-icon center-menu-icon'>
        <i className='feather-user font-lg bg-greylight btn-round-lg theme-dark-bg text-grey-500 ' /></NavLink>
      <NavLink activeClassName='active' to='/shop2' className='p-2 text-center ms-0 menu-icon center-menu-icon'>
        <i className='feather-shopping-bag font-lg bg-greylight btn-round-lg theme-dark-bg text-grey-500 ' /></NavLink>


      <span className={`p-2 pointer text-center ms-auto menu-icon ${notiClass}`} id='dropdownMenu3' data-bs-toggle='dropdown' aria-expanded='false'
            onClick={toggleisNoti}><span className='dot-count bg-warning' />
        <i className='feather-bell font-xl text-current' /></span>
      <div className={`dropdown-menu p-4 right-0 rounded-xxl border-0 shadow-lg top-100 ${notiClass}`} aria-labelledby='dropdownMenu3'>
        <h4 className='fw-700 font-xss mb-4'>Notification</h4>
        <div className='card bg-transparent-card w-100 border-0 ps-5 mb-3'>
          <img src={imageUrl(masterUser && masterUser.avatar ? masterUser.avatar : null)} alt='user' className='w40 position-absolute left-0' />
          <h5 className='font-xsss text-grey-900 mb-1 mt-0 fw-700 d-block'>Hendrix Stamp <span
            className='text-grey-400 font-xsssss fw-600 float-right mt-1'> 3 min</span></h5>
          <h6 className='text-grey-500 fw-500 font-xssss lh-4'>There are many variations of pass..</h6>
        </div>
        <div className='card bg-transparent-card w-100 border-0 ps-5 mb-3'>
          <img src={imageUrl(masterUser && masterUser.avatar ? masterUser.avatar : null)} alt='user' className='w40 position-absolute left-0' />
          <h5 className='font-xsss text-grey-900 mb-1 mt-0 fw-700 d-block'>Goria Coast <span
            className='text-grey-400 font-xsssss fw-600 float-right mt-1'> 2 min</span></h5>
          <h6 className='text-grey-500 fw-500 font-xssss lh-4'>Mobile Apps UI Designer is require..</h6>
        </div>

        <div className='card bg-transparent-card w-100 border-0 ps-5 mb-3'>
          <img src={imageUrl(masterUser && masterUser.avatar ? masterUser.avatar : null)} alt='user' className='w40 position-absolute left-0' />
          <h5 className='font-xsss text-grey-900 mb-1 mt-0 fw-700 d-block'>Surfiya Zakir <span
            className='text-grey-400 font-xsssss fw-600 float-right mt-1'> 1 min</span></h5>
          <h6 className='text-grey-500 fw-500 font-xssss lh-4'>Mobile Apps UI Designer is require..</h6>
        </div>
        <div className='card bg-transparent-card w-100 border-0 ps-5'>
          <img src={imageUrl(masterUser && masterUser.avatar ? masterUser.avatar : null)} alt='user' className='w40 position-absolute left-0' />
          <h5 className='font-xsss text-grey-900 mb-1 mt-0 fw-700 d-block'>Victor Exrixon <span
            className='text-grey-400 font-xsssss fw-600 float-right mt-1'> 30 sec</span></h5>
          <h6 className='text-grey-500 fw-500 font-xssss lh-4'>Mobile Apps UI Designer is require..</h6>
        </div>
      </div>
      <Link to='/defaultmessage' className='p-2 text-center ms-3 menu-icon chat-active-btn'><i
        className='feather-message-square font-xl text-current' /></Link>
      <Darkbutton />
      {props.isAdmin
        ? <li onClick={() => dispatch(hideComponent())}>
          <i className='feather-eye font-lg bg-greylight btn-round-lg theme-dark-bg text-grey-500 ' />
        </li>
        : <></>
      }
      <Link to='/defaultsettings' className='p-0 ms-3 menu-icon'><img src={imageUrl(masterUser && masterUser.avatar ? masterUser.avatar : null)}
                                                                      alt='user' className='w40 mt--1' /></Link>
      {leftNav()}
    </>
  }


  return (
    <Fragment>
      <div className='nav-header bg-white shadow-xs border-0'>
        {header()}
      </div>
    </Fragment>
  );
};

export default Header;
