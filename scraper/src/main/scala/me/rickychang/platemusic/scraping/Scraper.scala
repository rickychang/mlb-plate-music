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

  def getTeamSongs(teamHtml: Html): Seq[PlayerSong] =
    (for {
      playerDiv <- teamHtml $ (".well")
      playerLink <- playerDiv $ ("a")
      playerRes <- getPlayerHtml(playerLink.attribute("href").get.text)
    } yield getPlayerSongs(playerRes.get)).flatten

  def getLeagueSongs(leagueName: String, leagueURL: String): Map[String, Seq[PlayerSong]] =
    (for {
      leagueHtml <- UserAgent GET (leagueURL)
      l <- leagueHtml $ (".leagues")
      a <- l $ ("a")
      _ = println("processing: %s".format(a.text))
      teamHtml <- a.click
    } yield (a.text, getTeamSongs(teamHtml))).toMap

  def getPlayerSongs(playerHtml: Html): Seq[PlayerSong] =
    for {
      playerInfo <- playerHtml $ (".player-info")
      name <- playerInfo $ (".playerprofile")
      _ = println("processing: %s".format(name.text))
      position <- playerInfo $ ("h2")
      song <- playerHtml $ (".song")
      s <- Song(song)
    } yield new PlayerSong(name.text, position.text.split("-").toBuffer(1).trim, s)

  def getPlayerHtml(link: String): Option[Response] = {
    try {
      if (link.startsWith("http")) Some(UserAgent GET link)
      // ugly hack to workaround bug in chafed. If path of url contains a space
      // library wigs out...
      else Some(UserAgent GET new URL(
        http,
        "mlbplatemusic.com",
        80,
        link.split("/").map(e => URLEncoder.encode(e)).mkString("/"),
        ""))
    } catch {
      case _: Throwable => None
    }
  }

  override def main(args: Array[String]): Unit = {
    if (args.length != 1) println("please provide an output file name.")
    else {
      val alUrl = ("AL", "http://mlbplatemusic.com/al/")
      val nlUrl = ("NL", "http://mlbplatemusic.com/nl/")
      val pw = new java.io.PrintWriter(new File(args(0)))
      try {
        for (leagueUrl <- List(alUrl, nlUrl);
             (team, songs) <- getLeagueSongs(leagueUrl._1, leagueUrl._2);
             playerSong <- songs) {
          pw.write(
            List(leagueUrl._1, team, playerSong.name,
              playerSong.position, playerSong.song.artist,
              playerSong.song.title, playerSong.song.genre)
              .mkString("\t") + "\n")
        }
      } finally {
        pw.close()
      }
    }
  }

}