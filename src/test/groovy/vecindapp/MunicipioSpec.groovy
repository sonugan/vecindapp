package vecindapp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Municipio)
class MunicipioSpec extends HibernateSpec {

    Provincia provincia
    def setup() {
      provincia = new Provincia(nombre: "Buenos Aires")
    }

    def cleanup() {
    }

    void "test municipio valido"() {
        when:
          Municipio municipio = new Municipio(nombre: "Tres de Febrero", provincia: provincia)
          municipio.save()
        then:
          !municipio.hasErrors()
          Municipio.count()==1
    }

    void "test municipio sin nombre"() {
        when:
          Municipio municipio = new Municipio(provincia: provincia)
          municipio.save()
        then:
          municipio.hasErrors()
          municipio.errors.getFieldError('nombre')
          municipio.errors.getErrorCount() == 1
          Municipio.count() == 0
    }

    void "test municipio con nombre vacio"() {
        when:
          Municipio municipio = new Municipio(nombre: '', provincia: provincia)
          municipio.save()
        then:
          municipio.hasErrors()
          municipio.errors.getFieldError('nombre')
          municipio.errors.getErrorCount() == 1
          Municipio.count() == 0
    }

    void "test municipio sin provincia"() {
        when:
          Municipio municipio = new Municipio(nombre: "Tres de Febrero")
          municipio.save()
        then:
          municipio.hasErrors()
          municipio.errors.getFieldError('provincia')
          municipio.errors.getErrorCount() == 1
          Municipio.count() == 0
    }
}
