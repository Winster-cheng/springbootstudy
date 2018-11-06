import request from '@/utils/request';

export async function queryJobs(params) {
    return request(`/taskPlan/select`, {
      method: 'POST',
      body: params,
    });
  }