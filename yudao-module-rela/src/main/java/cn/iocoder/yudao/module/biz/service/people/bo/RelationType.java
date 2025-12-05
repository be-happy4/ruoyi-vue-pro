package cn.iocoder.yudao.module.biz.service.people.bo;

import java.util.List;

public record RelationType(
        Long id,
        String name,
        List<String> aliases
) {
    public RelationType(String name, String... aliases) {
        this(null, name, List.of(aliases));
    }

    // ========== 直系一代（配偶、父母、子女）==========
    public static final RelationType HUSBAND = new RelationType("丈夫", "老公", "先生", "当家的");
    public static final RelationType WIFE = new RelationType("妻子", "老婆", "媳妇", "内人", "太太");
    public static final RelationType FATHER = new RelationType("父亲", "爸爸", "爹", "阿爸", "老爷子", "爹爹");
    public static final RelationType MOTHER = new RelationType("母亲", "妈妈", "娘", "阿妈", "老太太", "娘娘");
    public static final RelationType SON = new RelationType("儿子", "仔", "犬子");
    public static final RelationType DAUGHTER = new RelationType("女儿", "闺女", "千金");

    // ========== 兄弟姐妹（平辈）==========
    public static final RelationType BROTHER_OLDER = new RelationType("哥哥", "大哥", "阿哥", "兄长");
    public static final RelationType BROTHER_YOUNGER = new RelationType("弟弟", "小弟", "阿弟", "舍弟");
    public static final RelationType SISTER_OLDER = new RelationType("姐姐", "大姐", "阿姐", "家姐");
    public static final RelationType SISTER_YOUNGER = new RelationType("妹妹", "小妹", "阿妹", "舍妹");

    // ========== 直系二代（祖父母、外祖父母、孙辈）==========
    public static final RelationType GRANDFATHER_PATERNAL = new RelationType("祖父", "爷爷", "阿公", "公公", "王父");
    public static final RelationType GRANDMOTHER_PATERNAL = new RelationType("祖母", "奶奶", "阿嬷", "婆婆", "王母");
    public static final RelationType GRANDFATHER_MATERNAL = new RelationType("外祖父", "外公", "姥爷", "外爷", "外王父");
    public static final RelationType GRANDMOTHER_MATERNAL = new RelationType("外祖母", "外婆", "姥姥", "姥婆", "外王母");

    public static final RelationType GRANDSON_PATERNAL = new RelationType("孙子");
    public static final RelationType GRANDDAUGHTER_PATERNAL = new RelationType("孙女");
    public static final RelationType GRANDSON_MATERNAL = new RelationType("外孙", "外孙子");
    public static final RelationType GRANDDAUGHTER_MATERNAL = new RelationType("外孙女");

    // ========== 直系三代（曾祖、曾孙）==========
    public static final RelationType GREAT_GRANDFATHER_PATERNAL = new RelationType("曾祖父", "太公", "太爷爷", "祖爷爷", "太王父");
    public static final RelationType GREAT_GRANDMOTHER_PATERNAL = new RelationType("曾祖母", "太婆", "太奶奶", "祖奶奶", "太王母");
    public static final RelationType GREAT_GRANDFATHER_MATERNAL = new RelationType("外曾祖父", "太姥爷", "外太公");
    public static final RelationType GREAT_GRANDMOTHER_MATERNAL = new RelationType("外曾祖母", "太姥姥", "外太婆");

    public static final RelationType GREAT_GRANDSON_PATERNAL = new RelationType("曾孙");
    public static final RelationType GREAT_GRANDDAUGHTER_PATERNAL = new RelationType("曾孙女");
    public static final RelationType GREAT_GRANDSON_MATERNAL = new RelationType("外曾孙");
    public static final RelationType GREAT_GRANDDAUGHTER_MATERNAL = new RelationType("外曾孙女");

    // ========== 直系四代（高祖、玄孙）==========
    public static final RelationType HIGH_GRANDFATHER_PATERNAL = new RelationType("高祖父", "高祖爷爷", "烈祖", "高王父");
    public static final RelationType HIGH_GRANDMOTHER_PATERNAL = new RelationType("高祖母", "高祖奶奶", "烈祖母", "高王母");
    public static final RelationType HIGH_GRANDFATHER_MATERNAL = new RelationType("外高祖父", "外高祖");
    public static final RelationType HIGH_GRANDMOTHER_MATERNAL = new RelationType("外高祖母");

    public static final RelationType HIGH_GRANDSON_PATERNAL = new RelationType("玄孙");
    public static final RelationType HIGH_GRANDDAUGHTER_PATERNAL = new RelationType("玄孙女");
    public static final RelationType HIGH_GRANDSON_MATERNAL = new RelationType("外玄孙");
    public static final RelationType HIGH_GRANDDAUGHTER_MATERNAL = new RelationType("外玄孙女");

    // ========== 直系五代（天祖、来孙）==========
    public static final RelationType HEAVENLY_GRANDFATHER = new RelationType("天祖父", "天祖", "鼻祖"); // 鼻祖为始祖，此处作五世祖
    public static final RelationType HEAVENLY_GRANDMOTHER = new RelationType("天祖母");
    public static final RelationType COMING_GRANDSON = new RelationType("来孙"); // 玄孙之子
    public static final RelationType COMING_GRANDDAUGHTER = new RelationType("来孙女");

    // ========== 姻亲（配偶方 & 子女配偶）==========
    public static final RelationType FATHER_IN_LAW_HUSBAND_SIDE = new RelationType("公公");
    public static final RelationType MOTHER_IN_LAW_HUSBAND_SIDE = new RelationType("婆婆");
    public static final RelationType FATHER_IN_LAW_WIFE_SIDE = new RelationType("岳父", "岳丈", "老丈人", "丈人", "泰山");
    public static final RelationType MOTHER_IN_LAW_WIFE_SIDE = new RelationType("岳母", "丈母娘", "泰水");

    public static final RelationType DAUGHTER_IN_LAW = new RelationType("儿媳", "媳妇");
    public static final RelationType SON_IN_LAW = new RelationType("女婿", "快婿");

    // ========== 配偶的兄弟姐妹（连襟、妯娌等）==========
    public static final RelationType HUSBANDS_BROTHER = new RelationType("大伯子", "小叔子"); // 统称“夫兄/夫弟”，口语分长幼
    public static final RelationType HUSBANDS_SISTER = new RelationType("大姑子", "小姑子"); // “夫姐/夫妹”
    public static final RelationType WIFES_BROTHER = new RelationType("内兄", "内弟", "舅子", "妻兄", "妻弟");
    public static final RelationType WIFES_SISTER = new RelationType("姨妹", "姨姐", "姨子"); // 较少用，常称“小姨子”

    public static final RelationType CO_BROTHER_IN_LAW_MALE = new RelationType("连襟"); // 妻姐妹之夫互称
    public static final RelationType CO_SISTER_IN_LAW_FEMALE = new RelationType("妯娌"); // 夫兄弟之妻互称

    // ========== 父系旁系长辈（伯叔姑及其配偶）==========
    public static final RelationType FATHERS_ELDER_BROTHER = new RelationType("伯父", "伯伯", "大伯", "大爷", "世父");
    public static final RelationType FATHERS_ELDER_BROTHER_WIFE = new RelationType("伯母", "大娘", "姆妈");
    public static final RelationType FATHERS_YOUNGER_BROTHER = new RelationType("叔父", "叔叔", "小叔", "阿叔", "叔父");
    public static final RelationType FATHERS_YOUNGER_BROTHER_WIFE = new RelationType("婶母", "婶婶", "叔母", "婶娘");
    public static final RelationType FATHERS_SISTER = new RelationType("姑母", "姑姑", "姑妈", "姑娘", "从母");
    public static final RelationType FATHERS_SISTER_HUSBAND = new RelationType("姑父", "姑丈", "姑爷", "姑婿");

    // ========== 母系旁系长辈（舅姨及其配偶）==========
    public static final RelationType MOTHERS_BROTHER = new RelationType("舅父", "舅舅", "舅爷", "舅氏");
    public static final RelationType MOTHERS_BROTHER_WIFE = new RelationType("舅母", "舅妈", "妗子", "舅婆");
    public static final RelationType MOTHERS_SISTER = new RelationType("姨母", "姨妈", "阿姨", "姨娘", "从母");
    public static final RelationType MOTHERS_SISTER_HUSBAND = new RelationType("姨父", "姨丈", "姨婿");

    // ========== 侄甥（兄弟姐妹的子女）==========
    public static final RelationType NEPHEW_BROTHERS_SON = new RelationType("侄子", "侄儿");
    public static final RelationType NIECE_BROTHERS_DAUGHTER = new RelationType("侄女");
    public static final RelationType NEPHEW_SISTERS_SON = new RelationType("外甥", "外甥儿");
    public static final RelationType NIECE_SISTERS_DAUGHTER = new RelationType("外甥女");

    // ========== 堂表亲（同辈旁系）==========
    // 堂亲（父系兄弟子女）
    public static final RelationType COUSIN_MALE_PATERNAL = new RelationType("堂兄", "堂哥", "堂弟", "同堂兄弟");
    public static final RelationType COUSIN_FEMALE_PATERNAL = new RelationType("堂姐", "堂妹", "同堂姐妹");

    // 表亲（父系姐妹 / 母系兄弟姐妹子女）
    public static final RelationType COUSIN_MALE_MATERNAL = new RelationType("表兄", "表哥", "表弟", "老表", "姨表兄弟", "舅表兄弟");
    public static final RelationType COUSIN_FEMALE_MATERNAL = new RelationType("表姐", "表妹", "表姑娘", "姨表姐妹", "舅表姐妹");

    // ========== 远房旁系（三服以外）==========
    public static final RelationType CLAN_UNCLE_ELDER = new RelationType("族伯", "族叔"); // 同宗共高祖以上之长辈
    public static final RelationType CLAN_COUSIN = new RelationType("族兄", "族弟", "族姐", "族妹"); // 同宗不同房
    public static final RelationType SECOND_COUSIN_MALE = new RelationType("再从兄弟", "从堂兄弟"); // 共高祖
    public static final RelationType SECOND_COUSIN_FEMALE = new RelationType("再从姐妹", "从堂姐妹");

    // ========== 特殊/古称（用于文献或传统场景）==========
    public static final RelationType PATERNAL_GRANDAUNT = new RelationType("姑祖母", "姑婆", "祖姑");
    public static final RelationType PATERNAL_GRANDUNCLE = new RelationType("伯祖父", "叔祖父", "祖伯", "祖叔");
    public static final RelationType MATERNAL_GRANDUNCLE = new RelationType("舅祖父", "舅公");
    public static final RelationType MATERNAL_GRANDAUNT = new RelationType("姨祖母", "姨婆");

    // 孙辈延伸
    public static final RelationType GRANDNEPHEW = new RelationType("侄孙", "甥孙");
    public static final RelationType GRANDNIECE = new RelationType("侄孙女", "甥孙女");

    // ========== 极少数但存在的姻亲扩展 ==========
    public static final RelationType DAUGHTER_IN_LAWS_FATHER = new RelationType("亲家公");
    public static final RelationType DAUGHTER_IN_LAWS_MOTHER = new RelationType("亲家母");
}