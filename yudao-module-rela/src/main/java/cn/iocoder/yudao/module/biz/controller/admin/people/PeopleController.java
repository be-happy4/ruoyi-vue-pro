package cn.iocoder.yudao.module.biz.controller.admin.people;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.biz.controller.admin.people.vo.*;
import cn.iocoder.yudao.module.biz.dal.dataobject.people.PeopleDO;
import cn.iocoder.yudao.module.biz.service.people.PeopleService;

@Tag(name = "管理后台 - 人物")
@RestController
@RequestMapping("/biz/people")
@Validated
public class PeopleController {

    @Resource
    private PeopleService peopleService;

    @PostMapping("/create")
    @Operation(summary = "创建人物")
    @PreAuthorize("@ss.hasPermission('biz:people:create')")
    public CommonResult<Long> createPeople(@Valid @RequestBody PeopleSaveReqVO createReqVO) {
        return success(peopleService.createPeople(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新人物")
    @PreAuthorize("@ss.hasPermission('biz:people:update')")
    public CommonResult<Boolean> updatePeople(@Valid @RequestBody PeopleSaveReqVO updateReqVO) {
        peopleService.updatePeople(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人物")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('biz:people:delete')")
    public CommonResult<Boolean> deletePeople(@RequestParam("id") Long id) {
        peopleService.deletePeople(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除人物")
                @PreAuthorize("@ss.hasPermission('biz:people:delete')")
    public CommonResult<Boolean> deletePeopleList(@RequestParam("ids") List<Long> ids) {
        peopleService.deletePeopleListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得人物")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('biz:people:query')")
    public CommonResult<PeopleRespVO> getPeople(@RequestParam("id") Long id) {
        PeopleDO people = peopleService.getPeople(id);
        return success(BeanUtils.toBean(people, PeopleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得人物分页")
    @PreAuthorize("@ss.hasPermission('biz:people:query')")
    public CommonResult<PageResult<PeopleRespVO>> getPeoplePage(@Valid PeoplePageReqVO pageReqVO) {
        PageResult<PeopleDO> pageResult = peopleService.getPeoplePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PeopleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出人物 Excel")
    @PreAuthorize("@ss.hasPermission('biz:people:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPeopleExcel(@Valid PeoplePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PeopleDO> list = peopleService.getPeoplePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "人物.xls", "数据", PeopleRespVO.class,
                        BeanUtils.toBean(list, PeopleRespVO.class));
    }

}