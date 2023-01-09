/*
 *  ***************************************************************
 * NUO - 2020
 * Creado por:      Ing. Joel Jalon Gomez 
 * Motivo:          Estructura base de respuesta de los servicios rest
 * Fecha Creacion:  2020-04-13
 *  ***************************************************************
 */
package com.sicpa.MyEnterprise.util;

import java.time.LocalDateTime;
import java.util.List;

public class ResponseBase {
	
	private LocalDateTime fechaHora;
	private int codigo;
	private String mensaje;
	private Integer statusCodeHttp;
	private Object listaObjetos;
	
	public ResponseBase(LocalDateTime fechaHora, int codigo, String mensaje, Integer statusCodeHttp,
			Object listaObjetos) {
		super();
		this.fechaHora = fechaHora;
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.statusCodeHttp = statusCodeHttp;
		this.listaObjetos = listaObjetos;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Integer getStatusCodeHttp() {
		return statusCodeHttp;
	}

	public void setStatusCodeHttp(Integer statusCodeHttp) {
		this.statusCodeHttp = statusCodeHttp;
	}

	public Object getListaObjetos() {
		return listaObjetos;
	}

	public void setListaObjetos(Object listaObjetos) {
		this.listaObjetos = listaObjetos;
	}
	
	
	
	
	

}
