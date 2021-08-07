import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class PermissionUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.permission.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#permission-uuid'));
  readInput: ElementFinder = element(by.css('input#permission-read'));
  writeInput: ElementFinder = element(by.css('input#permission-write'));
  shareInput: ElementFinder = element(by.css('input#permission-share'));
  deleteInput: ElementFinder = element(by.css('input#permission-delete'));
  addInput: ElementFinder = element(by.css('input#permission-add'));
  levelInput: ElementFinder = element(by.css('input#permission-level'));
  baseInfoSelect: ElementFinder = element(by.css('select#permission-baseInfo'));
  ownerSelect: ElementFinder = element(by.css('select#permission-owner'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  getReadInput() {
    return this.readInput;
  }
  getWriteInput() {
    return this.writeInput;
  }
  getShareInput() {
    return this.shareInput;
  }
  getDeleteInput() {
    return this.deleteInput;
  }
  getAddInput() {
    return this.addInput;
  }
  async setLevelInput(level) {
    await this.levelInput.sendKeys(level);
  }

  async getLevelInput() {
    return this.levelInput.getAttribute('value');
  }

  async baseInfoSelectLastOption() {
    await this.baseInfoSelect.all(by.tagName('option')).last().click();
  }

  async baseInfoSelectOption(option) {
    await this.baseInfoSelect.sendKeys(option);
  }

  getBaseInfoSelect() {
    return this.baseInfoSelect;
  }

  async getBaseInfoSelectedOption() {
    return this.baseInfoSelect.element(by.css('option:checked')).getText();
  }

  async ownerSelectLastOption() {
    await this.ownerSelect.all(by.tagName('option')).last().click();
  }

  async ownerSelectOption(option) {
    await this.ownerSelect.sendKeys(option);
  }

  getOwnerSelect() {
    return this.ownerSelect;
  }

  async getOwnerSelectedOption() {
    return this.ownerSelect.element(by.css('option:checked')).getText();
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
    const selectedRead = await this.getReadInput().isSelected();
    if (selectedRead) {
      await this.getReadInput().click();
    } else {
      await this.getReadInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedWrite = await this.getWriteInput().isSelected();
    if (selectedWrite) {
      await this.getWriteInput().click();
    } else {
      await this.getWriteInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedShare = await this.getShareInput().isSelected();
    if (selectedShare) {
      await this.getShareInput().click();
    } else {
      await this.getShareInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedDelete = await this.getDeleteInput().isSelected();
    if (selectedDelete) {
      await this.getDeleteInput().click();
    } else {
      await this.getDeleteInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedAdd = await this.getAddInput().isSelected();
    if (selectedAdd) {
      await this.getAddInput().click();
    } else {
      await this.getAddInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    await this.setLevelInput('5');
    await this.baseInfoSelectLastOption();
    await this.ownerSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
