package com.duanzm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * @Target(ElementType.TYPE)
 *      ElementType 这个枚举类型的常量提供了一个简单的分类：注释可能出现在Java程序中的语法位置
 */
/**
 * @Retention(RetentionPolicy.RUNTIME)
 *      RetentionPolicy 这个枚举类型的常量描述保留注释的各种策略，它们与元注释 @Retention 一起指定注释要保留多长时间
 */
/**
 * @Documented
 *      Document注解表明这个注解是由javadoc记录的，在默认情况下也有类似的记录工具。如果一个类型声明被注解了文档话，它的解释成为了公众API的一部分。
 */
/**
 * @Inherited   元注解，就是用来声明注解类型时需要使用到的注解
 *      使用此注声明出来的自定义注解，在使用此自定义注解时，如果注解在类上面，子类会自动继承此注解，否则的话，子类不会继承此注解。只有在类上才会有效，对方法，属性等其他无效
 */
/**
 * @SpringBootConfiguration
 *      1、@SpringBootConfiguration 继承自 @Configuration，二者功能也一致，标注当前类是配置类，
 *   并会将当前类内声明的一个或多个以@Bean注解标记的方法的实例纳入到Spring容器中，并且实例名就是方法名。
 */
/**
 * @EnableAutoConfiguration
 *      1、@EnableAutoConfiguration 简单概括下就是借助@Import的支持，将所有符合自动配置条件的bean定义加载到IoC容器。
 *      2、@EnableAutoConfiguration 作为一个符合Annotation，其中最关键的要属@Import(AutoConfigurationImportSelector.class)，
 *   借助 AuroConfigurationImportSelector,@EnableAutoConfiguration 可以帮助SpringBoot应用将所有符合条件的@Configuration配置都加载到当前SpringBoot创建并使用的IoC容器。
 *      3、AuroConfigurationImportSelector 中有一个 SpringFactoriesLoader,其主要功能就是从指定的配置文件META-INF/spirng.factories加载配置。配合 @EnableAutoConfiguration
 *   使用的话，它更多是提供一种配置查找的功能支持，即根据@EnableAutoConfiguration的完整类名 org.springframework.boot.autoconfigure.EnableAutoConfiiguration作为查找的Key，
 *   获取对应的一组 @Configuration 类。
 *      4、所以，@EnableAutoConfigurate 自动配置就变成了：从classpath中搜寻所有的META-INF/spring.facorties配置文件，并将其中 org.springframework.boot.autoconfigurate.EnableAutoConfiguration
 *   对应的配置项通过反射实例化为对应的标注了 @Configuration的JavaConfig 形式的IoC 容器配置类，然后汇总为一个并加载到IoC容器。
 */
/**
 * @ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
 *                @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
 *      1、@ComponentScan这个注解在Spring中很重要，它对应XML配置中的元素，@ComponentScan的功能其实就是自动扫描并加载符合条件的组件，比如（
 *   @Component和@Repository等）或者bean定义，最终将这些bean定义加载到 IOC容器中。
 *      2、我们可以通过basePackages等属性来细粒度的定制@ComponentScan自动扫描的范围，如果不指定，则默认Spring框架实现会从声明@Component
 *   所在类的package进行扫描，所以SpringBoot的启动累最好放在root package下，因为默认不指定basePackages.
 *      3、@ComponentScan告诉Spring哪个packages的用注解标识的类会被Spring自动扫描并且装入bean容器。
 */
public class SpringAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAdminApplication.class, args);
    }

}
