package com.ruoyi.datacenter.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资源归档信息对象 dc_resource_archives
 * 
 * @author ruoyi
 * @date 2021-03-05
 */
public class DcResourceArchives extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private String id;

    /** 资源名称 */
    @Excel(name = "资源名称")
    private String resourceName;

    /** 所属资源目录 */
    @Excel(name = "所属资源目录")
    private String resourceCatalogId;

    /** 所属数据库服务器 */
    @Excel(name = "所属数据库服务器")
    private String dataServerId;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deletedTime;

    /** 删除人 */
    @Excel(name = "删除人")
    private String deletedBy;

    /** 删除标记 */
    @Excel(name = "删除标记")
    private String deletedFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setResourceName(String resourceName) 
    {
        this.resourceName = resourceName;
    }

    public String getResourceName() 
    {
        return resourceName;
    }
    public void setResourceCatalogId(String resourceCatalogId) 
    {
        this.resourceCatalogId = resourceCatalogId;
    }

    public String getResourceCatalogId() 
    {
        return resourceCatalogId;
    }
    public void setDataServerId(String dataServerId) 
    {
        this.dataServerId = dataServerId;
    }

    public String getDataServerId() 
    {
        return dataServerId;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedBy(String updatedBy) 
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() 
    {
        return updatedBy;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }
    public void setDeletedTime(Date deletedTime) 
    {
        this.deletedTime = deletedTime;
    }

    public Date getDeletedTime() 
    {
        return deletedTime;
    }
    public void setDeletedBy(String deletedBy) 
    {
        this.deletedBy = deletedBy;
    }

    public String getDeletedBy() 
    {
        return deletedBy;
    }
    public void setDeletedFlag(String deletedFlag) 
    {
        this.deletedFlag = deletedFlag;
    }

    public String getDeletedFlag() 
    {
        return deletedFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("resourceName", getResourceName())
            .append("resourceCatalogId", getResourceCatalogId())
            .append("dataServerId", getDataServerId())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .append("deletedTime", getDeletedTime())
            .append("deletedBy", getDeletedBy())
            .append("deletedFlag", getDeletedFlag())
            .toString();
    }
}
