package vecindapp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Coordenada)
class CoordenadaSpec extends HibernateSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test coordenada valida"() {
        when:
          Coordenada coordenada = new Coordenada(longitud: 1f, latitud: 2f)
          coordenada.save()
        then:
          !coordenada.hasErrors()
          Coordenada.count() == 1
    }

    void "test coordenada latitud excede el maximo"() {
        when:
          Coordenada coordenada = new Coordenada(longitud: 1f, latitud: 91f)
          coordenada.save()
        then:
          coordenada.hasErrors()
          coordenada.errors.getFieldError('latitud')
          Coordenada.count() == 0
    }

    void "test coordenada latitud excede el minimo"() {
        when:
          Coordenada coordenada = new Coordenada(longitud: 1f, latitud: -91f)
          coordenada.save()
        then:
          coordenada.hasErrors()
          coordenada.errors.getFieldError('latitud')
          Coordenada.count() == 0
    }

    void "test coordenada longitud excede el maximo"() {
        when:
          Coordenada coordenada = new Coordenada(longitud: 181, latitud: 89f)
          coordenada.save()
        then:
          coordenada.hasErrors()
          Coordenada.count() == 0
          coordenada.errors.getFieldError('longitud')
    }

    void "test coordenada longitud excede el minimo"() {
        when:
          Coordenada coordenada = new Coordenada(longitud: -1, latitud: 80f)
          coordenada.save()
        then:
          coordenada.hasErrors()
          coordenada.errors.getFieldError('longitud')
          Coordenada.count() == 0
    }
}
