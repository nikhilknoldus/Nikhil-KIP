package com.knol.core
import com.knol.core.impl._
/*
 * KnolderSessionDao is a trait that containing 5 CRUD methods declaration
 * and these are implemented in the KnolderSessionDaoImpl
 */


trait KnolderSessionDao {

def delete(id:Int):Option[Int]
def insert(knolder:KnolderSession):Int
def selectById(id:Int):Option[KnolderSession]
def update(knol:KnolderSession):Option[Int]
//def ShowAll:List[KnolderSession]
}
