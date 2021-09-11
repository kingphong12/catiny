import React from 'react';
import {Switch} from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Login from 'app/modules/login/login';
import Register from 'app/modules/account/register/register';
import Demo from "app-js/demo/Demo";
import HomeSociala from 'app-js/pages/Home';
import Badge from "app-js/pages/Badge";
import Group from "app-js/pages/Group";
import Storie from "app-js/pages/Storie";
import Email from "app-js/pages/Email";
import EmailOpen from "app-js/pages/EmailOpen";
import Settings from "app/modules/setting/settings";
import Videos from "app-js/pages/Videos";
import Analytics from "app-js/pages/Analytics";
import Member from "app-js/pages/Member";
import ContactInfo from "app-js/pages/ContactInfo";
import SocialAccount from "app-js/pages/SocialAccount";
import Password from "app-js/pages/Password";
import Payment from "app-js/pages/Payment";
import Notification from "app-js/pages/Notification";
import HelpBox from "app-js/pages/HelpBox";
import Forgot from "app-js/pages/Forgot";
import Notfound from "app-js/pages/Notfound";
import ShopOne from "app-js/pages/ShopOne";
import ShopTwo from "app-js/pages/ShopTwo";
import ShopThree from "app-js/pages/ShopThree";
import SingleProduct from "app-js/pages/SingleProduct";
import Cart from "app-js/pages/Cart";
import Checkout from "app-js/pages/Checkout";
import Job from "app-js/pages/Job";
import Event from "app-js/pages/Event";
import Hotel from "app-js/pages/Hotel";
import GroupPage from "app-js/pages/GroupPage";
import UserPage from "app-js/pages/UserPage";
import AuthorPage from "app-js/pages/AuthorPage";
import ComingSoon from "app-js/pages/ComingSoon";
import HotelSingle from "app-js/pages/HotelSingle";
import Account2 from "app-js/pages/Account";
import Chat from 'app/modules/message/chat/chat';
import Livestream from "app/modules/livestream/livestream";
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({match}) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute exact path={`${match.url}demo`} component={Demo} />
      <ErrorBoundaryRoute exact path={`${match.url}home`} component={HomeSociala} />
      <ErrorBoundaryRoute exact path={`${match.url}defaultbadge`} component={Badge} />
      <ErrorBoundaryRoute exact path={`${match.url}defaultgroup`} component={Group} />
      <ErrorBoundaryRoute exact path={`${match.url}defaultstorie`} component={Storie} />
      <ErrorBoundaryRoute exact path={`${match.url}defaultemailbox`} component={Email} />
      <ErrorBoundaryRoute exact path={`${match.url}defaultemailopen`} component={EmailOpen} />
      <ErrorBoundaryRoute exact path={`${match.url}defaultsettings`} component={Settings} />
      <ErrorBoundaryRoute exact path={`${match.url}defaultvideo`} component={Videos} />
      <ErrorBoundaryRoute exact path={`${match.url}defaultanalytics`} component={Analytics} />
      <ErrorBoundaryRoute exact path={`${match.url}accountinformation`} component={Account2} />
      <ErrorBoundaryRoute exact path={`${match.url}defaultmember`} component={Member} />
      <ErrorBoundaryRoute exact path={`${match.url}contactinformation`} component={ContactInfo} />
      <ErrorBoundaryRoute exact path={`${match.url}socialaccount`} component={SocialAccount} />
      <ErrorBoundaryRoute exact path={`${match.url}password`} component={Password} />
      <ErrorBoundaryRoute exact path={`${match.url}payment`} component={Payment} />
      <ErrorBoundaryRoute exact path={`${match.url}defaultnotification`} component={Notification} />
      <ErrorBoundaryRoute exact path={`${match.url}helpbox`} component={HelpBox} />
      <ErrorBoundaryRoute exact path={`${match.url}login2`} component={Login} />
      <ErrorBoundaryRoute exact path={`${match.url}register`} component={Register} />
      <ErrorBoundaryRoute exact path={`${match.url}forgot`} component={Forgot} />
      <ErrorBoundaryRoute exact path={`${match.url}notfound`} component={Notfound} />
      <ErrorBoundaryRoute exact path={`${match.url}shop1`} component={ShopOne} />
      <ErrorBoundaryRoute exact path={`${match.url}shop2`} component={ShopTwo} />
      <ErrorBoundaryRoute exact path={`${match.url}shop3`} component={ShopThree} />
      <ErrorBoundaryRoute exact path={`${match.url}singleproduct`} component={SingleProduct} />
      <ErrorBoundaryRoute exact path={`${match.url}cart`} component={Cart} />
      <ErrorBoundaryRoute exact path={`${match.url}checkout`} component={Checkout} />
      <ErrorBoundaryRoute exact path={`${match.url}defaultmessage`} component={Chat} />
      <ErrorBoundaryRoute exact path={`${match.url}defaultlive`} component={Livestream} />
      <ErrorBoundaryRoute exact path={`${match.url}livestream`} component={Livestream} />
      <ErrorBoundaryRoute exact path={`${match.url}defaultjob`} component={Job} />
      <ErrorBoundaryRoute exact path={`${match.url}defaultevent`} component={Event} />
      <ErrorBoundaryRoute exact path={`${match.url}defaulthotel`} component={Hotel} />
      <ErrorBoundaryRoute exact path={`${match.url}grouppage`} component={GroupPage} />
      <ErrorBoundaryRoute exact path={`${match.url}userpage`} component={UserPage} />
      <ErrorBoundaryRoute exact path={`${match.url}groupauthorpage`} component={AuthorPage} />
      <ErrorBoundaryRoute exact path={`${match.url}comingsoon`} component={ComingSoon} />
      <ErrorBoundaryRoute exact path={`${match.url}defaulthoteldetails`} component={HotelSingle} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
