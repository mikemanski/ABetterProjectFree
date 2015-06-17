package mediaPlayer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer;


public class MediaPlayer2 extends Application 
   {
   
   MediaPlayer mediaPlayer;
   private Label time;
   Duration duration;
   Button fullScreenButton;
   Scene scene;
   Media media;
   double width;
   double height;
   MediaView mediaView;
   private static String MEDIA_URL;// = "http://fs2.vodlocker.com:8777/o2cel6r2gk4pcnokaiuch2og5l224rpor4l4tucr4i2fegpwpvhth222yi/v.mp4";

   @Override
   public void start(Stage primaryStage) 
      {
      //scene = setScene(this.width, this.height);
   
      primaryStage.setTitle("Enjoy");
      Group root = new Group();
      Scene scene = new Scene(root, 520, 440);
      
   // create media player //This part added from oracle tutorial 2

      //URL MEDIA_URL = urlString.toUrl();
      Media media = new Media(MEDIA_URL);
      MediaPlayer mediaPlayer = new MediaPlayer(media);
      mediaPlayer.setAutoPlay(true);
      
      MediaControl mediaControl = new MediaControl(mediaPlayer);
      scene.setRoot(mediaControl);
      
      
      primaryStage.setScene(scene);
   
      primaryStage.show();
      }
   
   public void setUrlAndLaunch(String mediaUrl)
      {
      MEDIA_URL = mediaUrl;
      System.out.println("launching video player");
      launch("did this work?");
      }
   
   
//   public Scene setScene(double width, double height) 
//      {
//      this.height = height;
//      this.width = width;
//      //Add your own path of the vidio that you want to play
//      String path = "F:/MediaPl/src/mediapl/Tom and Jerry Beach YouTube.mp4";
//   
//      media = new Media(new File(path).toURI().toString());
//   
//      mediaPlayer = new MediaPlayer(media);
//      //AutoPlay set to false
//      mediaPlayer.setAutoPlay(false);
//      mediaView = new MediaView(mediaPlayer);
//   
//      // DropShadow effect
//      DropShadow dropshadow = new DropShadow();
//      dropshadow.setOffsetY(5.0);
//      dropshadow.setOffsetX(5.0);
//      dropshadow.setColor(Color.WHITE);
//   
//      mediaView.setEffect(dropshadow);
//   
//      BorderPane borderPane = new BorderPane();
//      borderPane.setCenter(mediaView);
//      borderPane.setBottom(addToolBar());
//   
//      borderPane.setStyle("-fx-background-color: Black");
//      scene = new Scene(borderPane, 600, 600);
//      scene.setFill(Color.BLACK);
//      return scene;
//      }
   
   //buttons
//   playButton.setOnAction( (ActionEvent e) -> {mediaPlayer.play();} );
//   
//   pauseButton.setOnAction( (ActionEvent e) -> {mediaPlayer.pause(); } );
//   
//   forwardButton.setOnAction( (ActionEvent e) -> {mediaPlayer.seek(mediaPlayer.getCurrentTime().multiply(1.5));} );
//   
//   backButton.setOnAction( (ActionEvent e) -> {mediaPlayer.seek(mediaPlayer.getCurrentTime().divide(1.5));} );
//   
//   firstButton.setOnAction( (ActionEvent e) -> {mediaPlayer.seek(mediaPlayer.getStartTime()); mediaPlayer.stop();} );
//   
//   lastButton.setOnAction( (ActionEvent e) -> {mediaPlayer.seek(mediaPlayer.getTotalDuration()); mediaPlayer.stop();} );
//   
//   reloadButton.setOnAction( (ActionEvent e) -> {mediaPlayer.seek(mediaPlayer.getStartTime());} );
//   


   
//   fullScreenButton.setOnAction( (ActionEvent e) -> 
//      {
//      if (primaryStage.isFullScreen()) 
//         primaryStage.setFullScreen(false); 
//      else 
//         primaryStage.setFullScreen(true);
//      } );
   
   
//   filesButton.setOnAction( (ActionEvent e) -> 
//      {
//      FileChooser fc = new FileChooser();
//      fc.getExtensionFilters().add(new ExtensionFilter("*.flv", "*.mp4", "*.mpeg"));
//      File file = fc.showOpenDialog(null);
//      String path = file.getAbsolutePath();
//      path = path.replace("\\", "/");
//      media = new Media(new File(path).toURI().toString());
//      mediaPlayer.stop();
//      mediaPlayer = new MediaPlayer(media);
//      mediaPlayer.setAutoPlay(true);
//      mediaView.setMediaPlayer(mediaPlayer);
//      } );
   
   //time label
//   time = new Label();
//   time.setTextFill(Color.YELLOW);
//   time.setPrefWidth(80);
//
//   mediaPlayer.currentTimeProperty().addListener( (Observable ov) -> {updateValues();} );
//
//   mediaPlayer.setOnReady( () -> 
//      {
//      duration = mediaPlayer.getMedia().getDuration(); 
//      updateValues();
//      } );
   
   
   //updateValues() method
//   protected void updateValues() 
//      {
//      if (time != null) 
//         {
//         runLater( () -> 
//            {
//            Duration currentTime = mediaPlayer.getCurrentTime();
//            time.setText(formatTime(currentTime, duration));
//            } );
//         }
//      }
   
   
   
   //formatTime() method
//   private static String formatTime(Duration elapsed, Duration duration) 
//      {
//      int intElapsed = (int) floor(elapsed.toSeconds());
//      int elapsedHours = intElapsed / (60 * 60);
//      if (elapsedHours > 0) 
//         {
//         intElapsed -= elapsedHours * 60 * 60;
//         }
//      int elapsedMinutes = intElapsed / 60;
//      int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;
//   
//      if (duration.greaterThan(Duration.ZERO)) 
//         {
//         int intDuration = (int) floor(duration.toSeconds());
//         int durationHours = intDuration / (60 * 60);
//         if (durationHours > 0) {
//         intDuration -= durationHours * 60 * 60;
//         }
//         
//         int durationMinutes = intDuration / 60;
//         int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;
//         
//         if (durationHours > 0) 
//            {
//            return format("%d:%02d:%02d/%d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds, durationHours, durationMinutes, durationSeconds);
//            } 
//         else 
//            {
//         return format("%02d:%02d/%02d:%02d", elapsedMinutes, elapsedSeconds, durationMinutes, durationSeconds);
//            }
//         } 
//      else 
//         {
//         if (elapsedHours > 0) 
//            {
//         return format("%d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds);
//            } 
//         else 
//            {
//         return format("%02d:%02d", elapsedMinutes, elapsedSeconds);
//            }
//         }
//      }
   
   

   
   

   }
