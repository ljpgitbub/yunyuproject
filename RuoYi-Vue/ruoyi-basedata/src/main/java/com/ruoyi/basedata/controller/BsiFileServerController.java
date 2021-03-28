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
import com.ruoyi.basedata.domain.BsiFileServer;
import com.ruoyi.basedata.service.IBsiFileServerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文件服务器Controller
 * 
 * @author ruoyi
 * @date 2021-02-24
 */
@RestController
@RequestMapping("/basedata/fileServer")
public class BsiFileServerController extends BaseController
{
    @Autowired
    private IBsiFileServerService bsiFileServerService;

    /**
     * 查询文件服务器列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:fileServer:list')")
    @GetMapping("/list")
    public TableDataInfo list(BsiFileServer bsiFileServer)
    {
        startPage();
        List<BsiFileServer> list = bsiFileServerService.selectBsiFileServerList(bsiFileServer);
        return getDataTable(list);
    }

    /**
     * 导出文件服务器列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:fileServer:export')")
    @Log(title = "文件服务器", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BsiFileServer bsiFileServer)
    {
        List<BsiFileServer> list = bsiFileServerService.selectBsiFileServerList(bsiFileServer);
        ExcelUtil<BsiFileServer> util = new ExcelUtil<BsiFileServer>(BsiFileServer.class);
        return util.exportExcel(list, "fileServer");
    }

    /**
     * 获取文件服务器详细信息
     */
    @PreAuthorize("@ss.hasPermi('basedata:fileServer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bsiFileServerService.selectBsiFileServerById(id));
    }

    /**
     * 新增文件服务器
     */
    @PreAuthorize("@ss.hasPermi('basedata:fileServer:add')")
    @Log(title = "文件服务器", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsiFileServer bsiFileServer)
    {
        return toAjax(bsiFileServerService.insertBsiFileServer(bsiFileServer));
    }

    /**
     * 修改文件服务器
     */
    @PreAuthorize("@ss.hasPermi('basedata:fileServer:edit')")
    @Log(title = "文件服务器", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsiFileServer bsiFileServer)
    {
        return toAjax(bsiFileServerService.updateBsiFileServer(bsiFileServer));
    }

    /**
     * 删除文件服务器
     */
    @PreAuthorize("@ss.hasPermi('basedata:fileServer:remove')")
    @Log(title = "文件服务器", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bsiFileServerService.deleteBsiFileServerByIds(ids));
    }
}
