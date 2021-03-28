package com.ruoyi.basedata.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.basedata.mapper.BsiResourceCatalogMapper;
import com.ruoyi.basedata.domain.BsiResourceCatalog;
import com.ruoyi.basedata.service.IBsiResourceCatalogService;
import com.ruoyi.basedata.domain.TreeSelectString;

/**
 * 资源目录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-24
 */
@Service
public class BsiResourceCatalogServiceImpl implements IBsiResourceCatalogService 
{
    @Autowired
    private BsiResourceCatalogMapper bsiResourceCatalogMapper;

    /**
     * 查询资源目录
     * 
     * @param id 资源目录ID
     * @return 资源目录
     */
    @Override
    public BsiResourceCatalog selectBsiResourceCatalogById(String id)
    {
        return bsiResourceCatalogMapper.selectBsiResourceCatalogById(id);
    }

    /**
     * 查询资源目录列表
     * 
     * @param bsiResourceCatalog 资源目录
     * @return 资源目录
     */
    @Override
    public List<BsiResourceCatalog> selectBsiResourceCatalogList(BsiResourceCatalog bsiResourceCatalog)
    {
        return bsiResourceCatalogMapper.selectBsiResourceCatalogList(bsiResourceCatalog);
    }

    /**
     * 新增资源目录
     * 
     * @param bsiResourceCatalog 资源目录
     * @return 结果
     */
    @Override
    public int insertBsiResourceCatalog(BsiResourceCatalog bsiResourceCatalog)
    {
        return bsiResourceCatalogMapper.insertBsiResourceCatalog(bsiResourceCatalog);
    }

    /**
     * 修改资源目录
     * 
     * @param bsiResourceCatalog 资源目录
     * @return 结果
     */
    @Override
    public int updateBsiResourceCatalog(BsiResourceCatalog bsiResourceCatalog)
    {
        return bsiResourceCatalogMapper.updateBsiResourceCatalog(bsiResourceCatalog);
    }

    /**
     * 批量删除资源目录
     * 
     * @param ids 需要删除的资源目录ID
     * @return 结果
     */
    @Override
    public int deleteBsiResourceCatalogByIds(String[] ids)
    {
        return bsiResourceCatalogMapper.deleteBsiResourceCatalogByIds(ids);
    }

    /**
     * 删除资源目录信息
     * 
     * @param id 资源目录ID
     * @return 结果
     */
    @Override
    public int deleteBsiResourceCatalogById(String id)
    {
        return bsiResourceCatalogMapper.deleteBsiResourceCatalogById(id);
    }

    @Override
    public int getCountByParentId(String parentId, String catalogName) {
        return bsiResourceCatalogMapper.getCountByParentId(parentId, catalogName);
    }

    @Override
    public int getParentLevel(String parentId) {
        return bsiResourceCatalogMapper.getParentLevel(parentId);
    }

    @Override
    public List<String> getParentList(String parentId) {
        return bsiResourceCatalogMapper.getParentList(parentId);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param bsiResourceCatalogs
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelectString> buildBsiResourceCatalogTreeSelectStringId(List<BsiResourceCatalog> bsiResourceCatalogs) {
        List<BsiResourceCatalog> bsiResourceCatalogTrees = buildBsiResourceCatalogTree(bsiResourceCatalogs);
        return bsiResourceCatalogTrees.stream().map(TreeSelectString::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param bsiResourceCatalogs
     * @return 下拉树结构列表
     */
    @Override
    public List<BsiResourceCatalog> buildBsiResourceCatalogTree(List<BsiResourceCatalog> bsiResourceCatalogs) {
        List<BsiResourceCatalog> returnList = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        for (BsiResourceCatalog rc:bsiResourceCatalogs){
            tempList.add(rc.getId());
        }
        for (Iterator<BsiResourceCatalog> iterator = bsiResourceCatalogs.iterator(); iterator.hasNext();){
            BsiResourceCatalog rc =(BsiResourceCatalog) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if(!tempList.contains(rc.getParentId())){
                recursionFn(bsiResourceCatalogs, rc);
                returnList.add(rc);
            }
        }
        if(returnList.isEmpty()){
            returnList = bsiResourceCatalogs;
        }
        return returnList;
    }
    /**
     * 递归列表
     */
    private void recursionFn(List<BsiResourceCatalog> list, BsiResourceCatalog t){
        //得到子节点列表
        List<BsiResourceCatalog> childList = getChildList(list, t);
        t.setChildren(childList);
        for (BsiResourceCatalog tChild : childList){
            if(hasChild(list,tChild)){
                recursionFn(list, tChild);
            }
        }

    }
    /**
     * 得到子节点列表
     */
    private List<BsiResourceCatalog> getChildList(List<BsiResourceCatalog> list, BsiResourceCatalog t){
        List<BsiResourceCatalog> tlist = new ArrayList<>();
        Iterator<BsiResourceCatalog> it = list.iterator();
        while(it.hasNext()){
            BsiResourceCatalog n = (BsiResourceCatalog) it.next();
            if(StringUtils.isNotNull(n.getParentId()) && n.getParentId().equals(t.getParentId())){
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<BsiResourceCatalog> list, BsiResourceCatalog t){
        return getChildList(list,t).size() > 0 ? true : false;
    }
}
