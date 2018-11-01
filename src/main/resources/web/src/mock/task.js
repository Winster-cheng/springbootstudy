
const logs = [{
  time: "2018-10-12 12:12:10",
  content: `2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容12018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容12018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1`,
  status: {
    id: 4,
  }
},
{
  time: "2018-10-12 12:12:10",
  content: `2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1`,
  status: {
    id: 4,
  }
},
{
  time: "2018-10-12 12:12:10",
  content: `2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1`,
  status: {
    id: 4,
  }
},
{
  time: "2018-10-12 12:12:10",
  content: `2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1`,
  status: {
    id: 4,
  }
},
{
  time: "2018-10-12 12:12:10",
  content: `2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1
  2018-10-12 12:12:10 我是日志内容1`,
  status: {
    id: 4,
  }
},
{
  time: "2018-10-12 12:12:10",
  content: "2018-10-12 12:12:10 我是日志内容2",
  status: {
    id: 2,
  }
},
{
  time: "2018-10-12 12:12:10",
  content: "2018-10-12 12:12:10 我是日志内容3",
  status: {
    id: 3,
  }
}]
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
  {
    name: '任务名称071',
    id: 71,
    type: 0,
    shelltype: 3,
  },
  {
    name: '任务名称072',
    id: 744,
    type: 0,
    shelltype: 3,
  },
  {
    name: '任务名称072',
    id: 72,
    type: 0,
    shelltype: 3,
  },
  {
    name: '任务名称073',
    id: 713,
    type: 0,
    shelltype: 3,
  },
  {
    name: '任务名称0721',
    id: 721,
    type: 0,
    shelltype: 3,
  },
  {
    name: '任务名称0731',
    id: 73,
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
    status: {
      id:1,
      chineseName: "已提交"
    },
    type: 1,
    bussinessTime: "2019-23-11",
    startTime: "2018-09-08",
    executeTime: "2018-09-08 12:12:12",
  },
  {
    id: 2,
    name: 'MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库',
    owner: "test1",
    status: {
      id:2,
      chineseName: "等待中"
    },
    type: 1,
    bussinessTime: "2019-23-11",
    startTime: "2018-09-08",
    executeTime: "2018-09-08 12:12:12",
  },
  {
    id: 3,
    name: 'MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库',
    owner: "test1",
    status: {
      id:3,
      chineseName: "运行中"
    },
    type: 1,
    bussinessTime: "2019-23-11",
    startTime: "2018-09-08",
    executeTime: "2018-09-08 12:12:12",
  },
  {
    id: 4,
    name: 'MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库',
    owner: "test1",
    status: {
      id:4,
      chineseName: "成功"
    },
    type: 1,
    bussinessTime: "2019-23-11",
    startTime: "2018-09-08",
    executeTime: "2018-09-08 12:12:12",
  },
  {
    id: 5,
    name: 'MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库',
    owner: "test1",
    status: {
      id:5,
      chineseName: "失败"
    },
    type: 1,
    bussinessTime: "2019-23-11",
    startTime: "2018-09-08",
    executeTime: "2018-09-08 12:12:12",
  },
];
const instanceListData = {
  result: true,
  dataValue: {
    pageSize: 10,
    pageNo: 1,
    totalPage: 1,
    totalCount: 3,
    list: instances
  }
};
export default {
  'POST /api/submit/getDirectory': (req, res) => {
    setTimeout(()=>{
      res.json({
        result: true,
        list: treeData
      })      
    },2000)
  },
  'POST /api/taskPlan/getMoreDependencies': (req, res) => {
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
  'POST /api/taskPlan/getDependencies': (req, res) => {
    setTimeout(() => {
      res.json({
        result: true,
        list: dependenciesList
      })
    },2000)
  },
  'POST /api/taskInstance/getDependencies': (req, res) => {
    setTimeout(() => {
      res.json({
        result: true,
        list: dependenciesList
      })
    },2000)
  },
  'POST /api/submit/getContent': (req, res) => {
    res.json({
      result: true,
      dataValue: {
        content: "123"
      }
    })
  },
  'POST /api/submit/submit': (req, res) => {
    res.json({
      result: true,
    })
  },
  'POST /api/submit/save': (req, res) => {
    res.json({
      result: true,
    })
  },
  'POST /api/taskInstance/getLogs': (req, res) => {
    res.json({
      result: true,
      list: logs
    })
  },
  'POST /api/taskInstance/select': (req,res) => {
    setTimeout(()=>res.json(instanceListData),2000)
  },
  'POST /api/taskInstance/getStatus': (req,res) => {
    setTimeout(()=>res.json({
      result: true,
      list: [{
        id: 1,
        chineseName: "已提交"
      },{
        id: 2,
        chineseName: "等待中"
      },{
        id: 3,
        chineseName: "运行中"
      },{
      id: 4,
      chineseName: "成功"
    },{
    id: 5,
    chineseName: "失败"
    }]
    }),2000)
  },
};
