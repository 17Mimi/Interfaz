package grafico;

import javax.swing.*;
import java.awt.*;
import logica.Becas;
import logica.Estudiantes;
import java.util.ArrayList;

public class Reporte extends JFrame {
    private JTextArea textAreaBecados;
    private JComboBox<String> comboBoxCarrera;
    private JComboBox<String> comboBoxSexo;
    private JButton btnFiltrar;
    private Becas becas;

    public Reporte(Becas becas) {
        this.becas = becas;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 666, 483);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Reportes");
        lblNewLabel.setBounds(238, 10, 149, 46);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Estudiantes Becados:");
        lblNewLabel_1.setBounds(52, 106, 221, 35);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPane.add(lblNewLabel_1);

        textAreaBecados = new JTextArea();
        textAreaBecados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaBecados);
        scrollPane.setBounds(52, 151, 550, 220);
        contentPane.add(scrollPane);

        comboBoxCarrera = new JComboBox<>(new String[]{"Todas", "Ingeniería Civil", "Ingeniería Eléctrica", "Ingeniería Industrial", "Ingeniería en Sistemas", "Ingeniería Mecánica", "Ingeniería Marítima"});
        comboBoxCarrera.setBounds(52, 380, 200, 25);
        contentPane.add(comboBoxCarrera);

        comboBoxSexo = new JComboBox<>(new String[]{"Todos", "Masculino", "Femenino", "Otro"});
        comboBoxSexo.setBounds(262, 380, 150, 25);
        contentPane.add(comboBoxSexo);

        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(422, 380, 100, 25);
        contentPane.add(btnFiltrar);

        btnFiltrar.addActionListener(e -> filtrarBecados());

        mostrarBecados(); 
    }

    private void mostrarBecados() {
        ArrayList<Estudiantes> becados = becas.obtenerEstudiantesBecados();
        mostrarEstudiantesEnTextArea(becados);
    }

    private void mostrarEstudiantesEnTextArea(ArrayList<Estudiantes> estudiantes) {
        StringBuilder sb = new StringBuilder();
        for (Estudiantes estudiante : estudiantes) {
            sb.append("Nombre: ").append(estudiante.getNombre())
              .append(", Cédula: ").append(estudiante.getCedula())
              .append(", Carrera: ").append(estudiante.getCarrera())
              .append(", Índice: ").append(estudiante.getIndiceAcademico())
              .append(", Sexo: ").append(estudiante.getSexo())
              .append("\n\n");
        }
        textAreaBecados.setText(sb.toString());
    }

    private void filtrarBecados() {
        String carrera = (String) comboBoxCarrera.getSelectedItem();
        String sexo = (String) comboBoxSexo.getSelectedItem();

        ArrayList<Estudiantes> becadosFiltrados = new ArrayList<>();
        ArrayList<Estudiantes> todosBecados = becas.obtenerEstudiantesBecados();

        for (Estudiantes estudiante : todosBecados) {
            boolean cumpleCarrera = carrera.equals("Todas") || estudiante.getCarrera().equals(carrera);
            boolean cumpleSexo = sexo.equals("Todos") || estudiante.getSexo().equalsIgnoreCase(sexo);

            if (cumpleCarrera && cumpleSexo) {
                becadosFiltrados.add(estudiante);
            }
        }

        mostrarEstudiantesEnTextArea(becadosFiltrados);
    }
}
