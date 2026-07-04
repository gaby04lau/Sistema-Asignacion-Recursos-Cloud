package ar.edu.unahur.obj2.cloud;
public class PlanificadorDespliegues {

    public void ejecutar(OperacionInfraestructura operacion) throws LimiteOverprovisioningException {
        operacion.ejecutar();
    }
    
}