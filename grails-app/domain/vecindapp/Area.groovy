package vecindapp

class Area {
    String nombre
    List<Coordenada> contorno

    static constraints = {
      nombre unique: true, nullable: false, blank: false
      contorno nullable: false
    }
}
