package com.ruoyi.basedata.service;

import java.util.List;
import com.ruoyi.basedata.domain.BsiTrade;

/**
 * 行业Service接口
 * 
 * @author ruoyi
 * @date 2021-02-24
 */
public interface IBsiTradeService 
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
     * 批量删除行业
     * 
     * @param ids 需要删除的行业ID
     * @return 结果
     */
    public int deleteBsiTradeByIds(String[] ids);

    /**
     * 删除行业信息
     * 
     * @param id 行业ID
     * @return 结果
     */
    public int deleteBsiTradeById(String id);

    /**
     * 校验行业名称是否唯一
     *
     * @param name 行业名称
     * @return 结果
     */
    public String checkBsiTradeNameUnique(String name);

    /**
     * 本人同名修改
     * @param bsiTrade 行业
     * @return 结果
     */
    public String checkBsiTradeSelf(BsiTrade bsiTrade);
}
