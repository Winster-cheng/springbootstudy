import { queryJobs } from '@/services/job';

export default {
  namespace: 'job',

  state: {
    data: [],

  },

  effects: {
    *fetchJobs({ payload }, { call, put }) {
      const params = { pageNo: 1, pageSize: 10, orderByTime: "", filename: "", ...payload };
        const response = yield call(queryJobs, params);
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
        data: {
          ...state.data,
          list: action.payload.dataValue.list,
          pagination: {
            current: action.payload.dataValue.pageNo,
            pageSize: action.payload.dataValue.pageSize,
            total: action.payload.dataValue.totalCount
          }
        }
      };
    },
  },
};
