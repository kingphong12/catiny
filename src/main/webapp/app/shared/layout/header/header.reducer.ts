import axios from 'axios';
import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import {IQueryParams} from 'app/shared/reducers/reducer.utils';
import {IMasterUser} from 'app/shared/model/master-user.model';

const initialState = {
  loading: false,
  errorMessage: null,
  links: {next: 0},
  resultSearchMasterUser: [],
};

// const apiUrl = 'api/o/users';


// Actions

export const searchMasterUser = createAsyncThunk("headers/search_master_user", async ({query, page, size, sort}: IQueryParams) =>
{
  //  api/o/master-users/{uuid}/_public
  const requestUrl = `api/o/users/_search?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`;
  return axios.get<IMasterUser[]>(requestUrl);
});


// slice

export const HeadersSlice = createSlice({
  name: 'headers',
  initialState,
  reducers: {
    reset()
    {
      return initialState;
    },
    cleanResultSearchMasterUser(state)
    {
      return {...state, resultSearchMasterUser: []}
    }
  },
  extraReducers(builder)
  {
    builder
      .addCase(searchMasterUser.fulfilled, (state: any, action) =>
      {
        state.loading = false;
        state.resultSearchMasterUser = action.payload.data;
      });
  },
});

export const {reset, cleanResultSearchMasterUser} = HeadersSlice.actions;

// Reducer
export default HeadersSlice.reducer;
