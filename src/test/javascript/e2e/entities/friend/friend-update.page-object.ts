import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class FriendUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.friend.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#friend-uuid'));
  friendTypeSelect: ElementFinder = element(by.css('select#friend-friendType'));
  infoSelect: ElementFinder = element(by.css('select#friend-info'));
  friendSelect: ElementFinder = element(by.css('select#friend-friend'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setFriendTypeSelect(friendType) {
    await this.friendTypeSelect.sendKeys(friendType);
  }

  async getFriendTypeSelect() {
    return this.friendTypeSelect.element(by.css('option:checked')).getText();
  }

  async friendTypeSelectLastOption() {
    await this.friendTypeSelect.all(by.tagName('option')).last().click();
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

  async friendSelectLastOption() {
    await this.friendSelect.all(by.tagName('option')).last().click();
  }

  async friendSelectOption(option) {
    await this.friendSelect.sendKeys(option);
  }

  getFriendSelect() {
    return this.friendSelect;
  }

  async getFriendSelectedOption() {
    return this.friendSelect.element(by.css('option:checked')).getText();
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
    await waitUntilDisplayed(this.saveButton);
    await this.friendTypeSelectLastOption();
    await this.infoSelectLastOption();
    await this.friendSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
