package com.ruoyi.basedata.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.basedata.domain.BsiResourceCatalog;
import com.ruoyi.basedata.service.IBsiResourceCatalogService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 资源目录Controller
 * 
 * @author ruoyi
 * @date 2021-02-24
 */
@RestController
@RequestMapping("/basedata/resourceCatalog")
public class BsiResourceCatalogController extends BaseController
{
    @Autowired
    private IBsiResourceCatalogService bsiResourceCatalogService;

    /**
     * 查询资源目录列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:resourceCatalog:list')")
    @GetMapping("/list")
    public AjaxResult list(BsiResourceCatalog bsiResourceCatalog)
    {
        List<BsiResourceCatalog> list = bsiResourceCatalogService.selectBsiResourceCatalogList(bsiResourceCatalog);
        return AjaxResult.success(list);
    }

    /**
     * 导出资源目录列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:resourceCatalog:export')")
    @Log(title = "资源目录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BsiResourceCatalog bsiResourceCatalog)
    {
        List<BsiResourceCatalog> list = bsiResourceCatalogService.selectBsiResourceCatalogList(bsiResourceCatalog);
        ExcelUtil<BsiResourceCatalog> util = new ExcelUtil<BsiResourceCatalog>(BsiResourceCatalog.class);
        return util.exportExcel(list, "resourceCatalog");
    }

    /**
     * 获取资源目录详细信息
     */
    @PreAuthorize("@ss.hasPermi('basedata:resourceCatalog:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bsiResourceCatalogService.selectBsiResourceCatalogById(id));
    }

    /**
     * 新增资源目录
     */
    @PreAuthorize("@ss.hasPermi('basedata:resourceCatalog:add')")
    @Log(title = "资源目录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsiResourceCatalog bsiResourceCatalog)
    {
        //同级目录不能有相同资源目录名称，所以先查询同级目录下，有没有相同
        int count = bsiResourceCatalogService.getCountByParentId(bsiResourceCatalog.getParentId(),bsiResourceCatalog.getCatalogName());
        if(count > 0){
            return AjaxResult.error("新增资源目录'" + bsiResourceCatalog.getCatalogName() + "'失败，资源目录已存在");
        }
        //判断ParentId是否为0，如果为0，则代表父级目录是顶级目录。如果不为0，根据ParentId查询查询父级目录的层级，然后+1得到子集目录层级。顶级目录层级为1
        //获取所有父级目录的id，按顺序存入“所有父级”字段，按照目录层级排序即可。
        if ("0".equals(bsiResourceCatalog.getParentId())){
            bsiResourceCatalog.setLevel(1L);
            bsiResourceCatalog.setParentIds("0");
        }else{
            int parentLevel = bsiResourceCatalogService.getParentLevel(bsiResourceCatalog.getParentId());
            parentLevel += 1;
            bsiResourceCatalog.setLevel((long)parentLevel);
            List<String> parens = bsiResourceCatalogService.getParentList(bsiResourceCatalog.getParentId());
            String parentSp = "";
            for (String par : parens){
                parentSp += par + ",";
            }
            bsiResourceCatalog.setParentIds(parentSp.substring(0,parentSp.length()-1));
        }
        bsiResourceCatalog.setCreatedBy(Long.toString(SecurityUtils.getLoginUser().getUser().getUserId()));
        return toAjax(bsiResourceCatalogService.insertBsiResourceCatalog(bsiResourceCatalog));
    }

    /**
     * 修改资源目录
     */
    @PreAuthorize("@ss.hasPermi('basedata:resourceCatalog:edit')")
    @Log(title = "资源目录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsiResourceCatalog bsiResourceCatalog)
    {
        return toAjax(bsiResourceCatalogService.updateBsiResourceCatalog(bsiResourceCatalog));
    }

    /**
     * 删除资源目录
     */
    @PreAuthorize("@ss.hasPermi('basedata:resourceCatalog:remove')")
    @Log(title = "资源目录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bsiResourceCatalogService.deleteBsiResourceCatalogByIds(ids));
    }

    /**
     * 获取资源目录下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(BsiResourceCatalog bsiResourceCatalog){
        List<BsiResourceCatalog> rc = bsiResourceCatalogService.selectBsiResourceCatalogList(bsiResourceCatalog);
        return AjaxResult.success(bsiResourceCatalogService.buildBsiResourceCatalogTreeSelectStringId(rc));
    }
}
