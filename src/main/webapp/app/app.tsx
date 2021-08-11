import 'react-toastify/dist/ReactToastify.css';
import './app.scss';
import 'app/config/dayjs.ts';

import React, {useEffect} from 'react';
import {BrowserRouter as Router, Switch} from 'react-router-dom';
import {toast, ToastContainer} from 'react-toastify';
import {hot} from 'react-hot-loader';

import {useAppDispatch, useAppSelector} from 'app/config/store';
import {getSession} from 'app/shared/reducers/authentication';
import {getProfile} from 'app/shared/reducers/application-profile';
import Header from 'app/shared/layout/header/header';
import Footer from 'app/shared/layout/footer/footer';
import {hasAnyAuthority} from 'app/shared/auth/private-route';
import ErrorBoundary from 'app/shared/error/error-boundary';
import {AUTHORITIES} from 'app/config/constants';
import AppRoutes from 'app/routes';

import '../assets/scss/main.scss';
import LeftNav from "app/shared/layout/left-nav/left-nav";
import RightChat from "app-js/components/RightChat";
import PopupChat from "app-js/components/PopupChat";
import ErrorBoundaryRoute from "app/shared/error/error-boundary-route";
import Demo from "app-js/demo/Demo";


const baseHref = document.querySelector('base').getAttribute('href').replace(/\/$/, '');

export const App = () =>
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
  const hideComponent = useAppSelector(state => state.setting.hideComponent);

  // const paddingTop = isAdmin ? '52px' : '0px';
  const paddingTop = '0px';
  return (
    <Router basename={baseHref}>
      <Switch>
        <ErrorBoundaryRoute path='/demo' exact component={Demo} />
        <div className='app-container' style={{paddingTop}}>
          <ToastContainer position={toast.POSITION.TOP_LEFT} className='toastify-container' toastClassName='toastify-toast' />
          <ErrorBoundary>
            <Header isAuthenticated={isAuthenticated}
                    isAdmin={isAdmin}
                    currentLocale={currentLocale}
                    ribbonEnv={ribbonEnv}
                    isInProduction={isInProduction}
                    isOpenAPIEnabled={isOpenAPIEnabled} />
            {isAuthenticated && !hideComponent ? <><LeftNav /><RightChat /><PopupChat /></> : <></>}
          </ErrorBoundary>
          <div id='app-view-container'>
            <div className='jh-card' style={{paddingTop}}>
              <ErrorBoundary>
                <AppRoutes />
              </ErrorBoundary>
            </div>
            <Footer />
          </div>
        </div>
      </Switch>
    </Router>
  );
};

export default hot(module)(App);