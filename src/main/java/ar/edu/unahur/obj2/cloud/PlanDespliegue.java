package ar.edu.unahur.obj2.cloud;

import java.util.ArrayList;
import java.util.List;

public class PlanDespliegue implements OperacionInfraestructura {
    private List<OperacionInfraestructura> operaciones = new ArrayList<>();

    public void agregar(OperacionInfraestructura operacion) {
        operaciones.add(operacion);
    }

    @Override
    public void ejecutar() throws LimiteOverprovisioningException {
        List<OperacionInfraestructura> ejecutadasConExito = new ArrayList<>();
        try {
            for (OperacionInfraestructura op : operaciones) {
                op.ejecutar();
                ejecutadasConExito.add(op);
            }
        } catch (LimiteOverprovisioningException error) {
            for (OperacionInfraestructura op : ejecutadasConExito) {
                op.deshacer();
            }
            throw error; 
        }
    }

    @Override
    public void deshacer() throws LimiteOverprovisioningException {
        for (OperacionInfraestructura op : operaciones) {
            op.deshacer();
        }
    }
}