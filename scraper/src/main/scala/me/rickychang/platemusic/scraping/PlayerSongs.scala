package me.rickychang.platemusic.scraping

  case class PlayerSong(val name: String, val position: String, val song: Song)

  case class Song(val artist: String, val title: String, val genre: String)

  object Song {
    def apply(songHTML: org.chafed.Html): Option[Song] = {
      val artist = (songHTML $ (".artist")).headOption
      val title = (songHTML $ ("h2")).headOption
      val genre = (songHTML $ (".label-info")).headOption
      val song = for {
        a <- artist
        t <- title
        g <- genre
      } yield Song(a.text, t.text, g.text)
      song
    }

  }