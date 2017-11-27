package io.syndesis.connector.salesforce.springboot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.annotation.PostConstruct;
import io.syndesis.connector.salesforce.SalesforceDeleteSObjectWithIdComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.component.connector.ConnectorCustomizer;
import org.apache.camel.spi.HasId;
import org.apache.camel.spring.boot.util.CamelPropertiesHelper;
import org.apache.camel.spring.boot.util.HierarchicalPropertiesEvaluator;
import org.apache.camel.util.IntrospectionSupport;
import org.apache.camel.util.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Generated by camel-connector-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.maven.connector.SpringBootAutoConfigurationMojo")
@Configuration
@ConditionalOnBean(type = "org.apache.camel.spring.boot.CamelAutoConfiguration")
@AutoConfigureAfter(name = "org.apache.camel.spring.boot.CamelAutoConfiguration")
@EnableConfigurationProperties(SalesforceDeleteSObjectWithIdConnectorConfiguration.class)
public class SalesforceDeleteSObjectWithIdConnectorAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(SalesforceDeleteSObjectWithIdConnectorAutoConfiguration.class);
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private CamelContext camelContext;
    @Autowired
    private SalesforceDeleteSObjectWithIdConnectorConfiguration configuration;
    @Autowired(required = false)
    private List<ConnectorCustomizer<SalesforceDeleteSObjectWithIdComponent>> customizers;

    @Lazy
    @Bean(name = "salesforce-delete-sobject-with-id-component")
    @ConditionalOnClass(CamelContext.class)
    @ConditionalOnMissingBean
    public SalesforceDeleteSObjectWithIdComponent configureSalesforceDeleteSObjectWithIdComponent()
            throws Exception {
        SalesforceDeleteSObjectWithIdComponent connector = new SalesforceDeleteSObjectWithIdComponent();
        connector.setCamelContext(camelContext);
        Map<String, Object> parameters = new HashMap<>();
        IntrospectionSupport.getProperties(configuration, parameters, null,
                false);
        CamelPropertiesHelper.setCamelProperties(camelContext, connector,
                parameters, false);
        connector.setOptions(parameters);
        if (ObjectHelper.isNotEmpty(customizers)) {
            for (ConnectorCustomizer<SalesforceDeleteSObjectWithIdComponent> customizer : customizers) {
                boolean useCustomizer = (customizer instanceof HasId)
                        ? HierarchicalPropertiesEvaluator
                                .evaluate(
                                        applicationContext.getEnvironment(),
                                        "camel.connector.customizer",
                                        "camel.connector.salesforce-delete-sobject-with-id.customizer",
                                        ((HasId) customizer).getId())
                        : HierarchicalPropertiesEvaluator
                                .evaluate(applicationContext.getEnvironment(),
                                        "camel.connector.customizer",
                                        "camel.connector.salesforce-delete-sobject-with-id.customizer");
                if (useCustomizer) {
                    LOGGER.debug("Configure connector {}, with customizer {}",
                            connector, customizer);
                    customizer.customize(connector);
                }
            }
        }
        return connector;
    }

    @PostConstruct
    public void postConstructSalesforceDeleteSObjectWithIdComponent() {
        Map<String, Object> parameters = new HashMap<>();
        for (Map.Entry<String, SalesforceDeleteSObjectWithIdConnectorConfigurationCommon> entry : configuration
                .getConfigurations().entrySet()) {
            parameters.clear();
            SalesforceDeleteSObjectWithIdComponent connector = new SalesforceDeleteSObjectWithIdComponent(
                    entry.getKey());
            connector.setCamelContext(camelContext);
            try {
                IntrospectionSupport.getProperties(entry.getValue(),
                        parameters, null, false);
                CamelPropertiesHelper.setCamelProperties(camelContext,
                        connector, parameters, false);
                connector.setOptions(parameters);
                if (ObjectHelper.isNotEmpty(customizers)) {
                    for (ConnectorCustomizer<SalesforceDeleteSObjectWithIdComponent> customizer : customizers) {
                        boolean useCustomizer = (customizer instanceof HasId)
                                ? HierarchicalPropertiesEvaluator.evaluate(
                                        applicationContext.getEnvironment(),
                                        "camel.connector.customizer",
                                        "camel.connector.salesforce-delete-sobject-with-id."
                                                + entry.getKey()
                                                + ".customizer",
                                        ((HasId) customizer).getId())
                                : HierarchicalPropertiesEvaluator.evaluate(
                                        applicationContext.getEnvironment(),
                                        "camel.connector.customizer",
                                        "camel.connector.salesforce-delete-sobject-with-id."
                                                + entry.getKey()
                                                + ".customizer");
                        if (useCustomizer) {
                            LOGGER.debug(
                                    "Configure connector {}, with customizer {}",
                                    connector, customizer);
                            customizer.customize(connector);
                        }
                    }
                }
                camelContext.addComponent(entry.getKey(), connector);
            } catch (Exception e) {
                throw new BeanCreationException(entry.getKey(), e.getMessage(),
                        e);
            }
        }
    }
}