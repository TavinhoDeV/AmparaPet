import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class LoginView extends JFrame {
    public LoginView() {
        setTitle("Ampara - Sistema de Triagem e Acolhimento");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel();
        add(painelPrincipal);

        GridLayout gridLayout = new GridLayout(1, 2);
        painelPrincipal.setLayout(gridLayout);

        Color verdeMint = new Color(152,251,152);

        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new GridBagLayout());
        painelEsquerdo.setBackground(verdeMint);
        painelPrincipal.add(painelEsquerdo);

        JPanel painelTexto = new JPanel();
        painelTexto.setLayout(new GridLayout(2,1));
        painelTexto.setBackground(verdeMint);
        painelEsquerdo.add(painelTexto);

        JLabel titulo = new JLabel("AMPARA");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 36));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        painelTexto.add(titulo);

        JLabel subtitulo = new JLabel("Sistema de Triagem e Acolhimento");
        subtitulo.setFont(new Font("SansSerif", Font.ITALIC, 18));
        subtitulo.setForeground(Color.WHITE);
        subtitulo.setHorizontalAlignment(JLabel.CENTER);
        painelTexto.add(subtitulo);

        Color verdeClaro = new Color(220,245,225);

        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new GridBagLayout());
        painelDireito.setBackground(verdeClaro);
        painelPrincipal.add(painelDireito);

        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new GridLayout(7,1));
        painelFormulario.setBackground(verdeClaro);
        painelDireito.add(painelFormulario);

        JLabel tituloLogin = new JLabel("Bem-vindo(a)!");
        tituloLogin.setFont(new Font("SansSerif", Font.BOLD, 24));
        tituloLogin.setHorizontalAlignment(JLabel.CENTER);
        tituloLogin.setForeground(new Color(0, 128, 0));
        painelFormulario.add(tituloLogin);

        JLabel labelEmail = new JLabel("E-mail");
        labelEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
        labelEmail.setHorizontalAlignment(JLabel.LEFT);
        labelEmail.setForeground(new Color(0, 128, 0));
        painelFormulario.add(labelEmail);
        
        JTextField campoEmail = new JTextField();
        campoEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campoEmail.setForeground(new Color(0, 128, 0));
        painelFormulario.add(campoEmail);

        JLabel labelSenha = new JLabel("Senha");
        labelSenha.setFont(new Font("SansSerif", Font.PLAIN, 14));
        labelSenha.setHorizontalAlignment(JLabel.LEFT);
        labelSenha.setForeground(new Color(0, 128, 0));
        painelFormulario.add(labelSenha);

        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campoSenha.setForeground(new Color(0, 128, 0));
        painelFormulario.add(campoSenha);

        JButton botaoEntrar = new JButton("ENTRAR");
        botaoEntrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoEntrar.setForeground(verdeMint);
        painelFormulario.add(botaoEntrar);

        botaoEntrar.addActionListener(e -> {
            String email = campoEmail.getText();
            String senha = new String(campoSenha.getPassword());

            if (email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Por favor, preencha todos os campos!",
                    "Campos obrigatórios",
                    JOptionPane.WARNING_MESSAGE
                );
            } else if (!emailValido(email)){
                JOptionPane.showMessageDialog(
                    this,
                    "Digite um e-mail válido!",
                    "E-mail inválido",
                    JOptionPane.WARNING_MESSAGE
                );

            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Login realizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }

        });
    
    }

    private boolean emailValido(String email) {
        return email.contains("@")
            && email.contains(".")
            && !email.startsWith("@")
            && !email.endsWith(".");
    }

}