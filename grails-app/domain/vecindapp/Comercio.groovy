package vecindapp

class Comercio {
  String nombre
  String usuario
  Direccion direccion
  Rubro rubro

  static constraints = {
    nombre nullable: false, blank:false, minSize: 4, maxSize: 60
    usuario nullable: false, blank:false, minSize: 4, maxSize: 10
    direccion nullable: false
    rubro nullable: false
  }
}
