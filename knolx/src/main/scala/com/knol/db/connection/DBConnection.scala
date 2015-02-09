package com.knol.db.connection

import com.typesafe.config.ConfigFactory
import java.sql.DriverManager;
import org.slf4j.LoggerFactory


/*
 * DBConnection is a trait that is importing the application.conf file
 * that contains the connection information of database
 */
trait DBConnection {
  val logger = LoggerFactory.getLogger(this.getClass)
  val config = ConfigFactory.load();
  val url = config.getString("db.url");
  val user = config.getString("db.user")
  val password = config.getString("db.password");
  def getConnection(): Option[java.sql.Connection]
}
