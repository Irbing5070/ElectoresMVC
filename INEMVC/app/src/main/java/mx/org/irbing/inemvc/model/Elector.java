package mx.org.irbing.inemvc.model;

/**
 * Created by Irbing on 03/03/2018.
 */

public class Elector {
    //atributos de la clase

    private String idElector;
    private String nombre;
    private String voto;
    private int edad;
    private String fechaVotacion;

    public Elector() {
    }

    public Elector(String idElector, String nombre, String voto, int edad, String fechaVotacion) {
        this.idElector = idElector;
        this.nombre = nombre;
        this.voto = voto;
        this.edad = edad;
        this.fechaVotacion = fechaVotacion;
    }

    public String getIdElector() {
        return idElector;
    }

    public void setIdElector(String idElector) {
        this.idElector = idElector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechaVotacion() {
        return fechaVotacion;
    }

    public void setFechaVotacion(String fechaVotacion) {
        this.fechaVotacion = fechaVotacion;
    }

    @Override
    public String toString() {
        return "Elector{" +
                "idElector='" + idElector + '\'' +
                ", nombre='" + nombre + '\'' +
                ", voto='" + voto + '\'' +
                ", edad=" + edad +
                ", fechaVotacion='" + fechaVotacion + '\'' +
                '}';
    }
}