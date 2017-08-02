package vecindapp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Puntaje)
class PuntajeSpec extends HibernateSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test puntaje valido"() {
        when:
          Puntaje puntaje = new Puntaje(cantidadPositivos: 0, cantidadNegativos: 0)
          puntaje.save()
        then:
          !puntaje.hasErrors()
          Puntaje.count()==1
    }

    void "test puntaje cantidad positivos menor que cero"() {
        when:
          Puntaje puntaje = new Puntaje(cantidadPositivos: -1, cantidadNegativos: 0)
          puntaje.save()
        then:
          puntaje.hasErrors()
          puntaje.errors.getFieldError('cantidadPositivos')
          puntaje.errors.getErrorCount() == 1
          Puntaje.count() == 0
    }

    void "test puntaje cantidad negativos menor que cero"() {
        when:
          Puntaje puntaje = new Puntaje(cantidadPositivos: 0, cantidadNegativos: -1)
          puntaje.save()
        then:
          puntaje.hasErrors()
          puntaje.errors.getFieldError('cantidadNegativos')
          puntaje.errors.getErrorCount() == 1
          Puntaje.count() == 0
    }
}
