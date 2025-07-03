import java.sql.*;


interface OperacoesMatematicas {
    double soma(double a, double b);
    double subtracao(double a, double b);
    double multiplicacao(double a, double b);
    double divisao(double a, double b);
    double raizQuadrada(double a);
    double potencia(double base, double expoente);
    void salvarResultado(String operacao, double resultado);
}


class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/calculadora_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "senha";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null; 
        }
    }
}


class Calculadora implements OperacoesMatematicas {
    @Override
    public double soma(double a, double b) {
        double resultado = a + b;
        salvarResultado("soma", resultado);
        return resultado;
    }

    @Override
    public double subtracao(double a, double b) {
        double resultado = a - b;
        salvarResultado("subtracao", resultado);
        return resultado;
    }

    @Override
    public double multiplicacao(double a, double b) {
        double resultado = a * b;
        salvarResultado("multiplicacao", resultado);
        return resultado;
    }

    @Override
    public double divisao(double a, double b) {
        if (b == 0) {
            System.err.println("Erro: Divisão por zero.");
            return Double.NaN; // Retorna NaN ao invés de lançar exceção
        }
        double resultado = a / b;
        salvarResultado("divisao", resultado);
        return resultado;
    }

    @Override
    public double raizQuadrada(double a) {
        if (a < 0) {
            System.err.println("Erro: Número negativo não possui raiz real.");
            return Double.NaN;
        }
        double resultado = Math.sqrt(a);
        salvarResultado("raizQuadrada", resultado);
        return resultado;
    }

    @Override
    public double potencia(double base, double expoente) {
        double resultado = Math.pow(base, expoente);
        salvarResultado("potencia", resultado);
        return resultado;
    }

    @Override
    public void salvarResultado(String operacao, double resultado) {
        String sql = "INSERT INTO calculadora_resultados (operacao, resultado) VALUES (?, ?);";
        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (conn != null) {  // Verifica se a conexão foi bem-sucedida
                stmt.setString(1, operacao);
                stmt.setDouble(2, resultado);
                stmt.executeUpdate();
            } else {
                 System.err.println("Não foi possível salvar o resultado. Sem conexão com o banco.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar resultado no banco: " + e.getMessage());
        }
    }
}

// Classe principal para iniciar o programa
public class CalculadoraApp {
    public static void main(String[] args) {
        new CalculadoraGUI(); // Inicia a interface gráfica
    }
}

