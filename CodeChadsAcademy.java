import java.util.Random;

public class CodeChadsAcademy {

    public static void main(String[] args) {
        // Simulamos 4 estudiantes
        int[][] alumnos = new int[4][5]; // 4 alumnos, 5 notas cada uno
        Random rnd = new Random();

        // ============= 1ERA PARTE: CARGA DE NOTAS =============
        for (int i = 0; i < alumnos.length; i++) {
            // primeras 3 notas aleatorias entre 40 y 100
            alumnos[i][0] = 50 + rnd.nextInt(51);
            alumnos[i][1] = 50 + rnd.nextInt(51);
            alumnos[i][2] = 50 + rnd.nextInt(51);

            // nota 4: depende de nota 2
            if (alumnos[i][1] < 60) {
                alumnos[i][3] = 100;
            } else {
                alumnos[i][3] = alumnos[i][1];
            }

            // nota 5: depende de nota1+nota3
            if (alumnos[i][0] + alumnos[i][2] > 150) {
                alumnos[i][4] = 95;
            } else {
                alumnos[i][4] = 70;
            }
        }

        // ============= 2DA PARTE: ANÁLISIS =============
        for (int i = 0; i < alumnos.length; i++) {
            int[] notas = alumnos[i];
            System.out.println("\n=== Alumno " + (i+1) + " ===");

            // 1. Verificar aprobados
            int aprobadas = 0;
            for (int n : notas) {
                if (n >= 60) aprobadas++;
            }
            if (aprobadas == 5) {
                System.out.println("Resultado: Aprobaste todas. ¡Backend Sensei!");
            } else if (aprobadas == 0) {
                System.out.println("Resultado: No aprobaste ninguna. ¡Sos un clon de frontend!");
            } else {
                System.out.println("Resultado: Algunas aprobadas. Sos un refactor en progreso.");
            }

            // 2. Mayor variación consecutiva
            int maxSalto = 0, pos = -1;
            for (int j = 0; j < notas.length-1; j++) {
                int dif = Math.abs(notas[j] - notas[j+1]);
                if (dif > maxSalto) {
                    maxSalto = dif;
                    pos = j;
                }
            }
            System.out.println("Mayor salto fue de " + maxSalto + " puntos entre la prueba " 
                               + (pos+1) + " y la prueba " + (pos+2) + ".");

            // 3. Bonus por progreso
            boolean progresivo = true;
            for (int j = 0; j < notas.length-1; j++) {
                if (notas[j] >= notas[j+1]) {
                    progresivo = false;
                    break;
                }
            }
            if (progresivo) {
                System.out.println("¡Nivel PROGRESIVO! Sos un Stone Chad en crecimiento 📈");
            }

            // 4. Ordenar sin sort (burbujeo manual)
            int[] ordenadas = notas.clone();
            for (int a = 0; a < ordenadas.length; a++) {
                for (int b = a+1; b < ordenadas.length; b++) {
                    if (ordenadas[a] < ordenadas[b]) {
                        int temp = ordenadas[a];
                        ordenadas[a] = ordenadas[b];
                        ordenadas[b] = temp;
                    }
                }
            }
            System.out.print("Notas ordenadas de mayor a menor: ");
            for (int n : ordenadas) System.out.print(n + " ");
            System.out.println();

            // 5. Evaluación final por nivel
            int total = 0;
            for (int n : notas) total += n;
            if (total < 250) {
                System.out.println("Evaluación final: Normie total 😢");
            } else if (total < 350) {
                System.out.println("Evaluación final: Soft Chad");
            } else if (total < 450) {
                System.out.println("Evaluación final: Chad");
            } else {
                System.out.println("Evaluación final: Stone Chad definitivo 💪");
            }
        }

        // ============= 6. RANKING ENTRE VARIOS ALUMNOS =============
        System.out.println("\n=== Ranking Final ===");

        // Promedio más alto
        double maxProm = -1;
        int idxProm = -1;
        for (int i = 0; i < alumnos.length; i++) {
            double prom = promedio(alumnos[i]);
            if (prom > maxProm) {
                maxProm = prom;
                idxProm = i;
            }
        }
        System.out.println("Alumno con mayor promedio: " + (idxProm+1) + " con " + maxProm);

        // Más regular (menor desviación)
        double menorDesv = Double.MAX_VALUE;
        int idxReg = -1;
        for (int i = 0; i < alumnos.length; i++) {
            double desv = desviacion(alumnos[i]);
            if (desv < menorDesv) {
                menorDesv = desv;
                idxReg = i;
            }
        }
        System.out.println("Alumno más regular: " + (idxReg+1) + " con desviación " + menorDesv);

        // Peor en la tercera prueba
        int peorNota = 101, idxPeor = -1;
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i][2] < peorNota) {
                peorNota = alumnos[i][2];
                idxPeor = i;
            }
        }
        System.out.println("Alumno con peor rendimiento en la 3ra prueba: " + (idxPeor+1) + " con nota " + peorNota);
    }

    // Funciones auxiliares
    static double promedio(int[] notas) {
        int sum = 0;
        for (int n : notas) sum += n;
        return sum / 5.0;
    }

    static double desviacion(int[] notas) {
        double prom = promedio(notas);
        double suma = 0;
        for (int n : notas) {
            suma += Math.pow(n - prom, 2);
        }
        return Math.sqrt(suma / notas.length);
    }
}
