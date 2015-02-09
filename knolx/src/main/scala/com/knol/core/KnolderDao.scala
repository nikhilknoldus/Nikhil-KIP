package com.knol.core
import com.knol.core.impl._

/*
 * Defined a trait KnolderDao that has 5 functions left blank
 * later implemented in the KnolderDaoImpl
 */

trait KnolderDao {
  //def testCon():Int
  //insert function is inserting the records in the table
  def insert(knol:Knolder):Int
  /* selectById function is selecting the particular row on the basis
   * of id passed as parameter
   */
  def selectById(id:Int):Option[Knolder]
  /*
   * delete function is deleting a particular row(record) from the
   * table
   */
  def delete(id:Int):Option[Int]
  /*
   * update function is updating information of particular information
   */
  def update(knol:Knolder):Option[Int]
  /*
   * ShowAll function is selecting all the records that are available in
   * the in table
   */
  //def ShowAll:List[Knolder]
}
