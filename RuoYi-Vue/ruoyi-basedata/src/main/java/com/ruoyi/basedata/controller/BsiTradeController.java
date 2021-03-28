package com.ruoyi.basedata.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.basedata.constant.Constants;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.basedata.domain.BsiTrade;
import com.ruoyi.basedata.service.IBsiTradeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 行业Controller
 * 
 * @author ruoyi
 * @date 2021-02-24
 */
@RestController
@RequestMapping("/basedata/trade")
public class BsiTradeController extends BaseController
{
    @Autowired
    private IBsiTradeService bsiTradeService;

    /**
     * 查询行业列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:trade:list')")
    @GetMapping("/list")
    public TableDataInfo list(BsiTrade bsiTrade)
    {
        startPage();
        List<BsiTrade> list = bsiTradeService.selectBsiTradeList(bsiTrade);
        return getDataTable(list);
    }

    /**
     * 导出行业列表
     */
    @PreAuthorize("@ss.hasPermi('basedata:trade:export')")
    @Log(title = "行业", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BsiTrade bsiTrade)
    {
        List<BsiTrade> list = bsiTradeService.selectBsiTradeList(bsiTrade);
        ExcelUtil<BsiTrade> util = new ExcelUtil<BsiTrade>(BsiTrade.class);
        return util.exportExcel(list, "trade");
    }

    /**
     * 获取行业详细信息
     */
    @PreAuthorize("@ss.hasPermi('basedata:trade:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bsiTradeService.selectBsiTradeById(id));
    }

    /**
     * 新增行业
     */
    @PreAuthorize("@ss.hasPermi('basedata:trade:add')")
    @Log(title = "行业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsiTrade bsiTrade)
    {
        if(Constants.NOT_UNIQUE.equals(bsiTradeService.checkBsiTradeNameUnique(bsiTrade.getName()))){
            return AjaxResult.error("新增行业'" + bsiTrade.getName() + "'失败，行业名称已存在");
        }
        bsiTrade.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        return toAjax(bsiTradeService.insertBsiTrade(bsiTrade));
    }

    /**
     * 修改行业
     */
    @PreAuthorize("@ss.hasPermi('basedata:trade:edit')")
    @Log(title = "行业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsiTrade bsiTrade)
    {
        //判断本人同名修改
        if(Constants.NOT_UNIQUE.equals(bsiTradeService.checkBsiTradeSelf(bsiTrade))){
            bsiTrade.setUpdatedBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
            return toAjax(bsiTradeService.updateBsiTrade(bsiTrade));
        }else if(Constants.NOT_UNIQUE.equals(bsiTradeService.checkBsiTradeNameUnique(bsiTrade.getName()))){
            return AjaxResult.error("修改行业'" + bsiTrade.getName() + "'失败，行业名称已存在");
        }
        return toAjax(bsiTradeService.updateBsiTrade(bsiTrade));
    }

    /**
     * 删除行业
     */
    @PreAuthorize("@ss.hasPermi('basedata:trade:remove')")
    @Log(title = "行业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bsiTradeService.deleteBsiTradeByIds(ids));
    }

    /**
     * 查询所有行业
     */
    @GetMapping("/tradeList")
    public AjaxResult tradeList(BsiTrade bsiTrade){
        List<BsiTrade> data = bsiTradeService.selectBsiTradeList(bsiTrade);
        if(StringUtils.isNull(data)){
            data = new ArrayList<BsiTrade>();
        }
        return AjaxResult.success(data);
    }

}
