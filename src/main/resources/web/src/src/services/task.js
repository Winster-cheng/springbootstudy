import request from '@/utils/request';

export async function queryTaskTreeData() {
    return request(`/submit/getDirectory`,{
      method: 'POST',
    });
  }
export async function queryGraphNode(params){
  return request('/taskPlan/getMoreDependencies', {
    method: 'POST',
    body: {
      ...params,
    },
  });
}
export async function queryAllGraphNodes(jobPlanId){
  return request('/taskPlan/getDependencies', {
    method: 'POST',
    body: {
      jobPlanId,
    },
  });
}