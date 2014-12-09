package model.QueryBuild;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringEscapeUtils;

import dao.ModelDAO;

/**
 * Created by jesperbruun on 16/10/14.
 */
public class Executor extends ModelDAO {

    private static final String DELETE = "DELETE FROM ";
	private static final String SELECT = "SELECT ";
    private static final String FROM = " FROM ";
    private static final String WHERE = " WHERE ";
    private static final String INSERTINTO = "INSERT INTO ";
    private static final String UPDATE = "UPDATE ";
    private static final String VALUES = " VALUES ";

    private QueryBuilder queryBuilder;
    private Where where;
    
    public void setWhere(Where where) {
		this.where = where;
	}


	private Values values;
    private boolean getAll = false;

    protected QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    protected Where getWhere() {
        return where;
    }

    protected Values getValues() {
        return values;
    }

    protected boolean isGetAll() {
        return getAll;
    }

    public Executor(QueryBuilder queryBuilder, boolean getAll) {
        this.queryBuilder = queryBuilder;
        this.getAll = getAll;
    }

    public Executor(QueryBuilder queryBuilder, Where where) {
        this.queryBuilder = queryBuilder;
        this.where = where;
    }

    public void setQueryBuilder(QueryBuilder queryBuilder) {
		this.queryBuilder = queryBuilder;
	}

	public Executor(QueryBuilder queryBuilder, Values values) {
        this.queryBuilder = queryBuilder;
        this.values = values;
    }

    /**
     * Execute SQL and returns ResultSet.
     * @return ResultSet
     * @throws SQLException
     */
    public ResultSet ExecuteQuery() throws SQLException {
        String sql = "";
        if (isGetAll()) {
            sql = SELECT + getQueryBuilder().getSelectValue() + FROM + getQueryBuilder().getTableName() + ";";
            try {
                getConnection(false);
                getConn();
                String cleanSql = StringEscapeUtils.escapeSql(sql);
                sqlStatement = getConn().prepareStatement(cleanSql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            sql = SELECT + getQueryBuilder().getSelectValue() +
                    FROM + getQueryBuilder().getTableName() +
                    WHERE + getWhere().getWhereKey() + " " + getWhere().getWhereOperator() + " ?;";
            try {
                getConnection(false);
                getConn();
                String cleanSql = StringEscapeUtils.escapeSql(sql);
                sqlStatement = getConn().prepareStatement(cleanSql);
                sqlStatement.setString(1, getWhere().getWhereValue());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sqlStatement.executeQuery();
    }


    /**
     * Execute SQL Query. <strong>OBS nothing returns.</strong>
     * @return
     * @throws SQLException
     */
    public boolean Execute() {
        String sql = "";

        if (getQueryBuilder().isDelete() && getQueryBuilder().isSoftDelete()) {
            sql = UPDATE + getQueryBuilder().getTableName() + " SET active = 0" +
                    WHERE + getWhere().getWhereKey() + " " + getWhere().getWhereOperator() + " " + getWhere().getWhereValue() + ";  ";
            try {
                getConnection(false);
                getConn();
                String cleanSql = StringEscapeUtils.escapeSql(sql);
                sqlStatement = getConn().prepareStatement(cleanSql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (getQueryBuilder().isDelete() && !getQueryBuilder().isSoftDelete()) {
            sql = DELETE + getQueryBuilder().getTableName();
            if(getWhere().getWhereKey()!=null) {
            	sql = sql + " WHERE " + getWhere().getWhereKey() + " " + getWhere().getWhereOperator() + " " + getWhere().getWhereValue() + ";  ";
            } else {
            	sql = sql + ";";
            }

            try {
                getConnection(false);
                getConn();
                String cleanSql = StringEscapeUtils.escapeSql(sql);
                sqlStatement = getConn().prepareStatement(cleanSql);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            
        } else if(getQueryBuilder().isUpdate()) {
            sql = UPDATE + getQueryBuilder().getTableName() + " SET " + getQueryBuilder().getFields() + "" + WHERE + getWhere().getWhereKey() + " " + getWhere().getWhereOperator() + " ?;";
            try {
                getConnection(false);
                getConn();
//                String cleanSql = StringEscapeUtils.escapeSql(sql);                
                sqlStatement = getConn().prepareStatement(sql);
                sqlStatement.setString(1, getWhere().getWhereValue());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(sql);
            
            sql = INSERTINTO + getQueryBuilder().getTableName() + " (" + getQueryBuilder().getFields() + ")" + VALUES + "(";
            StringBuilder sb = new StringBuilder();
            for (String n : getValues().getValues()) {
                if (sb.length() > 0) sb.append(",");
                sb.append(" ?");
            }
            sql += sb.toString();
            sql += " );";
            try {
                getConnection(false);
                getConn();
                String cleanSql = StringEscapeUtils.escapeSql(sql);
                sqlStatement = getConn().prepareStatement(cleanSql);
                int x = 0;
                for (int i = 0; i < getValues().getValues().length; i++) {
                    x = i;
                    sqlStatement.setString(x+1, getValues().getValues()[i]);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        System.out.println("SQLStatement.toString: " + sqlStatement.toString());
//        System.out.println("SQLStatement: " + sqlStatement);
        Boolean executedSuccesfully = null;
		try {
			executedSuccesfully = sqlStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				getConn().close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//        
        
        return executedSuccesfully;
    }


}
