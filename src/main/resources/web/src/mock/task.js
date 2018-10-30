const treeData = [
  {
    name: '任务名称01',
    id: 1,
    type: 0,
    shelltype: 0,
    children: [
      {
        name: '任务名称02',
        id: 2,
        type: 0,
        shelltype: 0,
        children: [{ name: '任务名称10', id: 10, type: 1, shelltype: 1, }],
      },
      {
        name: '任务名称04',
        id: 4,
        type: 0,
        shelltype: 2
      },
      {
        name: '任务名称03',
        id: 3,
        type: 1,
        shelltype: 0,
      },
    ],
  },
  {
    name: '任务名称05',
    id: 5,
    type: 0,
    shelltype: 0,
    children: [
      {
        name: '任务名称06',
        id: 6,
        type: 1,
        shelltype: 4,
      },
    ],
  },
  {
    name: '任务名称07',
    id: 7,
    type: 0,
    shelltype: 3,
  },
]
const dependenciesList = [
  {
    id: 1,
    name: '收集日志',
    hasParent: false,
    hasChildren: true,
    input: [],
    output: [2, 3],
  },
  {
    id: 2,
    name: '入 es 集群',
    hasParent: true,
    hasChildren: true,
    input: [1],
    output: [4],
  },
  {
    id: 3,
    name: '入 hdfs',
    hasParent: true,
    hasChildren: true,
    input: [1],
    output: [4],
  },
  {
    id: 4,
    name: 'hive 计算',
    hasParent: true,
    hasChildren: true,
    input: [2, 3],
    output: [5],
  },
  {
    id: 5,
    name: 'report',
    hasParent: true,
    hasChildren: false,
    input: [4],
    output: [],
  },
]
const instances = [
  {
    id: 1,
    name: 'MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库',
    owner: "test1",
    status: 1,
    owner: 3,
    gmtModify: "2018-09-08",
  },
];
const instanceListData = {
  result: true,
  dataValue: {
    pageSize: 1,
    pageNo: 1,
    totalPage: 3,
    totalCount: 3,
    list: instances
  }
};
export default {
  'POST /submit/getDirectory': (req, res) => {
    setTimeout(()=>{
      res.json({
        result: true,
        list: treeData
      })      
    },2000)
  },
  'POST /taskPlan/getMoreDependencies': (req, res) => {
    setTimeout(() => {
      // res.json({
      //   result: true,
      //   isTop: false,
      //   dataValue: {},
      //   list: [{
      //     id: 10,
      //     name: "增加id10节点流程",
      //     hasParent: true,
      //     hasChildren: false,
      //     input: [2],
      //     output: []
      //   },{
      //     id: 11,
      //     name: "增加id11节点流程",
      //     hasParent: true,
      //     hasChildren: false,
      //     input: [3],
      //     output: []
      //   }]
      // })

      res.json({
        result: true,
        isTop: true,
        dataValue: {},
        list: [
        {
          id: 2,
          name: '入 es 集群',
          hasParent: false,
          hasChildren: true,
          input: [],
          output: [4],
        }
      ]
      })
    },2000)
  },
  'POST /taskPlan/getDependencies': (req, res) => {
    setTimeout(() => {
      res.json({
        result: true,
        list: dependenciesList
      })
    },2000)
  },
  'POST /submit/getContent': (req, res) => {
    res.json({
      result: true,
      dataValue: {
        content: "123"
      }
    })
  },
  'POST /submit/submit': (req, res) => {
    res.json({
      result: true,
    })
  },
  'POST /submit/save': (req, res) => {
    res.json({
      result: true,
    })
  },
  'POST /taskInstance/select': (req,res) => {
    setTimeout(()=>res.json(instanceListData),2000)
  },
};
