package com.atlasck.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.validator.LengthValidator;

import com.atlasck.domain.Answer;
import com.atlasck.domain.Question;
import com.atlasck.domain.Visitor;
import com.atlasck.web.converter.VisitorConverter;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.context.RequestContext;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Question.class, beanName = "questionBean")
public class QuestionBean {

	private Question question;
	private List<String> columns;
	private List<Question> allQuestions;
	private boolean dataVisible = false;
	private Answer answer;

	@PostConstruct
	public void init() {
		columns = new ArrayList<String>();
		columns.add("title");
		columns.add("body");
	}

	public String findAllQuestions() {
		allQuestions = new ArrayList<Question>();

		for (Question question : Question.findAllQuestions()) {
			if (question.getAnswers().size() == 0) {
				allQuestions.add(question);
			}
		}

		dataVisible = !allQuestions.isEmpty();
		return null;
	}

	public HtmlPanelGrid populateEditPanel() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);

		HtmlOutputText answersEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
		answersEditOutput.setId("answersEditOutput");
		answersEditOutput.setValue("Answers:   ");
		htmlPanelGrid.getChildren().add(answersEditOutput);

		InputTextarea answersEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
		answersEditInput.setId("answersEditInput");
		answersEditInput.setValueExpression("value",
				expressionFactory.createValueExpression(elContext, "#{questionBean.answer.answer}", String.class));
		answersEditInput.setRows(8);
		answersEditInput.setCols(60);
		answersEditInput.setAutoResize(false);
		answersEditInput.setRequired(true);
		htmlPanelGrid.getChildren().add(answersEditInput);

		Message answersEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
		answersEditInputMessage.setId("answersEditInputMessage");
		answersEditInputMessage.setFor("answersEditInput");
		answersEditInputMessage.setDisplay("icon");
		htmlPanelGrid.getChildren().add(answersEditInputMessage);

		HtmlOutputText visitorEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
		visitorEditOutput.setId("visitorEditOutput");
		visitorEditOutput.setValue("Visitor:   ");
		htmlPanelGrid.getChildren().add(visitorEditOutput);

		AutoComplete visitorEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
		visitorEditInput.setId("visitorEditInput");
		visitorEditInput.setValueExpression("value",
				expressionFactory.createValueExpression(elContext, "#{questionBean.question.visitor}", Visitor.class));
		visitorEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{questionBean.completeVisitor}",
				List.class, new Class[] { String.class }));
		visitorEditInput.setDropdown(true);
		visitorEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "visitor", String.class));
		visitorEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext,
				"#{visitor.nickname} #{visitor.email} #{visitor.createdAt} #{visitor.updatedAt}", String.class));
		visitorEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{visitor}", Visitor.class));
		visitorEditInput.setConverter(new VisitorConverter());
		visitorEditInput.setRequired(false);
		htmlPanelGrid.getChildren().add(visitorEditInput);

		Message visitorEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
		visitorEditInputMessage.setId("visitorEditInputMessage");
		visitorEditInputMessage.setFor("visitorEditInput");
		visitorEditInputMessage.setDisplay("icon");
		htmlPanelGrid.getChildren().add(visitorEditInputMessage);

		HtmlOutputText titleEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
		titleEditOutput.setId("titleEditOutput");
		titleEditOutput.setValue("Title: * ");
		htmlPanelGrid.getChildren().add(titleEditOutput);

		InputTextarea titleEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
		titleEditInput.setId("titleEditInput");
		titleEditInput.setValueExpression("value",
				expressionFactory.createValueExpression(elContext, "#{questionBean.question.title}", String.class));
		LengthValidator titleEditInputValidator = new LengthValidator();
		titleEditInputValidator.setMaximum(255);
		titleEditInput.addValidator(titleEditInputValidator);
		titleEditInput.setRequired(true);
		htmlPanelGrid.getChildren().add(titleEditInput);

		Message titleEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
		titleEditInputMessage.setId("titleEditInputMessage");
		titleEditInputMessage.setFor("titleEditInput");
		titleEditInputMessage.setDisplay("icon");
		htmlPanelGrid.getChildren().add(titleEditInputMessage);

		HtmlOutputText bodyEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
		bodyEditOutput.setId("bodyEditOutput");
		bodyEditOutput.setValue("Body: * ");
		htmlPanelGrid.getChildren().add(bodyEditOutput);

		InputTextarea bodyEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
		bodyEditInput.setId("bodyEditInput");
		bodyEditInput.setRows(8);
		bodyEditInput.setCols(60);
		bodyEditInput.setAutoResize(true);
		bodyEditInput.setValueExpression("value",
				expressionFactory.createValueExpression(elContext, "#{questionBean.question.body}", String.class));
		htmlPanelGrid.getChildren().add(bodyEditInput);

		Message bodyEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
		bodyEditInputMessage.setId("bodyEditInputMessage");
		bodyEditInputMessage.setFor("bodyEditInput");
		bodyEditInputMessage.setDisplay("icon");
		htmlPanelGrid.getChildren().add(bodyEditInputMessage);

		HtmlOutputText emailAnswerEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
		emailAnswerEditOutput.setId("emailAnswerEditOutput");
		emailAnswerEditOutput.setValue("Email Answer:   ");
		htmlPanelGrid.getChildren().add(emailAnswerEditOutput);

		SelectBooleanCheckbox emailAnswerEditInput = (SelectBooleanCheckbox) application
				.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
		emailAnswerEditInput.setId("emailAnswerEditInput");
		emailAnswerEditInput.setValueExpression("value",
				expressionFactory.createValueExpression(elContext, "#{questionBean.question.emailAnswer}", Boolean.class));
		emailAnswerEditInput.setRequired(false);
		htmlPanelGrid.getChildren().add(emailAnswerEditInput);

		Message emailAnswerEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
		emailAnswerEditInputMessage.setId("emailAnswerEditInputMessage");
		emailAnswerEditInputMessage.setFor("emailAnswerEditInput");
		emailAnswerEditInputMessage.setDisplay("icon");
		htmlPanelGrid.getChildren().add(emailAnswerEditInputMessage);

		return htmlPanelGrid;
	}

	public String persist() {
		String message = "";
		if (question.getId() != null) {
			answer.setQuestion(question);

			Set<Answer> answers = new HashSet<Answer>();
			answers.add(answer);
			question.setAnswers(answers);
			question.setVisible(true);

			// send email with the answer

			question.merge();
			message = "Successfully updated";
		} else {
			answer.persist();
			question.persist();
			message = "Successfully created";
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("createDialog.hide()");
		context.execute("editDialog.hide()");

		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		reset();
		return findAllQuestions();
	}

	public Answer getAnswer() {
		if (answer == null) {
			answer = new Answer();
		}

		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

}
