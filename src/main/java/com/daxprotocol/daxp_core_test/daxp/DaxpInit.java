package com.daxprotocol.daxp_core_test.daxp;
import org.daxprotocol.core.annotation.DaxpDic;
import org.daxprotocol.core.dictionary.DaxDictionaryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class DaxpInit {

    @Autowired
    AppDaxDictionary dic;

    @Autowired
    DaxDictionaryManager daxDicManager;

    public void daxpInitScanner(){

        try {
            ClassPathScanningCandidateComponentProvider scanner =
                    new ClassPathScanningCandidateComponentProvider(false);

            //Add a filter for your annotation
            scanner.addIncludeFilter(new AnnotationTypeFilter(DaxpDic.class));

            //Define the base package(s) to scan
            String basePackage = "com.daxprotocol.daxp_core_test";

            Set<BeanDefinition> beans = scanner.findCandidateComponents(basePackage);

            //Iterate over found classes
            for (BeanDefinition beanDef : beans) {
                String className = beanDef.getBeanClassName();
   //             System.out.println("Found annotated class: " + className);

                // Optionally load class and inspect
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(DaxpDic.class)) {
//                    System.out.println("Annotation value: " +
//                            clazz.getAnnotation(DaxpDic.class).name());
                    daxDicManager.populateFromAnnotations(dic,clazz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
