package ar.edu.unahur.obj2.cloud;

public class AlarmaSaturacionCritica implements SistemaInteresado {
    private boolean alarmaActiva = false;

    @Override
    public void actualizar(ClusterComputo cluster, Double vCPUsMovidas) {
        if (cluster.getVCPUsDisponibles() < 0) {
            alarmaActiva = true;
            System.out.println("¡Alarma! El clúster " + cluster.getIdentificador()
                    + " está en zona de overprovisioning. Disponibilidad: " + cluster.getVCPUsDisponibles() + " vCPUs");
        }
    }

    public boolean estaLaAlarmaActiva() {
        return alarmaActiva;
    }
}