package com.knol.core.impl

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

import com.knol.core.KnolderSessionDao
import com.knol.db.connection.DBConnectionImpl

/*
 * KnolderSessionDaoImpl is the implementation of the KnolderSessionDao
 * trait that is having same number of functions.
 */

class KnolderSessionDaoImpl extends DBConnectionImpl with KnolderSessionDao {
// val logger = LoggerFactory.getLogger(this.getClass)
  /*
   * delete function is taking knolderSession's id as parameter and
   * deleting the information from the table
   */

  def insert(knol: KnolderSession): Int ={
      val jdb = new DBConnectionImpl()
      val connection = jdb.getConnection()
      connection match {
        case Some(connection) =>
          try {
            val sql = "insert into knoldersession values(null,'" + knol.topic + "','" + knol.date + "','" + knol.knol_id + "')"
            val stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            val RES = stm.executeUpdate()
            val res = stm.getGeneratedKeys
            res.next()
            val NEWID = res.getInt(1)
            logger.debug("Created ID: " + NEWID + ", result got=" + RES)
            NEWID
          } catch {
            case ex: Exception =>
              logger.error("Can not create knol" + ex)
              0
          }
        case None => throw new Exception()
      }
    }

  def delete(id: Int): Option[Int] =
    {
      val jdb = new DBConnectionImpl()
        val connection = jdb.getConnection()
        connection match {
          case Some(connection) =>
            {             
            try {
              val sql = "delete from knoldersession where id=" + id
              val stm = connection.createStatement()
              val RETURN = stm.executeUpdate(sql)
              logger.debug("Delete successful: " + RETURN)    
              require(RETURN >0)
              Some(RETURN)
            } catch {
              case ee: Exception => {
                logger.error("error in creating", ee)
                None
              }
            }
          }
        case None => None
      }
    }

  /*
   * selectById function is taking KnolderSession's id as parameter
   * and then showing its information
   */
  def selectById(id: Int): Option[KnolderSession] =
    {
      val jdb = new DBConnectionImpl()
      try {
        val connection = jdb.getConnection()
        connection match {
          case Some(connection) =>
            {
              logger.debug("Connection established successfully")
              val sql = "select id,topic,date,knol_id from knoldersession where id=" + id;
              val stm = connection.createStatement()
              val rs = stm.executeQuery(sql);
              rs.next();
              val knol = KnolderSession(id, rs.getString("topic"), rs.getString("date"), rs.getInt("knol_id"));
              Some(knol)
            }
          case None => throw new Exception()
        }
      } catch {
        case ee: Exception =>
          {
            logger.error("Error in seleting a knol", ee)
            None
          }
      }
    }

  def update(knol: KnolderSession): Option[Int] =
    {
      val jdb = new DBConnectionImpl()
      try {
        val connection = jdb.getConnection()
        connection match {
          case Some(connection) =>
            {
              val sql = "update knoldersession set topic='" + knol.topic + "',date='" + knol.date + "' where id = " + knol.id;
              val stm = connection.createStatement()
              stm.executeUpdate(sql)
              val stmmt = stm.executeUpdate(sql) 
              require(stmmt > 0)
              Some(1)
            }

          case None => None
        }
      } catch {
        case ee: Exception =>
          {
            logger.error("Error in updating a knol", ee)
            None
          }
      }
    }
/*
  def ShowAll: List[KnolderSession] =
    {
      val jdb = new DBConnectionImpl()
      import scala.collection.mutable;
      var list = List[KnolderSession]();
      try {
        val connection = jdb.getConnection()
        connection match {
          case Some(connection) => {
            val preparedStatement: PreparedStatement = connection.prepareStatement("SELECT * FROM knoldersession");
            val resultSet: ResultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
              var knol = KnolderSession(resultSet.getInt("id"), resultSet.getString("topic"), resultSet.getString("date"), resultSet.getInt("knol_id"));
              list = list.::(knol)
            }
            list
          }
          case None => list
        }
      } catch {
        case ee: Exception =>
          {
            ee.printStackTrace();
            logger.error("Error while showing all knolders", ee)
          }
          list
      }
   }
   */
}
