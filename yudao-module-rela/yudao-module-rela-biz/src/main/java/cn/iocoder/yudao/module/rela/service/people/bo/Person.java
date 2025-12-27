package cn.iocoder.yudao.module.rela.service.people.bo;

import cn.iocoder.yudao.module.rela.service.people.enums.Sex;
import cn.iocoder.yudao.module.rela.service.people.util.FuzzyDate;

import java.util.Comparator;


public class Person {
    private final Long id;
    private final String name;
    private final Sex sex;
    private final FuzzyDate birthDay;

    public Person(String name, Sex sex, FuzzyDate birthDay) {
        this.id = null;
        this.name = name;
        this.sex = sex;
        this.birthDay = birthDay;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Sex getSex() { return sex; }
    public FuzzyDate getBirthDay() { return birthDay; }

    static final Comparator<Person> AGE_COMPARATOR = Comparator.comparing(
            Person::getBirthDay,
            Comparator.nullsLast(Comparator.naturalOrder()));

    public int compareAge(Person other) {
        return AGE_COMPARATOR.compare(this, other);
    }
}
