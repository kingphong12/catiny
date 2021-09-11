import React from 'react';
import {Switch} from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import Livestream from "./livestream";

const Routes = ({match}) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}`} component={Livestream} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={Livestream} />
    </Switch>
  </>
);

export default Routes;
