/*
 *  ***************************************************************
 * NUO - 2020
 * Creado por:      Ing. Joel Jalon Gomez 
 * Motivo:          Enumerado de errores
 * Fecha Creacion:  2020-04-13
 *  ***************************************************************
 */
package com.sicpa.MyEnterprise.util;

public enum catalogoErrores {
	OK("Ejecutado correctamente",0),
	NOT_FOUND("No se encontraron datos",1),
	NOT_SAVED("Error al momento de guardar",2),
	NOT_UPDATED("Error al momento de actualizar",3),
	NOT_DELETED("Error al momento de eliminar",4)
	
	;
	
	private String message;
	private int code;
	
	private catalogoErrores(String message, int code) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
