import {
  queryTaskTreeData,
  queryGraphNode,
  queryAllGraphNodes,
  queryContent,
  saveContent,
  submitContent,
  queryTaskInstance,
  queryAllInstanceNodes,
  queryInstanceNode,
  queryLogs,
  queryAllStatuss,
} from '@/services/task';
import {message} from 'antd';

const getEdges = list => {
  const edges = [];
  list.forEach (item => {
    const {id, output = []} = item;
    output.forEach (target => {
      edges.push ({
        source: id,
        target,
      });
    });
  });
  return edges;
};

export default {
  namespace: 'task',

  state: {
    treeData: [],
    graphNodes: {},
    newNodes: [],
    instanceNodes: {},
    newInstanceNodes: [],
    data: [],
    allStatus: [],
    logs: [],
  },

  effects: {
    *fetchTaskTreeData (_, {call, put}) {
      const response = yield call (queryTaskTreeData);
      yield put ({
        type: 'saveTreeData',
        payload: response,
      });
    },
    *fetchGraphNode (_, {call, put}) {
      const payload = yield call (queryGraphNode, _.payload);
      yield put ({
        type: 'saveGraphNode',
        payload,
      });
    },
    *fetchAllGraphNodes (_, {call, put}) {
      const payload = yield call (queryAllGraphNodes, _.payload);
      yield put ({
        type: 'saveGraphNodes',
        payload,
      });
    },
    *fetchCodeContent ({payload}, {call}) {
      const {result, dataValue} = yield call (queryContent, payload);
      if (result) {
        const {fileContent} = dataValue;
        return fileContent;
      }
      return null;
    },
    *submitTaskContent ({payload}, {call}) {
      return yield call (submitContent, payload);
    },
    *saveTaskContent ({payload}, {call}) {
      return yield call (saveContent, payload);
    },
    *fetchInstances ({payload}, {call, put}) {
      const params = {
        pageNo: 1,
        pageSize: 10,
        date: '',
        fileName: '',
        status: [],
        sortName: '',
        sortType: 0,
        ...payload,
      };
      const response = yield call (queryTaskInstance, params);
      yield put ({
        type: 'saveInstances',
        payload: response,
      });
    },
    *fetchInstanceNode (_, {call, put}) {
      const payload = yield call (queryInstanceNode, _.payload);
      yield put ({
        type: 'saveInstanceNode',
        payload,
      });
    },
    *fetchAllInstanceNodes (_, {call, put}) {
      const payload = yield call (queryAllInstanceNodes, _.payload);
      yield put ({
        type: 'saveInstanceNodes',
        payload,
      });
    },
    *fetchLogs (_, {call, put}) {
      const payload = yield call (queryLogs, _.payload);
      yield put ({
        type: 'saveLogs',
        payload,
      });
    },
    *fetchAllStatus (_, {call, put}) {
      const payload = yield call (queryAllStatuss, _.payload);
      yield put ({
        type: 'saveStatus',
        payload,
      });
    },
  },

  reducers: {
    saveLogs (state, {payload}) {
      const {result, list: logs} = payload;
      if (result) {
        return {
          ...state,
          logs,
        };
      }
      return state;
    },
    saveInstances (state, {payload}) {
      const {result, dataValue, message: msg} = payload;
      if (result) {
        return {
          ...state,
          data: {
            ...state.data,
            list: dataValue.list,
            pagination: {
              current: dataValue.pageNo,
              pageSize: dataValue.pageSize,
              total: dataValue.totalCount,
            },
          },
        };
      } 
      message.error (msg || '请求失败');
      return state;
    },
    saveStatus (state, {payload}) {
      const {result, list: allStatus} = payload;
      if (result) {
        return {
          ...state,
          allStatus,
        };
      }
      return state;
    },
    saveTreeData (state, {payload}) {
      const {result, list} = payload;
      if (result) {
        return {
          ...state,
          treeData: list,
        };
      }
      return state;
    },
    saveGraphNodes (state, {payload}) {
      const {list} = payload;
      return {
        ...state,
        graphNodes: {
          nodes: list,
          edges: getEdges (list),
        },
      };
    },
    saveGraphNode (state, action) {
      const {payload} = action;
      return {
        ...state,
        newNodes: payload,
      };
    },
    saveInstanceNodes (state, {payload}) {
      const {list} = payload;
      return {
        ...state,
        instanceNodes: {
          nodes: list,
          edges: getEdges (list),
        },
      };
    },
    saveInstanceNode (state, action) {
      const {payload} = action;
      return {
        ...state,
        newInstanceNodes: payload,
      };
    },
  },
};