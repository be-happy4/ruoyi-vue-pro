package cn.iocoder.yudao.module.rela.dal.dataobject.people;

import cn.iocoder.yudao.module.system.enums.common.SexEnum;
import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 人物 DO
 *
 * @author 芋道源码
 */
@TableName("biz_people")
@KeySequence("biz_people_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleDO extends BaseDO {

    /**
     * 人物编号
     */
    @TableId
    private Long id;
    /**
     * 人物名称
     */
    private String name;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 常用地址
     */
    private String address;
    /**
     * 性别
     */
    private SexEnum sex;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 主要头衔
     */
    private String title;
    /**
     * 户籍
     */
    private String census;
    /**
     * 小名、曾用名
     */
    private String alias;
    /**
     * 主要邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;


}