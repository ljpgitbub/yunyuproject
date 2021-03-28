import request from '@/utils/request'

// 查询数据库服务器列表
export function listDatabaseServer(query) {
  return request({
    url: '/basedata/databaseServer/list',
    method: 'get',
    params: query
  })
}

// 查询数据库服务器详细
export function getDatabaseServer(id) {
  return request({
    url: '/basedata/databaseServer/' + id,
    method: 'get'
  })
}

// 新增数据库服务器
export function addDatabaseServer(data) {
  return request({
    url: '/basedata/databaseServer',
    method: 'post',
    data: data
  })
}

// 修改数据库服务器
export function updateDatabaseServer(data) {
  return request({
    url: '/basedata/databaseServer',
    method: 'put',
    data: data
  })
}

// 删除数据库服务器
export function delDatabaseServer(id) {
  return request({
    url: '/basedata/databaseServer/' + id,
    method: 'delete'
  })
}

// 导出数据库服务器
export function exportDatabaseServer(query) {
  return request({
    url: '/basedata/databaseServer/export',
    method: 'get',
    params: query
  })
}