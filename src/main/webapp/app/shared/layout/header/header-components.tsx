import React from 'react';
import {Translate} from 'react-jhipster';

import {NavbarBrand, NavItem, NavLink} from 'reactstrap';
import {NavLink as Link} from 'react-router-dom';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faComments} from '@fortawesome/free-solid-svg-icons';

import appConfig from 'app/config/constants';
import {NavDropdown} from 'app/shared/layout/menus/menu-components';
import MenuItem from 'app/shared/layout/menus/menu-item';

export const BrandIcon = props => (
  <div {...props} className='brand-icon'>
    <img src='content/images/logo/Catiny-logo2.svg' alt='Logo' />
  </div>
);

export const Brand = props => (
  <NavbarBrand tag={Link} to="/" className="brand-logo">
    <BrandIcon />
    <span className="brand-title">
      {/*<Translate contentKey="global.title">Catiny</Translate>*/}
    </span>
    <span className="navbar-version">{appConfig.VERSION}</span>
  </NavbarBrand>
);

export const Home = props => (
  <NavItem>
    <NavLink tag={Link} to="/" className="d-flex align-items-center">
      <FontAwesomeIcon icon="home" />
      <span className="text-nowrap">
        <Translate contentKey="global.menu.home">Home</Translate>
      </span>
    </NavLink>
  </NavItem>
);

export const Other = props => (
  <NavDropdown name="other">
    <MenuItem icon={faComments} to="/chat/video-call" id="video-call">
      Video call
    </MenuItem>
    <MenuItem icon={faComments} to="/chat/messages" id="messages">
      Messages
    </MenuItem>
  </NavDropdown>
);
