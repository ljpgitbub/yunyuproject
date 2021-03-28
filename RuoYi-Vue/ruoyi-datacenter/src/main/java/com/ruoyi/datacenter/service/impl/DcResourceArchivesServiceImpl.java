package com.ruoyi.datacenter.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.datacenter.mapper.DcResourceArchivesMapper;
import com.ruoyi.datacenter.domain.DcResourceArchives;
import com.ruoyi.datacenter.service.IDcResourceArchivesService;

/**
 * 资源归档信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-05
 */
@Service
public class DcResourceArchivesServiceImpl implements IDcResourceArchivesService 
{
    @Autowired
    private DcResourceArchivesMapper dcResourceArchivesMapper;

    /**
     * 查询资源归档信息
     * 
     * @param id 资源归档信息ID
     * @return 资源归档信息
     */
    @Override
    public DcResourceArchives selectDcResourceArchivesById(String id)
    {
        return dcResourceArchivesMapper.selectDcResourceArchivesById(id);
    }

    /**
     * 查询资源归档信息列表
     * 
     * @param dcResourceArchives 资源归档信息
     * @return 资源归档信息
     */
    @Override
    public List<DcResourceArchives> selectDcResourceArchivesList(DcResourceArchives dcResourceArchives)
    {
        return dcResourceArchivesMapper.selectDcResourceArchivesList(dcResourceArchives);
    }

    /**
     * 新增资源归档信息
     * 
     * @param dcResourceArchives 资源归档信息
     * @return 结果
     */
    @Override
    public int insertDcResourceArchives(DcResourceArchives dcResourceArchives)
    {
        return dcResourceArchivesMapper.insertDcResourceArchives(dcResourceArchives);
    }

    /**
     * 修改资源归档信息
     * 
     * @param dcResourceArchives 资源归档信息
     * @return 结果
     */
    @Override
    public int updateDcResourceArchives(DcResourceArchives dcResourceArchives)
    {
        return dcResourceArchivesMapper.updateDcResourceArchives(dcResourceArchives);
    }

    /**
     * 批量删除资源归档信息
     * 
     * @param ids 需要删除的资源归档信息ID
     * @return 结果
     */
    @Override
    public int deleteDcResourceArchivesByIds(String[] ids)
    {
        return dcResourceArchivesMapper.deleteDcResourceArchivesByIds(ids);
    }

    /**
     * 删除资源归档信息信息
     * 
     * @param id 资源归档信息ID
     * @return 结果
     */
    @Override
    public int deleteDcResourceArchivesById(String id)
    {
        return dcResourceArchivesMapper.deleteDcResourceArchivesById(id);
    }
}
