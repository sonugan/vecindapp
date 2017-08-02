package vecindapp

class Vecino {
    String nombre
    String apellido
    String usuario
    Date fechaNacimiento
    Direccion direccion

    static constraints = {
        nombre nullable: false, blank:false, minSize: 4, maxSize: 40
        apellido nullable: false, blank:false, minSize: 4, maxSize: 40
        usuario nullable: false, blank:false, minSize: 4, maxSize: 10
        fechaNacimiento nullable: false
        direccion nullable: false
    }
}
