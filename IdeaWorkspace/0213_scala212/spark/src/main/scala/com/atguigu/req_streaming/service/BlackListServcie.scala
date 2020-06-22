package com.atguigu.req_streaming.service

import java.sql.Date
import java.text.SimpleDateFormat

import com.atguigu.req_streaming.bean.Ads_log
import com.atguigu.req_streaming.dao.BlackListDao
import com.atguigu.req_streaming.util.JdbcUtil
import com.atguigu.summer.framework.core.TService
import org.apache.spark.streaming.dstream.DStream

import scala.collection.mutable.ListBuffer


class BlackListServcie extends TService{
  private val blackListDao = new BlackListDao
  def analysis3()={
    val kfkDs = blackListDao.readKafka()
    val userDs = kfkDs.map(
      dt => {
        val dts = dt.split(" ")
        Ads_log(
          dts(0),
          dts(1),
          dts(2),
          dts(3),
          dts(4)
        )
      }
    )
    var reduceDs=userDs.transform(
      rdd=>{
        val conn = JdbcUtil.getConnection
        var pstat=conn.prepareStatement(
          """
            |select userid from black_list
          """.stripMargin)
        val resultSet = pstat.executeQuery()
        val bufferIds = ListBuffer[String]()
        while (resultSet.next()){
          bufferIds.append(resultSet.getString(1))
        }
        resultSet.close()
        pstat.close()
        conn.close()
        val sdf = new  SimpleDateFormat("yyyy-MM-dd")
        rdd
          .filter(s=> !bufferIds.contains(s.userid))
          .map(s=>{
            val date=new Date(s.dt.toLong)
            ((sdf.format(date),s.userid,s.adid),1)
          })
          .reduceByKey(_+_)
      }
    )
    reduceDs.foreachRDD(
      rdd=>{
        rdd.foreachPartition(
          iter=>{
            val conn = JdbcUtil.getConnection
            var pstat= conn.prepareStatement(
              """
                |insert into user_ad_count(dt,userid,adid,count)
                |values(?,?,?,?)
                |on duplicate key
                |update count =count +?
              """.stripMargin)
            //查询统计的数据，超过设定的阈值则加入黑名单
            var countPastat=conn.prepareStatement(
              """
                |insert into black_list(userid)
                |select userid from  user_ad_count
                |where dt=? and userid=? and adid=? and count>=100
                |on duplicate key
                |update userid = ?
              """.stripMargin)
            iter.foreach{
              case ((dt,userid,adid),count)=>{
                //设置参数
                pstat.setString(1,dt)
                pstat.setString(2,userid)
                pstat.setString(3,adid)
                pstat.setLong(4,count)
                pstat.setLong(5,count)
                pstat.executeUpdate()

                //设置参数
                countPastat.setString(1,dt)
                countPastat.setString(2,userid)
                countPastat.setString(3,adid)
                countPastat.setString(4,userid)
                //执行
                countPastat.executeUpdate()
              }
            }
            pstat.close()
            countPastat.close()
            conn.close()
          }
        )
      }

    )
  }
  def analysis2()={
    val kfkDs = blackListDao.readKafka()
    val userDs = kfkDs.map(
                    dt => {
                      val dts = dt.split(" ")
                      Ads_log(
                        dts(0),
                        dts(1),
                        dts(2),
                        dts(3),
                        dts(4)
                      )
                    }
                  )
    var reduceDs=userDs.transform(
      rdd=>{
        val conn = JdbcUtil.getConnection
        var pstat=conn.prepareStatement(
          """
            |select userid from black_list
          """.stripMargin)
        val resultSet = pstat.executeQuery()
        val bufferIds = ListBuffer[String]()
        while (resultSet.next()){
          bufferIds.append(resultSet.getString(1))
        }
        resultSet.close()
        pstat.close()
        conn.close()
        val sdf = new  SimpleDateFormat("yyyy-MM-dd")
        rdd
          .filter(s=> !bufferIds.contains(s.userid))
          .map(s=>{
              val date=new Date(s.dt.toLong)
              ((sdf.format(date),s.userid,s.adid),1)
           })
          .reduceByKey(_+_)
      }
    )
    reduceDs.foreachRDD(
      rdd=>{
        rdd.foreachPartition(
          iter=>{
            val conn = JdbcUtil.getConnection
            var pstat= conn.prepareStatement(
              """
                |insert into user_ad_count(dt,userid,adid,count)
                |values(?,?,?,?)
                |on duplicate key
                |update count =count +?
              """.stripMargin)
            //查询统计的数据，超过设定的阈值则加入黑名单
            var countPastat=conn.prepareStatement(
              """
                |insert into black_list(userid)
                |select userid from  user_ad_count
                |where dt=? and userid=? and adid=? and count>=100
                |on duplicate key
                |update userid = ?
              """.stripMargin)
            iter.foreach{
              case ((dt,userid,adid),count)=>{
                //设置参数
                pstat.setString(1,dt)
                pstat.setString(2,userid)
                pstat.setString(3,adid)
                pstat.setLong(4,count)
                pstat.setLong(5,count)
                pstat.executeUpdate()

                //设置参数
                countPastat.setString(1,dt)
                countPastat.setString(2,userid)
                countPastat.setString(3,adid)
                countPastat.setString(4,userid)
                //执行
                countPastat.executeUpdate()
              }
            }
            pstat.close()
            countPastat.close()
            conn.close()
          }
        )
      }

    )
  }
  def analysis1(): Unit ={
    val kafkaDS = blackListDao.readKafka()
    //封装样例类
    val adsDS = kafkaDS
                                        .map(
                                          dt => {
                                            val dts = dt.split(" ")
                                            Ads_log(dts(0), dts(1), dts(2), dts(3), dts(4))
                                          })
    //周期性读取数据
    var reduceDS= adsDS.transform(
                            rdd=>{
                              //查询黑名单列表
                              val conn = JdbcUtil.getConnection
                              //执行查询,
                              val pstat = conn.prepareStatement(
                                                        """
                                                          |select userid from black_list
                                                        """.stripMargin)
                              val resultSet = pstat.executeQuery()
                              //封装黑名单ids
                              val listIds = ListBuffer[String]()
                              while (resultSet.next()){
                                listIds.append(resultSet.getString(1))
                              }
                              //关闭连接
                              resultSet.close()
                              pstat.close()
                              conn.close()
                              val sdf = new SimpleDateFormat("yyyy-MM-dd")
                              rdd
                                .filter(s=> ! listIds.contains(s.userid))
                                .map(s=>{
                                    val date = new Date(s.dt.toLong)
                                  ((sdf.format(date),s.userid,s.adid),1L)
                                  })
                                .reduceByKey(_+_)
                            })
    //将数据插入ad表
    reduceDS.foreachRDD(
      rdd=>{
        //循环插入数据
        rdd.foreach{
//          case ((dt,userid,adid),count)=>{
            case ( (dt, userid, adid), count ) => {
            //获取连接插入数据
            val conn = JdbcUtil.getConnection
            var pstat=conn.prepareStatement(
              """
                |insert into user_ad_count(dt,userid,adid,count)
                |values(?,?,?,?)
                |on duplicate key
                |update count =count +?
              """.stripMargin)
            //设置参数
            pstat.setString(1,dt)
            pstat.setString(2,userid)
            pstat.setString(3,adid)
            pstat.setLong(4,count)
            pstat.setLong(5,count)
            //执行更新
           pstat.executeUpdate()

            //查询统计的数据，超过设定的阈值则加入黑名单
            var countPastat=conn.prepareStatement(
              """
                |insert into black_list(userid)
                |select userid from  user_ad_count
                |where dt=? and userid=? and adid=? and count>=100
                |on duplicate key
                |update userid = ?
              """.stripMargin)
            //设置参数
            countPastat.setString(1,dt)
            countPastat.setString(2,userid)
            countPastat.setString(3,adid)
            countPastat.setString(4,userid)
           //执行
            countPastat.executeUpdate()
            //关闭
            countPastat.close()
            pstat.close()
            conn.close()
          }
        }
      }
    )
  }


  override def analysis(): Any = {
    val ds = blackListDao.readKafka()

    //将数据转为样例类
    val ads_logDs = ds.map(dt => {
      val dts = dt.split(" ")
      Ads_log(
        dts(0),
        dts(1),
        dts(2),
        dts(3),
        dts(4),
      )
    })
    //    ads_logDs.print()
    //周期性的获取黑名单信息，判断当前用户的点击数据是否在黑名单中（transform）
    val resultDs = ads_logDs.transform(
      rdd => {
        val connection = JdbcUtil.getConnection
        val pstat = connection.prepareStatement(
          """
            |select userid from black_list
          """.stripMargin)
        val resultSet = pstat.executeQuery()
        val list = ListBuffer[String]()
        //过滤掉黑名单中用户
        while (resultSet.next()) {
          list.append(resultSet.getString(1))
        }
        resultSet.close()
        pstat.close()
        connection.close()
        //过滤
        val filterRdd = rdd.filter(s => !list.contains(s.userid))
        //非黑名单用户的点击量统计（day-userid-adid,sum）
        val sdf = new SimpleDateFormat("yyyy-MM-dd")

        filterRdd
          .map(s => {
            val date = new Date(s.dt.toLong)
            ((sdf.format(date), s.userid, s.adid), 1)
          })
          .reduceByKey(_ + _)
      }
    )
    //将超过阈值的用户拉如黑名单中
    resultDs.foreachRDD(
      rdd => {
        rdd.foreach {
          case ((day, userid, adid), sum) => {
            val connection = JdbcUtil.getConnection
            val pstat = connection.prepareStatement(
              """
                |insert into user_ad_count (dt,userid,adid,count)
                |values (?,?,?,?)
                |on duplicate key
                |update count = count+?
              """.stripMargin)
            pstat.setString(1, day)
            pstat.setString(2, userid)
            pstat.setString(3, adid)
            pstat.setLong(4, sum)
            pstat.setLong(5, sum)
            //执行更新
            pstat.executeUpdate()

            //插入数据之后，先判断
            var pstataDt = connection.prepareStatement(
              """
                |insert into black_list ( userid )
                |select userid from user_ad_count
                |where dt = ? and userid = ? and adid = ? and count >= 100
                |on duplicate key
                |update userid = ?
              """.stripMargin
            )
            pstataDt.setString(1, day)
            pstataDt.setString(2, userid)
            pstataDt.setString(3, adid)
            pstataDt.setString(4, userid)
            pstataDt.executeUpdate()

            pstataDt.close()
            pstat.close()
            connection.close()

          }
        }
      }
    )
    ds.print()
  }

}
