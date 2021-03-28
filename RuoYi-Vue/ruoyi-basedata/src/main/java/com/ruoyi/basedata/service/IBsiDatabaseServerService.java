package com.ruoyi.basedata.service;

import java.util.List;
import com.ruoyi.basedata.domain.BsiDatabaseServer;

/**
 * 数据库服务器Service接口
 * 
 * @author ruoyi
 * @date 2021-02-24
 */
public interface IBsiDatabaseServerService 
{
    /**
     * 查询数据库服务器
     * 
     * @param id 数据库服务器ID
     * @return 数据库服务器
     */
    public BsiDatabaseServer selectBsiDatabaseServerById(String id);

    /**
     * 查询数据库服务器列表
     * 
     * @param bsiDatabaseServer 数据库服务器
     * @return 数据库服务器集合
     */
    public List<BsiDatabaseServer> selectBsiDatabaseServerList(BsiDatabaseServer bsiDatabaseServer);

    /**
     * 新增数据库服务器
     * 
     * @param bsiDatabaseServer 数据库服务器
     * @return 结果
     */
    public int insertBsiDatabaseServer(BsiDatabaseServer bsiDatabaseServer);

    /**
     * 修改数据库服务器
     * 
     * @param bsiDatabaseServer 数据库服务器
     * @return 结果
     */
    public int updateBsiDatabaseServer(BsiDatabaseServer bsiDatabaseServer);

    /**
     * 批量删除数据库服务器
     * 
     * @param ids 需要删除的数据库服务器ID
     * @return 结果
     */
    public int deleteBsiDatabaseServerByIds(String[] ids);

    /**
     * 删除数据库服务器信息
     * 
     * @param id 数据库服务器ID
     * @return 结果
     */
    public int deleteBsiDatabaseServerById(String id);
}
