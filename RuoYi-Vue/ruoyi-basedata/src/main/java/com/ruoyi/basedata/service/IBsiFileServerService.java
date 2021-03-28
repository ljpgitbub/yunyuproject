package com.ruoyi.basedata.service;

import java.util.List;
import com.ruoyi.basedata.domain.BsiFileServer;

/**
 * 文件服务器Service接口
 * 
 * @author ruoyi
 * @date 2021-02-24
 */
public interface IBsiFileServerService 
{
    /**
     * 查询文件服务器
     * 
     * @param id 文件服务器ID
     * @return 文件服务器
     */
    public BsiFileServer selectBsiFileServerById(String id);

    /**
     * 查询文件服务器列表
     * 
     * @param bsiFileServer 文件服务器
     * @return 文件服务器集合
     */
    public List<BsiFileServer> selectBsiFileServerList(BsiFileServer bsiFileServer);

    /**
     * 新增文件服务器
     * 
     * @param bsiFileServer 文件服务器
     * @return 结果
     */
    public int insertBsiFileServer(BsiFileServer bsiFileServer);

    /**
     * 修改文件服务器
     * 
     * @param bsiFileServer 文件服务器
     * @return 结果
     */
    public int updateBsiFileServer(BsiFileServer bsiFileServer);

    /**
     * 批量删除文件服务器
     * 
     * @param ids 需要删除的文件服务器ID
     * @return 结果
     */
    public int deleteBsiFileServerByIds(String[] ids);

    /**
     * 删除文件服务器信息
     * 
     * @param id 文件服务器ID
     * @return 结果
     */
    public int deleteBsiFileServerById(String id);
}
