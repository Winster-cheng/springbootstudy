import request from '@/utils/request';

export async function queryTaskTreeData() {
    return request(`/task/getTreeData`);
  }