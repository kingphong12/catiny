import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class PostLikeUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.postLike.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#post-like-uuid'));
  infoSelect: ElementFinder = element(by.css('select#post-like-info'));
  postSelect: ElementFinder = element(by.css('select#post-like-post'));
  commentSelect: ElementFinder = element(by.css('select#post-like-comment'));

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

  async postSelectLastOption() {
    await this.postSelect.all(by.tagName('option')).last().click();
  }

  async postSelectOption(option) {
    await this.postSelect.sendKeys(option);
  }

  getPostSelect() {
    return this.postSelect;
  }

  async getPostSelectedOption() {
    return this.postSelect.element(by.css('option:checked')).getText();
  }

  async commentSelectLastOption() {
    await this.commentSelect.all(by.tagName('option')).last().click();
  }

  async commentSelectOption(option) {
    await this.commentSelect.sendKeys(option);
  }

  getCommentSelect() {
    return this.commentSelect;
  }

  async getCommentSelectedOption() {
    return this.commentSelect.element(by.css('option:checked')).getText();
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
    await this.postSelectLastOption();
    await this.commentSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
