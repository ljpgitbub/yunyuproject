package com.ruoyi.basedata.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 分析模型目录对象 bsi_analyse_model_catalog
 * 
 * @author ruoyi
 * @date 2021-02-25
 */
public class BsiAnalyseModelCatalog extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 目录编号 */
    private String id;

    /** 目录名称 */
    @Excel(name = "目录名称")
    private String name;

    /** 所属行业 */
    @Excel(name = "所属行业")
    private String tradeId;

    /** 所有父级 */
    @Excel(name = "所有父级")
    private String parentIds;

    /** 排序 */
    @Excel(name = "排序")
    private Long showOrder;

    /** 目录层级 */
    @Excel(name = "目录层级")
    private Long level;

    /** 删除标记（0代表存在 2代表删除） */
    @Excel(name = "删除标记", readConverterExp = "0=代表存在,2=代表删除")
    private String deletedFlag;

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

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setTradeId(String tradeId) 
    {
        this.tradeId = tradeId;
    }

    public String getTradeId() 
    {
        return tradeId;
    }
    public void setParentIds(String parentIds) 
    {
        this.parentIds = parentIds;
    }

    public String getParentIds() 
    {
        return parentIds;
    }
    public void setShowOrder(Long showOrder) 
    {
        this.showOrder = showOrder;
    }

    public Long getShowOrder() 
    {
        return showOrder;
    }
    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
    }
    public void setDeletedFlag(String deletedFlag) 
    {
        this.deletedFlag = deletedFlag;
    }

    public String getDeletedFlag() 
    {
        return deletedFlag;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("tradeId", getTradeId())
            .append("parentId", getParentId())
            .append("parentIds", getParentIds())
            .append("showOrder", getShowOrder())
            .append("level", getLevel())
            .append("deletedFlag", getDeletedFlag())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .append("deletedTime", getDeletedTime())
            .append("deletedBy", getDeletedBy())
            .toString();
    }
}
