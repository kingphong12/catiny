import {createSlice} from '@reduxjs/toolkit';

const initialState = {
  hideComponent: false,
  rightChatActive: true,
};


// Actions

// export const hideComponent  = () =>
// {
//  window.console.log(123213)
// }

// slice

export const SettingsSlice = createSlice({
  name: 'hideComponent',
  initialState,
  reducers: {
    hideComponent(state)
    {
      state.hideComponent = !state.hideComponent;
    },
    rightChatActive(state)
    {
      state.rightChatActive = !state.rightChatActive;
    }
  },
});
export const {hideComponent, rightChatActive} = SettingsSlice.actions;

// Reducer
export default SettingsSlice.reducer;
