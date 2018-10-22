const treeData = [
  {
    name: '任务名称01',
    id: 1,
    children: [
      {
        name: '任务名称02',
        id: 2,
        children: [{ name: '任务名称10', id: 10 }],
      },
      {
        name: '任务名称04',
        id: 4,
      },
      {
        name: '任务名称03',
        id: 3,
      },
    ],
  },
  {
    name: '任务名称05',
    id: 5,
    children: [
      {
        name: '任务名称06',
        id: 6,
      },
    ],
  },
  {
    name: '任务名称07',
    id: 7,
  },
]
export default {
  'GET /task/getTreeData': (req, res) => {
    setTimeout(()=>{
      res.json(treeData)      
    },2000)
  }
};
