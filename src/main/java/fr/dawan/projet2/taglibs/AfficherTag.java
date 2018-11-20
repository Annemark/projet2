package fr.dawan.projet2.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <prefix:afficher valeur="" />
 */
public class AfficherTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String valeur;
	
	@Override
	public int doStartTag() throws JspException {

		try {
			if (!(valeur.equals(null))) {
				pageContext.getOut().println(valeur);
			} else {
				pageContext.getOut().println("valeur par defaut");
			}
			
		} catch (IOException e) {

			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
}
