package com.ruoyi.datacenter.mapper;

import java.util.List;
import com.ruoyi.datacenter.domain.DcResourceArchives;

/**
 * 资源归档信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-05
 */
public interface DcResourceArchivesMapper 
{
    /**
     * 查询资源归档信息
     * 
     * @param id 资源归档信息ID
     * @return 资源归档信息
     */
    public DcResourceArchives selectDcResourceArchivesById(String id);

    /**
     * 查询资源归档信息列表
     * 
     * @param dcResourceArchives 资源归档信息
     * @return 资源归档信息集合
     */
    public List<DcResourceArchives> selectDcResourceArchivesList(DcResourceArchives dcResourceArchives);

    /**
     * 新增资源归档信息
     * 
     * @param dcResourceArchives 资源归档信息
     * @return 结果
     */
    public int insertDcResourceArchives(DcResourceArchives dcResourceArchives);

    /**
     * 修改资源归档信息
     * 
     * @param dcResourceArchives 资源归档信息
     * @return 结果
     */
    public int updateDcResourceArchives(DcResourceArchives dcResourceArchives);

    /**
     * 删除资源归档信息
     * 
     * @param id 资源归档信息ID
     * @return 结果
     */
    public int deleteDcResourceArchivesById(String id);

    /**
     * 批量删除资源归档信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDcResourceArchivesByIds(String[] ids);
}
