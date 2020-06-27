import java.io.IOException

import org.apache.hadoop.hbase.{CellUtil, CompareOperator, HBaseConfiguration, NamespaceDescriptor, TableName}
import org.apache.hadoop.hbase.client.{ColumnFamilyDescriptor, ColumnFamilyDescriptorBuilder, ConnectionFactory, Delete, Get, Put, Scan, TableDescriptor, TableDescriptorBuilder}
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter
import org.apache.hadoop.hbase.util.Bytes


object HbaseDDL {

  val conf = HBaseConfiguration.create
  conf.set("hbase.zookeeper.quorum", "hadoop202,hadoop203,hadoop204")
  var conn=ConnectionFactory.createConnection(conf)


  def main(args: Array[String]): Unit = {
//    createTable("stu1","info")
//    deleteTable("stu1")
//    getRowByRange("stu","1001","1006")
    getRowBySingleColumnValueFilter("stu","info","name","name")
  }


  /**
    * 创建namespace
    * @param name
    */
  def createNs(name:String)={
    val admin = conn.getAdmin
    if (nsExists(name)){
      val namespaceDescriptor = NamespaceDescriptor.create(name).build()
      admin.createNamespace(namespaceDescriptor)
    }
    closeConn()
  }
  /**
    * 判断namespace是否存在
    * @param name
    * @return
    */
  def nsExists(name:String)={
    val admin = conn.getAdmin
    val namespaces = admin.listNamespaceDescriptors()
    val nameVars = namespaces.map(_.getName)
    if(namespaces.contains(admin)) false
    true
  }

  /**
    * 创建表
    * @param name
    * @param familys
    * @return
    */
  def createTable(name:String,familys:String*):Boolean ={
    val admin = conn.getAdmin
    if (admin.tableExists(TableName.valueOf(name))) return false
    var thTable =TableDescriptorBuilder.newBuilder(TableName.valueOf(name))
    familys.foreach(
      family=>{
        val columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(family)).build()
        val descriptor = thTable.setColumnFamily(columnFamilyDescriptorBuilder).build()
        admin.createTable(descriptor)
      }
    )
    closeConn()
    true
  }

  /**
    * 删除表
    * @param tableName
    * @return
    */
  def deleteTable(tableName:String):Boolean={
    val admin = conn.getAdmin
    if (!admin.tableExists(TableName.valueOf(tableName))) return false
    admin.disableTable(TableName.valueOf(tableName))
    admin.deleteTable(TableName.valueOf(tableName))
    closeConn()
    true
  }

  def modifi(name:String,family:String)={

  }

  /**
    *新增cell
    */
  def putRow(tableName:String,rowKey:String,family:String,column:String,value:String)={
    val table = conn.getTable(TableName.valueOf(tableName))
    val put = new Put(Bytes.toBytes(rowKey))
    put.addColumn(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes(value))
    table.put(put)
    closeConn()
  }
  /**
    *删除行
    */
  def putRow(tableName:String,rowKey:String)={
    val table = conn.getTable(TableName.valueOf(tableName))
    val delete = new Delete((Bytes.toBytes(rowKey)))
    table.delete(delete)
    closeConn()
  }
  /**
    *删除列
    */
  def putCell(tableName:String,rowKey:String,family:String,column:String)={
    val table = conn.getTable(TableName.valueOf(tableName))
    val delete = new Delete((Bytes.toBytes(rowKey)))
    delete.addColumn(Bytes.toBytes(family),Bytes.toBytes(column))
    table.delete(delete)
    closeConn()
  }
  /**
    *删除列（所有版本）
    */
  def putCells(tableName:String,rowKey:String,family:String,column:String)={
    val table = conn.getTable(TableName.valueOf(tableName))
    val delete = new Delete((Bytes.toBytes(rowKey)))
    delete.addColumns(Bytes.toBytes(family),Bytes.toBytes(column))
    table.delete(delete)
    closeConn()
  }
  /**
    *查询行
    */
  def getRow(tableName:String,rowKey:String)={
    val table = conn.getTable(TableName.valueOf(tableName))
    val get = new Get((Bytes.toBytes(rowKey)))
    val result = table.get(get)
    val cells = result.rawCells()
    cells.foreach(
      cell=>{
        println(
          Bytes.toString(CellUtil.cloneFamily(cell))+";"+
          Bytes.toString(CellUtil.cloneRow(cell))+";"+
          Bytes.toString(CellUtil.cloneValue(cell))+";"
        )
      }
    )
    closeConn()
  }
  /**
    *查询列
    */
  def getCells(tableName:String,rowKey:String,family:String,column:String)={
    val table = conn.getTable(TableName.valueOf(tableName))
    val get = new Get((Bytes.toBytes(rowKey)))
    get.addColumn(Bytes.toBytes(family),Bytes.toBytes(column))
    val result = table.get(get)
    val cells = result.rawCells()
    cells.foreach(
      cell=>{
        println(
          Bytes.toString(CellUtil.cloneFamily(cell))+";"+
            Bytes.toString(CellUtil.cloneRow(cell))+";"+
            Bytes.toString(CellUtil.cloneValue(cell))+";"
        )
      }
    )
    closeConn()
  }
  /**
    *范围查询行
    */
  def getRowByRange(tableName:String,startRowKey:String,stopRowkey:String)={
    val table = conn.getTable(TableName.valueOf(tableName))
    val scan = new Scan(Bytes.toBytes(startRowKey),Bytes.toBytes(stopRowkey))
    val resultScanner = table.getScanner(scan)
    resultScanner.forEach(
        result=>{
          val cells = result.rawCells()
          cells.foreach(
            cell=>{
              println(
                Bytes.toString(CellUtil.cloneFamily(cell))+";"+
                  Bytes.toString(CellUtil.cloneRow(cell))+";"+
                  Bytes.toString(CellUtil.cloneValue(cell))+";"
              )
            }
          )
        }

    )
    closeConn()
  }
  /**
    *范围查询列
    */
  def getCellByRange(tableName:String,startRowKey:String,stopRowkey:String,family:String,column:String)={
    val table = conn.getTable(TableName.valueOf(tableName))
    val scan = new Scan(Bytes.toBytes(startRowKey),Bytes.toBytes(stopRowkey))
    scan.addColumn(Bytes.toBytes(family),Bytes.toBytes(column))
    val resultScanner = table.getScanner(scan)
    resultScanner.forEach(
      result=>{
        val cells = result.rawCells()
        cells.foreach(
          cell=>{
            println(
              Bytes.toString(CellUtil.cloneFamily(cell))+";"+
                Bytes.toString(CellUtil.cloneRow(cell))+";"+
                Bytes.toString(CellUtil.cloneValue(cell))+";"
            )
          }
        )
      }

    )
    closeConn()
  }
  /**
    *rowKey前缀过滤查询行
    */
  def getCellByPrefix(tableName:String,prefix:String)={
    val table = conn.getTable(TableName.valueOf(tableName))
    val scan = new Scan()
    scan.setRowPrefixFilter(Bytes.toBytes(prefix))
    val resultScanner = table.getScanner(scan)
    resultScanner.forEach(
      result=>{
        val cells = result.rawCells()
        cells.foreach(
          cell=>{
            println(
              Bytes.toString(CellUtil.cloneFamily(cell))+";"+
                Bytes.toString(CellUtil.cloneRow(cell))+";"+
                Bytes.toString(CellUtil.cloneValue(cell))+";"
            )
          }
        )
      }
    )
    closeConn()
  }
  /**
    *rowKey前缀过滤查询列
    */
  def getCellByPrefix(tableName:String,prefix:String,family:String,column:String)={
    val table = conn.getTable(TableName.valueOf(tableName))
    val scan = new Scan()
    scan.setRowPrefixFilter(Bytes.toBytes(prefix))
    scan.addColumn(Bytes.toBytes(family),Bytes.toBytes(column))
    val resultScanner = table.getScanner(scan)
    resultScanner.forEach(
      result=>{
        val cells = result.rawCells()
        cells.foreach(
          cell=>{
            println(
              Bytes.toString(CellUtil.cloneFamily(cell))+";"+
                Bytes.toString(CellUtil.cloneRow(cell))+";"+
                Bytes.toString(CellUtil.cloneValue(cell))+";"
            )
          }
        )
      }

    )
    closeConn()
  }
  /**
    *值过滤查询行
    */
  def getRowBySingleColumnValueFilter(tableName:String,family:String,column:String,value:String)={
    val table = conn.getTable(TableName.valueOf(tableName))
    val scan = new Scan()
    val filter = new SingleColumnValueFilter(Bytes.toBytes(family),Bytes.toBytes(column),CompareOperator.EQUAL,Bytes.toBytes(value))
    filter.setFilterIfMissing(true)
    scan.setFilter(filter)
    scan.addColumn(Bytes.toBytes(family),Bytes.toBytes(column))
    val resultScanner = table.getScanner(scan)
    resultScanner.forEach(
      result=>{
        val cells = result.rawCells()
        cells.foreach(
          cell=>{
            println(
              Bytes.toString(CellUtil.cloneFamily(cell))+";"+
              Bytes.toString(CellUtil.cloneRow(cell))+";"+
              Bytes.toString(CellUtil.cloneValue(cell))+";"
            )
          }
        )
      }

    )
    closeConn()
  }
  /**
    *值过滤查询列
    */
  def getCellBySingleColumnValueFilter(tableName:String,family:String,column:String,value:String)={
    val table = conn.getTable(TableName.valueOf(tableName))
    val scan = new Scan()
    val filter = new SingleColumnValueFilter(Bytes.toBytes(family),Bytes.toBytes(column),CompareOperator.EQUAL,Bytes.toBytes(value))
    scan.setFilter(filter)
    filter.setFilterIfMissing(true)
    scan.addColumn(Bytes.toBytes(family),Bytes.toBytes(column))
    val resultScanner = table.getScanner(scan)
    resultScanner.forEach(
      result=>{
        val cells = result.rawCells()
        cells.foreach(
          cell=>{
            println(
              Bytes.toString(CellUtil.cloneFamily(cell))+";"+
                Bytes.toString(CellUtil.cloneRow(cell))+";"+
                Bytes.toString(CellUtil.cloneValue(cell))+";"
            )
          }
        )
      }
    )
    closeConn()
  }
  /**
    * 关闭连接
    */
  def closeConn()={
    conn.close()
  }

}
