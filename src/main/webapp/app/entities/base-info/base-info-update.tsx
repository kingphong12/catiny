import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IMasterUser } from 'app/shared/model/master-user.model';
import { getEntities as getMasterUsers } from 'app/entities/master-user/master-user.reducer';
import { IClassInfo } from 'app/shared/model/class-info.model';
import { getEntities as getClassInfos } from 'app/entities/class-info/class-info.reducer';
import { getEntity, updateEntity, createEntity, reset } from './base-info.reducer';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const BaseInfoUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const masterUsers = useAppSelector(state => state.masterUser.entities);
  const classInfos = useAppSelector(state => state.classInfo.entities);
  const baseInfoEntity = useAppSelector(state => state.baseInfo.entity);
  const loading = useAppSelector(state => state.baseInfo.loading);
  const updating = useAppSelector(state => state.baseInfo.updating);
  const updateSuccess = useAppSelector(state => state.baseInfo.updateSuccess);

  const handleClose = () => {
    props.history.push('/base-info');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getMasterUsers({}));
    dispatch(getClassInfos({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.createdDate = convertDateTimeToServer(values.createdDate);
    values.modifiedDate = convertDateTimeToServer(values.modifiedDate);

    const entity = {
      ...baseInfoEntity,
      ...values,
      createdBy: masterUsers.find(it => it.id.toString() === values.createdById.toString()),
      modifiedBy: masterUsers.find(it => it.id.toString() === values.modifiedById.toString()),
      owner: masterUsers.find(it => it.id.toString() === values.ownerId.toString()),
      classInfo: classInfos.find(it => it.id.toString() === values.classInfoId.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          createdDate: displayDefaultDateTime(),
          modifiedDate: displayDefaultDateTime(),
        }
      : {
          ...baseInfoEntity,
          processStatus: 'NOT_PROCESSED',
          createdDate: convertDateTimeFromServer(baseInfoEntity.createdDate),
          modifiedDate: convertDateTimeFromServer(baseInfoEntity.modifiedDate),
          createdById: baseInfoEntity?.createdBy?.id,
          modifiedById: baseInfoEntity?.modifiedBy?.id,
          ownerId: baseInfoEntity?.owner?.id,
          classInfoId: baseInfoEntity?.classInfo?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.baseInfo.home.createOrEditLabel" data-cy="BaseInfoCreateUpdateHeading">
            <Translate contentKey="catinyApp.baseInfo.home.createOrEditLabel">Create or edit a BaseInfo</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="base-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.baseInfo.uuid')}
                id="base-info-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.baseInfo.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.processStatus')}
                id="base-info-processStatus"
                name="processStatus"
                data-cy="processStatus"
                type="select"
              >
                <option value="NOT_PROCESSED">{translate('catinyApp.ProcessStatus.NOT_PROCESSED')}</option>
                <option value="PROCESSING">{translate('catinyApp.ProcessStatus.PROCESSING')}</option>
                <option value="PROCESSED">{translate('catinyApp.ProcessStatus.PROCESSED')}</option>
                <option value="NEED_PROCESS">{translate('catinyApp.ProcessStatus.NEED_PROCESS')}</option>
                <option value="NEED_PROCESS_NOW">{translate('catinyApp.ProcessStatus.NEED_PROCESS_NOW')}</option>
                <option value="NEED_HANDLE">{translate('catinyApp.ProcessStatus.NEED_HANDLE')}</option>
                <option value="NEED_HANDLE_NOW">{translate('catinyApp.ProcessStatus.NEED_HANDLE_NOW')}</option>
                <option value="ERROR">{translate('catinyApp.ProcessStatus.ERROR')}</option>
                <option value="DONE">{translate('catinyApp.ProcessStatus.DONE')}</option>
              </ValidatedField>
              <UncontrolledTooltip target="processStatusLabel">
                <Translate contentKey="catinyApp.baseInfo.help.processStatus" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.modifiedClass')}
                id="base-info-modifiedClass"
                name="modifiedClass"
                data-cy="modifiedClass"
                type="text"
              />
              <UncontrolledTooltip target="modifiedClassLabel">
                <Translate contentKey="catinyApp.baseInfo.help.modifiedClass" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.createdDate')}
                id="base-info-createdDate"
                name="createdDate"
                data-cy="createdDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <UncontrolledTooltip target="createdDateLabel">
                <Translate contentKey="catinyApp.baseInfo.help.createdDate" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.modifiedDate')}
                id="base-info-modifiedDate"
                name="modifiedDate"
                data-cy="modifiedDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <UncontrolledTooltip target="modifiedDateLabel">
                <Translate contentKey="catinyApp.baseInfo.help.modifiedDate" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.notes')}
                id="base-info-notes"
                name="notes"
                data-cy="notes"
                type="textarea"
              />
              <UncontrolledTooltip target="notesLabel">
                <Translate contentKey="catinyApp.baseInfo.help.notes" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.deleted')}
                id="base-info-deleted"
                name="deleted"
                data-cy="deleted"
                check
                type="checkbox"
              />
              <UncontrolledTooltip target="deletedLabel">
                <Translate contentKey="catinyApp.baseInfo.help.deleted" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.priorityIndex')}
                id="base-info-priorityIndex"
                name="priorityIndex"
                data-cy="priorityIndex"
                type="text"
              />
              <UncontrolledTooltip target="priorityIndexLabel">
                <Translate contentKey="catinyApp.baseInfo.help.priorityIndex" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.countUse')}
                id="base-info-countUse"
                name="countUse"
                data-cy="countUse"
                type="text"
              />
              <UncontrolledTooltip target="countUseLabel">
                <Translate contentKey="catinyApp.baseInfo.help.countUse" />
              </UncontrolledTooltip>
              <ValidatedField
                id="base-info-createdBy"
                name="createdById"
                data-cy="createdBy"
                label={translate('catinyApp.baseInfo.createdBy')}
                type="select"
              >
                <option value="" key="0" />
                {masterUsers
                  ? masterUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="base-info-modifiedBy"
                name="modifiedById"
                data-cy="modifiedBy"
                label={translate('catinyApp.baseInfo.modifiedBy')}
                type="select"
              >
                <option value="" key="0" />
                {masterUsers
                  ? masterUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="base-info-owner"
                name="ownerId"
                data-cy="owner"
                label={translate('catinyApp.baseInfo.owner')}
                type="select"
              >
                <option value="" key="0" />
                {masterUsers
                  ? masterUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="base-info-classInfo"
                name="classInfoId"
                data-cy="classInfo"
                label={translate('catinyApp.baseInfo.classInfo')}
                type="select"
              >
                <option value="" key="0" />
                {classInfos
                  ? classInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/base-info" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default BaseInfoUpdate;
