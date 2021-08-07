import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class MessageContentUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.messageContent.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#message-content-uuid'));
  senderNameInput: ElementFinder = element(by.css('input#message-content-senderName'));
  attachInput: ElementFinder = element(by.css('textarea#message-content-attach'));
  contentInput: ElementFinder = element(by.css('textarea#message-content-content'));
  statusInput: ElementFinder = element(by.css('textarea#message-content-status'));
  searchFieldInput: ElementFinder = element(by.css('textarea#message-content-searchField'));
  infoSelect: ElementFinder = element(by.css('select#message-content-info'));
  groupSelect: ElementFinder = element(by.css('select#message-content-group'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setSenderNameInput(senderName) {
    await this.senderNameInput.sendKeys(senderName);
  }

  async getSenderNameInput() {
    return this.senderNameInput.getAttribute('value');
  }

  async setAttachInput(attach) {
    await this.attachInput.sendKeys(attach);
  }

  async getAttachInput() {
    return this.attachInput.getAttribute('value');
  }

  async setContentInput(content) {
    await this.contentInput.sendKeys(content);
  }

  async getContentInput() {
    return this.contentInput.getAttribute('value');
  }

  async setStatusInput(status) {
    await this.statusInput.sendKeys(status);
  }

  async getStatusInput() {
    return this.statusInput.getAttribute('value');
  }

  async setSearchFieldInput(searchField) {
    await this.searchFieldInput.sendKeys(searchField);
  }

  async getSearchFieldInput() {
    return this.searchFieldInput.getAttribute('value');
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

  async groupSelectLastOption() {
    await this.groupSelect.all(by.tagName('option')).last().click();
  }

  async groupSelectOption(option) {
    await this.groupSelect.sendKeys(option);
  }

  getGroupSelect() {
    return this.groupSelect;
  }

  async getGroupSelectedOption() {
    return this.groupSelect.element(by.css('option:checked')).getText();
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
    await this.setSenderNameInput('senderName');
    await waitUntilDisplayed(this.saveButton);
    await this.setAttachInput('attach');
    await waitUntilDisplayed(this.saveButton);
    await this.setContentInput('content');
    await waitUntilDisplayed(this.saveButton);
    await this.setStatusInput('status');
    await waitUntilDisplayed(this.saveButton);
    await this.setSearchFieldInput('searchField');
    await this.infoSelectLastOption();
    await this.groupSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
