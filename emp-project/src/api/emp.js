import request from '@/utils/request'
export function getEmp() {
    return request({
      url: '/api/getEmp',
      method: 'get'
    });
  };