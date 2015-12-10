package principal;

import dao.FuncionarioDAO;
import entidade.Funcionario;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Preciso Estudar Sempre
 */
public class Principal {
    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.inserirFuncionarios();
        
        System.out.print("\n");
        System.out.print("\n");
        List funcionarios = principal.listarFuncionarios();
        for(Object o : funcionarios){
            Funcionario f = (Funcionario) o;
            System.out.println(f.getNome());
        }
        
        System.out.print("\n");
        System.out.print("\n");
        System.out.println(principal.encontrarFuncionario().getNome());
    }
    
    private void inserirFuncionarios(){
        Funcionario funcionario1 = new Funcionario();
        Funcionario funcionario2 = new Funcionario();
        Funcionario funcionario3 = new Funcionario();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Date dataNascimento = new Date(System.currentTimeMillis());
        
        funcionario1.setNome("Henrique Paulo Pedro Henrique Ribeiro");
        funcionario1.setMatricula("FUNC 001");
        funcionario1.setEmail("henrique-paulo74@moppe.com.br");
        funcionario1.setDataNascimento(dataNascimento);
        funcionario1.setLogradouro("Rua Norberto Carlos de Oliveira");
        funcionario1.setBairro("Parque Residencial Irmãos Furquim");
        funcionario1.setNumero(100);
        
        funcionario2.setNome("Bernardo Henry Heitor da Silva");
        funcionario2.setMatricula("FUNC 002");
        funcionario2.setEmail("bernardo-henry84@vivax.com");
        funcionario2.setDataNascimento(dataNascimento);
        funcionario2.setLogradouro("Rua Princesa Teresa");
        funcionario2.setBairro("Cabuçu");
        funcionario2.setNumero(675);
        
        funcionario3.setNome("Juan Matheus Bernardo Nascimento");
        funcionario3.setMatricula("FUNC 003");
        funcionario3.setEmail("juan-matheus95@leonardopereira.com");
        funcionario3.setDataNascimento(dataNascimento);
        funcionario3.setLogradouro("Rua Adolfa Boaventura Cardoso");
        funcionario3.setBairro("Castelo Branco");
        funcionario3.setNumero(140);
        
        funcionarioDAO.insert(funcionario1);
        System.out.println("Dados inseridos com sucesso.");
        funcionarioDAO.insert(funcionario2);
        System.out.println("Dados inseridos com sucesso.");
        funcionarioDAO.insert(funcionario3);
        System.out.println("Dados inseridos com sucesso.");
    }

    private List<Funcionario> listarFuncionarios(){
        return new FuncionarioDAO().findAll();
    }
    
    private Funcionario encontrarFuncionario(){
        return new FuncionarioDAO().findById(1L);
    }
}
