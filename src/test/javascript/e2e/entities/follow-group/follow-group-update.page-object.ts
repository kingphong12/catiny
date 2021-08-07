import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class FollowGroupUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.followGroup.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#follow-group-uuid'));
  infoSelect: ElementFinder = element(by.css('select#follow-group-info'));
  groupDetailsSelect: ElementFinder = element(by.css('select#follow-group-groupDetails'));

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

  async groupDetailsSelectLastOption() {
    await this.groupDetailsSelect.all(by.tagName('option')).last().click();
  }

  async groupDetailsSelectOption(option) {
    await this.groupDetailsSelect.sendKeys(option);
  }

  getGroupDetailsSelect() {
    return this.groupDetailsSelect;
  }

  async getGroupDetailsSelectedOption() {
    return this.groupDetailsSelect.element(by.css('option:checked')).getText();
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
    await this.groupDetailsSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
