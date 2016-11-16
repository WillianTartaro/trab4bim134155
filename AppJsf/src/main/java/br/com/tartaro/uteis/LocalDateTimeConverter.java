package br.com.tartaro.uteis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
 
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

/**
 * @author WillianTartaro - @date 16 de nov de 2016 - 12:59:12
 *
 *Classe responsavel por fazer a conversão de datas.
 */


@FacesConverter(value= LocalDateTimeConverter.ID)//atribui o ID ao valor.
public class LocalDateTimeConverter extends DateTimeConverter {
 
	//Caminho da classe.
	public static final String ID="br.com.tartaro.uteis.LocalDateTimeConverter"; 
 
	/*
	 * metodo responsavel por capturar o horario e fazer a conversão.
	 */
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
 
		Date date = null;
		LocalDateTime localDateTime = null;
 
		Object object = super.getAsObject(facesContext, uiComponent, value);
 
		if(object instanceof Date){
 
			date = (Date) object;
 
			Instant instant = Instant.ofEpochMilli(date.getTime());
			localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	  		return localDateTime;
 
		}
		else{
 
			throw new IllegalArgumentException(String.format("value=%s Não foi possível converter LocalDateTime, resultado super.getAsObject=%s",value,object));			
		}			 
 
 
	}
 
	/*
	 * Metodo responsavel por mudar a data para strings.
	 */
	  @Override
	  public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
 
		  if(value == null)
			  return super.getAsString(facesContext, uiComponent, value);
 
		  if(value instanceof LocalDateTime){
 
			  LocalDateTime localDateTime = (LocalDateTime) value;
 
			  Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
 
			  Date  date =  Date.from(instant);
 
			  return super.getAsString(facesContext, uiComponent, date);
		  }
		  else{
 
			  throw new IllegalArgumentException(String.format("value=%s não é um LocalDateTime",value));  
		  }
 
	  }
}