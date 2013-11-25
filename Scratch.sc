object Scratch {

  import org.saddle.io.CsvImplicits._
  import org.saddle.io._
  import org.saddle._
  import me.rickychang.mlbmusic._

  val alHomeBattingFile = "/Users/rchang/Documents/projects/mlb-plate-music/2013-al-home-batting.csv"
                                                  //> alHomeBattingFile  : String = /Users/rchang/Documents/projects/mlb-plate-mus
                                                  //| ic/2013-al-home-batting.csv
  val nlHomeBattingFile = "/Users/rchang/Documents/projects/mlb-plate-music/2013-nl-home-batting.csv"
                                                  //> nlHomeBattingFile  : String = /Users/rchang/Documents/projects/mlb-plate-mus
                                                  //| ic/2013-nl-home-batting.csv
  val alHomePitchingFile = "/Users/rchang/Documents/projects/mlb-plate-music/2013-al-home-pitching.csv"
                                                  //> alHomePitchingFile  : String = /Users/rchang/Documents/projects/mlb-plate-mu
                                                  //| sic/2013-al-home-pitching.csv
  val nlHomePitchingFile = "/Users/rchang/Documents/projects/mlb-plate-music/2013-nl-home-pitching.csv"
                                                  //> nlHomePitchingFile  : String = /Users/rchang/Documents/projects/mlb-plate-mu
                                                  //| sic/2013-nl-home-pitching.csv
  val playerMusicFile = "/Users/rchang/Documents/projects/mlb-plate-music/mlbPlateMusic.tsv"
                                                  //> playerMusicFile  : String = /Users/rchang/Documents/projects/mlb-plate-music
                                                  //| /mlbPlateMusic.tsv
  val fileParser = new FileParser                 //> fileParser  : me.rickychang.mlbmusic.FileParser = me.rickychang.mlbmusic.Fil
                                                  //| eParser@236eb92a
  val qualifiedPAs = 50                           //> qualifiedPAs  : Int = 50
  val qualifiedIP = 81                            //> qualifiedIP  : Int = 81

  val playerMusic = fileParser.parsePlateMusic(playerMusicFile)
                                                  //> playerMusic  : org.saddle.Frame[(String, String),String,String] = [750 x 4]
                                                  //|                                                Song        Artist           
                                                  //|     Genre                                                                   
                                                  //|                                                      URL 
                                                  //|                            ------------------------ ------------- ----------
                                                  //| --------- ------------------------------------------------------------------
                                                  //| -------------------------------------------------------- 
                                                  //| Angels      C.J. Wilson ->           Night By Night       Chromeo         Al
                                                  //| ternative                    https://itunes.apple.com/us/album/night-by-nigh
                                                  //| t/id391396784?i=391396787&uo=4&at=10l3Hj&ct=mobile_music 
                                                  //|          Chris Iannetta ->             Up All Night         Drake         Hi
                                                  //| p-Hop/Rap                      https://itunes.apple.com/us/album/up-all-nigh
                                                  //| t/id378379062?i=378379369&uo=4&at=10l3Hj&ct=mobile_music 
                                                  //|          Collin Cowg
                                                  //| Output exceeds cutoff limit.
  
  
  
  
  
  
  
  
  
  
val alBatting = fileParser.parseBattingStats(alHomeBattingFile)
                                                  //> alBatting  : org.saddle.Frame[(String, String),String,String] = [329 x 11]
                                                  //|                                G AB PA  H 1B      3B HR  R RBI HBP 
                                                  //|                               -- -- -- -- --  ... -- -- -- --- --- 
                                                  //|   Yankees       Melky Mesa ->  1  2  2  2  1  ...  0  0  1   1   0 
                                                  //|   Red Sox    Quintin Berry ->  1  2  2  2  2  ...  0  0  2   2   0 
                                                  //|   Indians      Matt Carson ->  7  5  5  4  4  ...  0  0  3   1   0 
                                                  //|     Twins       Eric Fryer ->  3  6  8  3  2  ...  0  1  1   3   0 
                                                  //|   Indians Ezequiel Carrera ->  2  4  5  2  2  ...  0  0  1   1   0 
                                                  //| ...
                                                  //| Athletics    Jerry Blevins ->  1  1  1  0  0  ...  0  0  0   0   0 
                                                  //|   Indians      Cord Phelps ->  3  8  8  0  0  ...  0  0  0   0   0 
                                                  //|    Royals       Adam Moore ->  2  3  3  0  0  ...  0  0  0   0   0 
                                                  //|  Mariners    Brandon Bantz ->  1  2  2  0  0  ...  0  0  0   0   0 
                                                  //| White Sox   Bryan Anderson ->  3  8  8  0  0  ...  0  0  0   0   0 
                                                  //| 
val alPitching = fileParser.parsePitchingStats(alHomePitchingFile)
                                                  //> alPitching  : org.saddle.Frame[(String, String),String,String] = [335 x 3]
                                                  //|                                      IP ER SO 
                                                  //|                              ---------- -- -- 
                                                  //|   Rangers   Neftali Feliz ->  2.6666667  0  3 
                                                  //|   Indians      Blake Wood ->  1.3333334  0  1 
                                                  //|   Orioles      Luis Ayala ->  1.6666667  0  1 
                                                  //|   Indians     Ryan Raburn ->        1.0  0  1 
                                                  //|     Twins Tyler Robertson -> 0.33333334  0  1 
                                                  //| ...
                                                  //|   Red Sox   Joel Hanrahan ->  2.3333333  7  3 
                                                  //|   Yankees Dellin Betances ->  1.6666667  6  4 
                                                  //| Athletics  Pedro Figueroa ->        1.0  4  2 
                                                  //| Blue Jays      Sean Nolin ->  1.3333334  6  0 
                                                  //|    Angels David Carpenter -> 0.33333334  4  1 
                                                  //| 

val finalAlPitching = Utils.computeFinalPitchingStats(Utils.normalizeStatsByField(alPitching, playerMusic, "Artist")).sortedRowsBy(-_.raw(3))
                                                  //> finalAlPitching  : org.saddle.Frame[String,String,Float] = [101 x 4]
                                                  //|                              IP       ERA    SO   P 
                                                  //|                      ---------- --------- ----- --- 
                                                  //|         Metallica ->      203.0 3.3694582 202.0 5.0 
                                                  //|  Brantley Gilbert ->  179.66666 3.3395178 164.0 3.0 
                                                  //|       Johnny Cash ->  194.33334 2.5471697  69.5 3.0 
                                                  //|         Shinedown ->  185.33334 3.0917265 140.0 3.0 
                                                  //| Breaking Benjamin ->   74.66667 1.4464284 101.0 2.0 
                                                  //| ...
                                                  //|    Wisin & Yandel ->  3.3333333       5.4   3.0 1.0 
                                                  //|  Wolfgang Gartner -> 104.333336  3.536741  94.0 1.0 
                                                  //|        Wolfmother ->        8.0      2.25  12.0 1.0 
                                                  //|    Zac Brown Band -> 101.666664 3.3639345  73.0 1.0 
                                                  //|        ted nugent ->  48.333332  6.144828  34.0 1.0 
                                                  //| 
          
val finalAlBatting = Utils.computeFinalBattingStats(Utils.normalizeStatsByField(alBatting, playerMusic, "Genre")).sortedRowsBy(-_.raw(10))
                                                  //> finalAlBatting  : org.saddle.Frame[String,String,Float] = [19 x 11]
                                                  //|                       AVG         G        AB        PA         H          
                                                  //|    2B        3B        HR       RBI    P 
                                                  //|                ---------- --------- --------- --------- ---------  ... ----
                                                  //| ----- --------- --------- --------- ---- 
                                                  //| Hip-Hop/Rap ->  0.2715302   2249.75  7860.083  8839.082   2134.25  ... 441.
                                                  //| 08325     50.75 235.08333    1025.0 53.0 
                                                  //|        Rock -> 0.25949803    1302.5  4369.333    4916.0 1133.8333  ... 215.
                                                  //| 83331 20.333336 164.83334  579.3334 39.0 
                                                  //|             ->  0.2672647 1351.3334 4812.3335 5385.8335 1286.1669  ... 250.
                                                  //| 25002 14.416666 156.99998    686.75 37.0 
                                                  //|     Country -> 0.26523829     796.0    2707.0    3064.0     718.0  ...     
                                                  //| 156.0      14.0     100.0     383.0 19.0 
                                                  //| Alternative -> 0.24567875 405.83334   1152.25    1298.5 283.08334  ... 64.6
                                                  //| 66664 3.8333335 43.666664 178.33334 13.0 
                                                  //| ...
                                                  //|  Pop Latino -> 0.
                                                  //| Output exceeds cutoff limit.


}