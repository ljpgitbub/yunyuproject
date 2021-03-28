import request from '@/utils/request'

// 查询资源归档信息列表
export function listDcResourceArchives(query) {
  return request({
    url: '/datacenter/dcResourceArchives/list',
    method: 'get',
    params: query
  })
}

// 查询资源归档信息详细
export function getDcResourceArchives(id) {
  return request({
    url: '/datacenter/dcResourceArchives/' + id,
    method: 'get'
  })
}

// 新增资源归档信息
export function addDcResourceArchives(data) {
  return request({
    url: '/datacenter/dcResourceArchives',
    method: 'post',
    data: data
  })
}

// 修改资源归档信息
export function updateDcResourceArchives(data) {
  return request({
    url: '/datacenter/dcResourceArchives',
    method: 'put',
    data: data
  })
}

// 删除资源归档信息
export function delDcResourceArchives(id) {
  return request({
    url: '/datacenter/dcResourceArchives/' + id,
    method: 'delete'
  })
}

// 导出资源归档信息
export function exportDcResourceArchives(query) {
  return request({
    url: '/datacenter/dcResourceArchives/export',
    method: 'get',
    params: query
  })
}