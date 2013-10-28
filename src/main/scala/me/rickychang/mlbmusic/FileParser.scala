package me.rickychang.mlbmusic

import org.saddle.io.CsvImplicits._
import org.saddle.io._
import org.saddle._

class FileParser {

  private val plateMusicParams = CsvParams(separChar='\t', hasHeader=false)
  
  def parsePlateMusic(file: String): Frame[String, String, String] = {
    CsvParser.parse(List(1,3), plateMusicParams)(CsvFile(file)).withRowIndex(0).setColIndex(Index("Artist"))
  }
  
  def parseBattingStats(file: String): Frame[String,String,String] = {
    CsvParser.parse(List(0,2,3,4,5,6,7,8,9,10,11,15))(CsvFile(file)).withRowIndex(0).withColIndex(0)
  }

  def parsePitchingStats(file: String): Frame[String, String, String] = {
    val rawStats = CsvParser.parse(List(0, 2, 7, 12))(CsvFile(file)).withRowIndex(0).withColIndex(0)
    val otherStats = rawStats.colAt(1, 2)
    val normalizedIP = rawStats.col("IP").mapValues { v =>
      val Array(whole, frac) = v.split('.')
      val fracNum = frac.toInt
      val wholeNum = whole.toInt
      wholeNum + (fracNum / 3f)
    }.colAt(0).groupBy.transform(vec => vec.map(v => (v / vec.length).toString))
    normalizedIP.joinF(otherStats).setColIndex(Index("IP", "ER", "SO"))
  }

}