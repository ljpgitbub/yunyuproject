package com.ruoyi.basedata.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.basedata.mapper.BsiFileServerMapper;
import com.ruoyi.basedata.domain.BsiFileServer;
import com.ruoyi.basedata.service.IBsiFileServerService;

/**
 * 文件服务器Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-24
 */
@Service
public class BsiFileServerServiceImpl implements IBsiFileServerService 
{
    @Autowired
    private BsiFileServerMapper bsiFileServerMapper;

    /**
     * 查询文件服务器
     * 
     * @param id 文件服务器ID
     * @return 文件服务器
     */
    @Override
    public BsiFileServer selectBsiFileServerById(String id)
    {
        return bsiFileServerMapper.selectBsiFileServerById(id);
    }

    /**
     * 查询文件服务器列表
     * 
     * @param bsiFileServer 文件服务器
     * @return 文件服务器
     */
    @Override
    public List<BsiFileServer> selectBsiFileServerList(BsiFileServer bsiFileServer)
    {
        return bsiFileServerMapper.selectBsiFileServerList(bsiFileServer);
    }

    /**
     * 新增文件服务器
     * 
     * @param bsiFileServer 文件服务器
     * @return 结果
     */
    @Override
    public int insertBsiFileServer(BsiFileServer bsiFileServer)
    {
        return bsiFileServerMapper.insertBsiFileServer(bsiFileServer);
    }

    /**
     * 修改文件服务器
     * 
     * @param bsiFileServer 文件服务器
     * @return 结果
     */
    @Override
    public int updateBsiFileServer(BsiFileServer bsiFileServer)
    {
        return bsiFileServerMapper.updateBsiFileServer(bsiFileServer);
    }

    /**
     * 批量删除文件服务器
     * 
     * @param ids 需要删除的文件服务器ID
     * @return 结果
     */
    @Override
    public int deleteBsiFileServerByIds(String[] ids)
    {
        return bsiFileServerMapper.deleteBsiFileServerByIds(ids);
    }

    /**
     * 删除文件服务器信息
     * 
     * @param id 文件服务器ID
     * @return 结果
     */
    @Override
    public int deleteBsiFileServerById(String id)
    {
        return bsiFileServerMapper.deleteBsiFileServerById(id);
    }
}
