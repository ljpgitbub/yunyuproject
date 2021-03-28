package com.ruoyi.basedata.mapper;

import java.util.List;
import com.ruoyi.basedata.domain.BsiAnalyseModelCatalog;
import com.ruoyi.common.core.domain.entity.SysDept;
import org.apache.ibatis.annotations.Param;

/**
 * 分析模型目录Mapper接口
 * 
 * @author ruoyi
 * @date 2021-02-25
 */
public interface BsiAnalyseModelCatalogMapper 
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
     * 删除分析模型目录
     * 
     * @param id 分析模型目录ID
     * @return 结果
     */
    public int deleteBsiAnalyseModelCatalogById(String id);

    /**
     * 批量删除分析模型目录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsiAnalyseModelCatalogByIds(String[] ids);

    /**
     * 校验分析模型目录名称是否唯一
     *
     * @param bsiAnalyseModelCatalog 校验分析模型目录同级名称是否唯一
     * @return 结果
     */
    public int checkBsiAnalyseModelCatalogNameUnique(BsiAnalyseModelCatalog bsiAnalyseModelCatalog);

    /**
     * 本人同名修改
     *
     * @param bsiAnalyseModelCatalog 校验分析模型目录同级名称是否唯一
     * @return 结果
     */
    public int checkBsiAnalyseModelCatalogSelf(BsiAnalyseModelCatalog bsiAnalyseModelCatalog);

    /**
     * 根据ID查询所有子部门
     *
     * @param id 分析模型目录ID
     * @return 分析模型目录列表
     */
    public List<BsiAnalyseModelCatalog> selectChildrenBsiAnalyseModelCatalogById(String id);

    /**
     * 修改子元素关系
     *
     * @param bsiAnalyseModelCatalogs 子元素
     * @return 结果
     */
    public int updateBsiAnalyseModelCatalogChildren(@Param("bsiAnalyseModelCatalogs") List<BsiAnalyseModelCatalog> bsiAnalyseModelCatalogs);

}
