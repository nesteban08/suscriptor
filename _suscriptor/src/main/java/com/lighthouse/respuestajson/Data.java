package com.lighthouse.respuestajson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vacardozo
 */
public class Data {
    private List registros;
    private String tipo;
    private String id;
    private String campos;
    private String status;

    
    public Data setRegistros(List registros) {
        iniciarRegistros();
        this.registros = registros;
        return this;
    }
    
    public Data setRegistros(Object registro) {
        iniciarRegistros();
        this.getRegistros().add(registro);
        return this;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCampos(String campos) {
        this.campos = campos;
    }
    
    private void iniciarRegistros() {
        if(null == getRegistros()) {
            registros = new ArrayList();
        }
    }

    /**
     * @return the registros
     */
    public List getRegistros() {
        return registros;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the campos
     */
    public String getCampos() {
        return campos;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

   
    
    
    
}
