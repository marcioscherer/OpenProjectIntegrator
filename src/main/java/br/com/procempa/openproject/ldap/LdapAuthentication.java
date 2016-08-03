package br.com.procempa.openproject.ldap;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;


/**
 * Essa classe fornece métodos uteis para autenticação de usuarios em um LDAP. 
 * 
 * @author marcio.scherer
 */
public class LdapAuthentication {
	private static final String CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
	private static final String SECURITY_AUTH = "simple";
	private static Logger logger = Logger.getLogger(LdapAuthentication.class);

	/**
	 * Autenticar um determinado usuário no LDAP. 
	 * 
	 * @param userName
	 * @param password
	 * @param ldapUrl
	 * @throws LoginException
	 */
	public static void authenticateUser(String userName, String password, String ldapURL) throws LoginException {
		Properties props = new Properties();
		
		props.setProperty(Context.SECURITY_PRINCIPAL, userName);
		props.setProperty(Context.SECURITY_CREDENTIALS, password);
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY);
		props.setProperty(Context.PROVIDER_URL, ldapURL);
		props.setProperty(Context.SECURITY_AUTHENTICATION, SECURITY_AUTH);

		try {
			new InitialLdapContext(props, null);
			logger.info("Autenticao do usuario " + userName +" foi feita com sucesso!");

		} catch (Exception ex) {
			logger.info("Erro autenticando usuário", ex);
			throw new LoginException("Erro na autenticação do usuário");
		}
	}

}
