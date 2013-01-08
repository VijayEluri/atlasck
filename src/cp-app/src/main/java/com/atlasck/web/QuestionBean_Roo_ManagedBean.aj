// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.atlasck.web;

import com.atlasck.domain.Answer;
import com.atlasck.domain.Question;
import com.atlasck.domain.Visitor;
import com.atlasck.web.QuestionBean;
import com.atlasck.web.converter.VisitorConverter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.validator.LengthValidator;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.event.CloseEvent;

privileged aspect QuestionBean_Roo_ManagedBean {
    
    declare @type: QuestionBean: @ManagedBean(name = "questionBean");
    
    declare @type: QuestionBean: @SessionScoped;
    
    private String QuestionBean.name = "Questions";
    
    private HtmlPanelGrid QuestionBean.createPanelGrid;
    
    private HtmlPanelGrid QuestionBean.editPanelGrid;
    
    private HtmlPanelGrid QuestionBean.viewPanelGrid;
    
    private boolean QuestionBean.createDialogVisible = false;
    
    private List<Answer> QuestionBean.selectedAnswers;
    
    public String QuestionBean.getName() {
        return name;
    }
    
    public List<String> QuestionBean.getColumns() {
        return columns;
    }
    
    public List<Question> QuestionBean.getAllQuestions() {
        return allQuestions;
    }
    
    public void QuestionBean.setAllQuestions(List<Question> allQuestions) {
        this.allQuestions = allQuestions;
    }
    
    public boolean QuestionBean.isDataVisible() {
        return dataVisible;
    }
    
    public void QuestionBean.setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }
    
    public HtmlPanelGrid QuestionBean.getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }
    
    public void QuestionBean.setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }
    
    public HtmlPanelGrid QuestionBean.getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }
    
    public void QuestionBean.setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }
    
    public HtmlPanelGrid QuestionBean.getViewPanelGrid() {
        if (viewPanelGrid == null) {
            viewPanelGrid = populateViewPanel();
        }
        return viewPanelGrid;
    }
    
    public void QuestionBean.setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }
    
    public HtmlPanelGrid QuestionBean.populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText answersCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        answersCreateOutput.setId("answersCreateOutput");
        answersCreateOutput.setValue("Answers:   ");
        htmlPanelGrid.getChildren().add(answersCreateOutput);
        
        HtmlOutputText answersCreateInput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        answersCreateInput.setId("answersCreateInput");
        answersCreateInput.setValue("This relationship is managed from the Answer side");
        htmlPanelGrid.getChildren().add(answersCreateInput);
        
        Message answersCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        answersCreateInputMessage.setId("answersCreateInputMessage");
        answersCreateInputMessage.setFor("answersCreateInput");
        answersCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(answersCreateInputMessage);
        
        HtmlOutputText visitorCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        visitorCreateOutput.setId("visitorCreateOutput");
        visitorCreateOutput.setValue("Visitor:   ");
        htmlPanelGrid.getChildren().add(visitorCreateOutput);
        
        AutoComplete visitorCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        visitorCreateInput.setId("visitorCreateInput");
        visitorCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{questionBean.question.visitor}", Visitor.class));
        visitorCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{questionBean.completeVisitor}", List.class, new Class[] { String.class }));
        visitorCreateInput.setDropdown(true);
        visitorCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "visitor", String.class));
        visitorCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{visitor.nickname} #{visitor.email} #{visitor.createdAt} #{visitor.updatedAt}", String.class));
        visitorCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{visitor}", Visitor.class));
        visitorCreateInput.setConverter(new VisitorConverter());
        visitorCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(visitorCreateInput);
        
        Message visitorCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        visitorCreateInputMessage.setId("visitorCreateInputMessage");
        visitorCreateInputMessage.setFor("visitorCreateInput");
        visitorCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(visitorCreateInputMessage);
        
        HtmlOutputText titleCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        titleCreateOutput.setId("titleCreateOutput");
        titleCreateOutput.setValue("Title: * ");
        htmlPanelGrid.getChildren().add(titleCreateOutput);
        
        InputTextarea titleCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        titleCreateInput.setId("titleCreateInput");
        titleCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{questionBean.question.title}", String.class));
        LengthValidator titleCreateInputValidator = new LengthValidator();
        titleCreateInputValidator.setMaximum(255);
        titleCreateInput.addValidator(titleCreateInputValidator);
        titleCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(titleCreateInput);
        
        Message titleCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        titleCreateInputMessage.setId("titleCreateInputMessage");
        titleCreateInputMessage.setFor("titleCreateInput");
        titleCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(titleCreateInputMessage);
        
        HtmlOutputText createdAtCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        createdAtCreateOutput.setId("createdAtCreateOutput");
        createdAtCreateOutput.setValue("Created At:   ");
        htmlPanelGrid.getChildren().add(createdAtCreateOutput);
        
        Calendar createdAtCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        createdAtCreateInput.setId("createdAtCreateInput");
        createdAtCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{questionBean.question.createdAt}", Date.class));
        createdAtCreateInput.setNavigator(true);
        createdAtCreateInput.setEffect("slideDown");
        createdAtCreateInput.setPattern("dd/MM/yyyy");
        createdAtCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(createdAtCreateInput);
        
        Message createdAtCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        createdAtCreateInputMessage.setId("createdAtCreateInputMessage");
        createdAtCreateInputMessage.setFor("createdAtCreateInput");
        createdAtCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(createdAtCreateInputMessage);
        
        HtmlOutputText updatedAtCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        updatedAtCreateOutput.setId("updatedAtCreateOutput");
        updatedAtCreateOutput.setValue("Updated At:   ");
        htmlPanelGrid.getChildren().add(updatedAtCreateOutput);
        
        Calendar updatedAtCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        updatedAtCreateInput.setId("updatedAtCreateInput");
        updatedAtCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{questionBean.question.updatedAt}", Date.class));
        updatedAtCreateInput.setNavigator(true);
        updatedAtCreateInput.setEffect("slideDown");
        updatedAtCreateInput.setPattern("dd/MM/yyyy");
        updatedAtCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(updatedAtCreateInput);
        
        Message updatedAtCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        updatedAtCreateInputMessage.setId("updatedAtCreateInputMessage");
        updatedAtCreateInputMessage.setFor("updatedAtCreateInput");
        updatedAtCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(updatedAtCreateInputMessage);
        
        HtmlOutputText bodyCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        bodyCreateOutput.setId("bodyCreateOutput");
        bodyCreateOutput.setValue("Body: * ");
        htmlPanelGrid.getChildren().add(bodyCreateOutput);
        
        InputText bodyCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        bodyCreateInput.setId("bodyCreateInput");
        bodyCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{questionBean.question.body}", String.class));
        htmlPanelGrid.getChildren().add(bodyCreateInput);
        
        Message bodyCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        bodyCreateInputMessage.setId("bodyCreateInputMessage");
        bodyCreateInputMessage.setFor("bodyCreateInput");
        bodyCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(bodyCreateInputMessage);
        
        HtmlOutputText visibleCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        visibleCreateOutput.setId("visibleCreateOutput");
        visibleCreateOutput.setValue("Visible:   ");
        htmlPanelGrid.getChildren().add(visibleCreateOutput);
        
        SelectBooleanCheckbox visibleCreateInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        visibleCreateInput.setId("visibleCreateInput");
        visibleCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{questionBean.question.visible}", Boolean.class));
        visibleCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(visibleCreateInput);
        
        Message visibleCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        visibleCreateInputMessage.setId("visibleCreateInputMessage");
        visibleCreateInputMessage.setFor("visibleCreateInput");
        visibleCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(visibleCreateInputMessage);
        
        HtmlOutputText emailAnswerCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        emailAnswerCreateOutput.setId("emailAnswerCreateOutput");
        emailAnswerCreateOutput.setValue("Email Answer:   ");
        htmlPanelGrid.getChildren().add(emailAnswerCreateOutput);
        
        SelectBooleanCheckbox emailAnswerCreateInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        emailAnswerCreateInput.setId("emailAnswerCreateInput");
        emailAnswerCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{questionBean.question.emailAnswer}", Boolean.class));
        emailAnswerCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(emailAnswerCreateInput);
        
        Message emailAnswerCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        emailAnswerCreateInputMessage.setId("emailAnswerCreateInputMessage");
        emailAnswerCreateInputMessage.setFor("emailAnswerCreateInput");
        emailAnswerCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(emailAnswerCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid QuestionBean.populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText answersLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        answersLabel.setId("answersLabel");
        answersLabel.setValue("Answers:   ");
        htmlPanelGrid.getChildren().add(answersLabel);
        
        HtmlOutputText answersValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        answersValue.setId("answersValue");
        answersValue.setValue("This relationship is managed from the Answer side");
        htmlPanelGrid.getChildren().add(answersValue);
        
        HtmlOutputText visitorLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        visitorLabel.setId("visitorLabel");
        visitorLabel.setValue("Visitor:   ");
        htmlPanelGrid.getChildren().add(visitorLabel);
        
        HtmlOutputText visitorValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        visitorValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{questionBean.question.visitor}", Visitor.class));
        visitorValue.setConverter(new VisitorConverter());
        htmlPanelGrid.getChildren().add(visitorValue);
        
        HtmlOutputText titleLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        titleLabel.setId("titleLabel");
        titleLabel.setValue("Title:   ");
        htmlPanelGrid.getChildren().add(titleLabel);
        
        InputTextarea titleValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        titleValue.setId("titleValue");
        titleValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{questionBean.question.title}", String.class));
        titleValue.setReadonly(true);
        titleValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(titleValue);
        
        HtmlOutputText createdAtLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        createdAtLabel.setId("createdAtLabel");
        createdAtLabel.setValue("Created At:   ");
        htmlPanelGrid.getChildren().add(createdAtLabel);
        
        HtmlOutputText createdAtValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        createdAtValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{questionBean.question.createdAt}", Date.class));
        DateTimeConverter createdAtValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        createdAtValueConverter.setPattern("dd/MM/yyyy");
        createdAtValue.setConverter(createdAtValueConverter);
        htmlPanelGrid.getChildren().add(createdAtValue);
        
        HtmlOutputText updatedAtLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        updatedAtLabel.setId("updatedAtLabel");
        updatedAtLabel.setValue("Updated At:   ");
        htmlPanelGrid.getChildren().add(updatedAtLabel);
        
        HtmlOutputText updatedAtValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        updatedAtValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{questionBean.question.updatedAt}", Date.class));
        DateTimeConverter updatedAtValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        updatedAtValueConverter.setPattern("dd/MM/yyyy");
        updatedAtValue.setConverter(updatedAtValueConverter);
        htmlPanelGrid.getChildren().add(updatedAtValue);
        
        HtmlOutputText bodyLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        bodyLabel.setId("bodyLabel");
        bodyLabel.setValue("Body:   ");
        htmlPanelGrid.getChildren().add(bodyLabel);
        
        HtmlOutputText bodyValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        bodyValue.setId("bodyValue");
        bodyValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{questionBean.question.body}", String.class));
        htmlPanelGrid.getChildren().add(bodyValue);
        
        HtmlOutputText visibleLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        visibleLabel.setId("visibleLabel");
        visibleLabel.setValue("Visible:   ");
        htmlPanelGrid.getChildren().add(visibleLabel);
        
        HtmlOutputText visibleValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        visibleValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{questionBean.question.visible}", String.class));
        htmlPanelGrid.getChildren().add(visibleValue);
        
        HtmlOutputText emailAnswerLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        emailAnswerLabel.setId("emailAnswerLabel");
        emailAnswerLabel.setValue("Email Answer:   ");
        htmlPanelGrid.getChildren().add(emailAnswerLabel);
        
        HtmlOutputText emailAnswerValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        emailAnswerValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{questionBean.question.emailAnswer}", String.class));
        htmlPanelGrid.getChildren().add(emailAnswerValue);
        
        return htmlPanelGrid;
    }
    
    public Question QuestionBean.getQuestion() {
        if (question == null) {
            question = new Question();
        }
        return question;
    }
    
    public void QuestionBean.setQuestion(Question question) {
        this.question = question;
    }
    
    public List<Answer> QuestionBean.getSelectedAnswers() {
        return selectedAnswers;
    }
    
    public void QuestionBean.setSelectedAnswers(List<Answer> selectedAnswers) {
        if (selectedAnswers != null) {
            question.setAnswers(new HashSet<Answer>(selectedAnswers));
        }
        this.selectedAnswers = selectedAnswers;
    }
    
    public List<Visitor> QuestionBean.completeVisitor(String query) {
        List<Visitor> suggestions = new ArrayList<Visitor>();
        for (Visitor visitor : Visitor.findAllVisitors()) {
            String visitorStr = String.valueOf(visitor.getNickname() +  " "  + visitor.getEmail() +  " "  + visitor.getCreatedAt() +  " "  + visitor.getUpdatedAt());
            if (visitorStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(visitor);
            }
        }
        return suggestions;
    }
    
    public String QuestionBean.onEdit() {
        if (question != null && question.getAnswers() != null) {
            selectedAnswers = new ArrayList<Answer>(question.getAnswers());
        }
        return null;
    }
    
    public boolean QuestionBean.isCreateDialogVisible() {
        return createDialogVisible;
    }
    
    public void QuestionBean.setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }
    
    public String QuestionBean.displayList() {
        createDialogVisible = false;
        findAllQuestions();
        return "question";
    }
    
    public String QuestionBean.displayCreateDialog() {
        question = new Question();
        createDialogVisible = true;
        return "question";
    }
    
    public String QuestionBean.delete() {
        question.remove();
        FacesMessage facesMessage = new FacesMessage("Successfully deleted");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllQuestions();
    }
    
    public void QuestionBean.reset() {
        question = null;
        selectedAnswers = null;
        createDialogVisible = false;
    }
    
    public void QuestionBean.handleDialogClose(CloseEvent event) {
        reset();
    }
    
}
