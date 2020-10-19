package com.duanzm.springbootproject.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component //注册bean
@ConfigurationProperties(prefix = "person")
/**
 * @ConfigurationProperties 作用：
 *      将配置文件中配置的每一个属性的值，映射到这个组件中；
 *      告诉SpringBoot 将本类中的所有属性和配置文件中相关的配置进行绑定，
 *      参数 prefix = "person" :将配置文件中的dog下面的所有属性一一对应
 *      只有这个组件容器的组件，才能使用容器提供的 @ConfigurationProperties
 */

/**
 *                              @ConfigurationProperties                @value
 *  功能                             批量注入配置文件中的属性                一个个指定
 *  松散语法（-后面的首字母大写）             支持                             不支持
 *  SpEl @Validated                     不支持                             支持
 *  JSR303数据校验                        支持                             不支持
 *  复杂类型封装                        ta cc   支持                             不支持
 */
@Validated  //在pom文件中引入相关依赖
public class Person {

    //@Value("xiaohong")
    //@Email(message = "必须是邮箱格式") //自定义报错信息
    private String name;
    private Integer age;
    private Boolean happy;
    private Date birth;
    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;

    public Person() {
    }

    public Person(String name, Integer age, Boolean happy, Date birth, Map<String, Object> maps, List<Object> lists, Dog dog) {
        this.name = name;
        this.age = age;
        this.happy = happy;
        this.birth = birth;
        this.maps = maps;
        this.lists = lists;
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getHappy() {
        return happy;
    }

    public Date getBirth() {
        return birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setHappy(Boolean happy) {
        this.happy = happy;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", happy=" + happy +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
