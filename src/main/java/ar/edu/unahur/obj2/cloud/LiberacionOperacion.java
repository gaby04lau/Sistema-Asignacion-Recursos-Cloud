package ar.edu.unahur.obj2.cloud;

public class LiberacionOperacion implements OperacionInfraestructura {
    private ClusterComputo cluster;
    private Double vCPUs;

    public LiberacionOperacion(ClusterComputo cluster, Double vCPUs) {
        this.cluster = cluster;
        this.vCPUs = vCPUs;
    }

    @Override
    public void ejecutar() {
        cluster.liberar(vCPUs);
    }

    @Override
    public void deshacer() throws LimiteOverprovisioningException {
        cluster.asignar(vCPUs);
    }
}
