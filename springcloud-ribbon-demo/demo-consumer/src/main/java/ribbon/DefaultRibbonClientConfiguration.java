package ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认全局负载均衡策略
 * 不放在启动类包或子包，是不让spring扫描到。
 *
 * @author wangchen
 * @createDate 2020/08/18
 */
@Configuration
public class DefaultRibbonClientConfiguration {

    @Bean
    public IRule ribbonDefaultRule(){
        return new RoundRobinRule();
    }
}
