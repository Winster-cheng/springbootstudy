const jobs = [
    {
      jobId: 1,
      jobName: 'MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库MOCK测试仓库',
      responser: "test1",
      jobType: 2,
      planType: 3,
      updateDate: "2018-09-08",
    },
    {
      jobId: 2,
      jobName: 'MOCK测试仓库',
      responser: "test1",
      jobType: 2,
      planType: 3,
      updateDate: "2018-09-08",
    },
    {
      jobId: 3,
      jobName: 'MOCK测试仓库',
      responser: "test1",
      jobType: 2,
      planType: 3,
      updateDate: "2018-09-08",
    },
  ];
  
  const jobData = {
    list: jobs,
    message: null,
    pageNo: 1,
    pageSize: 10,
    success: true,
    totalCount: 21,
    totalPage: 2,
  };
   
export default {
    'POST /job/getJobPlans': (req,res) => {
      setTimeout(()=>res.json(jobData),2000)
    }
}