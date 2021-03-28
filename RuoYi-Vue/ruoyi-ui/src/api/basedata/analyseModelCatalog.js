import request from '@/utils/request'
import row from "element-ui/packages/row/src/row";

// 查询分析模型目录列表
export function listAnalyseModelCatalog(query) {
  return request({
    url: '/basedata/analyseModelCatalog/list',
    method: 'get',
    params: query
  })
}

// 查询分析模型目录详细
export function getAnalyseModelCatalog(id) {
  return request({
    url: '/basedata/analyseModelCatalog/' + id,
    method: 'get'
  })
}

// 新增分析模型目录
export function addAnalyseModelCatalog(data) {
  return request({
    url: '/basedata/analyseModelCatalog',
    method: 'post',
    data: data
  })
}

// 修改分析模型目录
export function updateAnalyseModelCatalog(data) {
  return request({
    url: '/basedata/analyseModelCatalog',
    method: 'put',
    data: data
  })
}

// 删除分析模型目录
export function delAnalyseModelCatalog(id) {
  return request({
    url: '/basedata/analyseModelCatalog/' + id,
    method: 'delete'
  })
}

// 导出分析模型目录
export function exportAnalyseModelCatalog(query) {
  return request({
    url: '/basedata/analyseModelCatalog/export',
    method: 'get',
    params: query
  })
}

// 查询分析模型目录（排除节点）
export function listAnalyseModelCatalogExcludeChild(id){
  return request({
    url: '/basedata/analyseModelCatalog/list/exclude/' +id,
    method: 'get'
  })
}
