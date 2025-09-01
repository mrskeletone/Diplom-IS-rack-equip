package com.example.mes_opcua;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;

@Configuration
public class OpcUAConfig {
    @Bean
    public OpcUaClient opcUaClient() throws Exception {
        String endpointUrl = "opc.tcp://LAPTOP-CTICSKLE:53530/OPCUA/SimulationServer";
        OpcUaClient client= OpcUaClient.create(
                endpointUrl,
                endpoints -> endpoints.stream()
                        .filter(e -> e.getSecurityPolicyUri().equals(SecurityPolicy.None.getUri()))
                        .findFirst(),
                configBuilder -> configBuilder
                        .setIdentityProvider(new AnonymousProvider())
                        .setRequestTimeout(UInteger.valueOf(5000)).build()
        );
        client.connect();
        return client;
    }

}
