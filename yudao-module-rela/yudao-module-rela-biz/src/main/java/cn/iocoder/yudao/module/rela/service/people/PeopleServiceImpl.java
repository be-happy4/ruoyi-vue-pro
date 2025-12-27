package cn.iocoder.yudao.module.rela.service.people;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.rela.controller.admin.people.vo.PeoplePageReqVO;
import cn.iocoder.yudao.module.rela.controller.admin.people.vo.PeopleSaveReqVO;
import cn.iocoder.yudao.module.rela.dal.dataobject.people.PeopleDO;
import cn.iocoder.yudao.module.rela.dal.mysql.people.PeopleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.PEOPLE_NOT_EXISTS;

/**
 * 人物 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PeopleServiceImpl implements PeopleService {

    @Resource
    private PeopleMapper peopleMapper;

    @Override
    public Long createPeople(PeopleSaveReqVO createReqVO) {
        // 插入
        PeopleDO people = BeanUtils.toBean(createReqVO, PeopleDO.class);
        peopleMapper.insert(people);

        // 返回
        return people.getId();
    }

    @Override
    public void updatePeople(PeopleSaveReqVO updateReqVO) {
        // 校验存在
        validatePeopleExists(updateReqVO.getId());
        // 更新
        PeopleDO updateObj = BeanUtils.toBean(updateReqVO, PeopleDO.class);
        peopleMapper.updateById(updateObj);
    }

    @Override
    public void deletePeople(Long id) {
        // 校验存在
        validatePeopleExists(id);
        // 删除
        peopleMapper.deleteById(id);
    }

    @Override
    public void deletePeopleListByIds(List<Long> ids) {
        // 删除
        peopleMapper.deleteByIds(ids);
    }


    private void validatePeopleExists(Long id) {
        if (peopleMapper.selectById(id) == null) {
            throw exception(PEOPLE_NOT_EXISTS);
        }
    }

    @Override
    public PeopleDO getPeople(Long id) {
        return peopleMapper.selectById(id);
    }

    @Override
    public PageResult<PeopleDO> getPeoplePage(PeoplePageReqVO pageReqVO) {
        return peopleMapper.selectPage(pageReqVO);
    }

}