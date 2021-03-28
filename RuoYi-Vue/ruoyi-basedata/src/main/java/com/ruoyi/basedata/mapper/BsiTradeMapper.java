package com.ruoyi.basedata.mapper;

import java.util.List;
import com.ruoyi.basedata.domain.BsiTrade;

/**
 * 行业Mapper接口
 * 
 * @author ruoyi
 * @date 2021-02-24
 */
public interface BsiTradeMapper 
{
    /**
     * 查询行业
     * 
     * @param id 行业ID
     * @return 行业
     */
    public BsiTrade selectBsiTradeById(String id);

    /**
     * 查询行业列表
     * 
     * @param bsiTrade 行业
     * @return 行业集合
     */
    public List<BsiTrade> selectBsiTradeList(BsiTrade bsiTrade);

    /**
     * 新增行业
     * 
     * @param bsiTrade 行业
     * @return 结果
     */
    public int insertBsiTrade(BsiTrade bsiTrade);

    /**
     * 修改行业
     * 
     * @param bsiTrade 行业
     * @return 结果
     */
    public int updateBsiTrade(BsiTrade bsiTrade);

    /**
     * 删除行业
     * 
     * @param id 行业ID
     * @return 结果
     */
    public int deleteBsiTradeById(String id);

    /**
     * 批量删除行业
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsiTradeByIds(String[] ids);

    /**
     * 校验行业名称是否唯一
     *
     * @param name 行业名称
     * @return 结果
     */
    public int checkBsiTradeNameUnique(String name);

    /**
     * 本人同名修改
     * @param bsiTrade 行业
     * @return 结果
     */
    public int checkBsiTradeSelf(BsiTrade bsiTrade);
}
