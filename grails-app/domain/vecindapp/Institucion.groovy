package vecindapp

class Institucion {
    String nombre
    String usuario
    Direccion direccion

    static constraints = {
        nombre nullable: false, blank:false, minSize: 4, maxSize: 60
        usuario nullable: false, blank:false, minSize: 4, maxSize: 10
        direccion nullable: false
    }
}
