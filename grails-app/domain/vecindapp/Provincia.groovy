package vecindapp

class Provincia {
    String nombre

    static constraints = {
        nombre nullable: false, blank: false, unique: true
    }
}
