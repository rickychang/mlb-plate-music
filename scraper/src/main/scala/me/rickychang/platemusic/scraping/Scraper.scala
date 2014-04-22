package me.rickychang.platemusic.scraping

import java.io.File
import java.net.URLEncoder

import scala.Option.option2Iterable
import scala.xml.NodeSeq.seqToNodeSeq

import org.chafed.Html
import org.chafed.Html.html2Node
import org.chafed.Response
import org.chafed.Response.response2List
import org.chafed.URI.str2URI
import org.chafed.URL
import org.chafed.UserAgent
import org.chafed.http

object Scraper extends App {

  def getTeamSongs(teamHtml: Html) = {
    val players = teamHtml $ ".music_Player li"
    for (playerElmt <- players) yield getPlayerSong(playerElmt)
  }

  def getPlayerSong(playerElmt: Html) = {
    val songLink = playerElmt.attribute("data-ios-link").map(_.head) map (_.text)
    val playerInfo = playerElmt $ ".info" head
    val playerNameRaw = (playerInfo $ "h3" head).text
    val playerName = if (playerNameRaw contains ",") playerNameRaw.split(",").reverse.mkString(" ").trim else playerNameRaw.trim
    val songTitle = (playerInfo $ "h4" head).text
    val songArtist = (playerInfo $ "h5" head).text
    val songGenre = songLink.map(e => getGenre(e)).flatten.headOption
    (playerName, songTitle, songArtist, songGenre, songLink)
  }

  def getGenre(songLink: String): Option[String] = {
    try {
      val itunesRes = UserAgent GET (songLink)
      val genreLinks = itunesRes.map { e =>
        val genresElmt = e $ ".genre"
        if (!genresElmt.isEmpty)
      	  genresElmt.head $("a")
        else
          Seq.empty
      }.flatten
      genreLinks.map(_.text).headOption
    } catch { 
      case e: Exception => None
    }
  }

  override def main(args: Array[String]): Unit = {
    println(args(0))
    if (args.length != 2) println("Usage: Scraper <input> <output>")
    else {
      val input = scala.io.Source.fromFile(args(0)).getLines
      val pw = new java.io.PrintWriter(new File(args(1)))
      try {
        for (line <- input) {
          val Array(teamName, teamURL) = line.split(",")
          println("Processing %s".format(teamName))
          val teamRes = UserAgent GET (teamURL)
          if (teamRes.isDefined) {
            val teamHtml = teamRes.get
            val teamSongs = getTeamSongs(teamHtml)
            for (song <- teamSongs) {
              pw.write(List(teamName, song._1, song._2, song._3, song._4.getOrElse(""), song._5.getOrElse(""))
                .mkString("\t") + "\n")
            }
          }

        }
      } finally {
        pw.close()
      }
    }
  }

}