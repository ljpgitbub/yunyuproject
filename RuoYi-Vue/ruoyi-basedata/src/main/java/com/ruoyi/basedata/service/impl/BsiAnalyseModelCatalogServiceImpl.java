package com.ruoyi.basedata.service.impl;

import java.util.List;

import com.ruoyi.basedata.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.basedata.mapper.BsiAnalyseModelCatalogMapper;
import com.ruoyi.basedata.domain.BsiAnalyseModelCatalog;
import com.ruoyi.basedata.service.IBsiAnalyseModelCatalogService;

/**
 * 分析模型目录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-25
 */
@Service
public class BsiAnalyseModelCatalogServiceImpl implements IBsiAnalyseModelCatalogService 
{
    @Autowired
    private BsiAnalyseModelCatalogMapper bsiAnalyseModelCatalogMapper;

    /**
     * 查询分析模型目录
     * 
     * @param id 分析模型目录ID
     * @return 分析模型目录
     */
    @Override
    public BsiAnalyseModelCatalog selectBsiAnalyseModelCatalogById(String id)
    {
        return bsiAnalyseModelCatalogMapper.selectBsiAnalyseModelCatalogById(id);
    }

    /**
     * 查询分析模型目录列表
     * 
     * @param bsiAnalyseModelCatalog 分析模型目录
     * @return 分析模型目录
     */
    @Override
    public List<BsiAnalyseModelCatalog> selectBsiAnalyseModelCatalogList(BsiAnalyseModelCatalog bsiAnalyseModelCatalog)
    {
        return bsiAnalyseModelCatalogMapper.selectBsiAnalyseModelCatalogList(bsiAnalyseModelCatalog);
    }

    /**
     * 新增分析模型目录
     * 
     * @param bsiAnalyseModelCatalog 分析模型目录
     * @return 结果
     */
    @Override
    public int insertBsiAnalyseModelCatalog(BsiAnalyseModelCatalog bsiAnalyseModelCatalog)
    {
        //判断是否是顶级节点
        if(Constants.THETOPNODE.equals(bsiAnalyseModelCatalog.getParentId())){
            bsiAnalyseModelCatalog.setParentIds(bsiAnalyseModelCatalog.getParentId());
            bsiAnalyseModelCatalog.setLevel((long)bsiAnalyseModelCatalog.getParentIds().split(",").length);
        }else {
            BsiAnalyseModelCatalog info = bsiAnalyseModelCatalogMapper.selectBsiAnalyseModelCatalogById(bsiAnalyseModelCatalog.getParentId());
            bsiAnalyseModelCatalog.setParentIds(info.getParentIds() + "," +bsiAnalyseModelCatalog.getParentId());
            bsiAnalyseModelCatalog.setLevel((long)bsiAnalyseModelCatalog.getParentIds().split(",").length);
        }
        return bsiAnalyseModelCatalogMapper.insertBsiAnalyseModelCatalog(bsiAnalyseModelCatalog);
    }

    /**
     * 修改分析模型目录
     * 
     * @param bsiAnalyseModelCatalog 分析模型目录
     * @return 结果
     */
    @Override
    public int updateBsiAnalyseModelCatalog(BsiAnalyseModelCatalog bsiAnalyseModelCatalog)
    {
        //判断是否是顶级节点
        if(Constants.THETOPNODE.equals(bsiAnalyseModelCatalog.getParentId())){
            BsiAnalyseModelCatalog oldBsiAnalyseModelCatalog = bsiAnalyseModelCatalogMapper.selectBsiAnalyseModelCatalogById(bsiAnalyseModelCatalog.getId());
            String newParentIds = bsiAnalyseModelCatalog.getParentId();
            String oldParentIds = oldBsiAnalyseModelCatalog.getParentIds();
            //修改子集pids
            updateBsiAnalyseModelCatalogChildren(bsiAnalyseModelCatalog.getId(), newParentIds, oldParentIds);
            //修改自己pids
            bsiAnalyseModelCatalog.setParentIds(newParentIds);
            bsiAnalyseModelCatalog.setLevel((long)bsiAnalyseModelCatalog.getParentIds().split(",").length);
        }else {
            //获取新父级pids
            BsiAnalyseModelCatalog newParentBsiAnalyseModelCatalog = bsiAnalyseModelCatalogMapper.selectBsiAnalyseModelCatalogById(bsiAnalyseModelCatalog.getParentId());
            BsiAnalyseModelCatalog oldBsiAnalyseModelCatalog = bsiAnalyseModelCatalogMapper.selectBsiAnalyseModelCatalogById(bsiAnalyseModelCatalog.getId());
            String newParentIds = newParentBsiAnalyseModelCatalog.getParentIds() + "," + bsiAnalyseModelCatalog.getParentId();
            String oldParentIds = oldBsiAnalyseModelCatalog.getParentIds();
            //修改子集pids
            updateBsiAnalyseModelCatalogChildren(bsiAnalyseModelCatalog.getId(), newParentIds, oldParentIds);
            //修改自己pids
            bsiAnalyseModelCatalog.setParentIds(newParentIds);
            bsiAnalyseModelCatalog.setLevel((long)bsiAnalyseModelCatalog.getParentIds().split(",").length);
        }
        return bsiAnalyseModelCatalogMapper.updateBsiAnalyseModelCatalog(bsiAnalyseModelCatalog);
    }

    /**
     * 批量删除分析模型目录
     * 
     * @param ids 需要删除的分析模型目录ID
     * @return 结果
     */
    @Override
    public int deleteBsiAnalyseModelCatalogByIds(String[] ids)
    {
        return bsiAnalyseModelCatalogMapper.deleteBsiAnalyseModelCatalogByIds(ids);
    }

    /**
     * 删除分析模型目录信息
     * 
     * @param id 分析模型目录ID
     * @return 结果
     */
    @Override
    public int deleteBsiAnalyseModelCatalogById(String id)
    {
        return bsiAnalyseModelCatalogMapper.deleteBsiAnalyseModelCatalogById(id);
    }

    /**
     * 校验分析模型目录同级名称是否唯一
     *
     * @param bsiAnalyseModelCatalog 分析模型目录名字
     * @return 结果
     */
    @Override
    public String checkBsiAnalyseModelCatalogNameUnique(BsiAnalyseModelCatalog bsiAnalyseModelCatalog) {
        int count = bsiAnalyseModelCatalogMapper.checkBsiAnalyseModelCatalogNameUnique(bsiAnalyseModelCatalog);
        if(count > 0){
            return Constants.NOT_UNIQUE;
        }
        return Constants.UNIQUE;
    }

    /**
     * 本人同名修改
     *
     * @param bsiAnalyseModelCatalog 分析模型目录名字
     * @return 结果
     */
    @Override
    public String checkBsiAnalyseModelCatalogSelf(BsiAnalyseModelCatalog bsiAnalyseModelCatalog) {
        int count = bsiAnalyseModelCatalogMapper.checkBsiAnalyseModelCatalogSelf(bsiAnalyseModelCatalog);
        if (count > 0){
            return Constants.NOT_UNIQUE;
        }
        return Constants.UNIQUE;
    }

    /**
     * 修改子元素关系
     *
     * @param id 被修改的分析模型目录ID
     * @param newParentIds 新的父ID集合
     * @param oldParentIds 旧的父ID集合
     */
    public void updateBsiAnalyseModelCatalogChildren(String id, String newParentIds, String oldParentIds)
    {
        List<BsiAnalyseModelCatalog> children = bsiAnalyseModelCatalogMapper.selectChildrenBsiAnalyseModelCatalogById(id);
        for (BsiAnalyseModelCatalog child : children)
        {
            child.setParentIds(child.getParentIds().replace(oldParentIds, newParentIds));
            child.setLevel((long) child.getParentIds().split(",").length);
        }
        if (children.size() > 0)
        {
            bsiAnalyseModelCatalogMapper.updateBsiAnalyseModelCatalogChildren(children);
        }
    }
}
