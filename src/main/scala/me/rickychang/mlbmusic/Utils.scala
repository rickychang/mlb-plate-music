package me.rickychang.mlbmusic

import org.saddle._
import org.saddle.io._

object Utils {

  def normalizeStatsByArtist(stats: Frame[String, String, String], playerMusic: Frame[String, String, String]): Frame[String, String, Float] = {
    val statsWithArtist = stats.join(playerMusic, how = index.InnerJoin).setColIndex(stats.colIx.concat(Index("Artist")))
    val normalizedStats = statsWithArtist.groupBy.transform { vec =>
      vec.map(v => if (v.forall(_.isDigit)) (v.toFloat / vec.length).toString else v)
    }
    normalizedStats
      .withRowIndex(statsWithArtist.numCols - 1)
      .mapValues(CsvParser.parseFloat)
      .rtransform(v => v concat Series("Players" -> 1f))
      .groupBy
      .combine(v => v.foldLeft(0f)(_+_))
  }

  def computeFinalBattingStats(stats: Frame[String, String, Float]): Frame[String, String, Float] = {
    stats.rtransform { v =>
      val games = v.first("G")
      val abs = v.first("AB")
      val pas = v.first("PA")
      val hits = v.first("H")
      val singles = v.first("1B")
      val doubles = v.first("2B")
      val triples = v.first("3B")
      val homeruns = v.first("HR")
      val rbis = v.first("RBI")
      val average = hits / abs
      val players = v.first("Players")
      Series("AVG" -> average,
        "G" -> games,
        "AB" -> abs,
        "PA" -> pas,
        "H" -> hits,
        "1B" -> singles,
        "2B" -> doubles,
        "3B" -> triples,
        "HR" -> homeruns,
        "RBI" -> rbis,
        "P" -> players)
    }
  }
  
  def computeFinalPitchingStats(stats: Frame[String, String, Float]): Frame[String,String,Float] = {
        stats.rtransform { v =>
      val innings = v.first("IP")
      val earnedRuns = v.first("ER")
      val strikeOuts = v.first("SO")
      val era = 9 * earnedRuns / innings
      val players = v.first("Players")
      Series("IP" -> innings,
        "ERA" -> era,
        "SO" -> strikeOuts,
        "P" -> players)
    }
  }

}