package vecindapp

class Rubro {
    String descripcion

    static constraints = {
        descripcion nullable: false, blank: false, minSize: 3, maxSize: 30
    }
}
