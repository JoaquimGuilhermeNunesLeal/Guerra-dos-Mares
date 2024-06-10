public class Tabuleiro {
    private int tamanho;
    private Objeto[][] grid;

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.grid = new Objeto[tamanho][tamanho];
    }

    public void adicionarObjeto(Objeto objeto) {
        grid[objeto.getX()][objeto.getY()] = objeto;
    }

    public String interagir(int x, int y) {
        if (grid[x][y] instanceof Navio) {
            return "Você encontrou um navio!";
        } else if (grid[x][y] instanceof Bomba) {
            return "Você encontrou uma bomba!";
        } else {
            return "Você não encontrou, somente água.";
        }
    }

    public String obterDica(int x, int y) {
        int naviosProximos = 0;
        int bombasProximas = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < tamanho && j >= 0 && j < tamanho) {
                    if (grid[i][j] instanceof Navio) {
                        naviosProximos++;
                    } else if (grid[i][j] instanceof Bomba) {
                        bombasProximas++;
                    }
                }
            }
        }

        if (naviosProximos > 0) {
            return "Dica: Há " + naviosProximos + " navio(s) próximo(s)!";
        } else if (bombasProximas > 0) {
            return "Dica: Há " + bombasProximas + " bomba(s) próxima(s)!";
        } else {
            return "Dica: não há nada por perto.";
        }
    }

    public int obterPontos(int x, int y) {
        if (grid[x][y] instanceof Navio) {
            return 1;
        } else if (grid[x][y] instanceof Bomba) {
            return -1;
        } else {
            return 0;
        }
    }

    public Objeto[][] getGrid() {
        return grid;
    }
}
