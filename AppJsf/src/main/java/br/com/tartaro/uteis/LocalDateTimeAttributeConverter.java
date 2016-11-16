package br.com.tartaro.uteis;

import java.sql.Timestamp;
import java.time.LocalDateTime;
 
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author WillianTartaro - @date 11 de nov de 2016 - 18:07:03
 *
 *Classe com a responsabilidade de fazer a conversão de atributos, para persistir.
 *
 */

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
 
 
	/*
	 * transforma em Timestamp, na hora que persistir no banco.
	 */
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
 
    	if(localDateTime != null)
    		return Timestamp.valueOf(localDateTime);
 
    	return null;
 
    }
 
    /*
     * Transforma um TimeStamp em LocalDateTime,Quando retorna do banco.
     */
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
 
    	if(timestamp != null)
    		return timestamp.toLocalDateTime();
 
    	return null;
    }
}