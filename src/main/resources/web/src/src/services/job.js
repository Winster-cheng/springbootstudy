import request from '@/utils/request';

export async function queryJobs(params) {
    return request(`/job/getJobPlans`, {
      method: 'POST',
      body: params,
    });
  }