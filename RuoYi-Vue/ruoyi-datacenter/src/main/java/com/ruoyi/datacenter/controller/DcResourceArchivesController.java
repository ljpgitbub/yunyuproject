package com.ruoyi.datacenter.controller;

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
import com.ruoyi.datacenter.domain.DcResourceArchives;
import com.ruoyi.datacenter.service.IDcResourceArchivesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 资源归档信息Controller
 * 
 * @author ruoyi
 * @date 2021-03-05
 */
@RestController
@RequestMapping("/datacenter/dcResourceArchives")
public class DcResourceArchivesController extends BaseController
{
    @Autowired
    private IDcResourceArchivesService dcResourceArchivesService;

    /**
     * 查询资源归档信息列表
     */
    @PreAuthorize("@ss.hasPermi('datacenter:dcResourceArchives:list')")
    @GetMapping("/list")
    public TableDataInfo list(DcResourceArchives dcResourceArchives)
    {
        startPage();
        List<DcResourceArchives> list = dcResourceArchivesService.selectDcResourceArchivesList(dcResourceArchives);
        return getDataTable(list);
    }

    /**
     * 导出资源归档信息列表
     */
    @PreAuthorize("@ss.hasPermi('datacenter:dcResourceArchives:export')")
    @Log(title = "资源归档信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DcResourceArchives dcResourceArchives)
    {
        List<DcResourceArchives> list = dcResourceArchivesService.selectDcResourceArchivesList(dcResourceArchives);
        ExcelUtil<DcResourceArchives> util = new ExcelUtil<DcResourceArchives>(DcResourceArchives.class);
        return util.exportExcel(list, "dcResourceArchives");
    }

    /**
     * 获取资源归档信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('datacenter:dcResourceArchives:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(dcResourceArchivesService.selectDcResourceArchivesById(id));
    }

    /**
     * 新增资源归档信息
     */
    @PreAuthorize("@ss.hasPermi('datacenter:dcResourceArchives:add')")
    @Log(title = "资源归档信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DcResourceArchives dcResourceArchives)
    {
        dcResourceArchives.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        return toAjax(dcResourceArchivesService.insertDcResourceArchives(dcResourceArchives));
    }

    /**
     * 修改资源归档信息
     */
    @PreAuthorize("@ss.hasPermi('datacenter:dcResourceArchives:edit')")
    @Log(title = "资源归档信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DcResourceArchives dcResourceArchives)
    {
        dcResourceArchives.setUpdatedBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        return toAjax(dcResourceArchivesService.updateDcResourceArchives(dcResourceArchives));
    }

    /**
     * 删除资源归档信息
     */
    @PreAuthorize("@ss.hasPermi('datacenter:dcResourceArchives:remove')")
    @Log(title = "资源归档信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(dcResourceArchivesService.deleteDcResourceArchivesByIds(ids));
    }
}
