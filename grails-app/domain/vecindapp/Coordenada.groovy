package vecindapp

class Coordenada {
  float latitud
  float longitud

  static constraints = {
      latitud min: -90f, max: 90f
      longitud min: 0f, max: 180f
  }
}
