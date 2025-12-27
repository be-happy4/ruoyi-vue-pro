package cn.iocoder.yudao.module.rela.dal.mysql.people;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.rela.dal.dataobject.people.PeopleDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.rela.controller.admin.people.vo.*;

/**
 * 人物 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PeopleMapper extends BaseMapperX<PeopleDO> {

    default PageResult<PeopleDO> selectPage(PeoplePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PeopleDO>()
                .likeIfPresent(PeopleDO::getName, reqVO.getName())
                .eqIfPresent(PeopleDO::getPhoneNumber, reqVO.getPhoneNumber())
                .likeIfPresent(PeopleDO::getAddress, reqVO.getAddress())
                .eqIfPresent(PeopleDO::getSex, reqVO.getSex())
                .likeIfPresent(PeopleDO::getIdNumber, reqVO.getIdNumber())
                .likeIfPresent(PeopleDO::getTitle, reqVO.getTitle())
                .likeIfPresent(PeopleDO::getCensus, reqVO.getCensus())
                .likeIfPresent(PeopleDO::getAlias, reqVO.getAlias())
                .likeIfPresent(PeopleDO::getEmail, reqVO.getEmail())
                .betweenIfPresent(PeopleDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(PeopleDO::getDeleted, reqVO.getDeleted())
                .eqIfPresent(PeopleDO::getAvatar, reqVO.getAvatar())
                .orderByDesc(PeopleDO::getId));
    }

}