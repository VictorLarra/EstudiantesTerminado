public class Niño{
    
    String nombre;
    int edad;
    boolean tieneMateriales;
    boolean tieneClases;
    boolean tieneTalleres;
    
  public Niño(String nombre, int edad, boolean tieneMateriales, boolean tieneClases, boolean tieneTalleres){
    this.nombre = nombre;
    this.edad= edad;
    this.tieneMateriales = tieneMateriales;
    this.tieneClases = tieneClases;
    this.tieneTalleres = tieneTalleres;
    
  }

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public void setEdad(int edad) {
    this.edad = edad;
}

public void setTieneMateriales(boolean tieneMateriales) {
    this.tieneMateriales = tieneMateriales;
}

public void setTieneClases(boolean tieneClases) {
    this.tieneClases = tieneClases;
}

public void setTieneTalleres(boolean tieneTalleres) {
    this.tieneTalleres = tieneTalleres;
}

public String getNombre() {
    return nombre;
}

public int getEdad() {
    return edad;
}

public boolean isTieneMateriales() {
    return tieneMateriales;
}

public boolean isTieneClases() {
    return tieneClases;
}

public boolean isTieneTalleres() {
    return tieneTalleres;
}


}