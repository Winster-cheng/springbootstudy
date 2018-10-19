import { queryTaskTreeData } from '@/services/task';

export default {
  namespace: 'task',

  state: {
    treeData: [],
  },

  effects: {
    *fetchTaskTreeData(_, { call, put }) {
      const response = yield call(queryTaskTreeData);
      yield put({
        type: 'saveTreeData',
        payload: Array.isArray(response) ? response : [],
      });
    },
  },

  reducers: {
    saveTreeData(state, action) {
      return {
        ...state,
        treeData: action.payload,
      };
    },
  },
};
