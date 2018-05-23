package com.suscriptor.entidad;

import java.io.Serializable;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author nvillegas
 */
@Entity
@Table(name = "CONEXION", schema = "subscriptordb")
@NamedQueries({ 
	@NamedQuery(name = "Conexion.findAll", query = "SELECT c FROM Conexion c ORDER BY c.nombre"),
        @NamedQuery(name = "Conexion.maxResults", query = "SELECT MAX(c.id) FROM Conexion c"),
        @NamedQuery(name = "Conexion.encontrarConexionPorCodigo", query = "SELECT c FROM Conexion c WHERE c.codigo=:codigo"),
        @NamedQuery(name = "Conexion.encontrarConexionPorId", query = "SELECT c FROM Conexion c WHERE c.id=:id")
})

@XmlRootElement
public class Conexion implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "SEQ_CONEXION")
    @SequenceGenerator(name = "SEQ_CONEXION", sequenceName = "CONEXION_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "CON_ID_CONEXION")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CON_NOMBRE_OPERADOR")
    private String nombre;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CON_CODIGO_OPERADOR")
    private String codigo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(name = "CON_XSLT_MAPEO")
    private String xslt_mapeo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(name = "CON_XSLT_ESTRUCTURA")
    private String xslt_estructura;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CON_CAPACIDADES")
    private String capacidades;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CON_TIPO_SERVICIO")
    private String tipoServicio;
  
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "CON_URL")
    private String url;

 

    @Override
    public int hashCode() {
        int hash = (id != null ? id.hashCode() : 0);
        return new HashCodeBuilder(3, 5).append(hash).append(id).
                append(nombre).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        boolean igual = false;

        if (object instanceof Conexion) {
            Conexion other = (Conexion) object;
            igual = (Objects.equals(this.getId(), other.getId()) && Objects.
                    equals(this.getNombre(), other.getNombre()));
        }

        return igual;
    }

    @Override
    public String toString() {
        StringBuilder valor = new StringBuilder(210);
        valor.append(this.getClass().getSimpleName());
        valor.append("{");
        valor.append("id:");
        valor.append(this.getId());
        valor.append(",nombre:");
        valor.append(this.getNombre());
        valor.append("}");

        return valor.toString();
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @return the xslt_mapeo
     */
    public String getXslt_mapeo() {
        return xslt_mapeo;
    }

    /**
     * @return the xslt_estructura
     */
    public String getXslt_estructura() {
        return xslt_estructura;
    }

    /**
     * @return the capacidades
     */
    public String getCapacidades() {
        return capacidades;
    }

    /**
     * @return the tipoServicio
     */
    public String getTipoServicio() {
        return tipoServicio;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    
    
      public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @param xslt_mapeo the xslt_mapeo to set
     */
    public void setXslt_mapeo(String xslt_mapeo) {
        this.xslt_mapeo = xslt_mapeo;
    }

    /**
     * @param xslt_estructura the xslt_estructura to set
     */
    public void setXslt_estructura(String xslt_estructura) {
        this.xslt_estructura = xslt_estructura;
    }

    /**
     * @param capacidades the capacidades to set
     */
    public void setCapacidades(String capacidades) {
        this.capacidades = capacidades;
    }

    /**
     * @param tipoServicio the tipoServicio to set
     */
    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

   
    
}
