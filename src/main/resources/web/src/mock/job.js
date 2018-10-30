const jobs = [
    {
      id: 1,
      name: 'MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库',
      owner: "test1",
      jobType: 2,
      executeRate: 3,
      gmtModify: "2018-09-08",
    },
    // {
    //   id: 2,
    //   name: 'ceshi1',
    //   owner: "test1",
    //   jobType: 2,
    //   executeRate: 3,
    //   gmtModify: "2018-09-08",
    // },
    // {
    //   id: 3,
    //   name: '2',
    //   owner: "test1",
    //   jobType: 2,
    //   executeRate: 3,
    //   gmtModify: "2018-09-08",
    // },
  ];
  
  const jobData = {
    result: true,
    dataValue: {
      pageSize: 1,
      pageNo: 1,
      totalPage: 3,
      totalCount: 3,
      list: jobs
    }
  };
   
export default {
    'POST /job/getJobPlans': (req,res) => {
      setTimeout(()=>res.json(jobData),2000)
    },
}