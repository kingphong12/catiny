import {createSlice} from '@reduxjs/toolkit';

const initialState = {
  hideComponent: false,

};


// Actions

// export const hideComponent  = () =>
// {
//  window.console.log(123213)
// }

// slice

export const SettingsSlice = createSlice({
    name: 'album',
    initialState,
    reducers: {
      hideComponent: (state) =>
      {
        state.hideComponent = !state.hideComponent;
      }
    },
  }
);
export const {hideComponent} = SettingsSlice.actions;

// Reducer
export default SettingsSlice.reducer;
