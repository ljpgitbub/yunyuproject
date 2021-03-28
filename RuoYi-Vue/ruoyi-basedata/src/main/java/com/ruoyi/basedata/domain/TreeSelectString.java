package com.ruoyi.basedata.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


/**
 * TreeSelectStringId树结构实体类
 *
 * @author ruoyi
 */
public class TreeSelectString implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private String id;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelectString> children;

    public TreeSelectString()
    {

    }
    public TreeSelectString(BsiResourceCatalog bsiResourceCatalog){
        this.id = bsiResourceCatalog.getId();
        this.label = bsiResourceCatalog.getCatalogName();
        this.children = bsiResourceCatalog.getChildren().stream().map(TreeSelectString::new).collect(Collectors.toList());
    }

    public TreeSelectString(Object o) {
    }


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public List<TreeSelectString> getChildren()
    {
        return children;
    }

    public void setChildren(List<TreeSelectString> children)
    {
        this.children = children;
    }
}
