package com.pyt.myself.demo.starter;

public class DataSourceService {

    private DataSource dataSource;

    public DataSourceService(DataSource dataSource){
        this.dataSource = dataSource;
    }
    public String getDriver() {
        return dataSource.getDriver();
    }
    public DataSource getDataSource() {
        return dataSource;
    }

    public String getUrl() {
        return dataSource.getUrl();
    }

    public String getPassword() {
        return dataSource.getPassword();
    }

    public String getUsername() {
        return dataSource.getUsername();
    }

    public String getDatabase() {
        return dataSource.getDatabase();
    }
}
