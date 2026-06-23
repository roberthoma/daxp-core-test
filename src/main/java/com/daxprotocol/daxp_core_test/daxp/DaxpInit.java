package com.daxprotocol.daxp_core_test.daxp;
import com.daxprotocol.daxp_core_test.api.DaxpCrmController;
import jakarta.annotation.PostConstruct;
import org.daxprotocol.core.annotation.DaxpCollection;
import org.daxprotocol.core.annotation.DaxpController;
import org.daxprotocol.core.annotation.DaxpEntity;
import org.daxprotocol.core.annotation.DaxpRegister;
import org.daxprotocol.core.application.DaxEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class DaxpInit {

    //@Autowired
    DaxEngine daxEngine;
    ApplicationContext appContext;

    @Autowired
    public DaxpInit(DaxEngine daxEngine, ApplicationContext appContext){
        this.daxEngine = daxEngine;
        this.appContext = appContext;
    }

    @PostConstruct
    public void daxpInitScanner(){

        try {
            ClassPathScanningCandidateComponentProvider scanner =
                    new ClassPathScanningCandidateComponentProvider(false);

            //Add a filter for your annotation
            scanner.addIncludeFilter(new AnnotationTypeFilter(DaxpRegister.class));
            scanner.addIncludeFilter(new AnnotationTypeFilter(DaxpEntity.class));
            scanner.addIncludeFilter(new AnnotationTypeFilter(DaxpCollection.class));

            scanner.addIncludeFilter(new AnnotationTypeFilter(DaxpController.class));


            DaxpCrmController  daxCtrl = appContext.getBean(DaxpCrmController.class);
            daxEngine.getHandlerRegistry().registerCtrl(daxCtrl);

            //Define the base package(s) to scan
            String basePackage = "com.daxprotocol.daxp_core_test";

            Set<BeanDefinition> beans = scanner.findCandidateComponents(basePackage);

            //Iterate over found classes
            for (BeanDefinition beanDef : beans) {
                String className = beanDef.getBeanClassName();
                // Optionally load class and inspect
                Class<?> clazz = Class.forName(className);
                daxEngine.register(clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
