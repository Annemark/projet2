package fr.dawan.projet2.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class UpperCaseTag extends BodyTagSupport {
	
	private static final long serialVersionUID = 1L;
	public int doAfterBody() throws JspException {
		try {
			if (this.getBodyContent()!= null) {
				String body_string = getBodyContent().getString();
				body_string = body_string.toUpperCase();
				getBodyContent().getEnclosingWriter().println(body_string);
			}
		} catch(IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}
}