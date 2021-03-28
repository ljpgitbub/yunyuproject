package com.ruoyi.basedata.controller;

import java.util.List;
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
import com.ruoyi.basedata.domain.BsiDatabaseServer;
import com.ruoyi.basedata.service.IBsiDatabaseServerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 数据库服务器Controller
 * 
 * @author ruoyi
 * @date 2021-02-24
 */
@RestController
@RequestMapping("/basedata/databaseServer")
public class BsiDatabaseServerController extends BaseController
{
    @Autowired
    private IBsiDatabaseServerService bsiDatabaseServerService;

    /**
     * 查询数据库服务器列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:databaseServer:list')")
    @GetMapping("/list")
    public TableDataInfo list(BsiDatabaseServer bsiDatabaseServer)
    {
        startPage();
        List<BsiDatabaseServer> list = bsiDatabaseServerService.selectBsiDatabaseServerList(bsiDatabaseServer);
        return getDataTable(list);
    }

    /**
     * 导出数据库服务器列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:databaseServer:export')")
    @Log(title = "数据库服务器", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BsiDatabaseServer bsiDatabaseServer)
    {
        List<BsiDatabaseServer> list = bsiDatabaseServerService.selectBsiDatabaseServerList(bsiDatabaseServer);
        ExcelUtil<BsiDatabaseServer> util = new ExcelUtil<BsiDatabaseServer>(BsiDatabaseServer.class);
        return util.exportExcel(list, "databaseServer");
    }

    /**
     * 获取数据库服务器详细信息
     */
    @PreAuthorize("@ss.hasPermi('basedata:databaseServer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bsiDatabaseServerService.selectBsiDatabaseServerById(id));
    }

    /**
     * 新增数据库服务器
     */
    @PreAuthorize("@ss.hasPermi('basedata:databaseServer:add')")
    @Log(title = "数据库服务器", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsiDatabaseServer bsiDatabaseServer)
    {
        return toAjax(bsiDatabaseServerService.insertBsiDatabaseServer(bsiDatabaseServer));
    }

    /**
     * 修改数据库服务器
     */
    @PreAuthorize("@ss.hasPermi('basedata:databaseServer:edit')")
    @Log(title = "数据库服务器", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsiDatabaseServer bsiDatabaseServer)
    {
        return toAjax(bsiDatabaseServerService.updateBsiDatabaseServer(bsiDatabaseServer));
    }

    /**
     * 删除数据库服务器
     */
    @PreAuthorize("@ss.hasPermi('basedata:databaseServer:remove')")
    @Log(title = "数据库服务器", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bsiDatabaseServerService.deleteBsiDatabaseServerByIds(ids));
    }
}
