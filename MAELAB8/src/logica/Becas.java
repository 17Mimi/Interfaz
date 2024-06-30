package logica;

import java.util.ArrayList;

public class Becas {
    private static final int MAX_ESTUDIANTES = 100;
    private ArrayList<Estudiantes> estudiantes;

    public Becas() {
        estudiantes = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiantes estudiante) {
        if (estudiantes.size() < MAX_ESTUDIANTES) {
            estudiantes.add(estudiante);
        } else {
            throw new IllegalStateException("No se puede agregar más estudiantes. Límite alcanzado.");
        }
    }

    public ArrayList<Estudiantes> obtenerEstudiantesBecados() {
        ArrayList<Estudiantes> estudiantesBecados = new ArrayList<>();
        for (Estudiantes estudiante : estudiantes) {
            if (estudiante.getIndiceAcademico() >= 2.0) {
                estudiantesBecados.add(estudiante);
            }
        }
        return estudiantesBecados;
    }
    public ArrayList<Estudiantes> obtenerEstudiantesBecadosPorSexo(String sexo) {
        ArrayList<Estudiantes> estudiantesBecadosPorSexo = new ArrayList<>();
        for (Estudiantes estudiante : estudiantes) {
            if (estudiante.getIndiceAcademico() >= 2.0 && estudiante.getSexo().equalsIgnoreCase(sexo)) {
                estudiantesBecadosPorSexo.add(estudiante);
            }
        }
        return estudiantesBecadosPorSexo;
    }
    
    public Estudiantes buscarPorCedula(String cedula) {
        for (Estudiantes estudiante : estudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                return estudiante;
            }
        }
        return null;
    }
    public ArrayList<Estudiantes> buscarBecadosPorCarreraYSexo(String carrera, String sexo) {
        ArrayList<Estudiantes> becadosFiltrados = new ArrayList<>();
        for (Estudiantes estudiante : obtenerEstudiantesBecados()) {
            if ((carrera.equals("Todas") || estudiante.getCarrera().equals(carrera)) &&
                (sexo.equals("Todos") || estudiante.getSexo().equals(sexo))) {
                becadosFiltrados.add(estudiante);
            }
        }
        return becadosFiltrados;
    }
}
