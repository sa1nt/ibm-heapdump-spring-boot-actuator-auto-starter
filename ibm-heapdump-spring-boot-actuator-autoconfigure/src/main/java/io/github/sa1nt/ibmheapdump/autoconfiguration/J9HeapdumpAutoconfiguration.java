package io.github.sa1nt.ibmheapdump.autoconfiguration;

import io.github.sa1nt.ibmheapdump.J9HeapdumpMvcEndpoint;
import org.springframework.boot.actuate.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(J9HeapdumpMvcEndpoint.class)
@ConditionalOnEnabledEndpoint("heapdump")
public class J9HeapdumpAutoconfiguration {

    @Bean
    @ConditionalOnMissingBean
    public J9HeapdumpMvcEndpoint heapdumpMvcEndpoint() {
        return new J9HeapdumpMvcEndpoint();
    }
}
