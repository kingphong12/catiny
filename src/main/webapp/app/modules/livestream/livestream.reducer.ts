import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import {serializeAxiosError} from "app/shared/reducers/reducer.utils";
import axios from 'axios';

const initialState = {
  currentVideoLivestream : null as any ,

};

const apiUrl = 'api/o/videos/livestream';


// Actions

export const initVideoLivestream = createAsyncThunk(
  'livestream/init_video_livestream',
  async () => {
    const requestUrl = `${apiUrl}`;
    return await axios.post<any>(requestUrl);
  },
  { serializeError: serializeAxiosError }
);


// slice

export const SettingsSlice = createSlice({
  name: 'hideComponent',
  initialState,
  reducers: {
  },
  extraReducers(builder)
  {
    builder.addCase(initVideoLivestream.fulfilled,(state, action)=>
    {
      state.currentVideoLivestream = action.payload.data;
    })
  }
});
// export const {} = SettingsSlice.actions;

// Reducer
export default SettingsSlice.reducer;
