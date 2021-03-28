import request from '@/utils/request'

// 查询资源目录列表
export function listResourceCatalog(query) {
  return request({
    url: '/basedata/resourceCatalog/list',
    method: 'get',
    params: query
  })
}

// 查询资源目录详细
export function getResourceCatalog(id) {
  return request({
    url: '/basedata/resourceCatalog/' + id,
    method: 'get'
  })
}

// 新增资源目录
export function addResourceCatalog(data) {
  return request({
    url: '/basedata/resourceCatalog',
    method: 'post',
    data: data
  })
}

// 修改资源目录
export function updateResourceCatalog(data) {
  return request({
    url: '/basedata/resourceCatalog',
    method: 'put',
    data: data
  })
}

// 删除资源目录
export function delResourceCatalog(id) {
  return request({
    url: '/basedata/resourceCatalog/' + id,
    method: 'delete'
  })
}

// 导出资源目录
export function exportResourceCatalog(query) {
  return request({
    url: '/basedata/resourceCatalog/export',
    method: 'get',
    params: query
  })
}
// 查询所有行业
export function tradeList(query) {
  console.log(query);
  return request({
    url: '/basedata/trade/tradeList',
    method: 'get',
    params: query
  })
}

// 查询资源目录下拉树结构
export function treeselect() {
  return request({
    url: '/basedata/resourceCatalog/treeselect',
    method: 'get'
  })
}
