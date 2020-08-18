package ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 客户端级别的 负载均衡策略
 *
 * @author wangchen
 * @createDate 2020/08/18
 */
@Configuration
public class DemoProviderRibbonClientConfiguration {

    @Bean
    @Primary
    public IRule ribbonCustomerRule(){
        return new RandomRule();
    }
}
