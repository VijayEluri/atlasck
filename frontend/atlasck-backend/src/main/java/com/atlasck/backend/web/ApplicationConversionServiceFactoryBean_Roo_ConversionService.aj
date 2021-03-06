// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.atlasck.backend.web;

import com.atlasck.backend.domain.Answers;
import com.atlasck.backend.domain.Questions;
import com.atlasck.backend.domain.Visitors;
import java.lang.String;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(new AnswersConverter());
        registry.addConverter(new QuestionsConverter());
        registry.addConverter(new VisitorsConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
    static class com.atlasck.backend.web.ApplicationConversionServiceFactoryBean.AnswersConverter implements Converter<Answers, String> {
        public String convert(Answers answers) {
            return new StringBuilder().append(answers.getCreatedAt()).append(" ").append(answers.getUpdatedAt()).append(" ").append(answers.getAnswer()).toString();
        }
        
    }
    
    static class com.atlasck.backend.web.ApplicationConversionServiceFactoryBean.QuestionsConverter implements Converter<Questions, String> {
        public String convert(Questions questions) {
            return new StringBuilder().append(questions.getTitle()).append(" ").append(questions.getCreatedAt()).append(" ").append(questions.getUpdatedAt()).append(" ").append(questions.getQuestion()).toString();
        }
        
    }
    
    static class com.atlasck.backend.web.ApplicationConversionServiceFactoryBean.VisitorsConverter implements Converter<Visitors, String> {
        public String convert(Visitors visitors) {
            return new StringBuilder().append(visitors.getNickname()).append(" ").append(visitors.getEmail()).append(" ").append(visitors.getIpAddress()).append(" ").append(visitors.getCreatedAt()).toString();
        }
        
    }
    
}
