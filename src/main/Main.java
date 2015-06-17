package main;

import java.io.IOException;
import javax.media.CannotRealizeException;
import javax.media.NoPlayerException;

import states.States;



public class Main
   {

   public static void main(String[] args) throws IOException, NoPlayerException, CannotRealizeException, InterruptedException
      {
      States.Start();
      }
   }
//   public static void main(String[] args) throws IOException, NoPlayerException, CannotRealizeException, InterruptedException
//      {
//      //Document doc0 = Jsoup.connect("http://www.mgoblue.com/").get();//This will just search links from our MAIN PAGE
//      /*
//      //print URL if body of page contains the word hockey
//      if(doc0.text().contains("hockey") || doc0.text().contains("Hockey")) 
//         {
//         PrintWriter out = new PrintWriter("ExportedHTML/Page" + pageNumber + ".html");//outputs html code of pages
//         out.println(doc0.toString());
//         out.close();
//         }
//
//      //get all links and recursively call processPage() on them
//      Elements links = doc0.select("a[href]");//Elements --> list of type Element
//      for(Element link: links)
//         {//makes sure we're only looking in the mgoblue.com domain
//         if(link.attr("href").contains("mgoblue.com")) processPage(link.attr("abs:href"));
//         }
//      */
//      
//      String baseUrl = "http://www.free-tv-video-online.info";
//      String inputString = "family guy";//SAY WE CHOOSE family guy
//      
//      inputString = inputString.replaceAll(" ", "%20");
//      System.out.println(inputString);
//      
//      
//      //#request# GET http://www.free-tv-video-online.info/search/?q=family%20guy&md=all
//      Document doc0 = Jsoup.connect(baseUrl + "/search/?q=" + inputString + "&md=all").userAgent("Chrome").get();
//      String htmlText = doc0.toString();
//      int indexToSearchFrom = 0;
//      int indexToEndAt = 0;
//      //int primaryResults = 0;
////      while (htmlText.substring(indexToSearchFrom, htmlText.length()).contains("<td width=\"97%\" class=\"mnlcategorylist\">"))
////         {
////         indexToSearchFrom = htmlText.indexOf("<td width=\"97%\" class=\"mnlcategorylist\">", indexToSearchFrom) + 1;
////         primaryResults++;
////         }
//      numOfShows = findNumberOfInstances(htmlText, "<td width=\"97%\" class=\"mnlcategorylist\">");//number of shows/movies
//      String showExts[] = new String[numOfShows];
//      String showNames[] = new String[numOfShows];
//      
//      indexToSearchFrom = 0;
//      
//      for (int i = 0; i < numOfShows; i++)
//         {
//         //System.out.println("initial indexToSearchFrom: " + indexToSearchFrom);//show extentions
//         indexToSearchFrom = htmlText.indexOf("<td width=\"97%\" class=\"mnlcategorylist\">", indexToSearchFrom + 5);
//         //System.out.println("new indexToSearchFrom: " + indexToSearchFrom);//show extentions
//         
//         showExts[i] = twoLayerParse(htmlText.substring(indexToSearchFrom), "<td width=\"97%\" class=\"mnlcategorylist\">", "<a href=", ">", 9, -1);
//         showNames[i] = twoLayerParse(htmlText.substring(indexToSearchFrom), "<td width=\"97%\" class=\"mnlcategorylist\">", "<b>", "</b>", 3, 0);//show names from search
//
//         System.out.println("showExts[" + i + "]: " + showExts[i]);//show extentions
//         System.out.println("showNames[" + i + "]: " + showNames[i]);//Show names
//         }  
//      
//      
//      
//      
//      
//      int chooseShow = 0;//SAY WE CHOOSE first option, family guy show
//      doc0 = Jsoup.connect(baseUrl + showExts[chooseShow]).userAgent("Chrome").get();
//      htmlText = doc0.toString();
//      
//      numOfSeasons = findNumberOfInstances(htmlText, "<td width=\"99%\" class=\"mnlcategorylist\"><a href=");//number of seasons
//      //Show myShow = new Show(secondResults, firstResultsExts[0]);//show of <secondResults> seasons
//      int seasonIncomplete = findNumberOfInstances(htmlText, "Next Episode Air Date");
//      
//      String seasonExts[] = new String[numOfSeasons];//OB
//      String seasonNames[] = new String[numOfSeasons];//OB
//      String seasonInfo[] = new String[numOfSeasons];
//      
//      //<td width="99%" class="mnlcategorylist"><a href="season_1.html"><b>Season 1</b></a> (7 Episodes, 69 Links)</td>
//      indexToSearchFrom = 0;
//      for (int i = 0; i < numOfSeasons; i++)
//         {
//         //indexToSearchFrom = htmlText.substring(indexToSearchFrom + 1).indexOf("<td width=\"99%\" class=\"mnlcategorylist\"><a href=");
//         indexToSearchFrom = htmlText.indexOf("<td width=\"99%\" class=\"mnlcategorylist\"><a href=", indexToSearchFrom + 5);
//               
//         seasonExts[i] = twoLayerParse(htmlText.substring(indexToSearchFrom), "<td width=\"99%\" class=\"mnlcategorylist\"><a href=", "<a href=", ">", 9, -1);//fill in season extentions
//         seasonNames[i] = twoLayerParse(htmlText.substring(indexToSearchFrom), "<td width=\"99%\" class=\"mnlcategorylist\"><a href=", "<b>", "</b>", 3, 0);//fill in season names
//
//         if ( (i == numOfSeasons - 1) && (1 == seasonIncomplete) )
//            {
//            seasonInfo[i] = twoLayerParse(htmlText.substring(indexToSearchFrom), "<td width=\"99%\" class=\"mnlcategorylist\"><a href=", "</b></a>", "<br>", 8, 0).trim();
//            seasonInfo[i] = seasonInfo[i] + " " +  twoLayerParse(htmlText.substring(indexToSearchFrom), "<td width=\"99%\" class=\"mnlcategorylist\"><a href=", "Next Episode Air Date", "<br>", 0, -1).trim();
//            seasonInfo[i] = seasonInfo[i] + ", " +  twoLayerParse(htmlText.substring(indexToSearchFrom), "Next Episode Air Date", "<br>", "</span></td>", 5, 0).trim();
//            }
//         else
//            {
//            seasonInfo[i] = twoLayerParse(htmlText.substring(indexToSearchFrom), "<td width=\"99%\" class=\"mnlcategorylist\"><a href=", "</b></a>", "</td>", 8, 0).trim();//fill in #of episodes & links
//            }
//         //myShow.seasonList[i].
//         
//         System.out.println("seasonExts[" + i + "]: " + seasonExts[i]);
//         System.out.println("seasonNames[" + i + "]: " + seasonNames[i]);
//         System.out.println("seasonInfo[" + i + "]: " + seasonInfo[i]);
//         }
//      
//      
//      
//      
//      
//      int chooseSeason = 0;//SAY WE CHOOSE first option, season 1
//      doc0 = Jsoup.connect(baseUrl + showExts[chooseShow] + seasonExts[chooseSeason]).userAgent("Chrome").get();
//      htmlText = doc0.toString();
//      
//      numOfEpisodes = findNumberOfInstances(htmlText, "<td class=\"episode\"><a name=\"e");//number of episodes
//      String episodeNames[] = new String[numOfEpisodes];//episode names
//      
////      <table width="100%" border="0" cellpadding="4" cellspacing="0">
////      <tr class="3" bgcolor="#E3E3E3" >
////                              <td class="episode"><a name="e1"></a><b>1. Death Has a Shadow</b></td>
//      indexToSearchFrom = 0;
//      
//      for (int i = 0; i < numOfEpisodes; i++)//number of episodes in this season
//         {        
//         indexToSearchFrom = htmlText.indexOf("<td class=\"episode\"><a name=\"e", indexToSearchFrom + 5);
//         
//         episodeNames[i] = twoLayerParse(htmlText.substring(indexToSearchFrom), "<td class=\"episode\"><a name=\"e", "<b>", "</b>", 3, 0);
//
//         System.out.println("episodeNames[" + i + "]: " + episodeNames[i]);
//         }
//      
//      
//      
//      
//      int chooseEpisode = 0;//SAY WE CHOOSE first option, episode 1 (or 0)
//      //no need for new doc grab
//      
////      indexToSearchFrom = htmlText.indexOf("<td class=\"episode\"><a name=\"e", 0);//index for first episode
////      String episodeText;
//      indexToSearchFrom = 0;
//      
//      for (int i = 0; i < chooseEpisode + 1; i++)
//         {
//         indexToSearchFrom = htmlText.indexOf("<td class=\"episode\"><a name=\"e", indexToSearchFrom + 1);//new index to end at is at start of next episode's list of links
//         }
//      
//      if (htmlText.substring(indexToSearchFrom + 1, htmlText.length()).contains("<td class=\"episode\"><a name=\"e"))//more episodes left in list
//         {
//         indexToEndAt = htmlText.indexOf("<td class=\"episode\"><a name=\"e", indexToSearchFrom + 1);//moves end index to start of next episode's text
//         }
//      else
//         {
//         indexToEndAt = htmlText.length();//moves end index to very end
//         } 
//      
//      String episodeText = htmlText.substring(indexToSearchFrom, indexToEndAt);//start of links list to end of links list, all text for this episode
//      numOfEpisodeLinks = findNumberOfInstances(episodeText, "visited(");//number of links for current episode<a onclick='visited(
//      
//      String[] parsedEpisodeExts = new String[numOfEpisodeLinks];
//      System.out.println("For episode[" + chooseEpisode + "], search for links between index of: " + indexToSearchFrom + " and " + indexToEndAt + ", numOfEpisodeLinks = " + numOfEpisodeLinks);
//      
//      indexToSearchFrom = 0;
//         //<a onclick='visited(2235481)' href="/interstitial2.html?lnk=%2Fplayer%2Fvodlocker.php%3Fid%3Dlkuo7q90nb0g&ttl=Family+Guy+Season+1+Episode+1" target="_blank">
//      for (int i = 0; i < numOfEpisodeLinks; i++)
//         {
//         indexToSearchFrom = episodeText.indexOf("visited(", indexToSearchFrom);
//         indexToSearchFrom = episodeText.indexOf("href=", indexToSearchFrom);
//         indexToEndAt = episodeText.indexOf("\"", indexToSearchFrom + 7);//find end or url extention
//         parsedEpisodeExts[i] = episodeText.substring(indexToSearchFrom + 6, indexToEndAt);//just the url extention, now parse
//         System.out.println("non-parsed EpisodeExts: " + parsedEpisodeExts[i]);
//         
//         parsedEpisodeExts[i] = parsedEpisodeExts[i].substring(parsedEpisodeExts[i].indexOf("player"));//tempThirdString.replaceAll("interstitial2.html?lnk=%2F", "");
//         parsedEpisodeExts[i] = "/" + parsedEpisodeExts[i];
//         parsedEpisodeExts[i] = parsedEpisodeExts[i].replaceAll("%2F", "/");
//         parsedEpisodeExts[i] = parsedEpisodeExts[i].replaceAll("%3F", "?");
//         parsedEpisodeExts[i] = parsedEpisodeExts[i].replaceAll("%3D", "=");
//         indexToEndAt = parsedEpisodeExts[i].indexOf("&amp");//"ttl=");
//         parsedEpisodeExts[i] = parsedEpisodeExts[i].substring(0, indexToEndAt);
//         
//         System.out.println("parsedEpisodeExts[" + i + "]: " + parsedEpisodeExts[i]);
//          
//         }
//
//     // #request# GET http://www.free-tv-video-online.info/player/vodlocker.php?id=lkuo7q90nb0g
//      // /interstitial2.html?lnk=%2F player %2F vodlocker.php %3F id %3D lkuo7q90nb0g &ttl=Family+Guy+Season+1+Episode+1" target="_blank">
//      // /interstitial2.html?lnk=%2F player %2F vodlocker.php %3F id %3D lkuo7q90nb0g&amp
//      
//      int chooseLink = 0;//SAY WE CHOOSE first option, first link (or 0)
//      
//      doc0 = Jsoup.connect(baseUrl + parsedEpisodeExts[chooseLink]).userAgent("Chrome").get();
//      htmlText = doc0.toString();
//      System.out.println("first connected to: " + baseUrl + parsedEpisodeExts[chooseLink]);
//      
//      doc0 = (Document) Jsoup.connect( twoLayerParse(htmlText.toLowerCase(), "<div id=\"vidcont\"", "src=", "html", 5, 4) ).userAgent("Chrome").get();
//      System.out.println("now connected to: " + twoLayerParse(htmlText.toLowerCase(), "<div id=\"vidcont\"", "src=", "html", 5, 4));
//      htmlText = doc0.toString();
//      
//      
//      
////      <script type='text/javascript'>  jwplayer("flvplayer").setup({
////      file: "http://50.7.100.74:8777/gkcebajjno4pcnokaiuch2og5jploy36l4xdmlsqhg2xrirahxbxq7yydm/v.mp4"
//      String fileUrl = twoLayerParse(htmlText, "jwplayer(\"flvplayer\").setup", "file: \"", ".mp4\"", 7, 4);
//      
//      System.out.println(fileUrl);
//      
//      //Stage fxPlayerStage = new Stage();
//      
////      MediaPlayer fxPlayer = new MediaPlayer();
////      fxPlayer.setUrlAndLaunch(fileUrl);
//      
//      MediaPlayer2 fxPlayer2 = new MediaPlayer2();
//      fxPlayer2.setUrlAndLaunch(fileUrl);
//
//      //fxPlayer.start(fxPlayerStage);
//      
////      public static void main(String[] args) {
////      if (args.length > 0) {
////                  arg1 = args[0];
////              }
////              Application.launch(fxPlayer);
////          }
////      
//      
//      
////      URL url = new URL(fileUrl);
////      URLConnection conn = url.openConnection();
////
////      InputStream is = conn.getInputStream();
////      OutputStream os = new FileOutputStream("familyGuyVid.mp4");
////
////      int k;
////      while((k = is.read()) != -1) {
////          os.write(k);
////      }
////      is.close();
////      os.close();
//      
//      //////////////////////
////      System.out.println("about to declare video file");
////      File videoFile = new File("familyGuyVid.mp4");
////      System.out.println("about to create VideoPlayer");
////      VideoPlayer player = new VideoPlayer(videoFile);//audioFile);
////      System.out.println("about to openFrame()");
////      player.openFrame();
////      System.out.println("about to wait10()");
////      
////      player.wait10();
////      player.play();
////      player.stop();
//      ///////////////////////
//      
////      //Open a URL Stream
////      Response resultImageResponse = Jsoup.connect(fileUrl).ignoreContentType(true).execute();
////
////      // output here
////      FileOutputStream out = (new FileOutputStream(new java.io.File("familyGuyTest.mp4")));
////      out.write(resultImageResponse.bodyAsBytes());           // resultImageResponse.body() is where the image's contents are.
////      out.close();
//      
//      // http://fs2.vodlocker.com:8777/o2celajjno4pcnokaiuch2og5j3hukpeu5ypp4wji7d2bzd2peojzsftay/v.mp4
//      
//      }
//   
//   static String twoLayerParse(String text, String startStr0, String startStr1, String endStr, int startOffset, int endOffset)
//      {
//      int startIndex = 0;
//      startIndex = text.indexOf(startStr0, startIndex);
//      startIndex = text.indexOf(startStr1, startIndex);
//      int indexToEndAt = text.indexOf(endStr, startIndex);
//      return text.substring(startIndex + startOffset, indexToEndAt + endOffset);
//      }
//   
//   
////   indexToSearchFrom = htmlText.indexOf("<b>", indexToEndAt);
////   indexToEndAt = htmlText.indexOf("</b>", indexToSearchFrom + 1);
////   firstResultsNames[i] = htmlText.substring(indexToSearchFrom + 3, indexToEndAt);
////   
//   
//   static int findNumberOfInstances(String text, String toFind)
//      {
//      int startIndex = 0;
//      int numOfResults = 0;
//      while (text.substring(startIndex).contains(toFind))
//         {
//         startIndex = text.indexOf(toFind, startIndex) + 1;
//         numOfResults++;
//         }
//      return numOfResults;
//      }
//
//   }
