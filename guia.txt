import javax.swing.*;
import java.awt.*;

public class CalculadoraGUI {
    private JFrame frame;
    private JTextField num1Field, num2Field, resultadoField;
    private Calculadora calculadora;

    public CalculadoraGUI() {
        calculadora = new Calculadora();
        frame = new JFrame("Calculadora XP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);
        frame.setLayout(new BorderLayout(5,5));

        // Painel do resultado
        JPanel resultadoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resultadoPanel.add(new JLabel("Resultado:"));
        resultadoField = new JTextField(15);
        resultadoField.setEditable(false);
        resultadoPanel.add(resultadoField);

        // Painel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Entradas"));
        inputPanel.add(new JLabel("Número 1:"));
        num1Field = new JTextField();
        inputPanel.add(num1Field);
        inputPanel.add(new JLabel("Número 2:"));
        num2Field = new JTextField();
        inputPanel.add(num2Field);

        // Painel dos botões
        JPanel botoesPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        botoesPanel.setBorder(BorderFactory.createTitledBorder("Operações"));

        JButton somaBtn = new JButton("+");
        JButton subtraiBtn = new JButton("-");
        JButton multiplicaBtn = new JButton("×");
        JButton divideBtn = new JButton("÷");
        JButton raizBtn = new JButton("√");
        JButton potenciaBtn = new JButton("^x");

        // Eventos
        somaBtn.addActionListener(e -> calcular("soma"));
        subtraiBtn.addActionListener(e -> calcular("subtracao"));
        multiplicaBtn.addActionListener(e -> calcular("multiplicacao"));
        divideBtn.addActionListener(e -> calcular("divisao"));
        raizBtn.addActionListener(e -> calcular("raizQuadrada"));
        potenciaBtn.addActionListener(e -> calcular("potencia"));

        botoesPanel.add(somaBtn);
        botoesPanel.add(subtraiBtn);
        botoesPanel.add(multiplicaBtn);
        botoesPanel.add(divideBtn);
        botoesPanel.add(raizBtn);
        botoesPanel.add(potenciaBtn);

        // Organização final
        frame.add(resultadoPanel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(botoesPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void calcular(String operacao) {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = num2Field.getText().isEmpty() ? 0 : Double.parseDouble(num2Field.getText());
            double resultado = 0;

            switch (operacao) {
                case "soma": resultado = calculadora.soma(num1, num2); break;
                case "subtracao": resultado = calculadora.subtracao(num1, num2); break;
                case "multiplicacao": resultado = calculadora.multiplicacao(num1, num2); break;
                case "divisao": resultado = calculadora.divisao(num1, num2); break;
                case "raizQuadrada": resultado = calculadora.raizQuadrada(num1); break;
                case "potencia": resultado = calculadora.potencia(num1, num2); break;
            }
            resultadoField.setText(String.valueOf(resultado));
        } catch (Exception e) {
            resultadoField.setText("Erro");
        }
    }
}
