package vecindapp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Comentario)
class ComentarioSpec extends HibernateSpec {

  Vecino vecino
  Puntaje puntaje

  def setup() {
      vecino = new Vecino(nombre: 'Juan', apellido: 'Sanchez', usuario:'Pepe'
        , fechaNacimiento: new Date()
        , direccion: new Direccion(calle: "calle falsa", altura: 100
            , codigoPostal: "A1234ABC"
            , provincia: new Provincia(nombre: "Buenos Aires")));

      puntaje = new Puntaje(cantidadPositivos: 0, cantidadNegativos: 0)
  }

  def cleanup() {
  }

  void "test comentario valido"() {
    /*when:
      Comentario comentario = new Comentario(fecha: new Date()
        , texto: 'un texto', puntaje: puntaje)
        //comentario.save()
    then:
      !comentario.hasErrors()
      comentario.errors.getErrorCount() == 1
      Comentario.count() == 0*/
  }

  void "test comentario sin vecino"() {
      when:
        Comentario comentario = new Comentario(fecha: new Date()
          , texto: 'un texto', puntaje: puntaje)
          comentario.save()
      then:
        comentario.hasErrors()
        comentario.errors.getFieldError('vecino')
        comentario.errors.getErrorCount() == 1
        Comentario.count() == 0
  }

  void "test comentario sin fecha"() {
      when:
        Comentario comentario = new Comentario(vecino: vecino
          , texto: 'un texto', puntaje: puntaje)
          comentario.save()
      then:
        comentario.hasErrors()
        comentario.errors.getFieldError('fecha')
        comentario.errors.getErrorCount() == 1
        Comentario.count() == 0
  }

  void "test comentario sin texto"() {
      when:
        Comentario comentario = new Comentario(vecino: vecino, fecha: new Date(), puntaje: puntaje)
          comentario.save()
      then:
        comentario.hasErrors()
        comentario.errors.getFieldError('texto')
        comentario.errors.getErrorCount() == 1
        Comentario.count() == 0
  }

  void "test comentario con texto vacio"() {
      when:
        Comentario comentario = new Comentario(vecino: vecino, fecha: new Date()
          , texto: '', puntaje: puntaje)
          comentario.save()
      then:
        comentario.hasErrors()
        comentario.errors.getFieldError('texto')
        comentario.errors.getErrorCount() == 1
        Comentario.count() == 0
  }

  void "test comentario con texto mas largo de lo permitido"() {
      when:
        Comentario comentario = new Comentario(vecino: vecino, fecha: new Date()
          , texto: '0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789\
          0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789\
          0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789\
          0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789\
          0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789\
          0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789\
          0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789\
          0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789\
          0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789\
          012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789'
          , puntaje: puntaje)
          comentario.save()
      then:
        comentario.hasErrors()
        comentario.errors.getFieldError('texto')
        comentario.errors.getErrorCount() == 1
        Comentario.count() == 0
  }

  void "test comentario con texto mas corto de lo permitido"() {
      when:
        Comentario comentario = new Comentario(vecino: vecino, fecha: new Date()
          , texto: '1', puntaje: puntaje)
          comentario.save()
      then:
        comentario.hasErrors()
        comentario.errors.getFieldError('texto')
        comentario.errors.getErrorCount() == 1
        Comentario.count() == 0
  }

  void "test comentario sin puntaje"() {
      when:
        Comentario comentario = new Comentario(vecino: vecino, fecha: new Date()
          , texto: 'un texto')
          comentario.save()
      then:
        comentario.hasErrors()
        comentario.errors.getFieldError('puntaje')
        comentario.errors.getErrorCount() == 1
        Comentario.count() == 0
  }
}
