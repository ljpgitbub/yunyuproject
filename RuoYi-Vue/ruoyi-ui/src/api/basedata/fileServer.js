import request from '@/utils/request'

// 查询文件服务器列表
export function listFileServer(query) {
  return request({
    url: '/basedata/fileServer/list',
    method: 'get',
    params: query
  })
}

// 查询文件服务器详细
export function getFileServer(id) {
  return request({
    url: '/basedata/fileServer/' + id,
    method: 'get'
  })
}

// 新增文件服务器
export function addFileServer(data) {
  return request({
    url: '/basedata/fileServer',
    method: 'post',
    data: data
  })
}

// 修改文件服务器
export function updateFileServer(data) {
  return request({
    url: '/basedata/fileServer',
    method: 'put',
    data: data
  })
}

// 删除文件服务器
export function delFileServer(id) {
  return request({
    url: '/basedata/fileServer/' + id,
    method: 'delete'
  })
}

// 导出文件服务器
export function exportFileServer(query) {
  return request({
    url: '/basedata/fileServer/export',
    method: 'get',
    params: query
  })
}