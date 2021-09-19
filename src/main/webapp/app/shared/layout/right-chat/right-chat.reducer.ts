import axios from 'axios';
import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import {loadMoreDataWhenScrolled, parseHeaderForLinks} from 'react-jhipster';
import {IQueryParams} from 'app/shared/reducers/reducer.utils';
import {defaultValue, IMessageGroup} from 'app/shared/model/message-group.model';
import {IMessageContent} from 'app/shared/model/message-content.model';

const initialState: any = {
  loading: false,
  errorMessage: null,
  links: {next: 0},
  updating: false,
  totalItems: 0,
  updateSuccess: false,
  messageGroup: defaultValue,
  messageGroups: [],
};

const apiUrlMessageGroups = 'api/o/messages/groups';
const apiUrlMessageContents = 'api/o/messages/contents';
const apiSearchUrl = 'api/o/message-groups/_search';

// Actions

export const searchEntities = createAsyncThunk('messageGroup/search_entity', async ({query, page, size, sort}: IQueryParams) =>
{
  const requestUrl = `${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`;
  return axios.get<IMessageGroup[]>(requestUrl);
});

// export const getEntities = createAsyncThunk('messageGroup/fetch_entity_list', async ({page, size, sort}: IQueryParams) =>
// {
//   const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}&` : '?'}cacheBuster=${new Date().getTime()}`;
//   return axios.get<IMessageGroup[]>(requestUrl);
// });

/**
 * get all message-groups joined
 */
export const getAllMessageGroupsJoined = createAsyncThunk('rightChat/get_all_message_groups_joined', async ({page, size, sort}: IQueryParams) =>
{
  const requestUrl = `${apiUrlMessageGroups}/joined${sort ? `?page=${page}&size=${size}&sort=${sort}&` : '?'}cacheBuster=${new Date().getTime()}`;
  return axios.get<IMessageGroup[]>(requestUrl);
});

/**
 * get all message-contents by group id
 */
export const getMessageContentByMessageGroupId = createAsyncThunk('rightChat/get_message_content_by_message_group_id',
  async ({page, size, sort, uuidMessageGroups}: IQueryParams & { uuidMessageGroups: string }) =>
  {
    const requestUrl = `${apiUrlMessageGroups}/${uuidMessageGroups}/contents/${`?page=${page}&size=${size}&`}cacheBuster=${new Date().getTime()}`;
    return axios.get<IMessageContent[]>(requestUrl);
  });

/**
 * get all message-contents by group id
 */
export const sendContentToGroup = createAsyncThunk('rightChat/send_content_to_group',
  async ({groupId, content}: { groupId: string, content: string }) =>
  {
    const requestUrl = `${apiUrlMessageGroups}/${groupId}/contents?cacheBuster=${new Date().getTime()}`;
    const formData = new FormData();
    formData.append('content', content);
    return axios.post<IMessageContent[]>(requestUrl, formData);
  });
/**
 * get all message-contents by group id
 */
export const createMessageGroup = createAsyncThunk('rightChat/create_message_group',
  async ({userIds, desiredName}: { userIds: any[], desiredName: string }) =>
  {
    const requestUrl = `${apiUrlMessageGroups}`;
    const formData = new FormData();
    userIds.forEach(userId => formData.append('userIds', userId));
    formData.append('desiredName', desiredName);
    return axios.post<IMessageContent[]>(requestUrl, formData);
  });
//
// export const getEntity = createAsyncThunk(
//   'messageGroup/fetch_entity',
//   async (id: string | number) =>
//   {
//     const requestUrl = `${apiUrl}/${id}`;
//     return axios.get<IMessageGroup>(requestUrl);
//   },
//   {serializeError: serializeAxiosError}
// );
//
// export const createEntity = createAsyncThunk(
//   'messageGroup/create_entity',
//   async (entity: IMessageGroup, thunkAPI) =>
//   {
//     return axios.post<IMessageGroup>(apiUrl, cleanEntity(entity));
//   },
//   {serializeError: serializeAxiosError}
// );
//
// export const updateEntity = createAsyncThunk(
//   'messageGroup/update_entity',
//   async (entity: IMessageGroup, thunkAPI) =>
//   {
//     return axios.put<IMessageGroup>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
//   },
//   {serializeError: serializeAxiosError}
// );
//
// export const partialUpdateEntity = createAsyncThunk(
//   'messageGroup/partial_update_entity',
//   async (entity: IMessageGroup, thunkAPI) =>
//   {
//     return axios.patch<IMessageGroup>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
//   },
//   {serializeError: serializeAxiosError}
// );
//
// export const deleteEntity = createAsyncThunk(
//   'messageGroup/delete_entity',
//   async (id: string | number, thunkAPI) =>
//   {
//     const requestUrl = `${apiUrl}/${id}`;
//     return await axios.delete<IMessageGroup>(requestUrl);
//   },
//   {serializeError: serializeAxiosError}
// );

// slice

export const RightChatSlice = createSlice({
  name: 'messageGroup',
  initialState,
  reducers: {
    reset()
    {
      return initialState;
    },
  },
  extraReducers(builder)
  {
    builder.addCase(getAllMessageGroupsJoined.fulfilled, (state, action) =>
    {
      const links = parseHeaderForLinks(action.payload.headers.link);
      return {
        ...state,
        loading: false,
        messageGroups: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
      };
    });
    // .addCase(deleteEntity.fulfilled, state =>
    // {
    //   state.updating = false;
    //   state.updateSuccess = true;
    //   state.entity = {};
    // })
    // .addMatcher(isFulfilled(getEntities, searchEntities), (state, action) =>
    // {
    //   const links = parseHeaderForLinks(action.payload.headers.link);
    //
    //   return {
    //     ...state,
    //     loading: false,
    //     links,
    //     entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
    //     totalItems: parseInt(action.payload.headers['x-total-count'], 10),
    //   };
    // })
    // .addMatcher(isFulfilled(createEntity, updateEntity, partialUpdateEntity), (state, action) =>
    // {
    //   state.updating = false;
    //   state.loading = false;
    //   state.updateSuccess = true;
    //   state.entity = action.payload.data;
    // })
    // .addMatcher(isPending(getEntities, getEntity, searchEntities), state =>
    // {
    //   state.errorMessage = null;
    //   state.updateSuccess = false;
    //   state.loading = true;
    // })
    // .addMatcher(isPending(createEntity, updateEntity, partialUpdateEntity, deleteEntity), state =>
    // {
    //   state.errorMessage = null;
    //   state.updateSuccess = false;
    //   state.updating = true;
    // })
  },
});

export const {reset} = RightChatSlice.actions;

// Reducer
export default RightChatSlice.reducer;
