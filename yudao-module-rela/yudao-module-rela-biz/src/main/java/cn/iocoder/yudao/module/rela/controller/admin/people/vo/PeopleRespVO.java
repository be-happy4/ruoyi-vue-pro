package cn.iocoder.yudao.module.rela.controller.admin.people.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 人物 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PeopleRespVO {

    @Schema(description = "人物编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "18931")
    @ExcelProperty("人物编号")
    private Long id;

    @Schema(description = "人物名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("人物名称")
    private String name;

    @Schema(description = "电话号码")
    @ExcelProperty("电话号码")
    private String phoneNumber;

    @Schema(description = "常用地址")
    @ExcelProperty("常用地址")
    private String address;

    @Schema(description = "性别", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty(value = "性别", converter = DictConvert.class)
    @DictFormat("system_user_sex") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Short sex;

    @Schema(description = "身份证号")
    @ExcelProperty("身份证号")
    private String idNumber;

    @Schema(description = "主要头衔")
    @ExcelProperty("主要头衔")
    private String title;

    @Schema(description = "户籍")
    @ExcelProperty("户籍")
    private String census;

    @Schema(description = "小名、曾用名")
    @ExcelProperty("小名、曾用名")
    private String alias;

    @Schema(description = "主要邮箱")
    @ExcelProperty("主要邮箱")
    private String email;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "是否删除", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty(value = "是否删除", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Short isDeleted;

    @Schema(description = "头像")
    @ExcelProperty("头像")
    private String avatar;

}