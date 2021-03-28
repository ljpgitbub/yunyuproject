package com.ruoyi.basedata.controller;

import java.util.Iterator;
import java.util.List;

import com.ruoyi.basedata.constant.Constants;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.lang3.ArrayUtils;
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
import com.ruoyi.basedata.domain.BsiAnalyseModelCatalog;
import com.ruoyi.basedata.service.IBsiAnalyseModelCatalogService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 分析模型目录Controller
 * 
 * @author ruoyi
 * @date 2021-02-25
 */
@RestController
@RequestMapping("/basedata/analyseModelCatalog")
public class BsiAnalyseModelCatalogController extends BaseController
{
    @Autowired
    private IBsiAnalyseModelCatalogService bsiAnalyseModelCatalogService;

    /**
     * 查询分析模型目录列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:analyseModelCatalog:list')")
    @GetMapping("/list")
    public AjaxResult list(BsiAnalyseModelCatalog bsiAnalyseModelCatalog)
    {
        List<BsiAnalyseModelCatalog> list = bsiAnalyseModelCatalogService.selectBsiAnalyseModelCatalogList(bsiAnalyseModelCatalog);
        return AjaxResult.success(list);
    }

    /**
     * 导出分析模型目录列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:analyseModelCatalog:export')")
    @Log(title = "分析模型目录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BsiAnalyseModelCatalog bsiAnalyseModelCatalog)
    {
        List<BsiAnalyseModelCatalog> list = bsiAnalyseModelCatalogService.selectBsiAnalyseModelCatalogList(bsiAnalyseModelCatalog);
        ExcelUtil<BsiAnalyseModelCatalog> util = new ExcelUtil<BsiAnalyseModelCatalog>(BsiAnalyseModelCatalog.class);
        return util.exportExcel(list, "analyseModelCatalog");
    }

    /**
     * 获取分析模型目录详细信息
     */
    @PreAuthorize("@ss.hasPermi('basedata:analyseModelCatalog:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bsiAnalyseModelCatalogService.selectBsiAnalyseModelCatalogById(id));
    }

    /**
     * 新增分析模型目录
     */
    @PreAuthorize("@ss.hasPermi('basedata:analyseModelCatalog:add')")
    @Log(title = "分析模型目录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsiAnalyseModelCatalog bsiAnalyseModelCatalog)
    {
        if(Constants.NOT_UNIQUE.equals(bsiAnalyseModelCatalogService.checkBsiAnalyseModelCatalogNameUnique(bsiAnalyseModelCatalog))){
            return AjaxResult.error("新增分析模型目录‘"+bsiAnalyseModelCatalog.getName()+"'失败,分析模型目录名称已存在");
        }
        bsiAnalyseModelCatalog.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        return toAjax(bsiAnalyseModelCatalogService.insertBsiAnalyseModelCatalog(bsiAnalyseModelCatalog));
    }

    /**
     * 修改分析模型目录
     */
    @PreAuthorize("@ss.hasPermi('basedata:analyseModelCatalog:edit')")
    @Log(title = "分析模型目录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsiAnalyseModelCatalog bsiAnalyseModelCatalog)
    {
        //判断本人同名修改
        if(Constants.NOT_UNIQUE.equals(bsiAnalyseModelCatalogService.checkBsiAnalyseModelCatalogSelf(bsiAnalyseModelCatalog))){
            bsiAnalyseModelCatalog.setUpdatedBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
            return toAjax(bsiAnalyseModelCatalogService.updateBsiAnalyseModelCatalog(bsiAnalyseModelCatalog));
        }else if(Constants.NOT_UNIQUE.equals(bsiAnalyseModelCatalogService.checkBsiAnalyseModelCatalogNameUnique(bsiAnalyseModelCatalog))){
            return AjaxResult.error("修改分析模型目录‘"+bsiAnalyseModelCatalog.getName()+"'失败,分析模型目录名称已存在");
        }
        bsiAnalyseModelCatalog.setUpdatedBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        return toAjax(bsiAnalyseModelCatalogService.updateBsiAnalyseModelCatalog(bsiAnalyseModelCatalog));
    }

    /**
     * 删除分析模型目录
     */
    @PreAuthorize("@ss.hasPermi('basedata:analyseModelCatalog:remove')")
    @Log(title = "分析模型目录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bsiAnalyseModelCatalogService.deleteBsiAnalyseModelCatalogById(ids[0]));
    }


    /**
     * 查询分析模型目录列表(排除节点)
     */
    @PreAuthorize("@ss.hasPermi('basedata:analyseModelCatalog:list')")
    @GetMapping("/list/exclude/{id}")
    public AjaxResult excludeChild(@PathVariable(value = "id", required = false) String id){
        List<BsiAnalyseModelCatalog> amc = bsiAnalyseModelCatalogService.selectBsiAnalyseModelCatalogList(new BsiAnalyseModelCatalog());
        Iterator<BsiAnalyseModelCatalog> it = amc.iterator();
        while (it.hasNext()){
            BsiAnalyseModelCatalog a = (BsiAnalyseModelCatalog) it.next();
            if(a.getId().equals(id) || ArrayUtils.contains(StringUtils.split(a.getParentIds(), ","), id + "")){
                it.remove();
            }
        }
        return AjaxResult.success(amc);
    }
}
