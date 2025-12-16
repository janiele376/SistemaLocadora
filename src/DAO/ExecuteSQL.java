package DAO;

import java.sql.Connection;

public class ExecuteSQL {
    private Connection con;

    public ExecuteSQL(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
}