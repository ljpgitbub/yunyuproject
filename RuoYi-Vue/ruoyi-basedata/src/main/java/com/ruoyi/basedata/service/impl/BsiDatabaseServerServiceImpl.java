package com.ruoyi.basedata.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.basedata.mapper.BsiDatabaseServerMapper;
import com.ruoyi.basedata.domain.BsiDatabaseServer;
import com.ruoyi.basedata.service.IBsiDatabaseServerService;

/**
 * 数据库服务器Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-24
 */
@Service
public class BsiDatabaseServerServiceImpl implements IBsiDatabaseServerService 
{
    @Autowired
    private BsiDatabaseServerMapper bsiDatabaseServerMapper;

    /**
     * 查询数据库服务器
     * 
     * @param id 数据库服务器ID
     * @return 数据库服务器
     */
    @Override
    public BsiDatabaseServer selectBsiDatabaseServerById(String id)
    {
        return bsiDatabaseServerMapper.selectBsiDatabaseServerById(id);
    }

    /**
     * 查询数据库服务器列表
     * 
     * @param bsiDatabaseServer 数据库服务器
     * @return 数据库服务器
     */
    @Override
    public List<BsiDatabaseServer> selectBsiDatabaseServerList(BsiDatabaseServer bsiDatabaseServer)
    {
        return bsiDatabaseServerMapper.selectBsiDatabaseServerList(bsiDatabaseServer);
    }

    /**
     * 新增数据库服务器
     * 
     * @param bsiDatabaseServer 数据库服务器
     * @return 结果
     */
    @Override
    public int insertBsiDatabaseServer(BsiDatabaseServer bsiDatabaseServer)
    {
        return bsiDatabaseServerMapper.insertBsiDatabaseServer(bsiDatabaseServer);
    }

    /**
     * 修改数据库服务器
     * 
     * @param bsiDatabaseServer 数据库服务器
     * @return 结果
     */
    @Override
    public int updateBsiDatabaseServer(BsiDatabaseServer bsiDatabaseServer)
    {
        return bsiDatabaseServerMapper.updateBsiDatabaseServer(bsiDatabaseServer);
    }

    /**
     * 批量删除数据库服务器
     * 
     * @param ids 需要删除的数据库服务器ID
     * @return 结果
     */
    @Override
    public int deleteBsiDatabaseServerByIds(String[] ids)
    {
        return bsiDatabaseServerMapper.deleteBsiDatabaseServerByIds(ids);
    }

    /**
     * 删除数据库服务器信息
     * 
     * @param id 数据库服务器ID
     * @return 结果
     */
    @Override
    public int deleteBsiDatabaseServerById(String id)
    {
        return bsiDatabaseServerMapper.deleteBsiDatabaseServerById(id);
    }
}
