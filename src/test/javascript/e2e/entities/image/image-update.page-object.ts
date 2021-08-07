import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class ImageUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.image.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#image-uuid'));
  nameInput: ElementFinder = element(by.css('input#image-name'));
  widthInput: ElementFinder = element(by.css('input#image-width'));
  heightInput: ElementFinder = element(by.css('input#image-height'));
  qualityInput: ElementFinder = element(by.css('input#image-quality'));
  pixelSizeInput: ElementFinder = element(by.css('input#image-pixelSize'));
  priorityIndexInput: ElementFinder = element(by.css('input#image-priorityIndex'));
  dataSizeInput: ElementFinder = element(by.css('input#image-dataSize'));
  fileInfoSelect: ElementFinder = element(by.css('select#image-fileInfo'));
  infoSelect: ElementFinder = element(by.css('select#image-info'));
  originalSelect: ElementFinder = element(by.css('select#image-original'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return this.nameInput.getAttribute('value');
  }

  async setWidthInput(width) {
    await this.widthInput.sendKeys(width);
  }

  async getWidthInput() {
    return this.widthInput.getAttribute('value');
  }

  async setHeightInput(height) {
    await this.heightInput.sendKeys(height);
  }

  async getHeightInput() {
    return this.heightInput.getAttribute('value');
  }

  async setQualityInput(quality) {
    await this.qualityInput.sendKeys(quality);
  }

  async getQualityInput() {
    return this.qualityInput.getAttribute('value');
  }

  async setPixelSizeInput(pixelSize) {
    await this.pixelSizeInput.sendKeys(pixelSize);
  }

  async getPixelSizeInput() {
    return this.pixelSizeInput.getAttribute('value');
  }

  async setPriorityIndexInput(priorityIndex) {
    await this.priorityIndexInput.sendKeys(priorityIndex);
  }

  async getPriorityIndexInput() {
    return this.priorityIndexInput.getAttribute('value');
  }

  async setDataSizeInput(dataSize) {
    await this.dataSizeInput.sendKeys(dataSize);
  }

  async getDataSizeInput() {
    return this.dataSizeInput.getAttribute('value');
  }

  async fileInfoSelectLastOption() {
    await this.fileInfoSelect.all(by.tagName('option')).last().click();
  }

  async fileInfoSelectOption(option) {
    await this.fileInfoSelect.sendKeys(option);
  }

  getFileInfoSelect() {
    return this.fileInfoSelect;
  }

  async getFileInfoSelectedOption() {
    return this.fileInfoSelect.element(by.css('option:checked')).getText();
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

  async originalSelectLastOption() {
    await this.originalSelect.all(by.tagName('option')).last().click();
  }

  async originalSelectOption(option) {
    await this.originalSelect.sendKeys(option);
  }

  getOriginalSelect() {
    return this.originalSelect;
  }

  async getOriginalSelectedOption() {
    return this.originalSelect.element(by.css('option:checked')).getText();
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
    await this.setNameInput('name');
    await waitUntilDisplayed(this.saveButton);
    await this.setWidthInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setHeightInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setQualityInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setPixelSizeInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setPriorityIndexInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setDataSizeInput('5');
    await this.fileInfoSelectLastOption();
    await this.infoSelectLastOption();
    await this.originalSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}