package vecindapp

class Vecino {
    String nombre
    String apellido
    String usuario
    Date fechaNacimiento
    Direccion direccion

    static constraints = {
        nombre nullable: false, blank:false
        apellido nullable: false, blank:false
        usuario nullable: false, blank:false
        fechaNacimiento nullable: false
        direccion nullable: false
    }
}
