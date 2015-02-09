package com.knol.core

import com.knol.core.impl.KnolManage

/*
 * This trait is for the combined query of both the tables
 * knolder and knoldersession 
 */
trait KnolManageDao {
   def ShowAll:List[KnolManage]
}
