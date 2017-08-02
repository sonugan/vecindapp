package vecindapp

class Municipio {
    String nombre
    static belongsTo = [provincia: Provincia]

    static constraints = {
        nombre nullable: false, blank: false
        provincia nullable: false
    }
}
