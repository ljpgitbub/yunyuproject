package com.ruoyi.basedata.service.impl;

import java.util.List;

import com.ruoyi.basedata.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.basedata.mapper.BsiTradeMapper;
import com.ruoyi.basedata.domain.BsiTrade;
import com.ruoyi.basedata.service.IBsiTradeService;

/**
 * 行业Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-24
 */
@Service
public class BsiTradeServiceImpl implements IBsiTradeService 
{
    @Autowired
    private BsiTradeMapper bsiTradeMapper;

    /**
     * 查询行业
     * 
     * @param id 行业ID
     * @return 行业
     */
    @Override
    public BsiTrade selectBsiTradeById(String id)
    {
        return bsiTradeMapper.selectBsiTradeById(id);
    }

    /**
     * 查询行业列表
     * 
     * @param bsiTrade 行业
     * @return 行业
     */
    @Override
    public List<BsiTrade> selectBsiTradeList(BsiTrade bsiTrade)
    {
        return bsiTradeMapper.selectBsiTradeList(bsiTrade);
    }

    /**
     * 新增行业
     * 
     * @param bsiTrade 行业
     * @return 结果
     */
    @Override
    public int insertBsiTrade(BsiTrade bsiTrade)
    {
        return bsiTradeMapper.insertBsiTrade(bsiTrade);
    }

    /**
     * 修改行业
     * 
     * @param bsiTrade 行业
     * @return 结果
     */
    @Override
    public int updateBsiTrade(BsiTrade bsiTrade)
    {
        return bsiTradeMapper.updateBsiTrade(bsiTrade);
    }

    /**
     * 批量删除行业
     * 
     * @param ids 需要删除的行业ID
     * @return 结果
     */
    @Override
    public int deleteBsiTradeByIds(String[] ids)
    {
        return bsiTradeMapper.deleteBsiTradeByIds(ids);
    }

    /**
     * 删除行业信息
     * 
     * @param id 行业ID
     * @return 结果
     */
    @Override
    public int deleteBsiTradeById(String id)
    {
        return bsiTradeMapper.deleteBsiTradeById(id);
    }

    /**
     * 校验行业名称是否唯一
     *
     * @param name 行业名称
     * @return 结果
     */
    @Override
    public String checkBsiTradeNameUnique(String name) {

        int count = bsiTradeMapper.checkBsiTradeNameUnique(name);
        if (count > 0)
        {
            return Constants.NOT_UNIQUE;
        }
        return Constants.UNIQUE;
    }

    /**
     * 本人同名修改
     *
     * @param bsiTrade 行业
     * @return 结果
     */
    @Override
    public String checkBsiTradeSelf(BsiTrade bsiTrade) {

        int count =bsiTradeMapper.checkBsiTradeSelf(bsiTrade);
        if (count > 0){
            return Constants.NOT_UNIQUE;
        }
        return Constants.UNIQUE;
    }
}
