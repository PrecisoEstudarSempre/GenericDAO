package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Preciso Estudar Sempre
 */
public class GenericDAO {
    private Connection openConnection() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return DriverManager.getConnection("jdbc:mysql://localhost/meu_banco", "meu_usuario", "minha_senha");
    }
    
    private void closeConnection(Connection connection, Statement statement){
        try{
            if(connection != null){
               connection.close();
               if(statement != null){
                   statement.close();
               }
            } 
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
    
    private void closeConnection(Connection connection, Statement statement, ResultSet resultSet){
        try{
            this.closeConnection(connection, statement);
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
    
    public void insertUpdateDelete(String sql, List<Object> parametros){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.openConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            this.receiveParameters(preparedStatement, parametros);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        } finally {
            this.closeConnection(connection, preparedStatement);
        }
    }
    
    private void receiveParameters(PreparedStatement preparedStatement, List<Object> parametros) throws SQLException{
        int paramPos = 1;
        for(Object parametro : parametros){
            if(parametro == null){
                preparedStatement.setNull(paramPos, 1);
            } else if(parametro instanceof Integer){
                preparedStatement.setInt(paramPos, (Integer) parametro);
            } else if(parametro instanceof Long){
                preparedStatement.setLong(paramPos, (Long) parametro);
            } else if(parametro instanceof Float){
                preparedStatement.setFloat(paramPos, (Float) parametro);
            } else if(parametro instanceof Double){
                preparedStatement.setDouble(paramPos, (Double) parametro);
            } else if(parametro instanceof Date){
                preparedStatement.setDate(paramPos, new java.sql.Date(((Date) parametro).getTime()));
            } else if(parametro instanceof String){
                preparedStatement.setString(paramPos, parametro.toString());
            }
            paramPos++;
        }
    }
    
    public List findAll(String sql, List<Object> parametros, RowMapping mapping){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List rows = new ArrayList();
        try {
            connection = this.openConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            this.receiveParameters(preparedStatement, parametros);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                rows.add(mapping.mapping(resultSet));
            }           
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        } finally {
            this.closeConnection(connection, preparedStatement, resultSet);
        }
        return rows;
    }

    public Object findById(String sql, List<Object> parametros, RowMapping mapping){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Object row = null;
        try {
            connection = this.openConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            this.receiveParameters(preparedStatement, parametros);
            resultSet = preparedStatement.executeQuery();
            resultSet = resultSet.isBeforeFirst() ? resultSet : null;
            if(resultSet != null){
                resultSet.next();
            }
            row = mapping.mapping(resultSet);
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        } finally {
            this.closeConnection(connection, preparedStatement, resultSet);
        }
        return row;
    }
}
