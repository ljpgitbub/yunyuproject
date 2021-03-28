package com.ruoyi.basedata.service;

import java.util.List;
import com.ruoyi.basedata.domain.BsiAnalyseModelCatalog;

/**
 * 分析模型目录Service接口
 * 
 * @author ruoyi
 * @date 2021-02-25
 */
public interface IBsiAnalyseModelCatalogService 
{
    /**
     * 查询分析模型目录
     * 
     * @param id 分析模型目录ID
     * @return 分析模型目录
     */
    public BsiAnalyseModelCatalog selectBsiAnalyseModelCatalogById(String id);

    /**
     * 查询分析模型目录列表
     * 
     * @param bsiAnalyseModelCatalog 分析模型目录
     * @return 分析模型目录集合
     */
    public List<BsiAnalyseModelCatalog> selectBsiAnalyseModelCatalogList(BsiAnalyseModelCatalog bsiAnalyseModelCatalog);

    /**
     * 新增分析模型目录
     * 
     * @param bsiAnalyseModelCatalog 分析模型目录
     * @return 结果
     */
    public int insertBsiAnalyseModelCatalog(BsiAnalyseModelCatalog bsiAnalyseModelCatalog);

    /**
     * 修改分析模型目录
     * 
     * @param bsiAnalyseModelCatalog 分析模型目录
     * @return 结果
     */
    public int updateBsiAnalyseModelCatalog(BsiAnalyseModelCatalog bsiAnalyseModelCatalog);

    /**
     * 批量删除分析模型目录
     * 
     * @param ids 需要删除的分析模型目录ID
     * @return 结果
     */
    public int deleteBsiAnalyseModelCatalogByIds(String[] ids);

    /**
     * 删除分析模型目录信息
     * 
     * @param id 分析模型目录ID
     * @return 结果
     */
    public int deleteBsiAnalyseModelCatalogById(String id);

    /**
     * 校验分析模型目录同级名称是否唯一
     *
     * @param bsiAnalyseModelCatalog 分析模型目录名字
     * @return 结果
     */
    public String checkBsiAnalyseModelCatalogNameUnique(BsiAnalyseModelCatalog bsiAnalyseModelCatalog);

    /**
     * 本人同名修改
     *
     * @param bsiAnalyseModelCatalog 分析模型目录名字
     * @return 结果
     */
    public String checkBsiAnalyseModelCatalogSelf(BsiAnalyseModelCatalog bsiAnalyseModelCatalog);
}
