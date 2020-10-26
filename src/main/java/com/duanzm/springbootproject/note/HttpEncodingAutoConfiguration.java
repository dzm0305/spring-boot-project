package com.duanzm.springbootproject.note;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * jar包中autoconfigure下 META-INF/spring.factories的 org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration 示例
 * 根据当前不同的条件判断，决定这个配置类是否生效？
 * 一但这个配置类生效；这个配置类就会给容器中添加各种组件；这些组件的属性是从对应的properties类中获取的，这些类里面的每一个属性又是和配置文件绑定的
 */
@Configuration(proxyBeanMethods = false)
    /**
     * proxyBeanMethods配置类是用来指定@Bean注解标注的方法是否使用代理。
     *  默认值是true，该配置类会使用代理（CGLIB），在同一个配置文件中调用其他被@Bean注解标注的方法获取对象时会直接从IOC容器中获取；
     *  如果设置为false，也就是不使用注解，每次调用@Bean标注的方法获取到的对象和IOC容器中都不一样，是一个新的对象，所以我们可以将此属性设置为false来提高性能；
     */
@EnableConfigurationProperties(ServerProperties.class)
    /**
     *     之前（person.java）用到的ConfigurationProperties注解，主要是用来把properties或者yml配置文件转换为bean来使用的，
     *     而@EnableConfigurationProperties注解的作用是@ConfigurationProperties注解生效。
     *     如果只配置 @ConfigurationProperties注解，在IOC容器中是获取不到properties配置文件转化的bean的，
     * 当然在@ConfigurationProperties加入注解的类上加@Component也可以使交与springboot管理
     */
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    /**
     * 如果想要当前配置文件生效，必须有servlet的运行环境
     */
@ConditionalOnClass(CharacterEncodingFilter.class)
    /**
     *  如果想要当前配置文件生效，必须有 CharacterEncodingFilter 这个类
     */
@ConditionalOnProperty(prefix = "server.servlet.encoding", value = "enabled", matchIfMissing = true)
    /**
     *  如果配置了 server.servlet.encoding 为false，条件不成立，配置不生效。默认为true
     */
public class HttpEncodingAutoConfiguration {

    //已经和SpringBoot的配置文件映射
    private final Encoding properties;

    //只有一个有参构造器的情况下，参数的值就会从容器中拿
    public HttpEncodingAutoConfiguration(ServerProperties properties) {
        this.properties = properties.getServlet().getEncoding();
    }

    @Bean   //给容器中添加一个组件，这个组件的某些值需要从properties中获取
    @ConditionalOnMissingBean
        /**
         *  它是修饰bean的一个注解，主要实现的是，当你的bean被注册之后，如果而注册相同类型的bean，就不会成功，它会保证你的bean只有一个，即你的实例只有一个，
         *  当你注册多个相同的bean时，会出现异常
         */
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding(this.properties.getCharset().name());
        filter.setForceRequestEncoding(this.properties.shouldForce(Encoding.Type.REQUEST));
        filter.setForceResponseEncoding(this.properties.shouldForce(Encoding.Type.RESPONSE));
        return filter;
    }

    /*@Bean
    public org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration.LocaleCharsetMappingsCustomizer localeCharsetMappingsCustomizer() {
        return new org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration.LocaleCharsetMappingsCustomizer(this.properties);
    }*/

    static class LocaleCharsetMappingsCustomizer
            implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>, Ordered {

        private final Encoding properties;

        LocaleCharsetMappingsCustomizer(Encoding properties) {
            this.properties = properties;
        }

        @Override
        public void customize(ConfigurableServletWebServerFactory factory) {
            if (this.properties.getMapping() != null) {
                factory.setLocaleCharsetMappings(this.properties.getMapping());
            }
        }

        @Override
        public int getOrder() {
            return 0;
        }

    }
}