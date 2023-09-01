package logica;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Responsable extends Persona {

    private String tipoResponsable;

    public Responsable() {
    }

    public Responsable(String tipoResponsable,int id, String dni, String nombre, String apellido, String telefono, String direccion, Date fechaNac) {
        super(id, dni, nombre, apellido, telefono, direccion, fechaNac);
        this.tipoResponsable = tipoResponsable;
    }

    public String getTipoResponsable() {
        return tipoResponsable;
    }

    public void setTipoResponsable(String tipoResponsable) {
        this.tipoResponsable = tipoResponsable;
    }

}
