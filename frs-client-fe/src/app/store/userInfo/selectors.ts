import {createFeatureSelector, createSelector} from '@ngrx/store';
import {UserInfoState} from './reducers';

export const selectUserInfoState = createFeatureSelector<UserInfoState>('userInfo');

export const selectUserId = createSelector(
  selectUserInfoState,
  (userInfo) => userInfo.guid
);

export const selectUserAvatar = createSelector(
  selectUserInfoState,
  (userInfo) => userInfo.avatar || 'assets/img/avatar.jpg'
);
