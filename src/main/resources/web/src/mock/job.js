const jobs = [
    {
      jobId: 1,
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
    pageSize: 20,
    success: true,
    totalCount: 21,
    totalPage: 2,
  };
   
export default {
    'POST /api/job/getJobPlans': (req,res) => res.json(jobData)
}