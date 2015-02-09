package com.knol.db.connection
import java.sql._
import java.sql.DriverManager
import com.mysql.jdbc.Connection._
import com.typesafe.config.ConfigFactory
/*
 * class DBConnectionImpl is extending the DBConneciton trait and
 * defining the connection details with the database
 */
class DBConnectionImpl extends DBConnection {
  def getConnection(): Option[java.sql.Connection] ={
      Class.forName("com.mysql.jdbc.Driver");
      try {
        val config = ConfigFactory.load();
        val url:String = config.getString("db.url");
        val user:String = config.getString("db.user")
        val password:String = config.getString("db.password");
        val conn=DriverManager.getConnection("jdbc:mysql://localhost/test","root","root");
        logger.debug("Connection created")
        Some(conn)
      }catch {
        case e: Exception => None
          
          }
    }
}
