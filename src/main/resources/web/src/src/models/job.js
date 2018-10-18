import { queryJobs } from '@/services/api';

export default {
  namespace: 'job',

  state: {
    jobList: [],
  },

  effects: {
    *fetchJobs({ payload }, { call, put }) {
        const response = yield call(queryJobs, payload);
        yield put({
          type: 'queryJobs',
          payload: response,
        });
      },
  },

  reducers: {
    queryJobs(state, action) {
      return {
        ...state,
        jobList: action.payload,
      };
    },
  },
};
