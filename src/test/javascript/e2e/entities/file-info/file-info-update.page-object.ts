import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class FileInfoUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.fileInfo.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#file-info-uuid'));
  nameFileInput: ElementFinder = element(by.css('input#file-info-nameFile'));
  typeFileInput: ElementFinder = element(by.css('input#file-info-typeFile'));
  pathInput: ElementFinder = element(by.css('input#file-info-path'));
  dataSizeInput: ElementFinder = element(by.css('input#file-info-dataSize'));
  infoSelect: ElementFinder = element(by.css('select#file-info-info'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setNameFileInput(nameFile) {
    await this.nameFileInput.sendKeys(nameFile);
  }

  async getNameFileInput() {
    return this.nameFileInput.getAttribute('value');
  }

  async setTypeFileInput(typeFile) {
    await this.typeFileInput.sendKeys(typeFile);
  }

  async getTypeFileInput() {
    return this.typeFileInput.getAttribute('value');
  }

  async setPathInput(path) {
    await this.pathInput.sendKeys(path);
  }

  async getPathInput() {
    return this.pathInput.getAttribute('value');
  }

  async setDataSizeInput(dataSize) {
    await this.dataSizeInput.sendKeys(dataSize);
  }

  async getDataSizeInput() {
    return this.dataSizeInput.getAttribute('value');
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
    await this.setNameFileInput('nameFile');
    await waitUntilDisplayed(this.saveButton);
    await this.setTypeFileInput('typeFile');
    await waitUntilDisplayed(this.saveButton);
    await this.setPathInput('path');
    await waitUntilDisplayed(this.saveButton);
    await this.setDataSizeInput('5');
    await this.infoSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
