import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        DecimalFormat formater = new DecimalFormat("###,###.##");

        ArrayList<Programa> programas = new ArrayList<>();

        Programa dibujo = new Programa("Dibujo", 2500000, 6, 7);
        Programa pintura = new Programa("Pintura", 2700000, 8, 9);
        Programa escultura = new Programa("Escultura", 3000000, 10, 12);

        programas.add(dibujo);
        programas.add(pintura);
        programas.add(escultura);

        int deseaAñadirNuevoNiño = JOptionPane.YES_OPTION;

        do {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del niño");
            String edadTxt = JOptionPane.showInputDialog("Ingrese la edad de: " + nombre);
            int edad = Integer.parseInt(edadTxt);

            Programa programaAplicable = null;

            for (Programa programa : programas) {
                if (edad >= programa.getEdadMinima() && edad <= programa.getEdadMaxima()) {
                    programaAplicable = programa;
                    break;
                }
            }

            if (programaAplicable == null) {
                JOptionPane.showMessageDialog(null, "No hay programas aplicables");
            } else {
                int deseaMatricular = JOptionPane.showConfirmDialog(null,
                        "Le aplica el programa: " + programaAplicable.getNombre() + " con valor: $"
                                + formater.format(programaAplicable.getValorBase()),
                        "¿Desea matricular el niño?", JOptionPane.YES_NO_OPTION);
                if (deseaMatricular == JOptionPane.YES_OPTION) {
                    double totalAPagar = programaAplicable.getValorBase();

                    Niño niño = new Niño(nombre, edad, false, false, false);
                    programaAplicable.obtenerEstudiantes().add(niño);

                    int tieneMaterialesEspeciales = JOptionPane.showConfirmDialog(null,
                            "¿Desea añadir materiales especiales por un valor de: "
                                    + formater.format(programaAplicable.VALOR_MATERIALES_ESPECIALES) + "?",
                            "Añadir valor adicional", JOptionPane.YES_NO_OPTION);
                    if (tieneMaterialesEspeciales == JOptionPane.YES_OPTION) {
                        niño.setTieneMateriales(true);
                        totalAPagar += programaAplicable.VALOR_MATERIALES_ESPECIALES;
                    }

                    int tieneClasesIntensivas = JOptionPane.showConfirmDialog(null,
                            "¿Desea añadir clases intensivas por un valor de: "
                                    + formater.format(programaAplicable.VALOR_CLASES_INTENSIVAS) + "?",
                            "Añadir valor adicional", JOptionPane.YES_NO_OPTION);
                    if (tieneClasesIntensivas == JOptionPane.YES_OPTION) {
                        niño.setTieneClases(true);
                        totalAPagar += programaAplicable.VALOR_CLASES_INTENSIVAS;
                    }

                    int tieneTalleresFinDeSemana = JOptionPane.showConfirmDialog(null,
                            "Desea añadir talleres de fines de semana por valor de: "
                                    + formater.format(programaAplicable.VALOR_TALLERES_FIN_DE_SEMANA) + "?",
                            "Añadir valor adicional", JOptionPane.YES_NO_OPTION);
                    if (tieneTalleresFinDeSemana == JOptionPane.YES_OPTION) {
                        niño.setTieneTalleres(true);
                        totalAPagar += programaAplicable.VALOR_TALLERES_FIN_DE_SEMANA;
                    }

                    JOptionPane.showMessageDialog(null, "VALOR TOTAL A PAGAR: $" + formater.format(totalAPagar),
                            "VALOR TOTAL", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            deseaAñadirNuevoNiño = JOptionPane.showConfirmDialog(null, "¿Desea añadir un nuevo niño?", "Continuar", JOptionPane.YES_NO_OPTION);
        } while (deseaAñadirNuevoNiño == JOptionPane.YES_OPTION);

        String informacion = "";
        double totalEscuela = 0;

        for (Programa programa : programas) {
        double totalPrograma = 0;
        informacion += "nombre del programa: " + programa.getNombre() + "\n";
        ArrayList<Niño> estudiantesPrograma = programa.obtenerEstudiantes();

        if (estudiantesPrograma.size() > 0) {
            for (Niño niño : estudiantesPrograma) {
                double totalNino = calcularTotalNino(programa, niño);

                totalPrograma += totalNino;
                informacion += String.format("   %s (%d) \n", niño.getNombre(), niño.getEdad());
                informacion += String.format("     Valor Base: $%s\n", formater.format(programa.getValorBase()));
                
                if (niño.tieneMateriales) {
                    informacion += String.format("     Materiales: $%s\n", formater.format(programa.VALOR_MATERIALES_ESPECIALES));
                }
                if (niño.tieneClases) {
                    informacion += String.format("     Clases intensivas: $%s\n", formater.format(programa.VALOR_CLASES_INTENSIVAS));
                }
                if (niño.tieneTalleres) {
                    informacion += String.format("     Talleres fin de semana: $%s\n", formater.format(programa.VALOR_TALLERES_FIN_DE_SEMANA));
                }
                informacion += String.format("   Total a pagar: $%s\n", formater.format(totalNino));
                informacion += "___________________________________\n";
            }
        } else {
            informacion += "No hay estudiantes matriculados en este programa.\n";
        }
        informacion += String.format("Total Progama %s: $%s\n", programa.getNombre(), formater.format(totalPrograma));
        informacion += "_______________________________________\n\n";

        totalEscuela += totalPrograma; 

    }
    
    JOptionPane.showMessageDialog(null, informacion + "TOTAL ESCUELA: $" + formater.format(totalEscuela));
}

// calculamos el total de niñ
static double calcularTotalNino(Programa programa, Niño niño) {
    double total = programa.getValorBase();
    if (niño.tieneMateriales) {
        total += programa.VALOR_MATERIALES_ESPECIALES;
    }
    if (niño.tieneClases) {
        total += programa.VALOR_CLASES_INTENSIVAS;
    }
    if (niño.tieneTalleres) {
        total += programa.VALOR_TALLERES_FIN_DE_SEMANA;
    }
    return total;
    }
}
