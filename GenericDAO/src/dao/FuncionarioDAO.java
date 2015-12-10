package dao;

import entidade.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Preciso Estudar Sempre
 */
public class FuncionarioDAO extends GenericDAO{
    
    public void insert(Funcionario funcionario){
        String sql = "INSERT INTO funcionario ("
                + "NM_FUNCIONARIO, "
                + "EM_FUNCIONARIO,"
                + "DT_NASCIMENTO_FUNCIONARIO,"
                + "MAT_FUNCIONARIO,"
                + "NM_LOGRADOURO,"
                + "NUM_LOGRADOURO,"
                + "NM_BAIRRO"
                + ") VALUES (?,?,?,?,?,?,?)";
        List<Object> parametros = new ArrayList<>();
        parametros.add(funcionario.getNome());
        parametros.add(funcionario.getEmail());
        parametros.add(funcionario.getDataNascimento());
        parametros.add(funcionario.getMatricula());
        parametros.add(funcionario.getLogradouro());
        parametros.add(funcionario.getNumero());
        parametros.add(funcionario.getBairro());
        super.insertUpdateDelete(sql, parametros);
    }
    
    public void update(Funcionario funcionario){
        String sql = "UPDATE FUNCIONARIO SET "
                + "NM_FUNCIONARIO = ?, "
                + "EM_FUNCIONARIO = ?,"
                + "DT_NASCIMENTO_FUNCIONARIO = ?,"
                + "MAT_FUNCIONARIO = ?,"
                + "NM_LOGRADOURO = ?,"
                + "NUM_LOGRADOURO = ?,"
                + "NM_BAIRRO = ? "
            + "WHERE ID = ?";
        List<Object> parametros = new ArrayList<>();
        parametros.add(funcionario.getNome());
        parametros.add(funcionario.getEmail());
        parametros.add(funcionario.getDataNascimento());
        parametros.add(funcionario.getMatricula());
        parametros.add(funcionario.getLogradouro());
        parametros.add(funcionario.getNumero());
        parametros.add(funcionario.getBairro());
        parametros.add(funcionario.getId());
        super.insertUpdateDelete(sql, parametros);
    }
    
    public void delete(Long id){
        String sql = "DELETE FROM FUNCIONARIO WHERE ID = ?";
        List<Object> parametros = new ArrayList<>();
        parametros.add(id);
        super.insertUpdateDelete(sql, parametros);
    }
    
    public List<Funcionario> findAll(){
        String sql = "SELECT * FROM FUNCIONARIO";
        List<Object> parametros = new ArrayList<>();
        return super.findAll(sql, parametros, new RowMapping<Funcionario>() {
            @Override
            public Funcionario mapping(ResultSet resultSet) throws SQLException{
                Funcionario funcionario = new Funcionario();
                if(resultSet != null){
                    funcionario.setId(resultSet.getLong("ID"));
                    funcionario.setNome(resultSet.getString("NM_FUNCIONARIO"));
                    funcionario.setEmail(resultSet.getString("EM_FUNCIONARIO"));
                    funcionario.setDataNascimento(resultSet.getDate("DT_NASCIMENTO_FUNCIONARIO"));
                    funcionario.setMatricula(resultSet.getString("MAT_FUNCIONARIO"));
                    funcionario.setLogradouro(resultSet.getString("NM_LOGRADOURO"));
                    funcionario.setNumero(resultSet.getInt("NUM_LOGRADOURO"));
                    funcionario.setBairro(resultSet.getString("NM_BAIRRO"));
                }
                return funcionario;
            }
        });
    }
    
    public Funcionario findById(Long id){
        String sql = "SELECT * FROM FUNCIONARIO WHERE ID = ?";
        List<Object> parametros = new ArrayList<>();
        parametros.add(id);
        return (Funcionario) super.findById(sql, parametros, new RowMapping<Funcionario>() {
            @Override
            public Funcionario mapping(ResultSet resultSet) throws SQLException{
                Funcionario funcionario = new Funcionario();
                if(resultSet != null){
                    funcionario.setId(resultSet.getLong("ID"));
                    funcionario.setNome(resultSet.getString("NM_FUNCIONARIO"));
                    funcionario.setEmail(resultSet.getString("EM_FUNCIONARIO"));
                    funcionario.setDataNascimento(resultSet.getDate("DT_NASCIMENTO_FUNCIONARIO"));
                    funcionario.setMatricula(resultSet.getString("MAT_FUNCIONARIO"));
                    funcionario.setLogradouro(resultSet.getString("NM_LOGRADOURO"));
                    funcionario.setNumero(resultSet.getInt("NUM_LOGRADOURO"));
                    funcionario.setBairro(resultSet.getString("NM_BAIRRO"));
                }
                return funcionario;
            }
        });
    }
}
