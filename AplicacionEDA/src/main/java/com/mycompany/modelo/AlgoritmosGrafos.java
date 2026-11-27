package com.mycompany.modelo;

public class AlgoritmosGrafos {

    public enum Tipo {
        ARBOL_TOPOLOGICO("Árbol topológico"),
        TSP("TSP"),
        PRIM("Prim"),
        KAHN("Kahn"),
        KRUSKAL("Kruskal"),
        DIJKSTRA("Dijkstra");

        private final String nombre;
        Tipo(String n) { this.nombre = n; }
        public String getNombre() { return nombre; }

        public static Tipo desde(String s) {
            if (s == null) return null;
            for (Tipo t : values()) if (t.nombre.equalsIgnoreCase(s)) return t;
            // compatibilidad con nombres antiguos del combo
            if (s.equalsIgnoreCase("TSM")) return TSP;
            if (s.equalsIgnoreCase("Khan")) return KAHN;
            if (s.equalsIgnoreCase("Kruskol")) return KRUSKAL;
            if (s.equalsIgnoreCase("Djisktra")) return DIJKSTRA;
            return null;
        }
    }

    public static String ejecutar(Tipo tipo) {
        if (tipo == null) return "Tipo de algoritmo no válido.";
        switch (tipo) {
            case PRIM:            return prim();
            case KRUSKAL:         return kruskal();
            case DIJKSTRA:        return dijkstra();
            case TSP:             return tsp();
            case KAHN:            return kahn();
            case ARBOL_TOPOLOGICO:return topologicoDFS();
            default:              return "No implementado.";
        }
    }

    // ====== Datos precompilados ======
    private static final String[] NODOS = {"A", "B", "C", "D", "E"};
    private static final double[][] MATRIZ = {
        {0, 2, 0, 6, 0},
        {2, 0, 3, 8, 5},
        {0, 3, 0, 0, 7},
        {6, 8, 0, 0, 9},
        {0, 5, 7, 9, 0}
    };

    // ====== PRIM ======
    private static String prim() {
        int n = MATRIZ.length;
        boolean[] vis = new boolean[n];
        double[] key = new double[n];
        int[] par = new int[n];
        java.util.Arrays.fill(key, Double.MAX_VALUE);
        java.util.Arrays.fill(par, -1);
        key[0] = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("=== ALGORITMO DE PRIM (MST) ===\n");
        sb.append("Grafo de 5 nodos (A,B,C,D,E) ponderado\n\n");
        double total = 0;
        for (int it = 0; it < n; it++) {
            int u = -1;
            double mn = Double.MAX_VALUE;
            for (int i = 0; i < n; i++)
                if (!vis[i] && key[i] < mn) { mn = key[i]; u = i; }
            if (u == -1) break;
            vis[u] = true;
            if (par[u] == -1) {
                sb.append("Inicio en nodo: ").append(NODOS[u]).append("\n");
            } else {
                sb.append("Arista: ").append(NODOS[par[u]]).append(" - ").append(NODOS[u])
                  .append("  (peso ").append(MATRIZ[u][par[u]]).append(")\n");
                total += MATRIZ[u][par[u]];
            }
            for (int v = 0; v < n; v++)
                if (MATRIZ[u][v] > 0 && !vis[v] && MATRIZ[u][v] < key[v]) {
                    key[v] = MATRIZ[u][v];
                    par[v] = u;
                }
        }
        sb.append("\nPeso total del MST: ").append(total).append("\n");
        return sb.toString();
    }

    // ====== KRUSKAL ======
    private static String kruskal() {
        int n = MATRIZ.length;
        java.util.List<int[]> aristas = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (MATRIZ[i][j] > 0)
                    aristas.add(new int[]{i, j, (int) MATRIZ[i][j]});
        aristas.sort((a, b) -> Integer.compare(a[2], b[2]));
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        StringBuilder sb = new StringBuilder();
        sb.append("=== ALGORITMO DE KRUSKAL (MST) ===\n");
        sb.append("Aristas ordenadas por peso:\n");
        for (int[] e : aristas)
            sb.append("  ").append(NODOS[e[0]]).append("-").append(NODOS[e[1]])
              .append("  peso ").append(e[2]).append("\n");
        sb.append("\nProceso:\n");
        int total = 0, usadas = 0;
        for (int[] e : aristas) {
            int ra = find(p, e[0]), rb = find(p, e[1]);
            if (ra != rb) {
                p[ra] = rb;
                sb.append("  ✓ Aceptar ").append(NODOS[e[0]]).append("-").append(NODOS[e[1]])
                  .append(" (peso ").append(e[2]).append(")\n");
                total += e[2];
                usadas++;
                if (usadas == n - 1) break;
            } else {
                sb.append("  ✗ Rechazar ").append(NODOS[e[0]]).append("-").append(NODOS[e[1]])
                  .append(" (forma ciclo)\n");
            }
        }
        sb.append("\nPeso total del MST: ").append(total).append("\n");
        return sb.toString();
    }

    private static int find(int[] p, int x) {
        while (p[x] != x) { p[x] = p[p[x]]; x = p[x]; }
        return x;
    }

    // ====== DIJKSTRA ======
    private static String dijkstra() {
        int n = MATRIZ.length;
        double[] dist = new double[n];
        boolean[] vis = new boolean[n];
        java.util.Arrays.fill(dist, Double.MAX_VALUE);
        int[] par = new int[n];
        java.util.Arrays.fill(par, -1);
        dist[0] = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("=== ALGORITMO DE DIJKSTRA ===\n");
        sb.append("Origen: A\n\n");
        for (int it = 0; it < n; it++) {
            int u = -1;
            double mn = Double.MAX_VALUE;
            for (int i = 0; i < n; i++)
                if (!vis[i] && dist[i] < mn) { mn = dist[i]; u = i; }
            if (u == -1) break;
            vis[u] = true;
            for (int v = 0; v < n; v++)
                if (MATRIZ[u][v] > 0 && dist[u] + MATRIZ[u][v] < dist[v]) {
                    dist[v] = dist[u] + MATRIZ[u][v];
                    par[v] = u;
                }
        }
        sb.append("Distancias mínimas desde A:\n");
        for (int i = 0; i < n; i++)
            sb.append("  A -> ").append(NODOS[i]).append(" : ")
              .append(dist[i] == Double.MAX_VALUE ? "∞" : (int) dist[i]).append("\n");
        sb.append("\nRutas:\n");
        for (int i = 1; i < n; i++) {
            if (dist[i] == Double.MAX_VALUE) continue;
            java.util.List<String> ruta = new java.util.ArrayList<>();
            int x = i;
            while (x != -1) { ruta.add(0, NODOS[x]); x = par[x]; }
            sb.append("  A -> ").append(NODOS[i]).append(" : ")
              .append(String.join(" -> ", ruta)).append("\n");
        }
        return sb.toString();
    }

    // ====== TSP ======
    private static String tsp() {
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
        StringBuilder sb = new StringBuilder();
        sb.append("=== TSP (Problema del Viajante) ===\n");
        sb.append("4 ciudades — evaluación exhaustiva (3! = 6 tours)\n\n");
        for (int[] p : perms) {
            int c = d[0][p[0]] + d[p[0]][p[1]] + d[p[1]][p[2]] + d[p[2]][0];
            sb.append("  Ruta 0 -> ").append(p[0]).append(" -> ").append(p[1])
              .append(" -> ").append(p[2]).append(" -> 0   costo = ").append(c).append("\n");
            if (c < mejor) { mejor = c; mejorP = p.clone(); }
        }
        sb.append("\nÓptimo: 0 -> ").append(mejorP[0]).append(" -> ").append(mejorP[1])
          .append(" -> ").append(mejorP[2]).append(" -> 0\n");
        sb.append("Costo mínimo: ").append(mejor).append("\n");
        sb.append("\nComplejidad: O(n!)  →  NP-Hard\n");
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

    // ====== KAHN ======
    private static String kahn() {
        int n = 5;
        int[][] adj = {{1, 2}, {3}, {3}, {4}, {}};
        int[] indeg = new int[n];
        for (int u = 0; u < n; u++)
            for (int v : adj[u]) indeg[v]++;
        StringBuilder sb = new StringBuilder();
        sb.append("=== ALGORITMO DE KAHN (topológico BFS) ===\n");
        sb.append("DAG de 5 nodos (0..4)\n\nGrados de entrada iniciales:\n");
        for (int i = 0; i < n; i++) sb.append("  nodo ").append(i).append(" : ").append(indeg[i]).append("\n");
        java.util.Queue<Integer> q = new java.util.LinkedList<>();
        for (int i = 0; i < n; i++) if (indeg[i] == 0) q.add(i);
        java.util.List<Integer> orden = new java.util.ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            orden.add(u);
            sb.append("\nProcesar nodo ").append(u).append("\n");
            for (int v : adj[u]) {
                indeg[v]--;
                if (indeg[v] == 0) q.add(v);
            }
        }
        if (orden.size() != n) sb.append("\n¡El grafo tiene ciclo!\n");
        else {
            sb.append("\nOrden topológico: ");
            for (int i = 0; i < orden.size(); i++) {
                if (i > 0) sb.append(" -> ");
                sb.append(orden.get(i));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // ====== TOPOLÓGICO DFS ======
    private static String topologicoDFS() {
        int n = 5;
        int[][] adj = {{1, 2}, {3}, {3}, {4}, {}};
        boolean[] vis = new boolean[n];
        java.util.Deque<Integer> pila = new java.util.ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        sb.append("=== ÁRBOL TOPOLÓGICO (DFS) ===\n");
        sb.append("Ordenamiento topológico usando DFS\n\n");
        for (int i = 0; i < n; i++)
            if (!vis[i]) dfs(i, adj, vis, pila, sb);
        sb.append("\nOrden topológico (post-orden invertido):\n  ");
        while (!pila.isEmpty()) sb.append(pila.pop()).append(" ");
        sb.append("\n");
        return sb.toString();
    }

    private static void dfs(int u, int[][] adj, boolean[] vis,
                            java.util.Deque<Integer> pila, StringBuilder sb) {
        vis[u] = true;
        for (int v : adj[u]) if (!vis[v]) dfs(v, adj, vis, pila, sb);
        pila.push(u);
        sb.append("  Visitado y apilado nodo ").append(u).append("\n");
    }
}