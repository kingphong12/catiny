import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class FollowUserUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.followUser.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#follow-user-uuid'));
  infoSelect: ElementFinder = element(by.css('select#follow-user-info'));
  followSelect: ElementFinder = element(by.css('select#follow-user-follow'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async infoSelectLastOption() {
    await this.infoSelect.all(by.tagName('option')).last().click();
  }

  async infoSelectOption(option) {
    await this.infoSelect.sendKeys(option);
  }

  getInfoSelect() {
    return this.infoSelect;
  }

  async getInfoSelectedOption() {
    return this.infoSelect.element(by.css('option:checked')).getText();
  }

  async followSelectLastOption() {
    await this.followSelect.all(by.tagName('option')).last().click();
  }

  async followSelectOption(option) {
    await this.followSelect.sendKeys(option);
  }

  getFollowSelect() {
    return this.followSelect;
  }

  async getFollowSelectedOption() {
    return this.followSelect.element(by.css('option:checked')).getText();
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }

  async enterData() {
    await waitUntilDisplayed(this.saveButton);
    await this.setUuidInput('64c99148-3908-465d-8c4a-e510e3ade974');
    await this.infoSelectLastOption();
    await this.followSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
