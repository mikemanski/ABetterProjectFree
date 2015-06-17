package util;

public class Util
   {

   
   
   public static String twoLayerParse(String text, String startStr0, String startStr1, String endStr, int startOffset, int endOffset)
      {
      int startIndex = 0;
      startIndex = text.indexOf(startStr0, startIndex);
      startIndex = text.indexOf(startStr1, startIndex);
      int indexToEndAt = text.indexOf(endStr, startIndex);
      return text.substring(startIndex + startOffset, indexToEndAt + endOffset);
      }
   
   
   public static int findNumberOfInstances(String text, String toFind)
      {
      int startIndex = 0;
      int numOfResults = 0;
      while (text.substring(startIndex).contains(toFind))
         {
         startIndex = text.indexOf(toFind, startIndex) + 1;
         numOfResults++;
         }
      return numOfResults;
      }
   
   public static String baseUrl = "http://www.free-tv-video-online.info";
   
   }
