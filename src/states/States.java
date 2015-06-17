package states;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.media.CannotRealizeException;
import javax.media.NoPlayerException;

import mediaPlayer.MediaPlayer2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import util.Util;
import windows.State0Win;
import windows.State1Win;
import windows.State2Win;
import windows.State3Win;
import windows.State4Win;

public class States
   {
   //State0
   static int numOfShows = 0;
   public static String showExts[];
   public static String showNames[];
   public static State0Win state0Win;
   //State1
   static int numOfSeasons = 0;//output of State1
   public static String seasonExts[];//output of State1
   public static String seasonNames[];//output of State1
   public static String seasonInfo[];//output of State1
   public static int chooseShow = 0;//user input at start State1
   public static State1Win state1Win;
   //State2
   static int numOfEpisodes = 0;
   static String[] episodeNames;
   public static State2Win state2Win;
   //State3
   public static int numOfEpisodeLinks = 0;
   public static String[] parsedEpisodeExts;
   public static State3Win state3Win;
   public static String[] hostNames;
   //State4
   public static State4Win state4Win;
   
//   public static void main(String[] args) throws IOException, NoPlayerException, CannotRealizeException, InterruptedException
//      {
//      Start(); 
//      }
   
   public static void Start()
      {
      if (state1Win != null)
         state1Win.dispose();
      if (state2Win != null)
         state2Win.dispose();
      if (state3Win != null)
         state3Win.dispose();
      if (state4Win != null)
         state4Win.dispose();
       
      state0Win = new State0Win();
      state0Win.setVisible(true);
      }
   
   public static void State0(String inputString) throws IOException
      {
      state0Win.dispose();
      System.out.println("<< Entering State 0 >>");
      inputString = inputString.replaceAll(" ", "%20");
      System.out.println(inputString);    
      
      //#request# GET http://www.free-tv-video-online.info/search/?q=family%20guy&md=all
      Document doc0 = Jsoup.connect(Util.baseUrl + "/search/?q=" + inputString + "&md=all").userAgent("Chrome").get();
      String htmlText = doc0.toString();
      int indexToSearchFrom = 0;
      int indexToEndAt = 0;

      numOfShows = Util.findNumberOfInstances(htmlText, "<td width=\"97%\" class=\"mnlcategorylist\">");//number of shows/movies
      showExts = new String[numOfShows];
      showNames = new String[numOfShows]; 
      
      for (int i = 0; i < numOfShows; i++)
         {
         indexToSearchFrom = htmlText.indexOf("<td width=\"97%\" class=\"mnlcategorylist\">", indexToSearchFrom + 5);
         //getting name of show/movie
         showExts[i] = Util.twoLayerParse(htmlText.substring(indexToSearchFrom), "<td width=\"97%\" class=\"mnlcategorylist\">", "<a href=", ">", 9, -1);
         showNames[i] = Util.twoLayerParse(htmlText.substring(indexToSearchFrom), "<td width=\"97%\" class=\"mnlcategorylist\">", "<b>", "</b>", 3, 0);//show names from search
         
         //getting info of show/movie
         indexToSearchFrom = htmlText.indexOf("a class=\"info", indexToSearchFrom + 5);
         showNames[i] = showNames[i] + " (" + Util.twoLayerParse(htmlText.substring(indexToSearchFrom), "href", ">", "<", 1, 0);//adds "TV Shows" or "Movies"
         indexToSearchFrom = htmlText.indexOf("a class=\"info", indexToSearchFrom + 5);    
         showNames[i] = showNames[i] + " / " + Util.twoLayerParse(htmlText.substring(indexToSearchFrom), "href", ">", "<", 1, 0);//adds "<#> Seasons" or "<genre>"
         indexToSearchFrom = htmlText.indexOf("a class=\"info", indexToSearchFrom + 5);    
         showNames[i] = showNames[i] + " / " + Util.twoLayerParse(htmlText.substring(indexToSearchFrom), "href", ">", "<", 1, 0) + ")";//adds "<#> Episodes" or "<year>"
               
         System.out.println("showExts[" + i + "]: " + showExts[i]);//show extentions
         System.out.println("showNames[" + i + "]: " + showNames[i]);//Show names
         }  

      state1Win = new State1Win(numOfShows, showNames);
      state1Win.setVisible(true);
      }

   
   public static void State1(int chooseShow) throws IOException
      {
      state1Win.dispose();
      System.out.println("<< Entering State 1 >>");
      Document doc0 = Jsoup.connect(Util.baseUrl + showExts[chooseShow]).userAgent("Chrome").get();
      String htmlText = doc0.toString();
      
      numOfSeasons = Util.findNumberOfInstances(htmlText, "<td width=\"99%\" class=\"mnlcategorylist\"><a href=");//number of seasons
      int seasonIncomplete = Util.findNumberOfInstances(htmlText, "Next Episode Air Date");
      
      seasonExts = new String[numOfSeasons];//OB
      seasonNames = new String[numOfSeasons];//OB
      seasonInfo = new String[numOfSeasons];
      
      //<td width="99%" class="mnlcategorylist"><a href="season_1.html"><b>Season 1</b></a> (7 Episodes, 69 Links)</td>
      int indexToSearchFrom = 0;
      for (int i = 0; i < numOfSeasons; i++)
         {
         //indexToSearchFrom = htmlText.substring(indexToSearchFrom + 1).indexOf("<td width=\"99%\" class=\"mnlcategorylist\"><a href=");
         indexToSearchFrom = htmlText.indexOf("<td width=\"99%\" class=\"mnlcategorylist\"><a href=", indexToSearchFrom + 5);
               
         seasonExts[i] = Util.twoLayerParse(htmlText.substring(indexToSearchFrom), "<td width=\"99%\" class=\"mnlcategorylist\"><a href=", "<a href=", ">", 9, -1);//fill in season extentions
         seasonNames[i] = Util.twoLayerParse(htmlText.substring(indexToSearchFrom), "<td width=\"99%\" class=\"mnlcategorylist\"><a href=", "<b>", "</b>", 3, 0);//fill in season names
   
         if ( (i == numOfSeasons - 1) && (1 == seasonIncomplete) )
            {
            seasonInfo[i] = Util.twoLayerParse(htmlText.substring(indexToSearchFrom), "<td width=\"99%\" class=\"mnlcategorylist\"><a href=", "</b></a>", "<br>", 8, 0).trim();
            seasonInfo[i] = seasonInfo[i] + " " +  Util.twoLayerParse(htmlText.substring(indexToSearchFrom), "<td width=\"99%\" class=\"mnlcategorylist\"><a href=", "Next Episode Air Date", "<br>", 0, -1).trim();
            seasonInfo[i] = seasonInfo[i] + ", " +  Util.twoLayerParse(htmlText.substring(indexToSearchFrom), "Next Episode Air Date", "<br>", "</span></td>", 5, 0).trim();
            }
         else
            {
            seasonInfo[i] = Util.twoLayerParse(htmlText.substring(indexToSearchFrom), "<td width=\"99%\" class=\"mnlcategorylist\"><a href=", "</b></a>", "</td>", 8, 0).trim();//fill in #of episodes & links
            }
         
         System.out.println("seasonExts[" + i + "]: " + seasonExts[i]);
         System.out.println("seasonNames[" + i + "]: " + seasonNames[i]);
         System.out.println("seasonInfo[" + i + "]: " + seasonInfo[i]);
         }
      
      state2Win = new State2Win(numOfSeasons, seasonNames, seasonInfo);
      state2Win.setVisible(true);
      }
      
   
   public static void State2(int chooseSeason) throws IOException
      {
      state2Win.dispose();
      System.out.println("<< Entering State 2 >>");
      Document doc0 = Jsoup.connect(Util.baseUrl + showExts[chooseShow] + seasonExts[chooseSeason]).userAgent("Chrome").get();
      String htmlText = doc0.toString();
      numOfEpisodes = Util.findNumberOfInstances(htmlText, "<td class=\"episode\"><a name=\"e");
      episodeNames = new String[numOfEpisodes];
      
      //  <td class="episode"><a name="e1"></a><b>1. Death Has a Shadow</b></td>
      int indexToSearchFrom = 0;

      for (int i = 0; i < numOfEpisodes; i++)//get name for earch episode in this season
         {        
         indexToSearchFrom = htmlText.indexOf("<td class=\"episode\"><a name=\"e", indexToSearchFrom + 5);
         
         episodeNames[i] = Util.twoLayerParse(htmlText.substring(indexToSearchFrom), "<td class=\"episode\"><a name=\"e", "<b>", "</b>", 3, 0);
   
         System.out.println("episodeNames[" + i + "]: " + episodeNames[i]);
         }
      
      state3Win = new State3Win(numOfEpisodes, episodeNames, htmlText);
      state3Win.setVisible(true);
      }
   
   
   public static void State3(int chooseEpisode, String htmlText) throws IOException //prompted to select link
      {//no need for new doc grab, reuse "htmlText"
      state3Win.dispose();
      System.out.println("<< Entering State 3 >>");
      int indexToSearchFrom = 0;
      int indexToEndAt = 0;
      
      for (int i = 0; i < chooseEpisode + 1; i++)
         {
         indexToSearchFrom = htmlText.indexOf("<td class=\"episode\"><a name=\"e", indexToSearchFrom + 1);//moves indexToSearchFrom to start of our episode's list of links
         }
      
      if (htmlText.substring(indexToSearchFrom + 1, htmlText.length()).contains("<td class=\"episode\"><a name=\"e"))//more episodes left in list
         {
         indexToEndAt = htmlText.indexOf("<td class=\"episode\"><a name=\"e", indexToSearchFrom + 1);//moves indexToEndAt to start of next episode's list of links
         }
      else
         {
         indexToEndAt = htmlText.length();//moves end index to very end
         } 
      
      String episodeText = htmlText.substring(indexToSearchFrom, indexToEndAt);//start of links list to end of links list, all text for this episode
      numOfEpisodeLinks = Util.findNumberOfInstances(episodeText, "visited(");//number of links for our episode
      
      parsedEpisodeExts = new String[numOfEpisodeLinks];
      hostNames = new String[numOfEpisodeLinks];
      //System.out.println("For episode[" + chooseEpisode + "], search for links between index of: " + indexToSearchFrom + " and " + indexToEndAt + ", numOfEpisodeLinks = " + numOfEpisodeLinks);
      
      indexToSearchFrom = 0;
         //<a onclick='visited(2235481)' href="/interstitial2.html?lnk=%2Fplayer%2Fvodlocker.php%3Fid%3Dlkuo7q90nb0g&ttl=Family+Guy+Season+1+Episode+1" target="_blank">
      for (int i = 0; i < numOfEpisodeLinks; i++)
         {
         //to get links to sources
         indexToSearchFrom = episodeText.indexOf("visited(", indexToSearchFrom);
         indexToSearchFrom = episodeText.indexOf("href=", indexToSearchFrom);
         indexToEndAt = episodeText.indexOf("\"", indexToSearchFrom + 7);//find end or url extention
         parsedEpisodeExts[i] = episodeText.substring(indexToSearchFrom + 6, indexToEndAt);//just the url extention, now parse
         //System.out.println("non-parsed EpisodeExts: " + parsedEpisodeExts[i]);
         
         //to get host names of the sources
         indexToSearchFrom = episodeText.indexOf("Host:", indexToSearchFrom);
         indexToEndAt = episodeText.indexOf("<br", indexToSearchFrom);
         hostNames[i] = episodeText.substring(indexToSearchFrom + 6, indexToEndAt);
         //System.out.println("Host names: " + hostNames[i]);
       
         parsedEpisodeExts[i] = parsedEpisodeExts[i].substring(parsedEpisodeExts[i].indexOf("player"));//tempThirdString.replaceAll("interstitial2.html?lnk=%2F", "");
         parsedEpisodeExts[i] = "/" + parsedEpisodeExts[i];
         parsedEpisodeExts[i] = parsedEpisodeExts[i].replaceAll("%2F", "/");
         parsedEpisodeExts[i] = parsedEpisodeExts[i].replaceAll("%3F", "?");
         parsedEpisodeExts[i] = parsedEpisodeExts[i].replaceAll("%3D", "=");
         indexToEndAt = parsedEpisodeExts[i].indexOf("&amp");//"ttl=");
         parsedEpisodeExts[i] = parsedEpisodeExts[i].substring(0, indexToEndAt);
         
         System.out.println("parsedEpisodeExts[" + i + "]: " + parsedEpisodeExts[i]);
         }
      
      state4Win = new State4Win(numOfEpisodeLinks, hostNames);
      state4Win.setVisible(true);
      }
   
   
   public static void State4(int chooseLink, boolean downloadInstead, String fileName) throws IOException
      {
      state4Win.dispose();
      System.out.println("<< Entering State 4 >>");
      
      Document doc0 = Jsoup.connect(Util.baseUrl + parsedEpisodeExts[chooseLink]).userAgent("Chrome").get();
      String htmlText = doc0.toString();
      //System.out.println("first connected to: " + Util.baseUrl + parsedEpisodeExts[chooseLink]);
      
      doc0 = (Document) Jsoup.connect( Util.twoLayerParse(htmlText.toLowerCase(), "<div id=\"vidcont\"", "src=", "html", 5, 4) ).userAgent("Chrome").get();
      //System.out.println("now connected to: " + Util.twoLayerParse(htmlText.toLowerCase(), "<div id=\"vidcont\"", "src=", "html", 5, 4));
      htmlText = doc0.toString();
   
      String fileUrl = Util.twoLayerParse(htmlText, "jwplayer(\"flvplayer\").setup", "file: \"", ".mp4\"", 7, 4);
      System.out.println("Source file found at: " + fileUrl);
      
      if (!downloadInstead) //Save video
         {
         System.out.println("Now starting stream...");
         MediaPlayer2 fxPlayer2 = new MediaPlayer2();
         fxPlayer2.setUrlAndLaunch(fileUrl);
         }
      else //Stream video
         {
         System.out.println("Now starting download");
         URL url = new URL(fileUrl);
         URLConnection conn = url.openConnection();
         InputStream is = conn.getInputStream();
         OutputStream os = new FileOutputStream(fileName + ".mp4");
      
         for(int k, byteCounter = 1; (k = is.read()) != -1 ; byteCounter++) 
            {
            os.write(k);
            if (byteCounter % 5242880 == 0) //5242880 bytes == 5 MB; 1048576 bytes == 1 MB
               System.out.println((byteCounter/1048576) + "MB complete...");
            }
         is.close();
         os.close();
         System.out.println("Download complete!");
         }
   
      }
   
   }
