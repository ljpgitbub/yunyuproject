import request from '@/utils/request'

// 查询行业列表
export function listTrade(query) {
  return request({
    url: '/basedata/trade/list',
    method: 'get',
    params: query
  })
}

// 查询行业详细
export function getTrade(id) {
  return request({
    url: '/basedata/trade/' + id,
    method: 'get'
  })
}

// 新增行业
export function addTrade(data) {
  return request({
    url: '/basedata/trade',
    method: 'post',
    data: data
  })
}

// 修改行业
export function updateTrade(data) {
  return request({
    url: '/basedata/trade',
    method: 'put',
    data: data
  })
}

// 删除行业
export function delTrade(id) {
  return request({
    url: '/basedata/trade/' + id,
    method: 'delete'
  })
}

// 导出行业
export function exportTrade(query) {
  return request({
    url: '/basedata/trade/export',
    method: 'get',
    params: query
  })
}

// 查询所有行业
export function tradeList(query) {
  return request({
    url: '/basedata/trade/tradeList',
    method: 'get',
    params: query
  })
}

// 回显行业名称
export function selectTradeName(datas, value) {
  var actions = [];
  Object.keys(datas).some((key) => {
    if (datas[key].id == ('' + value)) {
      actions.push(datas[key].name);
      return true;
    }
  })
  return actions.join('');
}

