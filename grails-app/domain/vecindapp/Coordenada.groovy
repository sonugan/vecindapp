package vecindapp

class Coordenada {
  float latitud
  float longitud

  static constraints = {
      latitud nullable: false
      longitud nullable: false
  }
}
