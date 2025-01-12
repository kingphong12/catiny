import {
  classInvalid,
  classValid,
  confirmPasswordSelector,
  currentPasswordSelector,
  newPasswordSelector,
  submitPasswordSelector,
} from '../../support/commands';

describe('/account/password', () => {
  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });
    cy.clearCookies();
    cy.visit('');
    cy.login('user', 'user');
    cy.clickOnPasswordItem();
  });

  beforeEach(() => {
    cy.intercept('POST', '/api/account/change-password').as('passwordSave');
  });

  it('requires current password', () => {
    cy.get(submitPasswordSelector).click({ force: true });
    cy.get(currentPasswordSelector).should('have.class', classInvalid).type('wrong-current-password');
    cy.get(submitPasswordSelector).click({ force: true });
    cy.get(currentPasswordSelector).should('have.class', classValid);
    cy.get(currentPasswordSelector).clear();
  });

  it('requires new password', () => {
    cy.get(newPasswordSelector).should('have.class', classInvalid).type('jhipster');
    cy.get(submitPasswordSelector).click({ force: true });
    cy.get(newPasswordSelector).should('have.class', classValid);
    cy.get(newPasswordSelector).clear();
  });

  it('requires confirm new password', () => {
    cy.get(newPasswordSelector).type('jhipster');
    cy.get(confirmPasswordSelector).should('have.class', classInvalid).type('jhipster');
    cy.get(submitPasswordSelector).click({ force: true });
    cy.get(confirmPasswordSelector).should('have.class', classValid);
    cy.get(newPasswordSelector).clear();
    cy.get(confirmPasswordSelector).clear();
  });

  it('should fail to update password when using incorrect current password', () => {
    cy.get(currentPasswordSelector).type('wrong-current-password');
    cy.get(newPasswordSelector).type('jhipster');
    cy.get(confirmPasswordSelector).type('jhipster');
    cy.get(submitPasswordSelector).click({ force: true });
    cy.wait('@passwordSave').then(({ response }) => expect(response.statusCode).to.equal(400));
    cy.get(currentPasswordSelector).clear();
    cy.get(newPasswordSelector).clear();
    cy.get(confirmPasswordSelector).clear();
  });

  it('should be able to update password', () => {
    cy.get(currentPasswordSelector).type('user');
    cy.get(newPasswordSelector).type('user');
    cy.get(confirmPasswordSelector).type('user');
    cy.get(submitPasswordSelector).click({ force: true });
    cy.wait('@passwordSave').then(({ response }) => expect(response.statusCode).to.equal(200));
  });
});
