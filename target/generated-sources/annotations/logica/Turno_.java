package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Odontologo;
import logica.Paciente;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-08-31T14:01:22")
@StaticMetamodel(Turno.class)
public class Turno_ { 

    public static volatile SingularAttribute<Turno, String> afeccion;
    public static volatile SingularAttribute<Turno, Odontologo> odonto;
    public static volatile SingularAttribute<Turno, Paciente> pacient;
    public static volatile SingularAttribute<Turno, Integer> idTurno;
    public static volatile SingularAttribute<Turno, Date> fechaTurno;
    public static volatile SingularAttribute<Turno, String> horaTurno;

}