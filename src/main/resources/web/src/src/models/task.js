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
} from '@/services/task';
import { message } from "antd";

const getEdges = list => {
  const edges = [];
  list.forEach( item => {
    const { id, output = [] } = item;
    output.forEach(target => {
      edges.push({
        source: id,
        target
      })
    })
  })
  return edges;
}

export default {
  namespace: 'task',

  state: {
    treeData: [],
    graphNodes: {},
    newNodes: [],
    instanceNodes: {},
    newInstanceNodes: [],
    data: []
  },

  effects: {
    *fetchTaskTreeData(_, { call, put }) {
      const response = yield call(queryTaskTreeData);
      yield put({
        type: 'saveTreeData',
        payload: response,
      });
    },
    *fetchGraphNode(_, { call, put }) {
      const payload = yield call(queryGraphNode, _.payload);
      yield put({
        type: 'saveGraphNode',
        payload,
      });
    },
    *fetchAllGraphNodes(_, { call, put }){
      const payload = yield call(queryAllGraphNodes, _.payload);
      yield put({
        type: 'saveGraphNodes',
        payload,
      })
    },
    *fetchCodeContent({ payload }, { call }){
      const { result, dataValue } = yield call(queryContent, payload);
      if(result){
        const { content } = dataValue;
        return content;
      }
      message.error(`请求出错`)
      return null;
    },
    *submitTaskContent({ payload }, { call }){
      return yield call(submitContent, payload);
    },
    *saveTaskContent({ payload }, { call }){
      return yield call(saveContent, payload);
    },
    *fetchInstances({ payload }, { call, put }) {
        const params = { 
          pageNo: 1,
          pageSize: 10,
          date: "",
          filename: "",
          status: "",
          timingSortType: 0,
          bussinessDateSortType: 0,
          startTimeSortType: 0,
          ...payload 
        };
        const response = yield call(queryTaskInstance, params);
        yield put({
          type: 'saveInstances',
          payload: response,
        });
      },
      *fetchInstanceNode(_, { call, put }) {
        const payload = yield call(queryInstanceNode, _.payload);
        yield put({
          type: 'saveInstanceNode',
          payload,
        });
      },
      *fetchAllInstanceNodes(_, { call, put }){
        const payload = yield call(queryAllInstanceNodes, _.payload);
        yield put({
          type: 'saveInstanceNodes',
          payload,
        })
      },
  },

  reducers: {
    saveInstances(state, action) {
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
    saveTreeData(state, { payload }) {
      const { result, list } = payload;
      if(result){
        return {
          ...state,
          treeData: list,
        };
      }
        return state
    },
    saveGraphNodes(state, { payload }){
      const { list } = payload;
      return {
        ...state,
        graphNodes: {
          nodes: list,
          edges: getEdges(list)
        }
      }
    },
    saveGraphNode(state, action) {
      const { payload } = action;
      return {
        ...state,
        newNodes: payload
      }
    },
    saveInstanceNodes(state, { payload }){
      const { list } = payload;
      return {
        ...state,
        instanceNodes: {
          nodes: list,
          edges: getEdges(list)
        }
      }
    },
    saveInstanceNode(state, action) {
      const { payload } = action;
      return {
        ...state,
        newInstanceNodes: payload
      }
    }
  },
};
