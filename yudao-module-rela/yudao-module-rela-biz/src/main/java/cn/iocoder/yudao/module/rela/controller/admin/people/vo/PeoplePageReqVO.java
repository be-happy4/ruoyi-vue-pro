package cn.iocoder.yudao.module.rela.controller.admin.people.vo;

import cn.iocoder.yudao.module.system.enums.common.SexEnum;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 人物分页 Request VO")
@Data
public class PeoplePageReqVO extends PageParam {

    @Schema(description = "人物名称", example = "赵六")
    private String name;

    @Schema(description = "电话号码")
    private String phoneNumber;

    @Schema(description = "常用地址")
    private String address;

    @Schema(description = "性别")
    private SexEnum sex;

    @Schema(description = "身份证号")
    private String idNumber;

    @Schema(description = "主要头衔")
    private String title;

    @Schema(description = "户籍")
    private String census;

    @Schema(description = "小名、曾用名")
    private String alias;

    @Schema(description = "主要邮箱")
    private String email;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "是否删除")
    private Boolean deleted;

    @Schema(description = "头像")
    private String avatar;

}