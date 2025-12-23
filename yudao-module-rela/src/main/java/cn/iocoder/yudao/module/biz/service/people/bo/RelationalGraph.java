package cn.iocoder.yudao.module.biz.service.people.bo;

import lombok.Data;

import java.util.List;

@Data
public class RelationalGraph {
    private final List<Person> persons;
    private final List<Relation> relations;
    private final List<RelationType> relaTypes;
}
