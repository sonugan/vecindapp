package vecindapp

class Vecindario {
  String nombre
  static hasMany=[contorno:Coordenada]

  static constraints = {
    nombre unique: true, nullable: false, blank: false
    //contorno nullable: false
  }
}
