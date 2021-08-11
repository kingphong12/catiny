import './header.scss';

import React, {Fragment, useState} from 'react';
import {Translate} from 'react-jhipster';

import {Brand} from './header-components';
import Darkbutton from "app-js/components/DarkMode";
import {Link, NavLink} from 'react-router-dom';
import {hideComponent} from "app/modules/setting/setting.reducer";
import {useDispatch} from 'react-redux';

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

  const renderDevRibbon = () =>
    props.isInProduction === false ? (
      <div className='ribbon dev'>
        <a href=''>
          <Translate contentKey={`global.ribbon.${props.ribbonEnv}`} />
        </a>
      </div>
    ) : null;

  const dispatch = useDispatch();

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

  const header = () =>
  {
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

      <form action='#' className='float-left header-search ms-3'>
        <div className='form-group mb-0 icon-input'>
          <i className='feather-search font-sm text-grey-400' />
          <input type='text' placeholder='Start typing to search..'
                 className='bg-grey border-0 lh-32 pt-2 pb-2 ps-5 pe-3 font-xssss fw-500 rounded-xl w350 theme-dark-bg' />
        </div>
      </form>
      <NavLink activeClassName='active' to='/home' className='p-2 text-center ms-3 menu-icon center-menu-icon'>
        <i className='feather-home font-lg bg-greylight btn-round-lg theme-dark-bg text-grey-500 ' />

      </NavLink>
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
      <div className={`dropdown-menu p-4 right-0 rounded-xxl border-0 shadow-lg ${notiClass}`} aria-labelledby='dropdownMenu3'>
        <h4 className='fw-700 font-xss mb-4'>Notification</h4>
        <div className='card bg-transparent-card w-100 border-0 ps-5 mb-3'>
          <img src='assets/images/user.png' alt='user' className='w40 position-absolute left-0' />
          <h5 className='font-xsss text-grey-900 mb-1 mt-0 fw-700 d-block'>Hendrix Stamp <span
            className='text-grey-400 font-xsssss fw-600 float-right mt-1'> 3 min</span></h5>
          <h6 className='text-grey-500 fw-500 font-xssss lh-4'>There are many variations of pass..</h6>
        </div>
        <div className='card bg-transparent-card w-100 border-0 ps-5 mb-3'>
          <img src='assets/images/user.png' alt='user' className='w40 position-absolute left-0' />
          <h5 className='font-xsss text-grey-900 mb-1 mt-0 fw-700 d-block'>Goria Coast <span
            className='text-grey-400 font-xsssss fw-600 float-right mt-1'> 2 min</span></h5>
          <h6 className='text-grey-500 fw-500 font-xssss lh-4'>Mobile Apps UI Designer is require..</h6>
        </div>

        <div className='card bg-transparent-card w-100 border-0 ps-5 mb-3'>
          <img src='assets/images/user.png' alt='user' className='w40 position-absolute left-0' />
          <h5 className='font-xsss text-grey-900 mb-1 mt-0 fw-700 d-block'>Surfiya Zakir <span
            className='text-grey-400 font-xsssss fw-600 float-right mt-1'> 1 min</span></h5>
          <h6 className='text-grey-500 fw-500 font-xssss lh-4'>Mobile Apps UI Designer is require..</h6>
        </div>
        <div className='card bg-transparent-card w-100 border-0 ps-5'>
          <img src='assets/images/user.png' alt='user' className='w40 position-absolute left-0' />
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
      <Link to='/defaultsettings' className='p-0 ms-3 menu-icon'><img src='assets/images/user.png' alt='user' className='w40 mt--1' /></Link>
    </>
  }


  return (
    <Fragment>
      <div className='nav-header bg-white shadow-xs border-0'>
        {header()}
      </div>
    </Fragment>
  );


  // return (
  //   <div id='app-header'>
  //     {renderDevRibbon()}
  //     <LoadingBar className='loading-bar' />
  //     <Navbar data-cy='navbar' dark expand='sm' fixed='top' className='bg-primary p-0'>
  //       <div className='container-xl'>
  //         <Brand />
  //         <Collapse isOpen={menuOpen} navbar>
  //           <Nav id='header-tabs' className='ml-auto' navbar>
  //             <Home />
  //             {props.isAuthenticated && props.isAdmin && <EntitiesMenu/>}
  //             {props.isAuthenticated && props.isAdmin && (
  //               <AdminMenu showOpenAPI={props.isOpenAPIEnabled} showDatabase={!props.isInProduction}/>
  //             )}
  //             <Other></Other>
  //             <LocaleMenu currentLocale={props.currentLocale} onClick={handleLocaleChange} />
  //             <AccountMenu isAuthenticated={props.isAuthenticated} />
  //           </Nav>
  //         </Collapse>
  //         <NavbarToggler aria-label="Menu" onClick={toggleMenu} />
  //       </div>
  //     </Navbar>
  //   </div>
  // );
};

export default Header;
