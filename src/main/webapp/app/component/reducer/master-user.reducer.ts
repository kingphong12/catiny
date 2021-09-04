import axios from 'axios';
import {createAsyncThunk, isFulfilled, isPending} from '@reduxjs/toolkit';
import {loadMoreDataWhenScrolled, parseHeaderForLinks} from 'react-jhipster';

import {cleanEntity} from 'app/shared/util/entity-utils';
import {createEntitySlice, IQueryParams, serializeAxiosError} from 'app/shared/reducers/reducer.utils';
import {defaultValue, IMasterUser} from 'app/shared/model/master-user.model';

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  links: {next: 0},
  updating: false,
  totalItems: 0,
  updateSuccess: false,
  resultSearchUsers: [],
};

const apiUrl = 'api/o/users';
const apiSearchUrl = 'api/o/users/_search';

// Actions

export const getMasterUsersDetailsPublic = createAsyncThunk("masterUser/get_master_users_details_public", async (uuid: string) =>
{
  //  api/o/master-users/{uuid}/_public
  return axios.get(`${apiUrl}/${uuid}/_public`);
});

export const searchMasterUser = createAsyncThunk("masterUser/search_master_user", async ({query, page, size, sort}: IQueryParams) =>
{
  //  api/o/master-users/{uuid}/_public
  const requestUrl = `${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`;
  return axios.get<IMasterUser[]>(requestUrl);
});

export const searchEntities = createAsyncThunk('masterUser/search_entity', async ({query, page, size, sort}: IQueryParams) =>
{
  const requestUrl = `${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`;
  return axios.get<IMasterUser[]>(requestUrl);
});

export const getEntities = createAsyncThunk('masterUser/fetch_entity_list', async ({page, size, sort}: IQueryParams) =>
{
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}&` : '?'}cacheBuster=${new Date().getTime()}`;
  return axios.get<IMasterUser[]>(requestUrl);
});

export const getEntity = createAsyncThunk(
  'masterUser/fetch_entity',
  async (id: string | number) =>
  {
    const requestUrl = `${apiUrl}/${id}`;
    return axios.get<IMasterUser>(requestUrl);
  },
  {serializeError: serializeAxiosError}
);

export const createEntity = createAsyncThunk(
  'masterUser/create_entity',
  async (entity: IMasterUser, thunkAPI) =>
  {
    return axios.post<IMasterUser>(apiUrl, cleanEntity(entity));
  },
  {serializeError: serializeAxiosError}
);

export const updateEntity = createAsyncThunk(
  'masterUser/update_entity',
  async (entity: IMasterUser, thunkAPI) =>
  {
    return axios.put<IMasterUser>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
  },
  {serializeError: serializeAxiosError}
);

export const partialUpdateEntity = createAsyncThunk(
  'masterUser/partial_update_entity',
  async (entity: IMasterUser, thunkAPI) =>
  {
    return axios.patch<IMasterUser>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
  },
  {serializeError: serializeAxiosError}
);

export const deleteEntity = createAsyncThunk(
  'masterUser/delete_entity',
  async (id: string | number, thunkAPI) =>
  {
    const requestUrl = `${apiUrl}/${id}`;
    return await axios.delete<IMasterUser>(requestUrl);
  },
  {serializeError: serializeAxiosError}
);

// slice

export const MasterUserSlice = createEntitySlice({
  name: 'masterUser',
  initialState,
  extraReducers(builder)
  {
    builder
      .addCase(getEntity.fulfilled, (state, action) =>
      {
        state.loading = false;
        state.entity = action.payload.data;
      })
      .addCase(searchMasterUser.fulfilled, (state: any, action) =>
      {
        state.loading = false;
        state.resultSearchUsers = action.payload.data;
      })
      .addCase(deleteEntity.fulfilled, state =>
      {
        state.updating = false;
        state.updateSuccess = true;
        state.entity = {};
      })
      .addMatcher(isFulfilled(getEntities, searchEntities), (state, action) =>
      {
        const links = parseHeaderForLinks(action.payload.headers.link);

        return {
          ...state,
          loading: false,
          links,
          entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
          totalItems: parseInt(action.payload.headers['x-total-count'], 10),
        };
      })
      .addMatcher(isFulfilled(createEntity, updateEntity, partialUpdateEntity), (state, action) =>
      {
        state.updating = false;
        state.loading = false;
        state.updateSuccess = true;
        state.entity = action.payload.data;
      })
      .addMatcher(isPending(getEntities, getEntity, searchEntities), state =>
      {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      })
      .addMatcher(isPending(createEntity, updateEntity, partialUpdateEntity, deleteEntity), state =>
      {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.updating = true;
      });
  },
});

export const {reset} = MasterUserSlice.actions;

// Reducer
export default MasterUserSlice.reducer;
