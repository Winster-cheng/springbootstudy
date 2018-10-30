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
export async function queryContent(fileId){
  return request('/submit/getContent', {
    method: 'POST',
    body: {
      fileId,
    },
  });
}
export async function saveContent(params){
  return request('/submit/save', {
    method: 'POST',
    body: {
      ...params
    },
  });
}
export async function submitContent(params){
  return request('/submit/submit', {
    method: 'POST',
    body: {
      ...params
    },
  });
}
// 任务实例接口
export async function queryTaskInstance(params) {
  return request(`/taskInstance/select`, {
    method: 'POST',
    body: params,
  });
}
export async function queryAllInstanceNodes(params) {
  return request(`/taskInstance/getDependencies`, {
    method: 'POST',
    body: params,
  });
}
export async function queryInstanceNode(params) {
  return request(`/taskPlan/getMoreDependencies`, {
    method: 'POST',
    body: params,
  });
}
export async function queryLogs(taskInstanceId) {
  return request(`/taskInstance/getLogs`, {
    method: 'POST',
    body: {
      taskInstanceId
    },
  });
}
