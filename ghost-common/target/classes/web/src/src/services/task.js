import request from '@/utils/request';

export async function queryTaskTreeData() {
    return request(`/api/taskSubmit/getDirectory`,{
      method: 'POST',
    });
  }
export async function queryGraphNode(params){
  return request('/api/taskPlan/getMoreDependencies', {
    method: 'POST',
    body: params,
  });
}
export async function queryAllGraphNodes(jobPlanId){
  return request('/api/taskPlan/getDependencies', {
    method: 'POST',
    body: {
      jobPlanId,
    },
  });
}
export async function queryContent(fileId){
  return request('/api/taskSubmit/getContent', {
    method: 'POST',
    body: {
      fileId,
    },
  });
}
export async function saveContent(params){
  return request('/api/taskSubmit/save', {
    method: 'POST',
    body: {
      ...params
    },
  });
}
export async function submitContent(params){
  return request('/api/taskSubmit/submit', {
    method: 'POST',
    body: {
      ...params
    },
  });
}
// 任务实例接口
export async function queryTaskInstance(params) {
  return request(`/api/taskInstance/select`, {
    method: 'POST',
    body: params,
  });
}
export async function queryAllInstanceNodes(taskInstanceId) {
  return request(`/api/taskInstance/getDependencies`, {
    method: 'POST',
    body: {
      taskInstanceId
    },
  });
}
export async function queryInstanceNode(params) {
  return request(`/api/taskInstance/getMoreDependencies`, {
    method: 'POST',
    body: params,
  });
}
export async function queryLogs(taskInstanceId) {
  return request(`/api/taskInstance/getLogs`, {
    method: 'POST',
    body: {
      taskInstanceId
    },
  });
}
export async function queryAllStatuss() {
  return request(`/api/taskInstance/getStatus`, {
    method: 'POST',
  });
}
