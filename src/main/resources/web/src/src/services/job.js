import request from '@/utils/request';

export async function queryJobs(params) {
    return request(`/api/taskPlan/select`, {
      method: 'POST',
      body: params,
    });
  }