package br.com.tartaro.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import br.com.tartaro.model.UsuarioModel;

/**
 *
 * @author WillianTartaro - @date 11 de nov de 2016 - 09:44:14
 *
 * Filter utilizado para fazer a autenticação de usuario logado, caso esteja logado, o usuario ira obter acesso a paginas internas.
 */

@WebFilter("/sistema/*") //irá filtrar os itens que estiverem em " /sistema/* "
public class AutenticacaoFilter implements Filter {

	/* Contrutor da classe. */
	 public AutenticacaoFilter() {
		 
	    }
	 
		public void destroy() {
	 
		}
		
		/* Metodo que ira avaliar se tem um usuario na sessão para acessar as paginas internas do sistema, caso nao tenha sessão, retorna para a pagina de login. */
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	 
			HttpSession httpSession 				= ((HttpServletRequest) request).getSession(); 
	 
			HttpServletRequest httpServletRequest   = (HttpServletRequest) request;
	 
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
	 
			if(httpServletRequest.getRequestURI().indexOf("index.xhtml") <= -1){
	 
				UsuarioModel usuarioModel =(UsuarioModel) httpSession.getAttribute("usuarioAutenticado");
	 
				if(usuarioModel == null){
	 
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+ "/index.xhtml");
	 
				}
				else{
	 
					chain.doFilter(request, response);
				}
			}		
			else{
	 
				chain.doFilter(request, response);
			}
		}
	 
		public void init(FilterConfig fConfig) throws ServletException {
	 
		}
}
