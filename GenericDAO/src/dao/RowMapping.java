package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Preciso Estudar Sempre
 */
public interface RowMapping<T> {
    
    /**
     * Método que realiza o mapeamento do result set em um objeto.
     * @param resultSet Objeto do tipo ResultSet. Contém o retorno da query.
     * @return T Retorna um objeto especificado pela generic.
     * @throws SQLException Erro de acesso ao banco ou SQL.
     */
    T mapping(ResultSet resultSet) throws SQLException;
}
