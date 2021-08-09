import React, {Fragment, useEffect, useState} from "react";
import {Link} from 'react-router-dom';
import {AccountMenu, AdminMenu, EntitiesMenu, LocaleMenu} from "app/shared/layout/menus";
import {Other} from "app/shared/layout/header/header-components";
import {useAppDispatch, useAppSelector} from "app/config/store";
import {setLocale} from "app/shared/reducers/locale";
import {Storage, Translate} from 'react-jhipster';
import {LoadingBar} from "react-redux-loading-bar";
import {getSession} from "app/shared/reducers/authentication";
import {getProfile} from "app/shared/reducers/application-profile";
import {hasAnyAuthority} from "app/shared/auth/private-route";
import {AUTHORITIES} from "app/config/constants";

export interface ISettingProp
{
  isAuthenticated: boolean;
  isAdmin: boolean;
  ribbonEnv: string;
  isInProduction: boolean;
  isOpenAPIEnabled: boolean;
  currentLocale: string;
}

const Settings = (props: ISettingProp) =>
{

  const dispatch = useAppDispatch();

  useEffect(() =>
  {
    dispatch(getSession());
    dispatch(getProfile());
  }, []);

  const currentLocale = useAppSelector(state => state.locale.currentLocale);
  const isAuthenticated = useAppSelector(state => state.authentication.isAuthenticated);
  const isAdmin = useAppSelector(state => hasAnyAuthority(state.authentication.account.authorities, [AUTHORITIES.ADMIN]));
  const ribbonEnv = useAppSelector(state => state.applicationProfile.ribbonEnv);
  const isInProduction = useAppSelector(state => state.applicationProfile.inProduction);
  const isOpenAPIEnabled = useAppSelector(state => state.applicationProfile.isOpenAPIEnabled);


  const [menuOpen, setMenuOpen] = useState(false);


  const handleLocaleChange = event =>
  {
    const langKey = event.target.value;
    Storage.session.set('locale', langKey);
    dispatch(setLocale(langKey));
  };

  const renderDevRibbon = () =>
    isInProduction === false ? (
      <div className='ribbon dev'>
        <a href=''>
          <Translate contentKey={`global.ribbon.${ribbonEnv}`} />
        </a>
      </div>
    ) : null;

  return (
    <Fragment>
      <div className='main-content bg-lightblue theme-dark-bg right-chat-active'>
        <div className='middle-sidebar-bottom'>
          <div className='middle-sidebar-left'>
            <div className='middle-wrap'>
              <div className='card w-100 border-0 bg-white shadow-xs p-0 mb-4'>
                <div className='card-body p-lg-5 p-4 w-100 border-0'>
                  <div className='row'>
                    <div className='col-lg-12'>
                      <h4 className='mb-4 font-xxl fw-700 mont-font mb-lg-5 mb-4 font-md-xs'>Settings</h4>
                      <div className='nav-caption fw-600 font-xssss text-grey-500 mb-2'>Genaral</div>
                      <ul className='list-inline mb-4'>
                        <li className='list-inline-item d-block border-bottom me-0'>
                          <Link to='/accountinformation' className='pt-2 pb-2 d-flex align-items-center'>
                            <i className='btn-round-md bg-primary-gradiant text-white feather-home font-md me-3' />
                            <h4 className='fw-600 font-xsss mb-0 mt-0'>Acount Information</h4>
                            <i className='ti-angle-right font-xsss text-grey-500 ms-auto mt-3' />
                          </Link>
                        </li>
                        <li className='list-inline-item d-block border-bottom me-0'>
                          <Link to='/contactinformation' className='pt-2 pb-2 d-flex align-items-center'>
                            <i className='btn-round-md bg-gold-gradiant text-white feather-map-pin font-md me-3' />
                            <h4 className='fw-600 font-xsss mb-0 mt-0'>Saved Address</h4>
                            <i className='ti-angle-right font-xsss text-grey-500 ms-auto mt-3' />
                          </Link>
                        </li>
                        <li className='list-inline-item d-block me-0'>
                          <Link to='/socialaccount' className='pt-2 pb-2 d-flex align-items-center'>
                            <i className='btn-round-md bg-red-gradiant text-white feather-twitter font-md me-3' />
                            <h4 className='fw-600 font-xsss mb-0 mt-0'>Social Acount</h4>
                            <i className='ti-angle-right font-xsss text-grey-500 ms-auto mt-3' />
                          </Link>
                        </li>
                      </ul>
                      <div className='nav-caption fw-600 font-xsss text-grey-500 mb-2'>Acount</div>
                      <ul className='list-inline mb-4'>
                        <li className='list-inline-item d-block border-bottom me-0'>
                          <Link to='/payment' className='pt-2 pb-2 d-flex align-items-center'>
                            <i className='btn-round-md bg-mini-gradiant text-white feather-credit-card font-md me-3' />
                            <h4 className='fw-600 font-xsss mb-0 mt-0'>My Cards</h4
                            ><i className='ti-angle-right font-xsss text-grey-500 ms-auto mt-3' />
                          </Link>
                        </li>
                        <li className='list-inline-item d-block  me-0'>
                          <Link to='/password' className='pt-2 pb-2 d-flex align-items-center'>
                            <i className='btn-round-md bg-blue-gradiant text-white feather-inbox font-md me-3' />
                            <h4 className='fw-600 font-xsss mb-0 mt-0'>Password</h4>
                            <i className='ti-angle-right font-xsss text-grey-500 ms-auto mt-3' />
                          </Link>
                        </li>
                      </ul>
                      <div className='nav-caption fw-600 font-xsss text-grey-500 mb-2'>Other</div>
                      <ul className='list-inline'>
                        <li className='list-inline-item d-block border-bottom me-0'>
                          <Link to='/defaultnoti' className='pt-2 pb-2 d-flex align-items-center'>
                            <i className='btn-round-md bg-gold-gradiant text-white feather-bell font-md me-3' />
                            <h4 className='fw-600 font-xsss mb-0 mt-0'>Notification</h4>
                            <i className='ti-angle-right font-xsss text-grey-500 ms-auto mt-3' />
                          </Link>
                        </li>
                        <li className='list-inline-item d-block border-bottom me-0'>
                          <a href='/helpbox' className='pt-2 pb-2 d-flex align-items-center'>
                            <i className='btn-round-md bg-primary-gradiant text-white feather-help-circle font-md me-3' />
                            <h4 className='fw-600 font-xsss mb-0 mt-0'>Help</h4>
                            <i className='ti-angle-right font-xsss text-grey-500 ms-auto mt-3' />
                          </a>
                        </li>
                        <li className='list-inline-item d-block me-0'>
                          <a href='/login' className='pt-2 pb-2 d-flex align-items-center'>
                            <i className='btn-round-md bg-red-gradiant text-white feather-lock font-md me-3' />
                            <h4 className='fw-600 font-xsss mb-0 mt-0'>Logout</h4>
                            <i className='ti-angle-right font-xsss text-grey-500 ms-auto mt-3' />
                          </a>
                        </li>
                        <li>
                          {renderDevRibbon()}
                          <LoadingBar className='loading-bar' />
                          {isAuthenticated && isAdmin && <EntitiesMenu />}
                          {isAuthenticated && isAdmin && (
                            <AdminMenu showOpenAPI={isOpenAPIEnabled} showDatabase={!isInProduction} />
                          )}
                          <LocaleMenu currentLocale={currentLocale} onClick={handleLocaleChange} />
                          <AccountMenu isAuthenticated={isAuthenticated} />
                          <Other />
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </Fragment>
  );
}

export default Settings;