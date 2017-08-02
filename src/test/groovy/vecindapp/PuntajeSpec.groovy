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

    void "test sumar puntaje"() {
        when:
          Puntaje puntaje = new Puntaje(cantidadPositivos: 0, cantidadNegativos: 0)
          puntaje.sumar()
        then:
          puntaje.cantidadPositivos == 1
    }

    void "test restar puntaje"() {
        when:
          Puntaje puntaje = new Puntaje(cantidadPositivos: 0, cantidadNegativos: 0)
          puntaje.restar()
        then:
          puntaje.cantidadNegativos == 1
    }

    void "test sumar puntaje especifico"() {
        when:
          Puntaje puntaje = new Puntaje(cantidadPositivos: 0, cantidadNegativos: 0)
          puntaje.sumar(3)
        then:
          puntaje.cantidadPositivos == 3
    }

    void "test restar puntaje especifico"() {
        when:
          Puntaje puntaje = new Puntaje(cantidadPositivos: 0, cantidadNegativos: 0)
          puntaje.restar(3)
        then:
          puntaje.cantidadNegativos == 3
    }

    void "test sumar puntaje negativo"() {
        when:
          Puntaje puntaje = new Puntaje(cantidadPositivos: 0, cantidadNegativos: 0)
          puntaje.sumar(-3)
        then:
          thrown Exception
    }

    void "test restar puntaje negativo"() {
        when:
          Puntaje puntaje = new Puntaje(cantidadPositivos: 0, cantidadNegativos: 0)
          puntaje.restar(-3)
        then:
          thrown Exception
    }

    void "test restar puntaje positivo total"() {
        when:
          Puntaje puntaje = new Puntaje(cantidadPositivos: 0, cantidadNegativos: 0)
          puntaje.sumar(5)
          puntaje.restar(3)
        then:
          puntaje.getTotal() == 2
    }

    void "test restar puntaje negativo total"() {
        when:
          Puntaje puntaje = new Puntaje(cantidadPositivos: 0, cantidadNegativos: 0)
          puntaje.sumar(3)
          puntaje.restar(5)
        then:
          puntaje.getTotal() == -2
    }

    void "test restar puntaje total 0"() {
        when:
          Puntaje puntaje = new Puntaje(cantidadPositivos: 0, cantidadNegativos: 0)
          puntaje.sumar(3)
          puntaje.restar(3)
        then:
          puntaje.getTotal() == 0
    }
}
