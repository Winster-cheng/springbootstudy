import { queryTaskTreeData, queryGraphNode, queryAllGraphNodes } from '@/services/task';

export default {
  namespace: 'task',

  state: {
    treeData: [],
    graphNodes: {},
    newNodes: []
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
    }
  },

  reducers: {
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
    }
  },
};
