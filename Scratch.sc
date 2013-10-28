object Scratch {

  import org.saddle.io.CsvImplicits._
  import org.saddle.io._
  import org.saddle._
  import me.rickychang.mlbmusic._

  val alHomeBattingFile = "/Users/ricchang/Documents/projects/mlb-plate-music/2013-al-home-batting.csv"
                                                  //> alHomeBattingFile  : String = /Users/ricchang/Documents/projects/mlb-plate-m
                                                  //| usic/2013-al-home-batting.csv
  val nlHomeBattingFile = "/Users/ricchang/Documents/projects/mlb-plate-music/2013-nl-home-batting.csv"
                                                  //> nlHomeBattingFile  : String = /Users/ricchang/Documents/projects/mlb-plate-m
                                                  //| usic/2013-nl-home-batting.csv
  val alHomePitchingFile = "/Users/ricchang/Documents/projects/mlb-plate-music/2013-al-home-pitching.csv"
                                                  //> alHomePitchingFile  : String = /Users/ricchang/Documents/projects/mlb-plate-
                                                  //| music/2013-al-home-pitching.csv
  val nlHomePitchingFile = "/Users/ricchang/Documents/projects/mlb-plate-music/2013-nl-home-pitching.csv"
                                                  //> nlHomePitchingFile  : String = /Users/ricchang/Documents/projects/mlb-plate-
                                                  //| music/2013-nl-home-pitching.csv
  val playerMusicFile = "/Users/ricchang/Documents/projects/mlb-plate-music/mlbPlateMusic.tsv"
                                                  //> playerMusicFile  : String = /Users/ricchang/Documents/projects/mlb-plate-mus
                                                  //| ic/mlbPlateMusic.tsv
  val fileParser = new FileParser                 //> fileParser  : me.rickychang.mlbmusic.FileParser = me.rickychang.mlbmusic.Fil
                                                  //| eParser@5328f6ee
  val qualifiedPAs = 251                          //> qualifiedPAs  : Int = 251
  val qualifiedIP = 81                            //> qualifiedIP  : Int = 81

  val playerMusic = fileParser.parsePlateMusic(playerMusicFile)
                                                  //> playerMusic  : org.saddle.Frame[String,String,String] = [750 x 1]
                                                  //|                            Artist 
                                                  //|                     ------------- 
                                                  //|      C.J. Wilson ->       Chromeo 
                                                  //|   Chris Iannetta ->         Drake 
                                                  //|   Collin Cowgill ->       J. Cole 
                                                  //|      Hank Conger -> Bingo Players 
                                                  //|   Ernesto Frieri ->   Gypsy Kings 
                                                  //| ...
                                                  //| Kirk Nieuwenhuis ->    TED NUGENT 
                                                  //|       Lucas Duda -> Jimmi Hendrix 
                                                  //|      Mike Baxter -> Ozzy Osbourne 
                                                  //|     Ruben Tejada -> Angel & Khriz 
                                                  //|   Scott Atchison -> George Strait 
                                                  //| 
  
  val alBatting = fileParser.parseBattingStats(alHomeBattingFile)
                                                  //> alBatting  : org.saddle.Frame[String,String,String] = [329 x 11]
                                                  //|                      G AB PA  H 1B      3B HR  R RBI HBP 
                                                  //|                     -- -- -- -- --  ... -- -- -- --- --- 
                                                  //|       Melky Mesa ->  1  2  2  2  1  ...  0  0  1   1   0 
                                                  //|    Quintin Berry ->  1  2  2  2  2  ...  0  0  2   2   0 
                                                  //|      Matt Carson ->  7  5  5  4  4  ...  0  0  3   1   0 
                                                  //|       Eric Fryer ->  3  6  8  3  2  ...  0  1  1   3   0 
                                                  //| Ezequiel Carrera ->  2  4  5  2  2  ...  0  0  1   1   0 
                                                  //| ...
                                                  //|    Jerry Blevins ->  1  1  1  0  0  ...  0  0  0   0   0 
                                                  //|      Cord Phelps ->  3  8  8  0  0  ...  0  0  0   0   0 
                                                  //|       Adam Moore ->  2  3  3  0  0  ...  0  0  0   0   0 
                                                  //|    Brandon Bantz ->  1  2  2  0  0  ...  0  0  0   0   0 
                                                  //|   Bryan Anderson ->  3  8  8  0  0  ...  0  0  0   0   0 
                                                  //| 
  
  val nlBatting = fileParser.parseBattingStats(nlHomeBattingFile)
                                                  //> nlBatting  : org.saddle.Frame[String,String,String] = [523 x 11]
                                                  //|                     G AB PA  H 1B      3B HR  R RBI HBP 
                                                  //|                    -- -- -- -- --  ... -- -- -- --- --- 
                                                  //|       Zach Duke ->  1  1  1  1  1  ...  0  0  0   0   0 
                                                  //|       Ryan Webb ->  1  1  1  1  1  ...  0  0  1   0   0 
                                                  //|    Blake DeWitt ->  1  1  1  1  0  ...  0  0  0   0   0 
                                                  //|    Tanner Roark ->  2  3  3  2  2  ...  0  0  0   0   0 
                                                  //|       Matt Diaz ->  4  4  4  2  2  ...  0  0  0   1   0 
                                                  //| ...
                                                  //|    Mike Kickham ->  2  3  3  0  0  ...  0  0  0   0   0 
                                                  //| Carlos Martinez ->  1  1  1  0  0  ...  0  0  0   0   0 
                                                  //|     Brian Flynn ->  1  0  1  0  0  ...  0  0  0   0   0 
                                                  //|      Nate Karns ->  2  1  3  0  0  ...  0  0  0   0   0 
                                                  //|       Alex Wood ->  7 12 12  0  0  ...  0  0  0   0   0 
                                                  //| 
  val alPitching = fileParser.parsePitchingStats(alHomePitchingFile)
                                                  //> alPitching  : org.saddle.Frame[String,String,String] = [335 x 3]
                                                  //|                            IP ER SO 
                                                  //|                    ---------- -- -- 
                                                  //|   Neftali Feliz ->  2.6666667  0  3 
                                                  //|      Blake Wood ->  1.3333334  0  1 
                                                  //|      Luis Ayala ->  1.6666667  0  1 
                                                  //|     Ryan Raburn ->        1.0  0  1 
                                                  //| Tyler Robertson -> 0.33333334  0  1 
                                                  //| ...
                                                  //|   Joel Hanrahan ->  2.3333333  7  3 
                                                  //| Dellin Betances ->  1.6666667  6  4 
                                                  //|  Pedro Figueroa ->        1.0  4  2 
                                                  //|      Sean Nolin ->  1.3333334  6  0 
                                                  //| David Carpenter -> 0.33333334  4  1 
                                                  //| 
  val nlPitching = fileParser.parsePitchingStats(nlHomePitchingFile)
                                                  //> nlPitching  : org.saddle.Frame[String,String,String] = [346 x 3]
                                                  //|                             IP ER SO 
                                                  //|                     ---------- -- -- 
                                                  //|    Xavier Cedeno ->        3.0  0  3 
                                                  //|    Zach Phillips ->  1.6666667  0  1 
                                                  //|    John McDonald -> 0.33333334  0  1 
                                                  //|   Chris Narveson ->        2.0  0  0 
                                                  //|   Skip Schumaker ->        2.0  0  1 
                                                  //| ...
                                                  //|     Eric Surkamp ->  2.6666667  7  0 
                                                  //|      Duane Below ->        1.0  3  1 
                                                  //|    Mike Zagurski ->  2.6666667  9  2 
                                                  //| Marc Rzepczynski ->  1.3333334  5  2 
                                                  //|     Casper Wells ->  0.6666667  5  0 
                                                  //| 
  
  val t = nlPitching.join(playerMusic, how=index.InnerJoin)
                                                  //> t  : org.saddle.Frame[String,Int,String] = [142 x 4]
                                                  //|                             0  1  2                        3 
                                                  //|                    ---------- -- -- ------------------------ 
                                                  //|   Xavier Cedeno ->        3.0  0  3             Daddy Yankee 
                                                  //|   John McDonald -> 0.33333334  0  1     Florida Georgia Line 
                                                  //|  Skip Schumaker ->        2.0  0  1             JASON ALDEAN 
                                                  //|       J.J. Putz ->  18.666666  1 23                    AC/DC 
                                                  //| Aroldis Chapman ->       41.0  3 79 Rage Against the Machine 
                                                  //| ...
                                                  //|    Jose Mijares ->  21.666666 17 25             Daddy Yankee 
                                                  //|  Charles Brewer ->  2.3333333  2  1                Metallica 
                                                  //|  Anthony Recker ->        1.0  2  0                   Atreyu 
                                                  //|  Anthony Recker ->        1.0  2  0             Trevor Jones 
                                                  //|    Casper Wells ->  0.6666667  5  0                    Drake 
                                                  //| 

  val finalAlPitching = Utils.computeFinalPitchingStats(Utils.normalizeStatsByArtist(alPitching, playerMusic)).sortedRowsBy(r => r.raw(1))
                                                  //> finalAlPitching  : org.saddle.Frame[String,String,Float] = [113 x 4]
                                                  //|                                  IP       ERA   SO   P 
                                                  //|                           --------- --------- ---- --- 
                                                  //|               Chimbala -> 2.6666667       0.0  3.0 1.0 
                                                  //|                  Drake ->       1.0       0.0  1.0 1.0 
                                                  //|   Los Tigres del Norte -> 1.6666667       0.0  1.0 1.0 
                                                  //|            Rhett Akins ->       1.0       0.0  0.5 1.0 
                                                  //|             Toby Keith ->       2.0       0.0  3.0 1.0 
                                                  //| ...
                                                  //| Juan Magan & Crossfire -> 48.333332 7.0758624 22.0 1.0 
                                                  //|       Blackberry Smoke -> 20.666666  7.403226 14.0 1.0 
                                                  //|             Nickelback -> 18.666666  7.714286 16.0 1.0 
                                                  //|           Led Zeppelin ->       1.0       9.0  1.0 1.0 
                                                  //|        Jose Pe–a Suazo ->      11.0  9.818182  9.0 1.0 
                                                  //| 
  
  
  
  
  
  
  
  

  val finalNlPitching = Utils.computeFinalPitchingStats(Utils.normalizeStatsByArtist(nlPitching, playerMusic))
                                                  //> finalNlPitching  : org.saddle.Frame[String,String,Float] = [114 x 4]
                                                  //|                                               IP        ERA    SO   P 
                                                  //|                                        --------- ---------- ----- --- 
                                                  //|                          116 Clique -> 39.333332  2.5169492  35.0 1.0 
                                                  //|                          A$AP Rocky ->      65.0  1.6615385  18.5 1.0 
                                                  //|             A$AP Rocky ft. Skrillex -> 24.666666  4.0135136  31.0 1.0 
                                                  //|                               AC/DC ->     176.0     2.8125 162.0 3.0 
                                                  //|                          AWOLNATION -> 50.333332  3.3079472  26.5 1.0 
                                                  //| ...
                                                  //|                      Wisin y Yandel ->      33.0 0.95454544  12.5 1.0 
                                                  //|                         Wiz Khalifa -> 44.333332   5.075188  36.0 1.0 
                                                  //|                              Zion I -> 21.333334    2.53125  22.0 1.0 
                                                  //|                                fun. -> 230.33333  1.5434154 111.5 3.0 
                                                  //| –ejo y Dalmatia featuring J Alvarez ->      98.0  1.1938776 108.0 1.0 
                                                  //| 
  

 
  
  val finalAlBatting = Utils.computeFinalBattingStats(Utils.normalizeStatsByArtist(alBatting, playerMusic))
                                                  //> finalAlBatting  : org.saddle.Frame[String,String,Float] = [194 x 11]
                                                  //|                                      AVG         G        AB        PA     
                                                  //|     H             2B        3B        HR       RBI   P 
                                                  //|                               ---------- --------- --------- --------- ----
                                                  //| -----  ... --------- --------- --------- --------- --- 
                                                  //|                   2 Chainz -> 0.24258476      84.0 314.66666 360.33334 76.3
                                                  //| 33336  ... 16.666666 5.3333335 7.3333335 31.333334 2.0 
                                                  //|                       A-Ha ->  0.1884058      10.0      34.5     37.75     
                                                  //|   6.5  ...       1.0       0.0      1.25       3.0 1.0 
                                                  //|                       ABBA ->        0.2      22.0      72.5      76.0     
                                                  //|  14.5  ...       4.0       0.0       2.0       7.0 2.0 
                                                  //|                      AC/DC ->  0.2769441 136.33334 488.66666  531.6666 135.
                                                  //| 33334  ...      30.0 1.3333334 21.333334  70.66667 3.0 
                                                  //|             Alexandra Stan -> 0.31391585      20.0     77.25      85.
                                                  //| Output exceeds cutoff limit.
  
  finalAlBatting.sortedRowsBy(-_.raw(0))          //> res0: org.saddle.Frame[String,String,Float] = [194 x 11]
                                                  //|                                    AVG    G    AB    PA    H        2B  3B 
                                                  //|   HR  RBI   P 
                                                  //|                             ---------- ---- ----- ----- ----  ... ---- --- 
                                                  //| ---- ---- --- 
                                                  //|               Kanye West -> 0.36603773 74.0 265.0 314.0 97.0  ... 15.0 1.0 
                                                  //| 17.0 60.0 1.0 
                                                  //|          Winsin y Yandel -> 0.34554973 26.0  95.5 106.0 33.0  ...  7.5 0.0 
                                                  //|  3.5 14.0 1.0 
                                                  //| Zion, feat. Jory y Ken-y -> 0.34554973 26.0  95.5 106.0 33.0  ...  7.5 0.0 
                                                  //|  3.5 14.0 1.0 
                                                  //|                  J. Cole -> 0.33333334 15.0  39.0  44.0 13.0  ...  2.0 1.0 
                                                  //|  1.0  6.0 1.0 
                                                  //|                Rick Ross -> 0.33333334 37.0 151.5 163.5 50.5  ... 10.0 1.5 
                                                  //|  4.0 23.0 1.0 
                                                  //| ...
                                                  //|                  El Alfa ->      0.125  6.0  16.0  17.0  2.0  ...  0.0 0.0 
                                                  //|  0.0  1.0 1.0 
                                                  //| Rage Against The Machine -> 0.10714286 37.0  84.0  89.0  9.0  ...  1.0 0.0 
                                                  //|  1.0  1.0 1.0 
                                                  //|                
                                                  //| Output exceeds cutoff limit.
  
  
  
  val finalNlBatting = Utils.computeFinalBattingStats(Utils.normalizeStatsByArtist(nlBatting, playerMusic)).sortedRowsBy(r => -r.raw(8))
                                                  //> finalNlBatting  : org.saddle.Frame[String,String,Float] = [246 x 11]
                                                  //|                                AVG     G    AB    PA     H        2B  3B   
                                                  //| HR  RBI   P 
                                                  //|                         ---------- ----- ----- ----- -----  ... ---- --- --
                                                  //| -- ---- --- 
                                                  //|            DJ Khaled -> 0.23799582 130.0 479.0 542.0 114.0  ... 26.0 0.0 28
                                                  //| .0 78.0 2.0 
                                                  //| Florida Georgia Line ->  0.2773893 140.0 429.0 501.0 119.0  ... 24.0 3.0 19
                                                  //| .0 68.0 3.0 
                                                  //|         Jason Aldean -> 0.23088023 211.0 693.0 772.0 160.0  ... 26.0 3.0 19
                                                  //| .0 84.5 5.0 
                                                  //|         Jim Johnston ->   0.302974 148.0 538.0 625.0 163.0  ... 24.0 2.0 19
                                                  //| .0 68.0 2.0 
                                                  //|           Alex Clare -> 0.34285715  75.0 280.0 320.0  96.0  ... 16.0 2.0 16
                                                  //| .0 69.0 1.0 
                                                  //| ...
                                                  //|        White Stripes -> 0.21428572  16.0  28.0  32.0   6.0  ...  2.0 0.0  0
                                                  //| .0  1.0 1.0 
                                                  //|         Willie Colon ->     0.3125  16.5  48.0  52.5  15.0  ...  3.0 0.0  0
                                                  //| .0  6.0 1.0 
                                                  //|       Wisin & Yandel 
                                                  //| Output exceeds cutoff limit.

}