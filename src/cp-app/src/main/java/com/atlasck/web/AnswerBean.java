package com.atlasck.web;

import java.util.Date;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import com.atlasck.domain.Answer;
import com.atlasck.domain.Question;
import com.atlasck.web.converter.QuestionConverter;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Answer.class, beanName = "answerBean")
public class AnswerBean {

	public HtmlPanelGrid populateEditPanel() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);

		HtmlOutputText questionEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
		questionEditOutput.setId("questionEditOutput");
		questionEditOutput.setValue("Question:   ");
		htmlPanelGrid.getChildren().add(questionEditOutput);

		AutoComplete questionEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
		questionEditInput.setId("questionEditInput");
		questionEditInput.setValueExpression("value",
				expressionFactory.createValueExpression(elContext, "#{answerBean.answer.question}", Question.class));
		questionEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{answerBean.completeQuestion}",
				List.class, new Class[] { String.class }));
		questionEditInput.setDropdown(true);
		questionEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "question", String.class));
		questionEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext,
				"#{question.title} #{question.createdAt} #{question.updatedAt} #{question.body}", String.class));
		questionEditInput
				.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{question}", Question.class));
		questionEditInput.setConverter(new QuestionConverter());
		questionEditInput.setRequired(false);
		htmlPanelGrid.getChildren().add(questionEditInput);

		Message questionEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
		questionEditInputMessage.setId("questionEditInputMessage");
		questionEditInputMessage.setFor("questionEditInput");
		questionEditInputMessage.setDisplay("icon");
		htmlPanelGrid.getChildren().add(questionEditInputMessage);

		HtmlOutputText questionBodyEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
		questionBodyEditOutput.setId("questionBodyEditOutput");
		questionBodyEditOutput.setValue("Question body:   ");
		htmlPanelGrid.getChildren().add(questionBodyEditOutput);

		InputTextarea questionBodyEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
		questionBodyEditInput.setId("questionBodyEditInput");
		questionBodyEditInput.setRows(8);
		questionBodyEditInput.setCols(60);
		questionBodyEditInput.setAutoResize(true);
		questionBodyEditInput.setValueExpression("value",
				expressionFactory.createValueExpression(elContext, "#{answerBean.answer.question.body}", String.class));
		htmlPanelGrid.getChildren().add(questionBodyEditInput);

		Message questionBodyEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
		questionBodyEditInputMessage.setId("questionBodyEditInputMessage");
		questionBodyEditInputMessage.setFor("questionBodyEditInput");
		questionBodyEditInputMessage.setDisplay("icon");
		htmlPanelGrid.getChildren().add(questionBodyEditInputMessage);

		HtmlOutputText createdAtEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
		createdAtEditOutput.setId("createdAtEditOutput");
		createdAtEditOutput.setValue("Created At:   ");
		htmlPanelGrid.getChildren().add(createdAtEditOutput);

		Calendar createdAtEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
		createdAtEditInput.setId("createdAtEditInput");
		createdAtEditInput.setValueExpression("value",
				expressionFactory.createValueExpression(elContext, "#{answerBean.answer.createdAt}", Date.class));
		createdAtEditInput.setNavigator(true);
		createdAtEditInput.setEffect("slideDown");
		createdAtEditInput.setPattern("dd/MM/yyyy");
		createdAtEditInput.setRequired(false);
		htmlPanelGrid.getChildren().add(createdAtEditInput);

		Message createdAtEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
		createdAtEditInputMessage.setId("createdAtEditInputMessage");
		createdAtEditInputMessage.setFor("createdAtEditInput");
		createdAtEditInputMessage.setDisplay("icon");
		htmlPanelGrid.getChildren().add(createdAtEditInputMessage);

		HtmlOutputText updatedAtEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
		updatedAtEditOutput.setId("updatedAtEditOutput");
		updatedAtEditOutput.setValue("Updated At:   ");
		htmlPanelGrid.getChildren().add(updatedAtEditOutput);

		Calendar updatedAtEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
		updatedAtEditInput.setId("updatedAtEditInput");
		updatedAtEditInput.setValueExpression("value",
				expressionFactory.createValueExpression(elContext, "#{answerBean.answer.updatedAt}", Date.class));
		updatedAtEditInput.setNavigator(true);
		updatedAtEditInput.setEffect("slideDown");
		updatedAtEditInput.setPattern("dd/MM/yyyy");
		updatedAtEditInput.setRequired(false);
		htmlPanelGrid.getChildren().add(updatedAtEditInput);

		Message updatedAtEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
		updatedAtEditInputMessage.setId("updatedAtEditInputMessage");
		updatedAtEditInputMessage.setFor("updatedAtEditInput");
		updatedAtEditInputMessage.setDisplay("icon");
		htmlPanelGrid.getChildren().add(updatedAtEditInputMessage);

		HtmlOutputText answerEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
		answerEditOutput.setId("answerEditOutput");
		answerEditOutput.setValue("Answer:   ");
		htmlPanelGrid.getChildren().add(answerEditOutput);

		InputTextarea answerEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
		answerEditInput.setId("answerEditInput");
		answerEditInput.setRows(8);
		answerEditInput.setCols(60);
		answerEditInput.setAutoResize(false);
		answerEditInput.setValueExpression("value",
				expressionFactory.createValueExpression(elContext, "#{answerBean.answer.answer}", String.class));
		htmlPanelGrid.getChildren().add(answerEditInput);

		Message answerEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
		answerEditInputMessage.setId("answerEditInputMessage");
		answerEditInputMessage.setFor("answerEditInput");
		answerEditInputMessage.setDisplay("icon");
		htmlPanelGrid.getChildren().add(answerEditInputMessage);

		return htmlPanelGrid;
	}
}
