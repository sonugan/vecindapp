package vecindapp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Institucion)
class InstitucionSpec extends HibernateSpec {
    Direccion direccion

    def setup() {
      direccion = new Direccion(calle: "calle falsa", altura: 100, codigoPostal: "A1234ABC"
        , municipio: new Municipio(nombre: "Tres de Febrero"
          , provincia: new Provincia(nombre: "Buenos Aires")))
    }

    def cleanup() {
    }

    void "test institucion valida"() {
        when:
          Institucion institucion = new Institucion(nombre: 'Distrito 13', usuario: "distr13", direccion: direccion)
          institucion.save()
        then:
          !institucion.hasErrors()
          Institucion.count()==1
    }

    void "test institucion sin nombre"() {
        when:
          Institucion institucion = new Institucion(usuario: "distr13", direccion: direccion)
          institucion.save()
        then:
          institucion.hasErrors()
          institucion.errors.getFieldError('nombre')
          institucion.errors.getErrorCount() == 1
          Institucion.count() == 0
    }

    void "test institucion con nombre vacio"() {
        when:
          Institucion institucion = new Institucion(nombre: '', usuario: "distr13", direccion: direccion)
          institucion.save()
        then:
          institucion.hasErrors()
          institucion.errors.getFieldError('nombre')
          institucion.errors.getErrorCount() == 1
          Institucion.count() == 0
    }

    void "test institucion con nombre mas largo de lo permitido"() {
        when:
          Institucion institucion = new Institucion(
            nombre: "1234567890123456789012345678901234567890123456789012345678901"
            , usuario: 'distr13', direccion: direccion)
          institucion.save()
        then:
          institucion.hasErrors()
          institucion.errors.getFieldError('nombre')
          institucion.errors.getErrorCount() == 1
          Institucion.count() == 0
    }

    void "test institucion con nombre mas corto de lo permitido"() {
        when:
          Institucion institucion = new Institucion(
            nombre: "123"
            , usuario: 'distr13', direccion: direccion)
          institucion.save()
        then:
          institucion.hasErrors()
          institucion.errors.getFieldError('nombre')
          institucion.errors.getErrorCount() == 1
          Institucion.count() == 0
    }

    void "test institucion sin usuario"() {
        when:
          Institucion institucion = new Institucion(nombre: "Distrito 13", direccion: direccion)
          institucion.save()
        then:
          institucion.hasErrors()
          institucion.errors.getFieldError('usuario')
          institucion.errors.getErrorCount() == 1
          Institucion.count() == 0
    }

    void "test institucion usuario vacio"() {
        when:
          Institucion institucion = new Institucion(nombre: "Distrito 13", usuario: '', direccion: direccion)
          institucion.save()
        then:
          institucion.hasErrors()
          institucion.errors.getFieldError('usuario')
          institucion.errors.getErrorCount() == 1
          Institucion.count() == 0
    }

    void "test institucion con usuario mas largo de lo permitido"() {
        when:
          Institucion institucion = new Institucion(
            nombre: "Distrito 13", usuario: 'asdfghijuhh', direccion: direccion)
          institucion.save()
        then:
          institucion.hasErrors()
          institucion.errors.getFieldError('usuario')
          institucion.errors.getErrorCount() == 1
          Institucion.count() == 0
    }

    void "test institucion con usuario mas corto de lo permitido"() {
        when:
          Institucion institucion = new Institucion(
            nombre: "Distrito 13", usuario: 'asd', direccion: direccion)
          institucion.save()
        then:
          institucion.hasErrors()
          institucion.errors.getFieldError('usuario')
          institucion.errors.getErrorCount() == 1
          Institucion.count() == 0
    }
}
