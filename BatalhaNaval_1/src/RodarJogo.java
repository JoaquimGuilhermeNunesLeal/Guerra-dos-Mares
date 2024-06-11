// ONDE IRA RODAR O JOGO
public class RodarJogo {
    public static void main(String[] args) {
        int tamanhoTabuleiro = 5;
        int numNavios = 8;
        int numBombas = 5;
        int numAgua = 12; 

        new Jogo(tamanhoTabuleiro, numNavios, numBombas, numAgua);
    }
}
