package com.knol.core.Impl

import java.sql.PreparedStatement

import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite

import com.knol.core.impl._
import com.knol.db.connection.DBConnectionImpl

class KnolderSessionDaoImplTest extends FunSuite with BeforeAndAfter {

  var knolRepo = new KnolderDaoImpl
  var knolSessionRepo = new KnolderSessionDaoImpl
  var knolManageObj = new KnolManageDaoImpl
  /*
   * Testing CRUD over the KnolderSession 
   */

  before {
    val dbCon = new DBConnectionImpl()
    val con = dbCon.getConnection()
    con match {
      case Some(con) => {
        try {

          val stmt1 = con.createStatement()
          var sql1 = "CREATE TABLE IF NOT EXISTS knolder (id int not null AUTO_INCREMENT, name varchar(20),email varchar(20),mobile varchar(20),primary key(id),unique key(email))";
          val st1: PreparedStatement = con.prepareStatement(sql1);
          st1.executeUpdate(sql1);
          knolRepo.insert(Knolder(0, "Adam", "adam@jason.com", "10101"))

          val stmt = con.createStatement()
          var sql = "create table IF NOT EXISTS knoldersession (id int not null auto_increment, topic varchar(20), date Date,knol_id int, primary key(id),unique key (topic), foreign key(id) references knolder(id))";
          val st: PreparedStatement = con.prepareStatement(sql);
          st.executeUpdate(sql);
          knolSessionRepo.insert(KnolderSession(0, "Angular", "2015-02-02", 1));
          
        } catch {
          case ex: Exception => {
            None
          }
        }
      }
      case None => None
    }
  }

  after {
    val dbCon = new DBConnectionImpl()
    val con = dbCon.getConnection()
    con match {
      case Some(con) =>
        {
          try {

            val stmt = con.createStatement()
            var sql = "Drop table knolder"
            var sql1 = "Drop table knoldersession"
            stmt.execute(sql1)
            stmt.execute(sql)
            con.close()
          } catch {
            case ex: Exception => {
              None
            }
          }
        }
      case None => None
    }
  }

  test("testing the Inserting of knolder into knoldersession") {
    // pending
    val knol = KnolderSession(0, "PearlScript", "2015-02-04", 1)
    assert(knolSessionRepo.insert(knol) === 0)
  }

  test("testing insertation for catch case coverage") {
    //pending
    val knol = KnolderSession(0, "Angular", "2015-02-02", 1)
    assert(knolSessionRepo.insert(knol) === 0)
  }

  test("Deleting a knoldersession") {
    // pending
    assert(knolSessionRepo.delete(1) === Some(1))
  }

  test("Deleting a knoldersession for catch case coverage") {
    //pending
    assert(knolSessionRepo.delete(10) === None)
  }

  test("Selecting the concerned knolder") {
    //pending
    val knol = KnolderSession(1, "Angular", "2015-02-02", 1)
    assert(knolSessionRepo.selectById(1) === Some(knol))
  }

  test("selecting a knolder with session for catch case coverage") {
    //pending
    val knol = KnolderSession(10, "sbt", "2015-02-03", 2)
    assert(knolSessionRepo.selectById(10) === None)
  }

  test("Testing the updation operation over the knoldersession record") {
    //pending
    val knol = KnolderSession(1, "ANGU", "2015-02-03", 0)
    assert(knolSessionRepo.update(knol) === Some(1))
  }

  test("Testing the updation of knolder for session for covering the catch case") {
    //pending
    val knol = KnolderSession(55, "ANGULAR", "2015-02-03", 0)
    assert(knolSessionRepo.update(knol) === None)
  }
}