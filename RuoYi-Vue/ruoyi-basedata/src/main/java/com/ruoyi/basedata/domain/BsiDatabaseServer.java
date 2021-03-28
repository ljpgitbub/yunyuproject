package com.ruoyi.basedata.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 数据库服务器对象 bsi_database_server
 * 
 * @author ruoyi
 * @date 2021-02-24
 */
public class BsiDatabaseServer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private String id;

    /** 服务器名称 */
    @Excel(name = "服务器名称")
    private String name;

    /** 服务器地址 */
    @Excel(name = "服务器地址")
    private String host;

    /** 实例名称 */
    @Excel(name = "实例名称")
    private String instanceName;

    /** 端口号 */
    @Excel(name = "端口号")
    private String port;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 数据库名称 */
    @Excel(name = "数据库名称")
    private String databaseName;

    /** 默认服务器 */
    @Excel(name = "默认服务器")
    private String isDefault;

    /** 所属行业 */
    @Excel(name = "所属行业")
    private String tradeId;

    /** 备注 */
    @Excel(name = "备注")
    private String description;

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

    /** 删除标记（0代表存在 2代表删除） */
    @Excel(name = "删除标记", readConverterExp = "0=代表存在,2=代表删除")
    private String deletedFlag;

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
    public void setHost(String host) 
    {
        this.host = host;
    }

    public String getHost() 
    {
        return host;
    }
    public void setInstanceName(String instanceName) 
    {
        this.instanceName = instanceName;
    }

    public String getInstanceName() 
    {
        return instanceName;
    }
    public void setPort(String port) 
    {
        this.port = port;
    }

    public String getPort() 
    {
        return port;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setDatabaseName(String databaseName) 
    {
        this.databaseName = databaseName;
    }

    public String getDatabaseName() 
    {
        return databaseName;
    }
    public void setIsDefault(String isDefault) 
    {
        this.isDefault = isDefault;
    }

    public String getIsDefault() 
    {
        return isDefault;
    }
    public void setTradeId(String tradeId) 
    {
        this.tradeId = tradeId;
    }

    public String getTradeId() 
    {
        return tradeId;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
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
            .append("name", getName())
            .append("host", getHost())
            .append("instanceName", getInstanceName())
            .append("port", getPort())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("databaseName", getDatabaseName())
            .append("isDefault", getIsDefault())
            .append("tradeId", getTradeId())
            .append("description", getDescription())
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
