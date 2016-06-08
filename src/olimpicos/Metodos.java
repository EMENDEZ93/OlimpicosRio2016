
package olimpicos;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class Metodos {

    public void Efecto_Boton_Entrada( Button X  ){
    
    ScaleTransition scaleTransition = new ScaleTransition(
    Duration.millis(1500), X);
    scaleTransition.setToX(0.90f);
    scaleTransition.setToY(0.90f);
    scaleTransition.setCycleCount( 4 );
    scaleTransition.setAutoReverse( true );
    ParallelTransition parallelTransition = new ParallelTransition();
    parallelTransition.getChildren().addAll( scaleTransition);
    parallelTransition.setCycleCount(Timeline.INDEFINITE); 
    parallelTransition.play();
   
    }
    
    
    
    public void Efecto_de_Entrada(ActionEvent event, ImageView imagev , Button X , Button X2) {
    
    RotateTransition rotateTransition = new RotateTransition(
    Duration.millis(3000), imagev);
    rotateTransition.setByAngle(180f);
    rotateTransition.setCycleCount(0);
    rotateTransition.setAutoReverse(false);
    
    //acercar al usuario
    ScaleTransition scaleTransition = new ScaleTransition(
    Duration.millis(2000), imagev);
    scaleTransition.setToX(3f);
    scaleTransition.setToY(3f);
    scaleTransition.setCycleCount(0);
    scaleTransition.setAutoReverse(false);

  FadeTransition ft = new FadeTransition(Duration.millis(1000), imagev);
    ft.setFromValue(1.0);
    ft.setToValue(0.0);
    ft.play();
    
    ParallelTransition parallelTransition = new ParallelTransition();
    parallelTransition.getChildren().addAll(rotateTransition, scaleTransition);
   // parallelTransition.setCycleCount(Timeline.INDEFINITE);
    parallelTransition.play();

    X.setVisible( false );    
    X2.setVisible( false );    

    
   //FadeTransition ft2 = new FadeTransition(Duration.millis(1000), X);
   // ft2.setFromValue(1.0);
   // ft2.setToValue(0.0);
   // ft2.play();
    
  //  FadeTransition ft3 = new FadeTransition(Duration.millis(1000), X2);
 //   ft3.setFromValue(1.0);
 //   ft3.setToValue(0.0);
  //  ft3.play();
    
    }
    
  public void Efecto_de_Volver(ActionEvent event, ImageView imagev , Button X, Button X2/*,ToolBar Barra,ToolBar Barra2*/) {
    
    RotateTransition rotateTransition = new RotateTransition(
    Duration.millis(3000), imagev);
    rotateTransition.setByAngle(-180.00f);
    rotateTransition.setCycleCount(0);
    rotateTransition.setAutoReverse(false);
    
    //acercar al usuario
    ScaleTransition scaleTransition = new ScaleTransition(
    Duration.millis(2000), imagev);
    scaleTransition.setToX(1f);
    scaleTransition.setToY(1f);
    scaleTransition.setCycleCount(0);
    scaleTransition.setAutoReverse(false);

  FadeTransition ft = new FadeTransition(Duration.millis(1000), imagev);
    ft.setFromValue(0.0);
    ft.setToValue(1.0);
    ft.play();
    
    ParallelTransition parallelTransition = new ParallelTransition();
    parallelTransition.getChildren().addAll(rotateTransition, scaleTransition);
   // parallelTransition.setCycleCount(Timeline.INDEFINITE);
    parallelTransition.play();

    FadeTransition ft2 = new FadeTransition(Duration.millis(9000),X);
    ft2.setFromValue(0.0);
    ft2.setToValue(1.0);
    ft2.play();
    X.setVisible(true);
    
    FadeTransition ft3 = new FadeTransition(Duration.millis(9000),X2);
    ft3.setFromValue(0.0);
    ft3.setToValue(1.0);
    ft3.play();
    X2.setVisible( true );      
  
    imagev.setVisible(true);
    
    // FadeTransition ft4 = new FadeTransition(Duration.millis(1000),Barra);
  //  ft4.setFromValue(1.0);
  //  ft4.setToValue(0.0);
  //  ft4.play();

  //  FadeTransition ft5 = new FadeTransition(Duration.millis(1000),Barra2);
  //  ft5.setFromValue(1.0);
  //  ft5.setToValue(0.0);
  //  ft5.play();
    
    } 
      
public void Efecto_Barra( ToolBar Barra,ToolBar Barra2 ){
  FadeTransition ft = new FadeTransition(Duration.millis(1000), Barra);
    ft.setFromValue(0.0);
    ft.setToValue(4.0);
    ft.play();
    Barra.setVisible(true);
    Barra2.setVisible(false);

}

public void Efecto_Barra3( ToolBar Barra ){
  FadeTransition ft = new FadeTransition(Duration.millis(1000), Barra);
    ft.setFromValue(0.0);
    ft.setToValue(4.0);
    ft.play();
    Barra.setVisible(true);
    
}


public void Efecto_GROUP( Group GR ){
  FadeTransition ft = new FadeTransition(Duration.millis(1000), GR);
    ft.setFromValue(4.0);
    ft.setToValue(0.0);
    ft.play();
   // Barra.setVisible(true);
}


}



