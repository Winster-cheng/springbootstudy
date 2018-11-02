import {queryJobs} from '@/services/job';

export default {
  namespace: 'job',

  state: {
    data: [],
  },

  effects: {
    *fetchJobs ({payload}, {call, put}) {
      const params = {
        pageNo: 1,
        pageSize: 10,
        timeSortType: 0,
        fileName: '',
        ...payload,
      };
      const {result, dataValue = {}, message} = yield call (queryJobs, params);
      if (result) {
        yield put ({
          type: 'queryJobs',
          payload: dataValue,
        });
      } else {
        return null;
      }
    },
  },
  reducers: {
    queryJobs (state, {payload}) {
      return {
        ...state,
        data: {
          ...state.data,
          list: payload.list,
          pagination: {
            current: payload.pageNo,
            pageSize: payload.pageSize,
            total: payload.totalCount,
          },
        },
      };
    },
  },
};
