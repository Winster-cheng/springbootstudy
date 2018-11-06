import { queryJobs } from '@/services/job';
import { message } from "antd";

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
      const {result, dataValue = {}, message: msg} = yield call (queryJobs, params);
      if (result) {
        yield put ({
          type: 'queryJobs',
          payload: dataValue,
        });
      } else {
        message.error(msg || "请求失败")
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
