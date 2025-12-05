package cn.iocoder.yudao.module.biz.service.people;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.biz.controller.admin.people.vo.*;
import cn.iocoder.yudao.module.biz.dal.dataobject.people.PeopleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 人物 Service 接口
 *
 * @author 芋道源码
 */
public interface PeopleService {

    /**
     * 创建人物
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPeople(@Valid PeopleSaveReqVO createReqVO);

    /**
     * 更新人物
     *
     * @param updateReqVO 更新信息
     */
    void updatePeople(@Valid PeopleSaveReqVO updateReqVO);

    /**
     * 删除人物
     *
     * @param id 编号
     */
    void deletePeople(Long id);

    /**
    * 批量删除人物
    *
    * @param ids 编号
    */
    void deletePeopleListByIds(List<Long> ids);

    /**
     * 获得人物
     *
     * @param id 编号
     * @return 人物
     */
    PeopleDO getPeople(Long id);

    /**
     * 获得人物分页
     *
     * @param pageReqVO 分页查询
     * @return 人物分页
     */
    PageResult<PeopleDO> getPeoplePage(PeoplePageReqVO pageReqVO);

}