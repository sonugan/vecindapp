package vecindapp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Direccion)
class DireccionSpec extends HibernateSpec {

  //Informacion sobre manejo de errores: http://docs.grails.org/3.1.1/api/grails/validation/ValidationErrors.html
    Municipio municipio
    def setup() {
      municipio = new Municipio(nombre: "Tres de Febrero"
        , provincia: new Provincia(nombre: "Buenos Aires"))
    }

    def cleanup() {
    }

    void "test direccion valida"() {
        when:
          Direccion direccion = new Direccion(calle: "calle falsa", altura: 123
            , codigoPostal: "A1234ABC", municipio: municipio)
          direccion.save()
        then:
          !direccion.hasErrors()
          Direccion.count()==1
    }

    void "test direccion sin calle"() {
        when:
          Direccion direccion = new Direccion(altura: 123, codigoPostal: "A1234ABC", municipio: municipio)
          direccion.save()
        then:
          direccion.hasErrors()
          direccion.errors.getFieldError('calle')
          direccion.errors.getErrorCount() == 1
          Direccion.count() == 0
    }

    void "test direccion con calle vacia"() {
        when:
          Direccion direccion = new Direccion(calle: '', altura: 123, codigoPostal: "A1234ABC", municipio: municipio)
          direccion.save()
        then:
          direccion.hasErrors()
          direccion.errors.getFieldError('calle')
          direccion.errors.getErrorCount() == 1
          Direccion.count() == 0
    }

    void "test direccion con altura menor a 0"() {
        when:
          Direccion direccion = new Direccion(calle: 'calle falsa', altura: -1
            , codigoPostal: "A1234ABC", municipio: municipio)
          direccion.save()
        then:
          direccion.hasErrors()
          direccion.errors.getFieldError('altura')
          direccion.errors.getErrorCount() == 1
          Direccion.count() == 0
    }

    void "test direccion sin codigo postal"() {
        when:
          Direccion direccion = new Direccion(calle: 'calle falsa', altura: 0, municipio: municipio)
          direccion.save()
        then:
          direccion.hasErrors()
          direccion.errors.getFieldError('codigoPostal')
          direccion.errors.getErrorCount() == 1
          Direccion.count() == 0
    }

    void "test direccion con codigo postal vacio"() {
        when:
          Direccion direccion = new Direccion(calle: 'calle falsa', altura: 0
            , codigoPostal: "", municipio: municipio)
          direccion.save()
        then:
          direccion.hasErrors()
          direccion.errors.getFieldError('codigoPostal')
          direccion.errors.getErrorCount() == 1
          Direccion.count() == 0
    }

    void "test direccion con codigo postal invalido"() {
        when:
          Direccion direccion = new Direccion(calle: 'calle falsa', altura: 0
            , codigoPostal: "A1234AB2", municipio: municipio)
          direccion.save()
        then:
          direccion.hasErrors()
          direccion.errors.getFieldError('codigoPostal')
          direccion.errors.getErrorCount() == 1
          Direccion.count() == 0
    }

    void "test direccion sin municipio"() {
        when:
          Direccion direccion = new Direccion(calle: 'calle falsa', altura: 0
            , codigoPostal: "A1234ABC")
          direccion.save()
        then:
          direccion.hasErrors()
          direccion.errors.getFieldError('municipio')
          direccion.errors.getErrorCount() == 1
          Direccion.count() == 0
    }
}
