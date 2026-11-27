package com.mycompany.modelo;

public class EjemplosComplejidad {

        public static String ejecutarEficiencia() {
        int n = 100000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        int objetivo = n - 1;

        // Búsqueda Lineal
        long t0 = System.nanoTime();
        int pasosL = 0;
        for (int i = 0; i < n; i++) { 
            pasosL++; 
            if (arr[i] == objetivo) break; 
        }
        long t1 = System.nanoTime();

        // Búsqueda Binaria
        int pasosB = 0;
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            pasosB++;
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == objetivo) break;
            else if (arr[mid] < objetivo) lo = mid + 1;
            else hi = mid - 1;
        }
        long t2 = System.nanoTime();

        StringBuilder sb = new StringBuilder();
        sb.append("=== EFICIENCIA ALGORÍTMICA ===\n");
        sb.append("Comparación: búsqueda lineal vs binaria\n\n");
        sb.append("Array de ").append(n).append(" elementos ordenados (0..").append(n - 1).append(")\n");
        sb.append("Buscando el número: ").append(objetivo).append("\n\n");
        sb.append("Búsqueda LINEAL  (O(n)):      ").append(pasosL)
          .append(" pasos | ").append(t1 - t0).append(" ns\n");
        sb.append("Búsqueda BINARIA (O(log n)): ").append(pasosB)
          .append(" pasos | ").append(t2 - t1).append(" ns\n\n");
        sb.append("Conclusión:\nUn algoritmo con mejor complejidad realiza\n");
        sb.append("dramáticamente menos operaciones sobre la misma entrada.\n");
        return sb.toString();
    }

    public static String ejecutarAnalisis() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ANÁLISIS DE COMPLEJIDAD ===\n\n");
        sb.append("Crecimiento del número de operaciones según n:\n\n");
        sb.append(String.format("%-10s %-12s %-12s %-12s %-14s%n",
                "n", "O(1)", "O(log n)", "O(n)", "O(n^2)"));
        sb.append("------------------------------------------------------------\n");
        
        for (int n : new int[]{10, 100, 1000, 10000, 100000}) {
            int logn = (int) (Math.log(n) / Math.log(2));
            sb.append(String.format("%-10d %-12d %-12d %-12d %-14d%n",
                    n, 1, logn, n, (long) n * n));
        }
        
        sb.append("\nNotas:\n");
        sb.append("  O(1)       constante     — acceso a array\n");
        sb.append("  O(log n)   logarítmica   — búsqueda binaria\n");
        sb.append("  O(n)       lineal        — recorrido simple\n");
        sb.append("  O(n log n) lineal-log    — merge sort\n");
        sb.append("  O(n^2)     cuadrática    — bubble sort\n");
        sb.append("  O(2^n)     exponencial   — subset-sum\n");
        sb.append("  O(n!)      factorial     — TSP exhaustivo\n");
        return sb.toString();
    }

    public static String ejecutarP() {
        int[] arr = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        int[] copia = arr.clone();
        java.util.Arrays.sort(copia);
        StringBuilder sb = new StringBuilder();
        sb.append("=== CLASE P ===\n\n");
        sb.append("Problemas resolubles en tiempo polinomial.\n\n");
        sb.append("Ejemplo: ordenar un arreglo (Arrays.sort)\n");
        sb.append("Entrada: ").append(java.util.Arrays.toString(arr)).append("\n");
        sb.append("Salida : ").append(java.util.Arrays.toString(copia)).append("\n\n");
        sb.append("Complejidad: O(n log n)  →  POLINOMIAL\n\n");
        sb.append("P = problemas que una máquina determinista\n");
        sb.append("resuelve en tiempo polinomial.\n");
        return sb.toString();
    }

    public static String ejecutarNP() {
        int[] nums = {3, 34, 4, 12, 5, 2};
        int objetivo = 9;
        int[] sub = {4, 5};
        int s = sub[0] + sub[1];
        StringBuilder sb = new StringBuilder();
        sb.append("=== CLASE NP ===\n\n");
        sb.append("Problemas cuya solución se VERIFICA en tiempo polinomial.\n\n");
        sb.append("Ejemplo: verificar SUBSET-SUM\n");
        sb.append("Conjunto: ").append(java.util.Arrays.toString(nums)).append("\n");
        sb.append("Objetivo: ").append(objetivo).append("\n");
        sb.append("Subconjunto propuesto: ").append(java.util.Arrays.toString(sub)).append("\n");
        sb.append("Suma = ").append(s).append("\n");
        sb.append("¿Es solución? ").append(s == objetivo ? "SÍ ✓" : "NO ✗").append("\n\n");
        sb.append("Verificación: O(n)  →  polinomial  ✓\n");
        sb.append("Encontrar la solución: no se conoce algoritmo polinomial.\n");
        return sb.toString();
    }

    public static String ejecutarNPHard() {
        int[][] d = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        int[] perm = {1, 2, 3};
        java.util.List<int[]> perms = new java.util.ArrayList<>();
        permutar(perm, 0, perms);
        int mejor = Integer.MAX_VALUE;
        int[] mejorP = null;
        for (int[] p : perms) {
            int c = d[0][p[0]] + d[p[0]][p[1]] + d[p[1]][p[2]] + d[p[2]][0];
            if (c < mejor) { mejor = c; mejorP = p.clone(); }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("=== NP-HARD ===\n\n");
        sb.append("Tan difíciles como el problema más difícil de NP.\n\n");
        sb.append("Ejemplo: TSP (optimización) con 4 ciudades\n");
        sb.append("Buscando el tour de menor costo...\n\n");
        sb.append("Ruta óptima: 0 -> ").append(mejorP[0])
          .append(" -> ").append(mejorP[1])
          .append(" -> ").append(mejorP[2]).append(" -> 0\n");
        sb.append("Costo mínimo: ").append(mejor).append("\n\n");
        sb.append("Complejidad exhaustiva: O(n!)  →  NO polinomial\n");
        sb.append("Si hubiera algoritmo polinomial para TSP, P = NP.\n");
        return sb.toString();
    }

    public static String ejecutarNPComplete() {
        int[] nums = {3, 34, 4, 12, 5, 2};
        int objetivo = 9;
        int n = nums.length;
        StringBuilder sb = new StringBuilder();
        sb.append("=== NP-COMPLETE ===\n\n");
        sb.append("En NP y todo problema NP se reduce a él.\n\n");
        sb.append("Ejemplo: SUBSET-SUM (decisión)\n");
        sb.append("Conjunto: ").append(java.util.Arrays.toString(nums)).append("\n");
        sb.append("Objetivo: ").append(objetivo).append("\n\n");
        boolean found = false;
        for (int mask = 0; mask < (1 << n); mask++) {
            int s = 0;
            StringBuilder sub = new StringBuilder("{");
            for (int i = 0; i < n; i++)
                if ((mask & (1 << i)) != 0) { s += nums[i]; sub.append(nums[i]).append(","); }
            sub.append("}");
            if (s == objetivo) {
                sb.append("Solución encontrada: ").append(sub).append("\n");
                found = true;
                break;
            }
        }
        if (!found) sb.append("No existe subconjunto que sume ").append(objetivo).append("\n");
        sb.append("\nComplejidad fuerza bruta: O(2^n)  →  exponencial\n");
        sb.append("NP-Complete = NP ∩ NP-Hard\n");
        return sb.toString();
    }

    private static void permutar(int[] a, int idx, java.util.List<int[]> out) {
        if (idx == a.length) { out.add(a.clone()); return; }
        for (int i = idx; i < a.length; i++) {
            int t = a[idx]; a[idx] = a[i]; a[i] = t;
            permutar(a, idx + 1, out);
            t = a[idx]; a[idx] = a[i]; a[i] = t;
        }
    }
}