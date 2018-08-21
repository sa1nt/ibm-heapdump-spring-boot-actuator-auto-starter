package io.github.sa1nt.ibmheapdump.autoconfiguration;

import io.github.sa1nt.ibmheapdump.J9HeapdumpMvcEndpoint;
import org.springframework.boot.actuate.autoconfigure.ManagementContextConfiguration;
import org.springframework.boot.actuate.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.actuate.endpoint.mvc.HeapdumpMvcEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Configuration to inject a {@link J9HeapdumpMvcEndpoint} instead of the default {@link HeapdumpMvcEndpoint} if the
 * /heapdump Actuator endpoint is enabled.
 * <p>
 * Ordered to run with highest priority to override the default
 * {@link org.springframework.boot.actuate.autoconfigure.EndpointWebMvcManagementContextConfiguration}.
 * <p>
 * Will back off if a bean of type {@link HeapdumpMvcEndpoint} or {@link J9HeapdumpMvcEndpoint} is provided by user code.
 */
@ManagementContextConfiguration
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConditionalOnClass(J9HeapdumpMvcEndpoint.class)
@ConditionalOnEnabledEndpoint("heapdump")
public class J9HeapdumpAutoconfiguration {

    @Bean
    @ConditionalOnMissingBean({J9HeapdumpMvcEndpoint.class, HeapdumpMvcEndpoint.class})
    public HeapdumpMvcEndpoint heapdumpMvcEndpoint() {
        return new J9HeapdumpMvcEndpoint();
    }
}
