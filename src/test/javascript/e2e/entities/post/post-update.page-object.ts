import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class PostUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.post.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#post-uuid'));
  postInTypeSelect: ElementFinder = element(by.css('select#post-postInType'));
  postTypeSelect: ElementFinder = element(by.css('select#post-postType'));
  contentInput: ElementFinder = element(by.css('textarea#post-content'));
  searchFieldInput: ElementFinder = element(by.css('textarea#post-searchField'));
  infoSelect: ElementFinder = element(by.css('select#post-info'));
  groupSelect: ElementFinder = element(by.css('select#post-group'));
  pageSelect: ElementFinder = element(by.css('select#post-page'));
  parentSelect: ElementFinder = element(by.css('select#post-parent'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setPostInTypeSelect(postInType) {
    await this.postInTypeSelect.sendKeys(postInType);
  }

  async getPostInTypeSelect() {
    return this.postInTypeSelect.element(by.css('option:checked')).getText();
  }

  async postInTypeSelectLastOption() {
    await this.postInTypeSelect.all(by.tagName('option')).last().click();
  }
  async setPostTypeSelect(postType) {
    await this.postTypeSelect.sendKeys(postType);
  }

  async getPostTypeSelect() {
    return this.postTypeSelect.element(by.css('option:checked')).getText();
  }

  async postTypeSelectLastOption() {
    await this.postTypeSelect.all(by.tagName('option')).last().click();
  }
  async setContentInput(content) {
    await this.contentInput.sendKeys(content);
  }

  async getContentInput() {
    return this.contentInput.getAttribute('value');
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

  async pageSelectLastOption() {
    await this.pageSelect.all(by.tagName('option')).last().click();
  }

  async pageSelectOption(option) {
    await this.pageSelect.sendKeys(option);
  }

  getPageSelect() {
    return this.pageSelect;
  }

  async getPageSelectedOption() {
    return this.pageSelect.element(by.css('option:checked')).getText();
  }

  async parentSelectLastOption() {
    await this.parentSelect.all(by.tagName('option')).last().click();
  }

  async parentSelectOption(option) {
    await this.parentSelect.sendKeys(option);
  }

  getParentSelect() {
    return this.parentSelect;
  }

  async getParentSelectedOption() {
    return this.parentSelect.element(by.css('option:checked')).getText();
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
    await this.postInTypeSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.postTypeSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.setContentInput('content');
    await waitUntilDisplayed(this.saveButton);
    await this.setSearchFieldInput('searchField');
    await this.infoSelectLastOption();
    await this.groupSelectLastOption();
    await this.pageSelectLastOption();
    await this.parentSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
