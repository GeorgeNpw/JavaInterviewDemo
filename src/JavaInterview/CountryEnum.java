package JavaInterview;

/**
 * @author npw
 * @date 2019/5/13
 * 枚举类
 */

public enum  CountryEnum {

    ONE(1,"齐"), TWO(2,"楚"), THREE(3,"燕"),
    FOUR(4,"赵"), FIVE(5,"魏"), SIX(6,"韩");

    //使用Lombok注解自动生成get方法
    //@Getter private Integer retCode;
    //@Getter private String retMessage;
    private Integer retCode;
    private String retMessage;

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum foreach_CountryEnum(int index){
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum element : myArray){
            if (index == element.getRetCode()){
                return element;
            }
        }

        return null;
    }

}

/**
 * mysql dbName = CountryEnum
 * table ONE
 * ID userName age birth userEmail
 * 1   齐      109
 * ONE (1, "齐", v2, v3, v4, v5)
 *
 */
