import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Jogo extends JFrame {
    private int tamanho;
    private Tabuleiro tabuleiro;
    private Pontos pontos;
    private JButton[][] buttons;
    private JLabel pontosLabel;
    private JLabel chancesLabel;
    private int numNavios;
    private int numBombas;
    private int numAgua;
    private String nomeJogador;
    
    
    public Jogo(int tamanho, int numNavios, int numBombas, int numAgua) {
        this.tamanho = tamanho;
        this.numNavios = numNavios;
        this.numBombas = numBombas;
        this.numAgua = numAgua;
        exibirTelaInicial();
    }
 private void exibirTelaInicial() {
        JFrame telaInicial = new JFrame("Batalha Naval - Entrada do Jogador");
        telaInicial.setSize(400, 300);
        telaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaInicial.setLayout(new BorderLayout());
JLabel nomeLabel = new JLabel("Digite seu nome:");
        nomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField nomeField = new JTextField();
        nomeField.setHorizontalAlignment(JTextField.CENTER);

        JButton iniciarButton = new JButton("Iniciar Jogo");
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nomeJogador = nomeField.getText();
                if (nomeJogador.isEmpty()) {
                    JOptionPane.showMessageDialog(telaInicial, "Por favor, digite um nome.");
                } else {
                    telaInicial.dispose();
                    iniciarJogo();
                }
            }
        });

        telaInicial.add(nomeLabel, BorderLayout.NORTH);
        telaInicial.add(nomeField, BorderLayout.CENTER);
        telaInicial.add(iniciarButton, BorderLayout.SOUTH);
        telaInicial.setVisible(true);
    }

    private void iniciarJogo() {
        this.tabuleiro = new Tabuleiro(tamanho);
        this.pontos = new Pontos();
        inicializarTabuleiro(numNavios, numBombas, numAgua);
        initComponents();
        
    }
   
    private void inicializarTabuleiro(int numNavios, int numBombas, int numAgua1) {
        Random rand = new Random();
        int colocados = 0;
        while (colocados < numNavios) {
            int x = rand.nextInt(tamanho);
            int y = rand.nextInt(tamanho);
            if (tabuleiro.getGrid()[x][y] == null) {
                tabuleiro.adicionarObjeto(new Navio(x, y));
                colocados++;
            }
        }

        colocados = 0;
        while (colocados < numBombas) {
            int x = rand.nextInt(tamanho);
            int y = rand.nextInt(tamanho);
            if (tabuleiro.getGrid()[x][y] == null) {
                tabuleiro.adicionarObjeto(new Bomba(x, y));
                colocados++;
            }
        }
        colocados = 0;
        while (colocados < numAgua) {
            int x = rand.nextInt(tamanho);
            int y = rand.nextInt(tamanho);
            if (tabuleiro.getGrid()[x][y] == null) {
                tabuleiro.adicionarObjeto(new Agua(x, y));
                colocados++;
            }
        }
    }

    private void initComponents() {
        setTitle("Batalha Naval");
        setSize(600, 600);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(tamanho, tamanho));
        buttons = new JButton[tamanho][tamanho];
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                final int x = i;
                final int y = j;
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jogar(x, y);
                    }
                });
                gridPanel.add(buttons[i][j]);
            }
        }

        JPanel statusPanel = new JPanel(new GridLayout(1, 2));
        pontosLabel = new JLabel("Pontos: " + pontos.getPontos());
        pontosLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chancesLabel = new JLabel("Chances: " + pontos.getChances());
        chancesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusPanel.add(pontosLabel);
        statusPanel.add(chancesLabel);

        add(gridPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void jogar(int x, int y) {
        Objeto objeto  = tabuleiro.getGrid()[x][y];
        String resultado = tabuleiro.interagir(x, y);
        int pontosObtidos = tabuleiro.obterPontos(x, y);
        pontos.adicionarPontos(pontosObtidos);
        pontos.decrementarChances();
        if (pontosObtidos > 0) {
            buttons[x][y].setText("Navio");
            buttons[x][y].setIcon(((Navio)objeto).getIcone());
        } else if (pontosObtidos < 0) {
            buttons[x][y].setText("Bomba");
            buttons[x][y].setIcon(((Bomba)objeto).getIcone());
        } else {
            buttons[x][y].setText("Água");
            buttons[x][y].setIcon(((Agua)objeto).getIcone());
            
        }
        buttons[x][y].setEnabled(false);
        pontosLabel.setText("Pontos: " + pontos.getPontos());
        chancesLabel.setText("Chances: " + pontos.getChances());
        JOptionPane.showMessageDialog(this, resultado);

        if (pontos.jogoTerminado()) {
            if (pontos.jogadorGanhou()) {
                JOptionPane.showMessageDialog(this, "Parabéns! " + nomeJogador + " Você venceu o jogo!");
            } else {
                JOptionPane.showMessageDialog(this, "Fim de jogo!!! " + nomeJogador + " Você perdeu.");
            }
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja jogar novamente?", "Jogo Terminado", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                reiniciarJogo();
            } else {
                System.exit(0);
            }
        } else {
            String dica = tabuleiro.obterDica(x, y);
            JOptionPane.showMessageDialog(this, dica);
        }
    }

    private void reiniciarJogo() {
        getContentPane().removeAll();
        iniciarJogo();
        revalidate();
        repaint();
    }

}



