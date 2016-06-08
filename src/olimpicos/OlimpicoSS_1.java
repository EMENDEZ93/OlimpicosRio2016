package olimpicos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * @author Edwin Mendez
 */
public class OlimpicoSS_1 extends Application {

    private Pagination pagination;
    AnchorPane anchor = new AnchorPane();

    private Pagination pagination2;
    AnchorPane anchor2 = new AnchorPane();

    private Pagination pagination3;
    AnchorPane anchor3 = new AnchorPane();

//nodo padre
    Group GR;

//ArrayList Auxiliares 
    ArrayList<CPaises2> AuxiliarObjetos = new ArrayList<>();
    ArrayList<Deportista> AuxiliarObjetosDeportistas = new ArrayList<>();
    ArrayList<CDestacado> AuxiliarObjetosDestacados = new ArrayList<>();
    ArrayList<CDestacadoColombiano> AuxiliarObjetosDestacadosColombianos = new ArrayList<>();

//lista para paises     
    ObservableList<String> data = FXCollections.observableArrayList();
    ListView<String> listView = new ListView<String>(data);

//lista para medallas de Oro    
    ObservableList<String> dataOro = FXCollections.observableArrayList();
    ListView<String> listOro = new ListView<String>(dataOro);

//lista para medallas de Plata        
    ObservableList<String> dataPlata = FXCollections.observableArrayList();
    ListView<String> listPlata = new ListView<String>(dataPlata);

//lista para medallas de Bronce            
    ObservableList<String> dataBronce = FXCollections.observableArrayList();
    ListView<String> listBronce = new ListView<String>(dataBronce);

//lista para medallas de Totla           
    ObservableList<String> dataTotal = FXCollections.observableArrayList();
    ListView<String> listTotal = new ListView<String>(dataTotal);

//datos de todos los paises
    ObservableList<PieChart.Data> DataPaises;
    PieChart chart = new PieChart();

//datos de consulta para los paises
    ObservableList<PieChart.Data> DataConsultaPais;
    PieChart chartPais = new PieChart();

//titulos
    Text TRegistro = new Text();
    Text TPaises = new Text();
    Text TMedallas = new Text();
    Text TDeporte = new Text();
    Text MedallaPaises = new Text();
    Text MedallasDestacados = new Text("Medallas Ganadas");
    Text MedallasParaDes = new Text("-Oro-   -Plata-   -Bronce-");
    Text MedallaGanada = new Text("Medalla Lograda");

//botonos para ToolBar    
    Button Registro = new Button("Registro");
    Button Medalleria = new Button("Medallero");
    Button SurAmerica = new Button("Sur America");
    Button Figuras = new Button("Destacados");
    Button Registro_Colombia = new Button("Historia Colombia");
    Button Colombia = new Button("Colombia 2016");
    Button CrearPais = new Button("Crear Pais");
    Button CrearDestacados = new Button("Crear Destacados");
    Button CrearDestacadosColombianos = new Button("Crear DesColombianos");
    Button Datos = new Button("Datos");
    Button Consulta = new Button("Consulta");
    Button Cerrar = new Button("Cerrar Sesion");
    Button Cerrar2 = new Button("Cerrar Sesion");

    Label lresultado = new Label();
    Label linfo = new Label();
    Label pathFile = new Label();
    Label pathFileDestacados = new Label();
    String rutaImagen;

//Barra para Usuario     
    ToolBar Barra = new ToolBar(
            Registro, new Separator(),
            Medalleria, new Separator(),
            SurAmerica, new Separator(),
            Figuras, new Separator(),
            Registro_Colombia, new Separator(),
            Colombia, new Separator(),
            Datos, new Separator(),
            Consulta, new Separator(),
            Cerrar
    );

//Barra para Administrador    
    ToolBar BarraAdmin = new ToolBar(
            CrearPais, new Separator(),
            CrearDestacados, new Separator(),
            CrearDestacadosColombianos, new Separator(),
            Cerrar2
    );

//boton para agregar medalla     
    Button Agregar = new Button("Agregar medalla");

//Boton para agregar foto     
    Button Foto = new Button("Foto");
    Button FotoDestacados = new Button("Foto");

//textfiel para nombre del deportista    
    TextField TNombre = new TextField("");

//textfiel para nombre del pais        
    TextField TNombrePais = new TextField();
    Button BCrearP = new Button("- Crear -");

//textfiel para nombre del deportista destacados    
    TextField TNombreDestacados = new TextField();
    TextField TNacionalidadDestacados = new TextField("");
    TextArea AreaPerfilDestacados = new TextArea();
    TextArea AreaLogroDestacados = new TextArea();
    ChoiceBox<Integer> OroDestacados = new ChoiceBox<>();
    ChoiceBox<Integer> PlataDestacados = new ChoiceBox<>();
    ChoiceBox<Integer> BronceDestacados = new ChoiceBox<>();
    Button BCrearDestacado = new Button("- Crear Destacado-");
    String rutaImagenDestacados;

//textfiel para nombre del deportista destacados colombia     
    TextField TNombreDestacadosColombiano = new TextField();
    TextArea AreaPerfilDestacadosColombiano = new TextArea();
    TextArea AreaLogroDestacadosColombiano = new TextArea();
    TextField TOlimpicoDestacadosColombiano = new TextField();
    TextField TDeporteDestacadosColombiano = new TextField();
    String rutaImagenDestacadosColombiano;
    Button BCrearDestacadoColombiano = new Button("- Crear Destacado Colombiano -");
    Button FotoDestacadosColombiano = new Button("- Foto -");
    Button BCrearDestacadosColombiano = new Button("- Crear D.C -");
    String rutaImagenDestacadoColombiano;
    ChoiceBox<String> MedallasDestacadoColombia = new ChoiceBox<>();

//creacion choicebox paises, deportes, medallas   
    ChoiceBox< String> Paises = new ChoiceBox<>();
    ChoiceBox< String> CDeportes = new ChoiceBox<>();
    ChoiceBox< String> Medallas = new ChoiceBox<>();

//contador para que desaparesca la imagen principal 4 segundos
    int Desaparece;

//consulta de paises o personajes                                
    ChoiceBox<String> CConsulta = new ChoiceBox<>();
    Button Bconsultar = new Button();
    TextField TConsulta = new TextField();

//nodos Admin
    TextField User = new TextField();
    PasswordField PasswordUser = new PasswordField();
    Button EntrarAdmin = new Button("- Ingresar -");

    public static void main(String[] args) {
//ejecutor
    launch(args);
    }

//boton para entrar a la app
    Button Entrar = new Button("- ingresar -");
    Button Admin = new Button("- Admin -");

//clase de todos los metodos    
    Metodos Metodo = new Metodos();

    @Override
    public void start(Stage Stage) throws Exception {

//efecto de letra para los botones        
    Agregar.setFont(Font.font("Segoe Script", FontWeight.BOLD, 15));
    Registro.setFont(Font.font("Segoe Script", FontWeight.THIN, 10));
    Medalleria.setFont(Font.font("Segoe Script", FontWeight.THIN, 10));
    SurAmerica.setFont(Font.font("Segoe Script", FontWeight.THIN, 10));
    Figuras.setFont(Font.font("Segoe Script", FontWeight.THIN, 10));
    Registro_Colombia.setFont(Font.font("Segoe Script", FontWeight.THIN, 10));
    CrearPais.setFont(Font.font("Segoe Script", FontWeight.THIN, 10));
    Colombia.setFont(Font.font("Segoe Script", FontWeight.THIN, 10));
    CrearDestacados.setFont(Font.font("Segoe Script", FontWeight.THIN, 10));
    CrearDestacadosColombianos.setFont(Font.font("Segoe Script", FontWeight.THIN, 10));
    Datos.setFont(Font.font("Segoe Script", FontWeight.THIN, 10));
    Consulta.setFont(Font.font("Segoe Script", FontWeight.THIN, 10));
    Foto.setFont(Font.font("Segoe Script", FontWeight.THIN, 10));

//imagenes a utilizar src\\Imagenes\\
    ImageView image8 = new ImageView(new Image(getClass().getResourceAsStream("\\Imagenes\\Anillos.jpg")));
    ImageView image2 = new ImageView(new Image(getClass().getResourceAsStream("\\Imagenes\\Rio2.png")));
    ImageView image4 = new ImageView(new Image(getClass().getResourceAsStream("\\Imagenes\\Medalla.png")));
    ImageView image3 = new ImageView(new Image(getClass().getResourceAsStream("\\Imagenes\\Sport.png")));
    ImageView image5 = new ImageView(new Image(getClass().getResourceAsStream("\\Imagenes\\Mapa.png")));
    ImageView image7 = new ImageView(new Image(getClass().getResourceAsStream("\\Imagenes\\Logo.png")));
    ImageView image6 = new ImageView(new Image(getClass().getResourceAsStream("\\Imagenes\\Mapa2.png")));
    ImageView image9 = new ImageView(new Image(getClass().getResourceAsStream("\\Imagenes\\Colombia.jpg")));
    ImageView image10 = new ImageView(new Image(getClass().getResourceAsStream("\\Imagenes\\Juegos.png")));
    ImageView image11 = new ImageView(new Image(getClass().getResourceAsStream("\\Imagenes\\CrearP.jpg")));
    ImageView image12 = new ImageView(new Image(getClass().getResourceAsStream("\\Imagenes\\Famosos.jpg")));
    ImageView image13 = new ImageView(new Image(getClass().getResourceAsStream("\\Imagenes\\DestacadosColombia.jpg")));
    ImageView image14 = new ImageView(new Image(getClass().getResourceAsStream("\\Imagenes\\Datos.jpg")));
    ImageView image15 = new ImageView(new Image(getClass().getResourceAsStream("\\Imagenes\\Datos1.jpg")));

//lista de deportes en los olimpicos
    CDeportes.getItems().addAll("Atletismo", "Bádminton", "Baloncesto", "Balonmano", "Boxeo", "BMX", "Ciclismo de montaña", "Ciclismo en pista",
    "Ciclismo en ruta", "Natación", "Natación sincronizada", "Salto", "Waterpolo", "Esgrima", "Equitación", "Fútbol", "Gimnasia en trampolín",
    "Gimnasia artística", "Gimnasia rítmica", "Golf", "Halterofilia", "Hockey hierba", "Lucha", "Pentatlón moderno", "Aguas bravas", "Aguas tranquilas",
    "Remo", "Rugby", "Taekwondo", "Tenis", "Tenis de mesa", "Tiro con arco", "Tiro deportivo", "Triatlón", "Triatlón", "Voleibol",
    "Vóley playa", "Yudo");

//lista de consultas    
    CConsulta.getItems().addAll("Pais","Consultar");

//agregar items al choicebox para el registro    
    Medallas.getItems().addAll("Oro", "Plata", "Bronce");

//medallas para la creacion de historicos de colombia    
    MedallasDestacadoColombia.getItems().addAll("Oro", "Plata", "Bronce");

//Evento para cuando se selecciones colombia 
    Paises.setOnAction(event -> Ingresar(event));

//evento del boton agregar medalla
    Agregar.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent event) {
        try {
        //llamar metodo si es seleccionado colombia para agregar al archivo
            if (Paises.getValue().equalsIgnoreCase("Colombia")) {

                ingresarDeportista();

                }
                
            Agregar_Medalla_Pais();
            TNombre.setText(null);
            CDeportes.getSelectionModel().clearSelection();
            Paises.getSelectionModel().selectFirst();
            Paises.getSelectionModel().clearSelection();
            Medallas.getSelectionModel().clearSelection();

        } catch (Exception ex) {
        
            //System.out.println("error en boton de registro");
        
        }

    }
    });

//node nulos por default      
    TRegistro.setVisible(false);
    Agregar.setVisible(false);
    TNombre.setVisible(false);
    CDeportes.setVisible(false);
    TPaises.setVisible(false);
    Paises.setVisible(false);
    Medallas.setVisible(false);
    listView.setVisible(false);
    listOro.setVisible(false);
    listPlata.setVisible(false);
    listBronce.setVisible(false);
    listTotal.setVisible(false);
    Foto.setVisible(false);
    pathFile.setVisible(false);
    TPaises.setVisible(false);
    MedallaPaises.setVisible(false);
    image10.setVisible(false);
    image2.setFitWidth(720);
    image2.setFitHeight(550);
    image2.setVisible(false);
    TNombrePais.setVisible(false);
    BCrearP.setVisible(false);
    TNombreDestacados.setVisible(false);
    FotoDestacados.setVisible(false);
    TNacionalidadDestacados.setVisible(false);
    AreaPerfilDestacados.setVisible(false);
    AreaLogroDestacados.setVisible(false);
    OroDestacados.setVisible(false);
    PlataDestacados.setVisible(false);
    BronceDestacados.setVisible(false);
    BCrearDestacado.setVisible(false);
    TNombreDestacadosColombiano.setVisible(false);
    AreaPerfilDestacadosColombiano.setVisible(false);
    AreaLogroDestacadosColombiano.setVisible(false);
    BCrearDestacadoColombiano.setVisible(false);
    FotoDestacadosColombiano.setVisible(false);
    TOlimpicoDestacadosColombiano.setVisible(false);
    MedallasDestacadoColombia.setVisible(false);
    TDeporteDestacadosColombiano.setVisible(false);
    BCrearDestacadosColombiano.setVisible(false);
    CConsulta.setVisible(false);
    Bconsultar.setVisible(false);
    TConsulta.setVisible(false);
    chart.setVisible(false);
    chartPais.setVisible(false);
    MedallasDestacados.setVisible(false);
    MedallasParaDes.setVisible(false);
    MedallaGanada.setVisible(false);

//evento para el registro    
    Registro.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {

            Agregar_Paises_ChoiceBox();
            Agregar_Paises_Array();

            image2.setVisible(true);
            
        //se limpian las lista para actualizarlas cuando se agregan a una medalla
            listView.getItems().clear();
            listOro.getItems().clear();
            listPlata.getItems().clear();
            listBronce.getItems().clear();
            listTotal.getItems().clear();

            TRegistro.setX(10.0);
            TRegistro.setY(50.0);
            TRegistro.setCache(true);
            TRegistro.setText("Registro de Medalleria");
            TRegistro.setFill(Color.GOLD);
            TRegistro.setFont(Font.font("Segoe Script", FontWeight.BOLD, 50));

            InnerShadow is = new InnerShadow();
            is.setOffsetX(2.0f);
            is.setOffsetY(2.0f);
            TRegistro.setEffect(is);
            TRegistro.setVisible(true);
            TRegistro.setLayoutX(80);
            TRegistro.setLayoutY(40);

            Agregar.setVisible(true);
            Agregar.setLayoutX(310);
            Agregar.setLayoutY(415);

            TPaises.setX(10.0);
            TPaises.setY(50.0);
            TPaises.setCache(true);
            TPaises.setText("Pais");
            TPaises.setFill(Color.GOLD);
            TPaises.setFont(Font.font("Segoe Script", FontWeight.BOLD, 50));

            InnerShadow iss = new InnerShadow();
            iss.setOffsetX(2.0f);
            iss.setOffsetY(2.0f);
            TPaises.setEffect(iss);
            TPaises.setVisible(true);
            TPaises.setLayoutX(200);
            TPaises.setLayoutY(130);
            
            Paises.setVisible(true);
            Paises.setPrefSize(180, 50);
            Paises.setLayoutX(330);
            Paises.setLayoutY(140);

            TMedallas.setX(10.0);
            TMedallas.setY(50.0);
            TMedallas.setCache(true);
            TMedallas.setText("Medalla");
            TMedallas.setFill(Color.GOLD);
            TMedallas.setFont(Font.font("Segoe Script", FontWeight.BOLD, 50));
            InnerShadow iis = new InnerShadow();
            iis.setOffsetX(2.0f);
            iis.setOffsetY(2.0f);
            TMedallas.setEffect(iss);
            TMedallas.setVisible(true);
            TMedallas.setLayoutX(200);
            TMedallas.setLayoutY(190);
            
            Medallas.setVisible(true);
            Medallas.setPrefSize(100, 50);
            Medallas.setLayoutX(410);
            Medallas.setLayoutY(200);
            image10.setVisible(false);

            anchor.setVisible(false);
            anchor2.setVisible(false);
        
        //anular nodos de crear pais                 
            image11.setVisible(false);
            TNombrePais.setVisible(false);
            BCrearP.setVisible(false);
 
        //anular nodos de crear famosos                
            image12.setVisible(false);
            TNombreDestacados.setVisible(false);
            FotoDestacados.setVisible(false);
            TNacionalidadDestacados.setVisible(false);
            AreaPerfilDestacados.setVisible(false);
            AreaLogroDestacados.setVisible(false);
            BronceDestacados.setVisible(false);
            OroDestacados.setVisible(false);
            PlataDestacados.setVisible(false);
            BCrearDestacado.setVisible(false);
            MedallasParaDes.setVisible(false);
            MedallasDestacados.setVisible(false);

        //anular nodos de crear Colombianos                
            image13.setVisible(false);
            TNombreDestacadosColombiano.setVisible(false);
            FotoDestacadosColombiano.setVisible(false);
            AreaPerfilDestacadosColombiano.setVisible(false);
            AreaLogroDestacadosColombiano.setVisible(false);
            TOlimpicoDestacadosColombiano.setVisible(false);
            MedallasDestacadoColombia.setVisible(false);
            TDeporteDestacadosColombiano.setVisible(false);
            BCrearDestacadosColombiano.setVisible(false);
            MedallaGanada.setVisible(false);
  
        //anular nodos de datos                
            chart.setVisible(false);
            image14.setVisible(false);

        //anular nodos de datos consultar               
            image15.setVisible(false);
            CConsulta.setVisible(false);
            chartPais.setVisible(false);
            TConsulta.setVisible(false);
            Bconsultar.setVisible(false);
             
            image9.setVisible(false);
            image7.setVisible(false);
            image6.setVisible(false);
            image5.setVisible(false);
            image4.setVisible(false);
            image3.setVisible(false);
            image8.setVisible(false);
            listView.setVisible(false);
            listOro.setVisible(false);
            listPlata.setVisible(false);
            listBronce.setVisible(false);
            listTotal.setVisible(false);
            anchor.setVisible(false);
            MedallaPaises.setVisible(false);
            anchor3.setVisible(false);

    }
});

    MedallaPaises.setX(10.0);
    MedallaPaises.setY(50.0);
    MedallaPaises.setCache(true);
    MedallaPaises.setText("Paises");
    MedallaPaises.setFill(Color.CORNFLOWERBLUE);
    MedallaPaises.setFont(Font.font("Segoe Script", FontWeight.BOLD, 30));
    InnerShadow iff = new InnerShadow();
    iff.setOffsetX(2.0f);
    iff.setOffsetY(2.0f);
    MedallaPaises.setEffect(iff);

    image3.setFitWidth(720);
    image3.setFitHeight(550);

    image3.setVisible(false);
    image4.setFitWidth(100);
    image4.setFitHeight(40);
    image4.setVisible(false);
    image5.setFitWidth(350);
    image5.setFitHeight(300);
    image5.setVisible(false);

//evento para el boton medalleria     
    Medalleria.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {

            image3.setVisible(true);
            image4.setVisible(true);
            image4.setLayoutX(115);
            image4.setLayoutY(30);

            image5.setVisible(true);
            image5.setLayoutX(400);
            image5.setLayoutY(300);

        //tamaño de las listas
            listView.setPrefSize(100, 470);
            listOro.setPrefSize(50, 470);
            listPlata.setPrefSize(50, 470);
            listBronce.setPrefSize(50, 470);
            listTotal.setPrefSize(50, 470);

            MedallaPaises.setVisible(true);
            MedallaPaises.setLayoutX(10);
            MedallaPaises.setLayoutY(15);

            listView.getItems().clear();
            listOro.getItems().clear();
            listPlata.getItems().clear();
            listBronce.getItems().clear();
            listTotal.getItems().clear();
            image10.setVisible(false);

        //anular nodos de crear pais                 
            image11.setVisible(false);
            TNombrePais.setVisible(false);
            BCrearP.setVisible(false);
        
        //anular nodos de crear famosos                
            image12.setVisible(false);
            TNombreDestacados.setVisible(false);
            FotoDestacados.setVisible(false);
            TNacionalidadDestacados.setVisible(false);
            AreaPerfilDestacados.setVisible(false);
            AreaLogroDestacados.setVisible(false);
            BronceDestacados.setVisible(false);
            OroDestacados.setVisible(false);
            PlataDestacados.setVisible(false);
            BCrearDestacado.setVisible(false);
            MedallasParaDes.setVisible(false);
            MedallasDestacados.setVisible(false);

        //anular nodos de crear Colombianos                
            image13.setVisible(false);
            TNombreDestacadosColombiano.setVisible(false);
            FotoDestacadosColombiano.setVisible(false);
            AreaPerfilDestacadosColombiano.setVisible(false);
            AreaLogroDestacadosColombiano.setVisible(false);
            TOlimpicoDestacadosColombiano.setVisible(false);
            MedallasDestacadoColombia.setVisible(false);
            TDeporteDestacadosColombiano.setVisible(false);
            BCrearDestacadosColombiano.setVisible(false);
            MedallaGanada.setVisible(false);

        //anular nodos de datos                
            chart.setVisible(false);
            image14.setVisible(false);

        //anular nodos de datos consultar               
            image15.setVisible(false);
            CConsulta.setVisible(false);
            chartPais.setVisible(false);
            TConsulta.setVisible(false);
            Bconsultar.setVisible(false);
            
            anchor.setVisible(false);
            anchor2.setVisible(false);
            image9.setVisible(false);
            TMedallas.setVisible(false);
            TPaises.setVisible(false);
            TRegistro.setVisible(false);
            image7.setVisible(false);
            image6.setVisible(false);
            image2.setVisible(false);
            image8.setVisible(false);
            image2.setVisible(false);
            Agregar.setVisible(false);
            TNombre.setVisible(false);
            CDeportes.setVisible(false);
            Paises.setVisible(false);
            Medallas.setVisible(false);
            Foto.setVisible(false);
            anchor.setVisible(false);
            anchor3.setVisible(false);
            TDeporte.setVisible(false);

        //agregar a array lis los paises para posteriormente ordenarlos
            Agregar_Paises_Array();

        //se crea un vector de paises 
            CPaises[] Trasladar = new CPaises[AuxiliarObjetos.size()];

            //se pasan los valores al vector    
                for (int i = 0; i < AuxiliarObjetos.size(); i++) {

                    Trasladar[i] = new CPaises(
                    AuxiliarObjetos.get(i).NombrePais,
                    AuxiliarObjetos.get(i).MOro,
                    AuxiliarObjetos.get(i).MPlata,
                    AuxiliarObjetos.get(i).MBronce);

                }

        //se ordena el vector con respecto a los valores que deseamos    
             Arrays.sort(Trasladar);

            //imprimir en la tabla Paises     
                for (int i = 0; i < Trasladar.length; i++) {

                //list paises  
                    data.addAll(Trasladar[(Trasladar.length - 1) - i].NombrePais);

                //lista oro 
                    //se pasa el entero a string ya que la lista solo lee string
                    String value2 = String.valueOf(Trasladar[(Trasladar.length - 1) - i].MOro);
                    dataOro.addAll(value2);

                //lista plata  
                    String value3 = String.valueOf(Trasladar[(Trasladar.length - 1) - i].MPlata);
                    dataPlata.addAll(value3);

                //lista bronce  
                    String value4 = String.valueOf(Trasladar[(Trasladar.length - 1) - i].MBronce);
                    dataBronce.addAll(value4);

                //lista total 
                    String value5 = String.valueOf(
                        Trasladar[(Trasladar.length - 1) - i].MOro
                        + Trasladar[(Trasladar.length - 1) - i].MPlata
                        + Trasladar[(Trasladar.length - 1) - i].MBronce);
                    dataTotal.addAll(value5);

                }

        //posiciones lista paises  
            listView.setVisible(true);
            listView.setLayoutX(20);
            listView.setLayoutY(70);

        //posiciones lista oro    
            listOro.setVisible(true);
            listOro.setLayoutX(120);
            listOro.setLayoutY(70);

        //posiciones lista plata    
            listPlata.setVisible(true);
            listPlata.setLayoutX(150);
            listPlata.setLayoutY(70);

        //posicion lists bronce    
            listBronce.setVisible(true);
            listBronce.setLayoutX(180);
            listBronce.setLayoutY(70);

        //posicion lista total  
            listTotal.setVisible(true);
            listTotal.setLayoutX(210);
            listTotal.setLayoutY(70);

    }
});

    image6.setFitWidth(720);
    image6.setFitHeight(550);
    image6.setVisible(false);
    image7.setFitWidth(200);
    image7.setFitHeight(200);
    image7.setVisible(false);

//evento del boton agregar medalla
    SurAmerica.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {

            image6.setVisible(true);
            image7.setVisible(true);
            image7.setLayoutX(475);
            image7.setLayoutY(300);

            listView.getItems().clear();
            listOro.getItems().clear();
            listPlata.getItems().clear();
            listBronce.getItems().clear();
            listTotal.getItems().clear();

        //tamaño de la lista
            listView.setPrefSize(100, 235);

        //tamaño de la lista
            listOro.setPrefSize(30, 235);

        //tamaño de la lista
            listPlata.setPrefSize(30, 235);

        //tamaño de la lista
            listBronce.setPrefSize(30, 235);

        //tamaño de la lista
            listTotal.setPrefSize(30, 235);

        //anular nodos de crear pais                 
            image11.setVisible(false);
            TNombrePais.setVisible(false);
            BCrearP.setVisible(false);

        //anular nodos de crear famosos                
            image12.setVisible(false);
            TNombreDestacados.setVisible(false);
            FotoDestacados.setVisible(false);
            TNacionalidadDestacados.setVisible(false);
            AreaPerfilDestacados.setVisible(false);
            AreaLogroDestacados.setVisible(false);
            BronceDestacados.setVisible(false);
            OroDestacados.setVisible(false);
            PlataDestacados.setVisible(false);
            BCrearDestacado.setVisible(false);
            MedallasParaDes.setVisible(false);
            MedallasDestacados.setVisible(false);

        //anular nodos de crear Colombianos                
            image13.setVisible(false);
            TNombreDestacadosColombiano.setVisible(false);
            FotoDestacadosColombiano.setVisible(false);
            AreaPerfilDestacadosColombiano.setVisible(false);
            AreaLogroDestacadosColombiano.setVisible(false);
            TOlimpicoDestacadosColombiano.setVisible(false);
            MedallasDestacadoColombia.setVisible(false);
            TDeporteDestacadosColombiano.setVisible(false);
            BCrearDestacadosColombiano.setVisible(false);
            MedallaGanada.setVisible(false);

        //anular nodos de datos                
            chart.setVisible(false);
            image14.setVisible(false);

        //anular nodos de datos consultar               
            image15.setVisible(false);
            CConsulta.setVisible(false);
            chartPais.setVisible(false);
            TConsulta.setVisible(false);
            Bconsultar.setVisible(false);

            image10.setVisible(false);
            anchor.setVisible(false);
            anchor2.setVisible(false);
            TMedallas.setVisible(false);
            TPaises.setVisible(false);
            TRegistro.setVisible(false);
            image5.setVisible(false);
            image3.setVisible(false);
            image8.setVisible(false);
            image2.setVisible(false);
            Agregar.setVisible(false);
            TNombre.setVisible(false);
            CDeportes.setVisible(false);
            Paises.setVisible(false);
            Medallas.setVisible(false);
            Foto.setVisible(false);
            anchor.setVisible(false);
            image9.setVisible(false);
            anchor3.setVisible(false);
            TDeporte.setVisible(false);

            Agregar_Paises_Array();

            CPaises[] Trasladar2 = new CPaises[AuxiliarObjetos.size()];

            for (int i = 0; i < AuxiliarObjetos.size(); i++) {

                Trasladar2[i] = new CPaises(
                AuxiliarObjetos.get(i).NombrePais,
                AuxiliarObjetos.get(i).MOro,
                AuxiliarObjetos.get(i).MPlata,
                AuxiliarObjetos.get(i).MBronce);

            }

            Arrays.sort(Trasladar2);

            String[] ComparaPais = {"Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Ecuador", "Paraguay", "Peru", "Uruguay", "Venezuela"};

        //imprimir en la tabla Paises     
            for (int i = 0; i < Trasladar2.length; i++) {

                for (int e = 0; e < 10; e++) {

                //validacion para imprimir solo suramericanos
                    if (Trasladar2[(Trasladar2.length - 1) - i].NombrePais.equalsIgnoreCase(ComparaPais[e])) {

                    //list paises  
                        data.addAll(Trasladar2[(Trasladar2.length - 1) - i].NombrePais);

                    //lista oro  
                        String value2 = String.valueOf(Trasladar2[(Trasladar2.length - 1) - i].MOro);
                        dataOro.addAll(value2);

                    //lista plata  
                        String value3 = String.valueOf(Trasladar2[(Trasladar2.length - 1) - i].MPlata);
                        dataPlata.addAll(value3);

                    //lista bronce  
                        String value4 = String.valueOf(Trasladar2[(Trasladar2.length - 1) - i].MBronce);
                        dataBronce.addAll(value4);

                    //lista total 
                        String value5 = String.valueOf(
                            Trasladar2[(Trasladar2.length - 1) - i].MOro
                            + Trasladar2[(Trasladar2.length - 1) - i].MPlata
                            + Trasladar2[(Trasladar2.length - 1) - i].MBronce);
                            dataTotal.addAll(value5);

                    }

                }

            }

    MedallaPaises.setVisible(true);
    MedallaPaises.setLayoutX(8);
    MedallaPaises.setLayoutY(200);

    image4.setVisible(true);
    image4.setLayoutX(115);
    image4.setLayoutY(215);

    listView.setVisible(true);
    listView.setLayoutX(20);
    listView.setLayoutY(260);

    listOro.setVisible(true);
    listOro.setLayoutX(120);
    listOro.setLayoutY(260);

    listPlata.setVisible(true);
    listPlata.setLayoutX(150);
    listPlata.setLayoutY(260);

    listBronce.setVisible(true);
    listBronce.setLayoutX(180);
    listBronce.setLayoutY(260);

    listTotal.setVisible(true);
    listTotal.setLayoutX(210);
    listTotal.setLayoutY(260);

        }
    });

    image9.setFitWidth(720);
    image9.setFitHeight(550);
    image9.setVisible(false);

//historia        
    Registro_Colombia.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {

            Agregar_Destacados_Colombianos_Array();
        //anchor se limpia para que no sobre escriba en pagination  
            anchor2.getChildren().clear();
            pagination2 = new Pagination(AuxiliarObjetosDestacadosColombianos.size(), 0);
            pagination2.setPageFactory((Integer pageIndex) -> CreatePageDestacadosColombianos(pageIndex));
            pagination2.setVisible(false);
            pagination2.setLayoutX(100);
            anchor2.setVisible(false);
            anchor2.getChildren().addAll(pagination2);

        //anular nodos de crear pais                 
            image11.setVisible(false);
            TNombrePais.setVisible(false);
            BCrearP.setVisible(false);

        //anular nodos de crear famosos                
            image12.setVisible(false);
            TNombreDestacados.setVisible(false);
            FotoDestacados.setVisible(false);
            TNacionalidadDestacados.setVisible(false);
            AreaPerfilDestacados.setVisible(false);
            AreaLogroDestacados.setVisible(false);
            BronceDestacados.setVisible(false);
            OroDestacados.setVisible(false);
            PlataDestacados.setVisible(false);
            BCrearDestacado.setVisible(false);
            MedallasParaDes.setVisible(false);
            MedallasDestacados.setVisible(false);

        //anular nodos de crear Colombianos                
            image13.setVisible(false);
            TNombreDestacadosColombiano.setVisible(false);
            FotoDestacadosColombiano.setVisible(false);
            AreaPerfilDestacadosColombiano.setVisible(false);
            AreaLogroDestacadosColombiano.setVisible(false);
            TOlimpicoDestacadosColombiano.setVisible(false);
            MedallasDestacadoColombia.setVisible(false);
            TDeporteDestacadosColombiano.setVisible(false);
            BCrearDestacadosColombiano.setVisible(false);
            MedallaGanada.setVisible(false);

        //anular nodos de datos                
            chart.setVisible(false);
            image14.setVisible(false);

        //anular nodos de datos consultar               
            image15.setVisible(false);
            CConsulta.setVisible(false);
            chartPais.setVisible(false);
            TConsulta.setVisible(false);
            Bconsultar.setVisible(false);

            image9.setVisible(true);
            image7.setVisible(true);
            image7.setLayoutX(500);
            image7.setLayoutY(350);

            pagination2.setVisible(true);
            anchor2.setVisible(true);
            anchor2.setPrefSize(300, 300);
            anchor2.setLayoutX(40);
            anchor2.setLayoutY(40);

            image10.setVisible(false);

            MedallaPaises.setVisible(false);
            TMedallas.setVisible(false);
            TPaises.setVisible(false);
            TRegistro.setVisible(false);
            image5.setVisible(false);
            image4.setVisible(false);
            image3.setVisible(false);
            image8.setVisible(false);
            image2.setVisible(false);
            Agregar.setVisible(false);
            TNombre.setVisible(false);
            CDeportes.setVisible(false);
            Paises.setVisible(false);
            Medallas.setVisible(false);
            Foto.setVisible(false);
            anchor.setVisible(false);
            listView.setVisible(false);
            listOro.setVisible(false);
            listPlata.setVisible(false);
            listBronce.setVisible(false);
            listTotal.setVisible(false);
            image6.setVisible(false);
            anchor3.setVisible(false);
            TDeporte.setVisible(false);

        }
    });

    image10.setFitWidth(720);
    image10.setFitHeight(550);
    image10.setVisible(false);

//evento para ver colombianos ganadores        
    Colombia.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {

            Agragar_Array_Deportista();

            anchor3.getChildren().clear();
            pagination3 = new Pagination(AuxiliarObjetosDeportistas.size(), 0);
            pagination3.setPageFactory((Integer pageIndex) -> createPageIM(pageIndex));
            pagination3.setVisible(false);
            anchor3.setVisible(false);
            anchor3.getChildren().addAll(pagination3);

        //anular nodos de crear pais                 
            image11.setVisible(false);
            TNombrePais.setVisible(false);
            BCrearP.setVisible(false);

        //anular nodos de crear famosos                
            image12.setVisible(false);
            TNombreDestacados.setVisible(false);
            FotoDestacados.setVisible(false);
            TNacionalidadDestacados.setVisible(false);
            AreaPerfilDestacados.setVisible(false);
            AreaLogroDestacados.setVisible(false);
            BronceDestacados.setVisible(false);
            OroDestacados.setVisible(false);
            PlataDestacados.setVisible(false);
            BCrearDestacado.setVisible(false);
            MedallasParaDes.setVisible(false);
            MedallasDestacados.setVisible(false);

        //anular nodos de crear Colombianos                
            image13.setVisible(false);
            TNombreDestacadosColombiano.setVisible(false);
            FotoDestacadosColombiano.setVisible(false);
            AreaPerfilDestacadosColombiano.setVisible(false);
            AreaLogroDestacadosColombiano.setVisible(false);
            TOlimpicoDestacadosColombiano.setVisible(false);
            MedallasDestacadoColombia.setVisible(false);
            TDeporteDestacadosColombiano.setVisible(false);
            BCrearDestacadosColombiano.setVisible(false);
            MedallaGanada.setVisible(false);

        //anular nodos de datos                
            chart.setVisible(false);
            image14.setVisible(false);

        //anular nodos de datos consultar               
            image15.setVisible(false);
            CConsulta.setVisible(false);
            chartPais.setVisible(false);
            TConsulta.setVisible(false);
            Bconsultar.setVisible(false);

            TMedallas.setVisible(false);
            TPaises.setVisible(false);
            TRegistro.setVisible(false);
            image8.setVisible(false);
            image2.setVisible(false);
            Agregar.setVisible(false);
            TNombre.setVisible(false);
            CDeportes.setVisible(false);
            Paises.setVisible(false);
            Medallas.setVisible(false);
            Foto.setVisible(false);
            anchor.setVisible(false);
            listView.setVisible(false);
            listOro.setVisible(false);
            listPlata.setVisible(false);
            listBronce.setVisible(false);
            listTotal.setVisible(false);
            image5.setVisible(false);
            image4.setVisible(false);
            image3.setVisible(false);
            image7.setVisible(false);
            image6.setVisible(false);
            anchor2.setVisible(false);
            image9.setVisible(false);
            MedallaPaises.setVisible(false);
            TDeporte.setVisible(false);
            pagination3.setVisible(true);
            anchor3.setVisible(true);
            anchor3.setPrefSize(300, 300);
            anchor3.setLayoutX(40);
            anchor3.setLayoutY(40);

            image10.setVisible(true);

        }
    });

    image8.setFitWidth(720);
    image8.setFitHeight(550);
    image8.setVisible(false);

//evento para famosos de brasil    
    Figuras.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {

            anchor.getChildren().clear();
            Agregar_Destacados_Array();
            pagination = new Pagination(AuxiliarObjetosDestacados.size(), 0);
            pagination.setPageFactory((Integer pageIndex) -> CreatePageDestacados(pageIndex));
            pagination.setVisible(false);
            anchor.setVisible(false);
            anchor.getChildren().addAll(pagination);
            pagination.setLayoutX(10);
            MedallaPaises.setVisible(false);

        //anular nodos de crear pais                 
            image11.setVisible(false);
            TNombrePais.setVisible(false);
            BCrearP.setVisible(false);
        
        //anular nodos de crear famosos                
            image12.setVisible(false);
            TNombreDestacados.setVisible(false);
            FotoDestacados.setVisible(false);
            TNacionalidadDestacados.setVisible(false);
            AreaPerfilDestacados.setVisible(false);
            AreaLogroDestacados.setVisible(false);
            BronceDestacados.setVisible(false);
            OroDestacados.setVisible(false);
            PlataDestacados.setVisible(false);
            BCrearDestacado.setVisible(false);
            MedallasParaDes.setVisible(false);
            MedallasDestacados.setVisible(false);

        //anular nodos de crear Colombianos                
            image13.setVisible(false);
            TNombreDestacadosColombiano.setVisible(false);
            FotoDestacadosColombiano.setVisible(false);
            AreaPerfilDestacadosColombiano.setVisible(false);
            AreaLogroDestacadosColombiano.setVisible(false);
            TOlimpicoDestacadosColombiano.setVisible(false);
            MedallasDestacadoColombia.setVisible(false);
            TDeporteDestacadosColombiano.setVisible(false);
            BCrearDestacadosColombiano.setVisible(false);
            MedallaGanada.setVisible(false);

        //anular nodos de datos                
            chart.setVisible(false);
            image14.setVisible(false);

        //anular nodos de datos consultar               
            image15.setVisible(false);
            CConsulta.setVisible(false);
            chartPais.setVisible(false);
            TConsulta.setVisible(false);
            Bconsultar.setVisible(false);

            TMedallas.setVisible(false);
            TPaises.setVisible(false);
            TRegistro.setVisible(false);
            image8.setVisible(true);
            image2.setVisible(false);
            Agregar.setVisible(false);
            TNombre.setVisible(false);
            CDeportes.setVisible(false);
            Paises.setVisible(false);
            Medallas.setVisible(false);
            Foto.setVisible(false);
            listView.setVisible(false);
            listOro.setVisible(false);
            listPlata.setVisible(false);
            listBronce.setVisible(false);
            listTotal.setVisible(false);
            image5.setVisible(false);
            image4.setVisible(false);
            image3.setVisible(false);
            image7.setVisible(false);
            image6.setVisible(false);
            image9.setVisible(false);

            anchor2.setVisible(false);
            image10.setVisible(false);
            anchor3.setVisible(false);
            TDeporte.setVisible(false);

            pagination.setVisible(true);
            anchor.setVisible(true);
            anchor.setPrefSize(300, 300);
            anchor.setLayoutX(40);
            anchor.setLayoutY(40);

        }
    });

    image11.setFitWidth(720);
    image11.setFitHeight(550);
    image11.setVisible(false);
  
//evento para crear pais    
    CrearPais.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {

        image11.setVisible(true);
        TNombrePais.setLayoutX(350);
        TNombrePais.setLayoutY(250);
        TNombrePais.setVisible(true);
        TNombrePais.setPromptText("      Crear Pais");
        TNombrePais.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

        BCrearP.setLayoutX(380);
        BCrearP.setLayoutY(305);
        BCrearP.setVisible(true);
        BCrearP.setFont(Font.font("Segoe Script", FontWeight.THIN, 15));

            BCrearP.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {

                try {
                    ingresarPais();
                } catch (URISyntaxException ex) {
    
                    Logger.getLogger(OlimpicoSS_1.class.getName()).log(Level.SEVERE, null, ex);

                    JOptionPane.showMessageDialog( null , "No se a Creado el Pais" , " - Error al Validar - " , JOptionPane.ERROR_MESSAGE );

                }

                }
            });

    //anular nodos de registro      
        TDeporte.setVisible(false);
        TMedallas.setVisible(false);
        TPaises.setVisible(false);
        CDeportes.setVisible(false);
        Paises.setVisible(false);
        Medallas.setVisible(false);
        TNombre.setVisible(false);
        Foto.setVisible(false);
        Agregar.setVisible(false);
        TRegistro.setVisible(false);

    //anular nodos para medalleria
        image3.setVisible(false);
        listView.setVisible(false);
        listOro.setVisible(false);
        listPlata.setVisible(false);
        listBronce.setVisible(false);
        listTotal.setVisible(false);
        MedallaPaises.setVisible(false);
        image4.setVisible(false);

    //anular nodos de destacados        
        image8.setVisible(false);
        anchor.setVisible(false);

    //anular nodos de Historia Colombia        
        image9.setVisible(false);
        anchor2.setVisible(false);

    //anular nodos de Colombia 2016        
        image10.setVisible(false);
        anchor3.setVisible(false);
    
    //anular nodos de crear famosos                
        image12.setVisible(false);
        TNombreDestacados.setVisible(false);
        FotoDestacados.setVisible(false);
        TNacionalidadDestacados.setVisible(false);
        AreaPerfilDestacados.setVisible(false);
        AreaLogroDestacados.setVisible(false);
        BronceDestacados.setVisible(false);
        OroDestacados.setVisible(false);
        PlataDestacados.setVisible(false);
        BCrearDestacado.setVisible(false);
        MedallasParaDes.setVisible(false);
        MedallasDestacados.setVisible(false);

    //anular nodos de crear Colombianos                
        image13.setVisible(false);
        TNombreDestacadosColombiano.setVisible(false);
        FotoDestacadosColombiano.setVisible(false);
        AreaPerfilDestacadosColombiano.setVisible(false);
        AreaLogroDestacadosColombiano.setVisible(false);
        TOlimpicoDestacadosColombiano.setVisible(false);
        MedallasDestacadoColombia.setVisible(false);
        TDeporteDestacadosColombiano.setVisible(false);
        BCrearDestacadosColombiano.setVisible(false);
        MedallaGanada.setVisible(false);

    //anular nodos de datos                
        chart.setVisible(false);
        image14.setVisible(false);

    //anular nodos de datos consultar               
        image15.setVisible(false);
        CConsulta.setVisible(false);
        chartPais.setVisible(false);
        TConsulta.setVisible(false);
        Bconsultar.setVisible(false);

        }
    });

    image12.setFitWidth(720);
    image12.setFitHeight(550);
    image12.setVisible(false);
    
//crear famoso    
    CrearDestacados.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {

            image12.setVisible(true);

        //anular nodos de registro      
            TDeporte.setVisible(false);
            TMedallas.setVisible(false);
            TPaises.setVisible(false);
            CDeportes.setVisible(false);
            Paises.setVisible(false);
            Medallas.setVisible(false);
            TNombre.setVisible(false);
            Foto.setVisible(false);
            Agregar.setVisible(false);
            TRegistro.setVisible(false);

        //anular nodos para medalleria
            image3.setVisible(false);
            listView.setVisible(false);
            listOro.setVisible(false);
            listPlata.setVisible(false);
            listBronce.setVisible(false);
            listTotal.setVisible(false);
            MedallaPaises.setVisible(false);
            image4.setVisible(false);

        //anular nodos de destacados        
            image8.setVisible(false);
            anchor.setVisible(false);

        //anular nodos de historia colombia        
            image9.setVisible(false);
            anchor2.setVisible(false);

        //anular nodos de Colombia 2016        
            image10.setVisible(false);
            anchor3.setVisible(false);

        //anular nodos de crear pais                 
            image11.setVisible(false);
            TNombrePais.setVisible(false);
            BCrearP.setVisible(false);

        //anular nodos de crear Colombianos                
            image13.setVisible(false);
            TNombreDestacadosColombiano.setVisible(false);
            FotoDestacadosColombiano.setVisible(false);
            AreaPerfilDestacadosColombiano.setVisible(false);
            AreaLogroDestacadosColombiano.setVisible(false);
            TOlimpicoDestacadosColombiano.setVisible(false);
            MedallasDestacadoColombia.setVisible(false);
            TDeporteDestacadosColombiano.setVisible(false);
            BCrearDestacadosColombiano.setVisible(false);
            MedallaGanada.setVisible(false);

        //anular nodos de datos                
            chart.setVisible(false);
            image14.setVisible(false);

        //anular nodos de datos consultar               
            image15.setVisible(false);
            CConsulta.setVisible(false);
            chartPais.setVisible(false);
            TConsulta.setVisible(false);
            Bconsultar.setVisible(false);

            TNombreDestacados.setVisible(true);
            TNombreDestacados.setLayoutX(20);
            TNombreDestacados.setLayoutY(50);
            TNombreDestacados.setPromptText("Nombre del Destacado");
            TNombreDestacados.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

            FotoDestacados.setVisible(true);
            FotoDestacados.setLayoutX(20);
            FotoDestacados.setLayoutY(100);
            FotoDestacados.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));
            FotoDestacados.setTextFill(Color.RED);
            
                FotoDestacados.setOnAction( new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        FileChooser fileChooser2 = new FileChooser();
                        fileChooser2.getExtensionFilters().addAll(new ExtensionFilter("Imagenes", "*.jpg", "*.png", "*.gif"));
                        File selectedFile2 = fileChooser2.showOpenDialog(null);
                        if (selectedFile2 != null) {
                            FotoDestacados.setTextFill(Color.GREEN);
                            pathFileDestacados.setText(selectedFile2.getName());
                            System.out.println(selectedFile2.getAbsolutePath());
                            System.out.println(selectedFile2.toURI().toString());
                            rutaImagenDestacados = selectedFile2.toURI().toString();
                        } else {
                            FotoDestacados.setTextFill(Color.RED);
                            pathFile.setText("Archivo no seleccionado");
                            rutaImagenDestacados = "";
                        }
                    }
                });

            TNacionalidadDestacados.setVisible(true);
            TNacionalidadDestacados.setLayoutX(20);
            TNacionalidadDestacados.setLayoutY(150);
            TNacionalidadDestacados.setPromptText("Nacionalidad");
            TNacionalidadDestacados.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

            AreaPerfilDestacados.setVisible(true);
            AreaPerfilDestacados.setLayoutX(20);
            AreaPerfilDestacados.setLayoutY(200);
            AreaPerfilDestacados.setPrefSize(300, 10);
            AreaPerfilDestacados.setPromptText("Perfil");
            AreaPerfilDestacados.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

            AreaLogroDestacados.setVisible(true);
            AreaLogroDestacados.setLayoutX(20);
            AreaLogroDestacados.setLayoutY(250);
            AreaLogroDestacados.setPrefSize(300, 10);
            AreaLogroDestacados.setPromptText("Logro");
            AreaLogroDestacados.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

            //criterio de el maximo ganador en la historia de los olimpicos con 28 medallas de oro Michael Phelps   
                for (int i = 0; i < 28; i++) {
                    OroDestacados.getItems().add(i);
                    PlataDestacados.getItems().add(i);
                    BronceDestacados.getItems().add(i);
                }

            MedallasDestacados.setVisible(true);
            MedallasDestacados.setLayoutX(20);
            MedallasDestacados.setLayoutY(300);
            MedallasDestacados.setFill(Color.GOLD);
            MedallasDestacados.setEffect(new Bloom());
            MedallasDestacados.setFont(Font.font("Segoe Script", FontWeight.BOLD, 20));

            MedallasParaDes.setVisible(true);
            MedallasParaDes.setLayoutX(20);
            MedallasParaDes.setLayoutY(330);
            MedallasParaDes.setFill(Color.GOLD);
            MedallasParaDes.setEffect(new Bloom());
            MedallasParaDes.setFont(Font.font("Segoe Script", FontWeight.BOLD, 20));

            OroDestacados.setVisible(true);
            OroDestacados.getSelectionModel().selectFirst();
            OroDestacados.setLayoutX(20);
            OroDestacados.setLayoutY(350);

            PlataDestacados.setVisible(true);
            PlataDestacados.getSelectionModel().selectFirst();
            PlataDestacados.setLayoutX(100);
            PlataDestacados.setLayoutY(350);

            BronceDestacados.setVisible(true);
            BronceDestacados.getSelectionModel().selectFirst();
            BronceDestacados.setLayoutX(180);
            BronceDestacados.setLayoutY(350);

                BCrearDestacado.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {

                        try {
                            IngresarDestacados();
                            TNombreDestacados.setText("");
                            rutaImagenDestacados = ("");
                            TNacionalidadDestacados.setText("");
                            AreaPerfilDestacados.setText("");
                            AreaLogroDestacados.setText("");
                            OroDestacados.getSelectionModel().selectFirst();
                            PlataDestacados.getSelectionModel().selectFirst();
                            BronceDestacados.getSelectionModel().selectFirst();

                        } catch (URISyntaxException ex) {
                            System.out.println("error en el boton crear destacados");
                        }
                    }
                });

            BCrearDestacado.setVisible(true);
            BCrearDestacado.setLayoutX(20);
            BCrearDestacado.setLayoutY(400);
            BCrearDestacado.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

        }
    });

    image13.setFitWidth(720);
    image13.setFitHeight(550);
    image13.setVisible(false);

//crear historico de colombia    
    CrearDestacadosColombianos.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {

            image13.setVisible(true);

        //anular nodos de registro      
            TDeporte.setVisible(false);
            TMedallas.setVisible(false);
            TPaises.setVisible(false);
            CDeportes.setVisible(false);
            Paises.setVisible(false);
            Medallas.setVisible(false);
            TNombre.setVisible(false);
            Foto.setVisible(false);
            Agregar.setVisible(false);
            TRegistro.setVisible(false);

        //anular nodos para medalleria
            image3.setVisible(false);
            listView.setVisible(false);
            listOro.setVisible(false);
            listPlata.setVisible(false);
            listBronce.setVisible(false);
            listTotal.setVisible(false);
            MedallaPaises.setVisible(false);
            image4.setVisible(false);

        //anular nodos de destacados        
            image8.setVisible(false);
            anchor.setVisible(false);

        //anular nodos de historia colombia        
            image9.setVisible(false);
            anchor2.setVisible(false);

        //anular nodos de Colombia 2016        
            image10.setVisible(false);
            anchor3.setVisible(false);

        //anular nodos de crear famosos                
            image12.setVisible(false);
            TNombreDestacados.setVisible(false);
            FotoDestacados.setVisible(false);
            TNacionalidadDestacados.setVisible(false);
            AreaPerfilDestacados.setVisible(false);
            AreaLogroDestacados.setVisible(false);
            BronceDestacados.setVisible(false);
            OroDestacados.setVisible(false);
            PlataDestacados.setVisible(false);
            BCrearDestacado.setVisible(false);
            MedallasParaDes.setVisible(false);
            MedallasDestacados.setVisible(false);

        //anular nodos de crear pais                 
            image11.setVisible(false);
            TNombrePais.setVisible(false);
            BCrearP.setVisible(false);

        //anular nodos de datos                
            chart.setVisible(false);
            image14.setVisible(false);

        //anular nodos de datos consultar               
            image15.setVisible(false);
            CConsulta.setVisible(false);
            chartPais.setVisible(false);
            TConsulta.setVisible(false);
            Bconsultar.setVisible(false);

            TNombreDestacadosColombiano.setVisible(true);
            TNombreDestacadosColombiano.setLayoutX(20);
            TNombreDestacadosColombiano.setLayoutY(100);
            TNombreDestacadosColombiano.setPromptText("Historico Colombiano");
            TNombreDestacadosColombiano.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

            FotoDestacadosColombiano.setVisible(true);
            FotoDestacadosColombiano.setLayoutX(20);
            FotoDestacadosColombiano.setLayoutY(150);
            FotoDestacadosColombiano.setTextFill(Color.RED);
            FotoDestacadosColombiano.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));
          
                FotoDestacadosColombiano.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        FileChooser fileChooser2 = new FileChooser();
                        fileChooser2.getExtensionFilters().addAll(new ExtensionFilter("Imagenes", "*.jpg", "*.png", "*.gif"));
                        File selectedFile2 = fileChooser2.showOpenDialog(null);
                        if (selectedFile2 != null) {
                            FotoDestacadosColombiano.setTextFill(Color.GREEN);
                            pathFileDestacados.setText(selectedFile2.getName());
                            System.out.println(selectedFile2.getAbsolutePath());
                            System.out.println(selectedFile2.toURI().toString());
                            rutaImagenDestacadosColombiano = selectedFile2.toURI().toString();
                        } else {
                            FotoDestacadosColombiano.setTextFill(Color.RED);
                            pathFile.setText("Archivo no seleccionado");
                            rutaImagenDestacadosColombiano = "";
                        }
                    }
                });

            AreaPerfilDestacadosColombiano.setVisible(true);
            AreaPerfilDestacadosColombiano.setLayoutX(20);
            AreaPerfilDestacadosColombiano.setLayoutY(200);
            AreaPerfilDestacadosColombiano.setPrefSize(300, 10);
            AreaPerfilDestacadosColombiano.setPromptText("Perfil");
            AreaPerfilDestacadosColombiano.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

            AreaLogroDestacadosColombiano.setVisible(true);
            AreaLogroDestacadosColombiano.setLayoutX(20);
            AreaLogroDestacadosColombiano.setLayoutY(250);
            AreaLogroDestacadosColombiano.setPrefSize(300, 10);
            AreaLogroDestacadosColombiano.setPromptText("Logro");
            AreaLogroDestacadosColombiano.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

            TOlimpicoDestacadosColombiano.setVisible(true);
            TOlimpicoDestacadosColombiano.setLayoutX(20);
            TOlimpicoDestacadosColombiano.setLayoutY(300);
            TOlimpicoDestacadosColombiano.setPromptText("Olimpico Ganador");
            TOlimpicoDestacadosColombiano.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

            MedallaGanada.setVisible(true);
            MedallaGanada.setLayoutX(20);
            MedallaGanada.setLayoutY(360);
            MedallaGanada.setFill(Color.GOLD);
            MedallaGanada.setEffect(new Bloom());
            MedallaGanada.setFont(Font.font("Segoe Script", FontWeight.BOLD, 20));

            MedallasDestacadoColombia.setVisible(true);
            MedallasDestacadoColombia.setLayoutX(20);
            MedallasDestacadoColombia.setLayoutY(380);

            TDeporteDestacadosColombiano.setVisible(true);
            TDeporteDestacadosColombiano.setLayoutX(20);
            TDeporteDestacadosColombiano.setLayoutY(430);
            TDeporteDestacadosColombiano.setPromptText("Competicion");
            TDeporteDestacadosColombiano.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

            BCrearDestacadosColombiano.setVisible(true);
            BCrearDestacadosColombiano.setLayoutX(20);
            BCrearDestacadosColombiano.setLayoutY(480);
            BCrearDestacadosColombiano.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

                BCrearDestacadosColombiano.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        try {
                            IngresarDestacadoColombiano();
                            TNombreDestacadosColombiano.setText(""); 
                            rutaImagenDestacadosColombiano = (""); 
                            AreaPerfilDestacadosColombiano.setText("");
                            AreaLogroDestacadosColombiano.setText("");
                            TOlimpicoDestacadosColombiano.setText("");
                            MedallasDestacadoColombia.getSelectionModel().clearSelection();
                            TDeporteDestacadosColombiano.setText("");
                            rutaImagenDestacadosColombiano = "";
                        } catch (URISyntaxException ex) {
                            System.out.println("error en el boton crear destacados");
                        }
                    }
                });

        }
    });

//evento para datos de medalleria por paises pie chart
    DataPaises = FXCollections.observableArrayList();

    image14.setFitWidth(720);
    image14.setFitHeight(550);
    image14.setVisible(false);

    Datos.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {

            image14.setVisible(true);

        //anular nodos de registro      
            TDeporte.setVisible(false);
            TMedallas.setVisible(false);
            TPaises.setVisible(false);
            CDeportes.setVisible(false);
            Paises.setVisible(false);
            Medallas.setVisible(false);
            TNombre.setVisible(false);
            Foto.setVisible(false);
            Agregar.setVisible(false);
            TRegistro.setVisible(false);

        //anular nodos para medalleria
            image3.setVisible(false);
            listView.setVisible(false);
            listOro.setVisible(false);
            listPlata.setVisible(false);
            listBronce.setVisible(false);
            listTotal.setVisible(false);
            MedallaPaises.setVisible(false);
            image4.setVisible(false);
    
        //anular nodos de destacados        
            image8.setVisible(false);
            anchor.setVisible(false);

        //anular nodos de historia colombia        
            image9.setVisible(false);
            anchor2.setVisible(false);

        //anular nodos de Colombia 2016        
            image10.setVisible(false);
            anchor3.setVisible(false);

        //anular nodos de crear Colombianos                
            image13.setVisible(false);
            TNombreDestacadosColombiano.setVisible(false);
            FotoDestacadosColombiano.setVisible(false);
            AreaPerfilDestacadosColombiano.setVisible(false);
            AreaLogroDestacadosColombiano.setVisible(false);
            TOlimpicoDestacadosColombiano.setVisible(false);
            MedallasDestacadoColombia.setVisible(false);
            TDeporteDestacadosColombiano.setVisible(false);
            BCrearDestacadosColombiano.setVisible(false);
            MedallaGanada.setVisible(false);

        //anular nodos de crear famosos                
            image12.setVisible(false);
            TNombreDestacados.setVisible(false);
            FotoDestacados.setVisible(false);
            TNacionalidadDestacados.setVisible(false);
            AreaPerfilDestacados.setVisible(false);
            AreaLogroDestacados.setVisible(false);
            BronceDestacados.setVisible(false);
            OroDestacados.setVisible(false);
            PlataDestacados.setVisible(false);
            BCrearDestacado.setVisible(false);
            MedallasParaDes.setVisible(false);
            MedallasDestacados.setVisible(false);

        //anular nodos de crear pais                 
            image11.setVisible(false);
            TNombrePais.setVisible(false);
            BCrearP.setVisible(false);

        //anular nodos de datos consultar               
            image15.setVisible(false);
            CConsulta.setVisible(false);
            chartPais.setVisible(false);
            TConsulta.setVisible(false);
            Bconsultar.setVisible(false);

            AuxiliarObjetos.clear();
            Agregar_Paises_Array();

            CPaises[] Trasladar3 = new CPaises[AuxiliarObjetos.size()];
                for (int i = 0; i < AuxiliarObjetos.size(); i++) {
                    Trasladar3[i] = new CPaises(
                    AuxiliarObjetos.get(i).NombrePais,
                    AuxiliarObjetos.get(i).MOro,
                    AuxiliarObjetos.get(i).MPlata,
                    AuxiliarObjetos.get(i).MBronce);
                }

            Arrays.sort(Trasladar3);

            chart.setVisible(true);
            DataPaises.clear();
            chart.setLayoutX(90);
            chart.setLayoutY(75);

                if (Trasladar3.length > 5) {

                    int ContadorMedallas = 0;

                    for (int i = 0; i < 5; i++) {
                        DataPaises.add(
                            new PieChart.Data(
                                    //nombre del pais en el chart + las medallas
                                    Trasladar3[(Trasladar3.length - 1) - i].NombrePais + "  |  " + 
                                    (Trasladar3[(Trasladar3.length - 1) - i].MOro +
                                    Trasladar3[(Trasladar3.length - 1) - i].MPlata +
                                    Trasladar3[(Trasladar3.length - 1) - i].MBronce),
                                    
                                    //medallas porcentaje en el chart visual en la torta
                                    (Trasladar3[(Trasladar3.length - 1) - i].MOro +
                                    Trasladar3[(Trasladar3.length - 1) - i].MPlata +
                                    Trasladar3[(Trasladar3.length - 1) - i].MBronce)));

                                    ContadorMedallas += (Trasladar3[(Trasladar3.length - 1) - i].MOro + 
                                    Trasladar3[(Trasladar3.length - 1) - i].MPlata + 
                                    Trasladar3[(Trasladar3.length - 1) - i].MBronce);

                    }

                int ContadorMedallas2 = 0;

                    for (int k = 5; k < Trasladar3.length; k++) {

                        ContadorMedallas2 += (Trasladar3[(Trasladar3.length - 1) - k].MOro + 
                        Trasladar3[(Trasladar3.length - 1) - k].MPlata +
                        Trasladar3[(Trasladar3.length - 1) - k].MBronce);

                    }

                DataPaises.add(new PieChart.Data("Otros Paises  |  " + ContadorMedallas2, ContadorMedallas2));

                chart.setTitle("Medallas Disputadas  |  " + (ContadorMedallas + ContadorMedallas2));

                } else if (Trasladar3.length < 6) {

                    int ContadorMedallas3 = 0;

                        for (int i = 0; i < Trasladar3.length; i++) {
                            DataPaises.add(
                                new PieChart.Data(Trasladar3[(Trasladar3.length - 1) - i].NombrePais + "  |  " + 
                                        (Trasladar3[(Trasladar3.length - 1) - i].MOro + 
                                        Trasladar3[(Trasladar3.length - 1) - i].MPlata + 
                                        Trasladar3[(Trasladar3.length - 1) - i].MBronce),
                                        
                                        (Trasladar3[(Trasladar3.length - 1) - i].MOro +
                                        Trasladar3[(Trasladar3.length - 1) - i].MPlata + 
                                        Trasladar3[(Trasladar3.length - 1) - i].MBronce)));

                        ContadorMedallas3 += (Trasladar3[(Trasladar3.length - 1) - i].MOro + 
                        Trasladar3[(Trasladar3.length - 1) - i].MPlata + 
                        Trasladar3[(Trasladar3.length - 1) - i].MBronce);

                        }

                    chart.setTitle("Medallas Disputadas  |  " + (ContadorMedallas3));

                }

        }
    });

    chart.setData(DataPaises);

    DataConsultaPais = FXCollections.observableArrayList();

    DataPaises.clear();
    chartPais.setLayoutX(180);
    chartPais.setLayoutY(80);

    image15.setFitWidth(720);
    image15.setFitHeight(550);
    image15.setVisible(false);

//consulta de paises    
    Consulta.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {

            image15.setVisible(true);

        //anular nodos de registro      
            TDeporte.setVisible(false);
            TMedallas.setVisible(false);
            TPaises.setVisible(false);
            CDeportes.setVisible(false);
            Paises.setVisible(false);
            Medallas.setVisible(false);
            TNombre.setVisible(false);
            Foto.setVisible(false);
            Agregar.setVisible(false);
            TRegistro.setVisible(false);

        //anular nodos para medalleria
            image3.setVisible(false);
            listView.setVisible(false);
            listOro.setVisible(false);
            listPlata.setVisible(false);
            listBronce.setVisible(false);
            listTotal.setVisible(false);
            MedallaPaises.setVisible(false);
            image4.setVisible(false);

        //anular nodos de destacados        
            image8.setVisible(false);
            anchor.setVisible(false);

        //anular nodos de historia colombia        
            image9.setVisible(false);
            anchor2.setVisible(false);

        //anular nodos de Colombia 2016        
            image10.setVisible(false);
            anchor3.setVisible(false);

        //anular nodos de crear famosos                
            image12.setVisible(false);
            TNombreDestacados.setVisible(false);
            FotoDestacados.setVisible(false);
            TNacionalidadDestacados.setVisible(false);
            AreaPerfilDestacados.setVisible(false);
            AreaLogroDestacados.setVisible(false);
            BronceDestacados.setVisible(false);
            OroDestacados.setVisible(false);
            PlataDestacados.setVisible(false);
            BCrearDestacado.setVisible(false);
            MedallasParaDes.setVisible(false);
            MedallasDestacados.setVisible(false);

//anular nodos de crear Colombianos                
            image13.setVisible(false);
            TNombreDestacadosColombiano.setVisible(false);
            FotoDestacadosColombiano.setVisible(false);
            AreaPerfilDestacadosColombiano.setVisible(false);
            AreaLogroDestacadosColombiano.setVisible(false);
            TOlimpicoDestacadosColombiano.setVisible(false);
            MedallasDestacadoColombia.setVisible(false);
            TDeporteDestacadosColombiano.setVisible(false);
            BCrearDestacadosColombiano.setVisible(false);
            MedallaGanada.setVisible(false);

//anular nodos de datos                
            chart.setVisible(false);
            image14.setVisible(false);

//anular nodos de crear pais                 
            image11.setVisible(false);
            TNombrePais.setVisible(false);
            BCrearP.setVisible(false);


            CConsulta.setVisible(true);
            CConsulta.setLayoutX(100);
            CConsulta.setLayoutY(200);

                CConsulta.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {

                        if (CConsulta.getValue().equalsIgnoreCase("Pais")) {

                            System.out.println("seleccionado Pais");
                            Bconsultar.setVisible(true);
                            Bconsultar.setLayoutX(70);
                            Bconsultar.setLayoutY(300);
                            Bconsultar.setText("- Consultar Pais -");
                            Bconsultar.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

                                Bconsultar.setOnAction(new EventHandler<ActionEvent>() {
                                    public void handle(ActionEvent event) {

                                        AuxiliarObjetos.clear();
                                        Agregar_Paises_Array();

                                        for (int i = 0; i < AuxiliarObjetos.size(); i++) {

                                            if (TConsulta.getText().equalsIgnoreCase(AuxiliarObjetos.get(i).getNombrePais())) {
    
                                                DataConsultaPais.clear();

                                            for (int j = 0; j < 3; j++) {

                                                String[] Met = {"Oro " + AuxiliarObjetos.get(i).MOro, "Plata " + AuxiliarObjetos.get(i).MPlata, "Bronce " + AuxiliarObjetos.get(i).MBronce};

                                                Integer[] Meda = {AuxiliarObjetos.get(i).MOro, AuxiliarObjetos.get(i).MPlata, AuxiliarObjetos.get(i).MBronce};

                                                chartPais.setTitle(AuxiliarObjetos.get(i).getNombrePais());
                                                DataConsultaPais.add(new PieChart.Data(Met[j], Meda[j]));
                                                chartPais.setVisible(true);

                                            }
                                         
                                            
                                            }
                                        }
        }
    });

    chartPais.setData(DataConsultaPais);

    TConsulta.setVisible(true);
    TConsulta.setLayoutX(50);
    TConsulta.setLayoutY(250);
    TConsulta.setPromptText("   Consultar Pais");
    TConsulta.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

    } else if (CConsulta.getValue().equalsIgnoreCase("Deportista")) {

        Bconsultar.setVisible(true);
        Bconsultar.setLayoutX(250);
        Bconsultar.setLayoutY(250);
        Bconsultar.setText("- Consulta Deportista -");

            Bconsultar.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                }
            });
    }

        }
    });

        }
    });//fin de boton de consultar

//Barra de botonos no visible     
    Barra.setVisible(false);
    Barra.setPrefSize(720, 20);

 //Buscar la imagen para insertar en la barra del interfaz 
    Image image = new Image(getClass().getResourceAsStream("\\Imagenes\\Rio.jpg"));
    ImageView imagev = new ImageView(image);
    imagev.setFitWidth(720);
    imagev.setFitHeight(550);

    DropShadow ds = new DropShadow();
    ds.setOffsetY(10);
    ds.setHeight(100);
    ds.setWidth(100);
    ds.setColor(Color.color(0.0, 0.0, 0.0));

    Entrar.setShape(new Circle(10));
    Entrar.setMaxSize(100, 100);
    Entrar.setFont(Font.font("Segoe Script", FontWeight.THIN, 12));
    Entrar.setTextFill(Color.DARKBLUE);
    Entrar.setPrefSize(100, 100);
    Entrar.setLayoutX(400);
    Entrar.setLayoutY(435);
    Entrar.setEffect(ds);
 
//efecto para el boton     
    Metodo.Efecto_Boton_Entrada(Entrar);

//evento para el boton entrarda    
    Entrar.setOnAction(event -> Ingresar(event, imagev));

    Admin.setShape(new Circle(10));
    Admin.setMaxSize(100, 100);
    Admin.setFont(Font.font("Segoe Script", FontWeight.THIN, 12));
    Admin.setTextFill(Color.DARKBLUE);
    Admin.setPrefSize(100, 100);
    Admin.setLayoutX(200);
    Admin.setLayoutY(435);
    Admin.setEffect(ds);
    Metodo.Efecto_Boton_Entrada(Admin);

        Admin.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("- Eres el Admin -");

                User.setLayoutX(20);
                User.setLayoutY(20);
                User.setVisible(true);
                User.setPromptText("   Administrador");
                User.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

                PasswordUser.setLayoutX(20);
                PasswordUser.setLayoutY(60);
                PasswordUser.setVisible(true);
                PasswordUser.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));
                PasswordUser.setPromptText("- Contraseña -");

                EntrarAdmin.setLayoutX(20);
                EntrarAdmin.setLayoutY(100);
                EntrarAdmin.setVisible(true);
                EntrarAdmin.setFont(Font.font("Segoe Script", FontWeight.BOLD, 12));

                EntrarAdmin.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        if (User.getText().equalsIgnoreCase("COI") && PasswordUser.getText().equalsIgnoreCase("Rio2016*")) {

                            //efecto en logo de entrada    
                            Metodo.Efecto_de_Entrada(event, imagev, Entrar, Admin);
                            Metodo.Efecto_Barra3(BarraAdmin);
                            CrearPais.fire();
                            
                            User.setVisible(false);
                            PasswordUser.setVisible(false);
                            EntrarAdmin.setVisible(false);
                            Barra.setVisible(false);

                            Desaparece = 0;
                            Timeline timeline = new Timeline(new KeyFrame(
                            Duration.seconds(1), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                            Desaparece++;
                            if (Desaparece == 4) {
                            imagev.setVisible(false);

                            }
                            }
                            }));
                            timeline.setCycleCount(4);
                            timeline.play();

                        } else {
                          
                            JOptionPane.showMessageDialog(null, " Error de Usuario o contraseña ", " - Error al Validar - ", JOptionPane.ERROR_MESSAGE);

                        }

        }
});

        }
});//fin botonAdmin
        
    User.setVisible(false);
    PasswordUser.setVisible(false);
    EntrarAdmin.setVisible(false);
    BarraAdmin.setVisible(false);
    BarraAdmin.setPrefSize(720, 20);

        Cerrar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                Metodo.Efecto_de_Volver(event, imagev, Entrar, Admin);

            }
        });

        Cerrar2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                Metodo.Efecto_de_Volver(event, imagev, Entrar, Admin);

            }
        });

//creacion de nodo group    
    GR = new Group();

//Titulo del escenario //interfaz   
    Stage.setTitle("- Rio 2016 -");

 
    GR.getChildren().addAll(image2, image3, image5, image6, image8, image9, image7, image10, image11, image12,
        image13, image14, image15,image4,Barra, BarraAdmin, Agregar, TNombre, Paises, Medallas, listView,
        listOro, listPlata,listBronce, listTotal,Foto, anchor, anchor2, CDeportes, TRegistro, TPaises,
        TMedallas, TDeporte, MedallaPaises,anchor3, TNombrePais, BCrearP, TNombreDestacados, FotoDestacados,
        TNacionalidadDestacados, AreaPerfilDestacados,AreaLogroDestacados, OroDestacados, PlataDestacados,
        BronceDestacados, BCrearDestacado, TNombreDestacadosColombiano,AreaPerfilDestacadosColombiano, AreaLogroDestacadosColombiano,
        BCrearDestacadoColombiano, FotoDestacadosColombiano,TOlimpicoDestacadosColombiano, MedallasDestacadoColombia,
        TDeporteDestacadosColombiano, BCrearDestacadosColombiano, chart,CConsulta, Bconsultar, TConsulta, chartPais,
        MedallasDestacados, MedallasParaDes, MedallaGanada, imagev, Entrar, Admin, User, PasswordUser, EntrarAdmin);

//creacion de la escena
    Scene scene = new Scene(GR, 720, 550);

//Buscar la imagen para insertar en la barra del interfaz 
    Image IconoInterfaz = new Image(getClass().getResourceAsStream("\\Imagenes\\Logo.png"));

//codigo para agregarla como icono de barra para la interfaz //a la imagen    
    Stage.getIcons().add(IconoInterfaz);

//agregar la escena al escenario
    Stage.setScene(scene);

//iniciar
    Stage.show();

    }

    
//evento parae el boton ingresar    
    private void Ingresar(ActionEvent event, ImageView imagev) {

     //efecto en logo de entrada    
        Metodo.Efecto_de_Entrada(event, imagev, Entrar, Admin);
        Metodo.Efecto_Barra3(Barra);
        BarraAdmin.setVisible(false);
        Registro.fire();

        Desaparece = 0;

        Timeline timeline = new Timeline(new KeyFrame(
        Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Desaparece++;

                if (Desaparece == 4) {

                    imagev.setVisible(false);

                }

            }
        }));
        timeline.setCycleCount(4);
        timeline.play();
    }

/*    
    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        //int page = pageIndex ();

        ImageView[] Imagenes = {
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\MICHAEL.png"))),
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\EMIL.png"))),
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\NADIA.png"))),
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\ROBERT.png"))),
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\USAIN.png")))

        };

        for (int i = 0; i < 5; i++) {
            Imagenes[i].setFitWidth(500);
            Imagenes[i].setFitHeight(320);
        }

        Label[] R = {
            new Label("MICHAEL FRED PHELPS"),
            new Label("EMIL ZATOPEK"),
            new Label("NADIA ELENA COMANECI"),
            new Label("ROBERT BEAMON"),
            new Label("USAIN BOLT")};

        for (int i = 0; i < 5; i++) {

            R[i].setTextFill(Color.YELLOW);

            R[i].setFont(Font.font("Arial", FontWeight.BOLD, 10));
        }

        Text[] text = {
            //biografia MICHAEL FRED PHELPS
            new Text("Nadador estadounidense, que ganó un total de 14 medallas olímpicas de oro en su carrera,             \n"
            + " más de las que ningún atleta olímpico haya conseguido hasta ahora. Se le menciona                  \n"
            + "como el mejor nadador de la historia y uno de los mejores atletas olímpicos de todos los tiempos.   \n"
            + " Batió 37 records mundiales en natación.Phelps, ostenta el record de medallas olímpicas conseguidas \n"
            + "en unos juegos olímpicos, con 8 medallas de oro conseguidas en los Juegos Olímpicos de Pekín 2008,  \n"
            + "superando a Mark Spitz que consiguió 7 en los Juegos Olímpicos de Múnich 1972."),
            //biografi EMIL ZATOPEK
            new Text("Atleta Checoslovaco, especialista en pruebas de fondo. Alcanzó gran reconocimiento por su actuación \n"
            + "en los Juegos Olímpicos de Londres 1948, y especialmente en los de Helsinki 1952.Destacó por su     \n"
            + "fuerza y resistencia, así como por su ritmo, lo que le valió el apelativo de “la locomotora humana”. \n"
            + "Cuatro medallas de oro y una de planta en los juegos olímpicos de 1948 y 1952.                      \n"
            + "                                                                                                    \n"
            + "                                                                                                      "),
            //biografia NADIA ELENA COMANECI
            new Text("Gimnasta de origen rumano, ganadora de nueve medallas olímpicas, de las cuales cinco son de oro.     \n"
            + "Primera en conseguir una puntuación perfecta, 10 en asimétricas, en una competición olímpica.       \n"
            + "Es considerada una de las mejores atletas del siglo XX y estás por derecho propio entre las mejores \n"
            + "gimnastas de todos los tiempos.                                                                     \n"
            + "                                                                                                    \n"
            + "                                                                                                      "),
            //bliografia ROBERT BEAMON
            new Text("Robert “Bob” Beamon, atleta estadounidense, famoso por su record del mundo en salto de longitud,      \n"
            + "llamado el salto del siglo, conseguido en los Juegos Olímpicos de Mexico 1968, dicho record perduró \n"
            + "22 años, siendo superado por Mike Powell en el Mundial de Tokio 1991.                               \n"
            + "Sigue siendo la segunda mejor marca.                                                                \n"
            + "                                                                                                    \n"
            + "                                                                                                      "),
            //biografia USAIN BOLT
            new Text("Atleta jamaicano, especialista en pruebas de velocidad. Tiene diez medallas de oro y dos de plata,   \n"
            + " cinco mundiales y cuatro olímpicas. Tiene los records mundiales en 100 y 200 metros y en 4x100 con \n"
            + "el equipo de Jamaica, durante los Juegos Olimpicos de Pekin. De momento en estos juegos de Londres  \n"
            + " 2012 acaba de conseguir la medalla de oro en los 100 metros lisos.                                 \n"
            + "                                                                                                    \n"
            + "                                                                                                      ")};

        for (int i = 0; i < 5; i++) {

            text[i].setFill(Color.WHITE);
            text[i].setFont(Font.font("Arial", FontWeight.BOLD, 10));

        }

        VBox element = new VBox();
        element.getChildren().addAll(Imagenes[pageIndex], R[pageIndex], text[pageIndex]);

        box.getChildren().addAll(element);

        return box;

    }

*/
    
/*    
    public VBox createPage2(Integer pageIndex) {

        VBox box = new VBox(5);

        ImageView[] Imagenes = {
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\Maria.jpg"))),
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\Mariana.jpg"))),
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\Ximena.jpg"))),
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\Catherine.jpg"))),
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\Jackeline.jpg"))),
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\Calle.jpg"))),
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\Yuri.jpg"))),
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\Mabel.jpg"))),
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\Zuluaga.jpg"))),
            new ImageView(new Image(OlimpicoSS_1.class.getResourceAsStream("\\Imagenes\\Chechi.jpg")))

        };

        for (int i = 0; i < 10; i++) {
            Imagenes[i].setFitWidth(500);
            Imagenes[i].setFitHeight(320);
        }

        Label[] R = {
            new Label("Maria Isabel Urrutia"),
            new Label("Mariana Pajon"),
            new Label("Ximena Restrepo"),
            new Label("Catherine Ibarguen"),
            new Label("Jackeline Renteria"),
            new Label("Maria Luisa Calle"),
            new Label("Yuri Alvear"),
            new Label("Mabel Mosquera"),
            new Label("Fabiola Zuluaga"),
            new Label("Cecilia Baena")};

        for (int i = 0; i < 10; i++) {

            R[i].setTextFill(Color.YELLOW);

            R[i].setFont(Font.font("Arial", FontWeight.BOLD, 10));
        }

        Text[] text = {
            //biografia isabel urrutia
            new Text("La pesista fue la primera medallista de oro de Colombia, en los Juegos Olímpicos de Sídney 2000.\n"
            + " Ganó además 24 preseas en los campeonatos mundiales.       "),
            //biografi mariana pajon
            new Text("Después de 12 años, obtuvo la segunda medalla de oro olímpica para Colombia. La bicicrosista se\n"
            + " impuso en todas las series y confirmó por qué tiene 13 títulos mundiales. "),
            //biografia Ximena restrepo
            new Text(" Fue la primera colombiana en ganar una medalla olímpica en atletismo. Conquistó la de bronce en\n"
            + "Barcelona 1992 en los 400 metros planos."),
            //bliografia Catherine ibarguen
            new Text("La antioqueña logró la medalla de plata en los Olímpicos de Londres 2012. En su especialidad, el\n"
            + " salto triple, también tiene una medalla de bronce en un campeonato mundial."),
            //bliografia jackeline renteria
            new Text(" Esta luchadora se ha dado el lujo de ganar doble medalla olímpica en un deporte poco tradicional.\n"
            + " Logró el bronce en Beijing 2008 y repitió en Londres 2012."),
            //bliografia maria luisa calle
            new Text("La ciclista ganó la medalla de bronce en la prueba por puntos en los Olímpicos de Atenas 2004."),
            //bliografia yuri alvares
            new Text(" La yudoca vallecaucana logró, a sus 27 años, la presea de bronce en Londres 2012. Es múltiple   \n"
            + " campeona mundial y panamericana."),
            //bliografia mabel mosquera
            new Text("A sus 35 años obtuvo la presea de bronce en la categoría de los 53 kilogramos en                 \n"
            + "los Olímpicos de Atenas 2004. "),
            //bliografia fabiola zuluaga
            new Text("La mejor tenista colombiana de la historia alcanzó el puesto 16 de la WTA, el 16 de enero de 2005.\n"
            + " Su mayor logro en un Grand Slam fue alcanzar las semifinales del Abierto de Australia, en 2004."),
            //biografia cecilia baena
            new Text("Con 24 títulos mundiales es la mejor patinadora colombiana de la historia. La negativa del Comité \n"
            + "Olímpico Internacional de convertir el patinaje de carreras en un deporte olímpico fue           \n"
            + "el mayor obstáculo de su carrera. ")};

        for (int i = 0; i < 10; i++) {

            text[i].setFill(Color.WHITE);
            text[i].setFont(Font.font("Arial", FontWeight.BOLD, 10));

        }

        VBox element = new VBox();
        element.getChildren().addAll(Imagenes[pageIndex], R[pageIndex], text[pageIndex]);

        box.getChildren().addAll(element);

        return box;

    }

*/

    private void Ingresar(ActionEvent event) {

        try {

            if (Paises.getValue().equalsIgnoreCase("Colombia")) {

                //posicion en la interfaz            
                Foto.setVisible(true);
                Foto.setLayoutX(340);
                Foto.setLayoutY(380);

                    Foto.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(final ActionEvent e) {
                            FileChooser fileChooser = new FileChooser();
                            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Imagenes", "*.jpg", "*.png", "*.gif"));
                            File selectedFile = fileChooser.showOpenDialog(null);
                        if (selectedFile != null) {
                            pathFile.setText(selectedFile.getName());
                            System.out.println(selectedFile.getAbsolutePath());
                            System.out.println(selectedFile.toURI().toString());
                            rutaImagen = selectedFile.toURI().toString();
                        } else {
                            pathFile.setText("Archivo no seleccionado");
                            rutaImagen = "";
                        }
                        
                        }
                    });

        //posicion en la interfaz            
            TNombre.setVisible(true);
            TNombre.setPrefSize(180, 50);
            TNombre.setPromptText("Nombre del Deportista");
            TNombre.setLayoutX(280);
            TNombre.setLayoutY(320);

            TDeporte.setX(10.0);
            TDeporte.setY(50.0);
            TDeporte.setCache(true);
            TDeporte.setText("Deporte");
            TDeporte.setFill(Color.GOLD);
            TDeporte.setFont(Font.font("Segoe Script", FontWeight.BOLD, 50));
            InnerShadow in = new InnerShadow();
            in.setOffsetX(2.0f);
            in.setOffsetY(2.0f);
            TDeporte.setEffect(in);
            TDeporte.setVisible(true);
            TDeporte.setLayoutX(160);
            TDeporte.setLayoutY(250);
        //posicion en la interfaz            
            CDeportes.setVisible(true);
            CDeportes.setPrefSize(180, 50);
            CDeportes.setLayoutX(380);
            CDeportes.setLayoutY(260);

        } else {

            TNombre.setVisible(false);
            CDeportes.setVisible(false);
            TDeporte.setVisible(false);
            Foto.setVisible(false);

            }

        } catch (Exception d) {
        
        System.out.println("termino el error en el boton");

        }

    }

    public int itemsPerPage() {
        return 1;
    }

    public VBox createPageIM(int pageIndex) {

        VBox box = new VBox(5);
        box.getChildren().clear();
        int page = pageIndex * itemsPerPage();

        Label[] NombreArrayDeportistas = new Label[AuxiliarObjetosDeportistas.size()];
        Label[] DeporteArrayDeportistas = new Label[AuxiliarObjetosDeportistas.size()];
        ImageView[] ImagenesArrayDeportistas = new ImageView[AuxiliarObjetosDeportistas.size()];

        for (int i = 0; i < AuxiliarObjetosDeportistas.size(); i++) {

            ImagenesArrayDeportistas[i] = new ImageView(new Image(AuxiliarObjetosDeportistas.get(i).getRuta()));
            ImagenesArrayDeportistas[i].setFitHeight(300);
            ImagenesArrayDeportistas[i].setFitWidth(300);

            NombreArrayDeportistas[i] = new Label("Nombre : " + AuxiliarObjetosDeportistas.get(i).getNombre());
            NombreArrayDeportistas[i].setTextFill(Color.YELLOW);
            NombreArrayDeportistas[i].setFont(Font.font("Segoe Script", FontWeight.BOLD, 25));
            NombreArrayDeportistas[i].setEffect(new Bloom());

            DeporteArrayDeportistas[i] = new Label("Deporte : " + AuxiliarObjetosDeportistas.get(i).getDeporte());
            DeporteArrayDeportistas[i].setTextFill(Color.YELLOW);
            DeporteArrayDeportistas[i].setFont(Font.font("Segoe Script", FontWeight.BOLD, 25));
            DeporteArrayDeportistas[i].setEffect(new Bloom());

        }


        for (int i = page; i < page + itemsPerPage(); i++) {

            box.getChildren().addAll(ImagenesArrayDeportistas[pageIndex], NombreArrayDeportistas[pageIndex], DeporteArrayDeportistas[pageIndex]);

        }

        return box;
    }

    public VBox CreatePageDestacados(int pageIndex) {

        VBox box = new VBox(5);
        box.getChildren().clear();
        int page = pageIndex * itemsPerPage();

        ImageView[] ImagenesArrayDestacados = new ImageView[AuxiliarObjetosDestacados.size()];
        Label[] NombreArrayDestacados = new Label[AuxiliarObjetosDestacados.size()];
        Label[] NacionalidadArrayDeportistas = new Label[AuxiliarObjetosDestacados.size()];
        Label[] PerfilArrayDeportistas = new Label[AuxiliarObjetosDestacados.size()];
        Label[] LogroArrayDeportistas = new Label[AuxiliarObjetosDestacados.size()];
        Label[] MedallasArrayDeportistas = new Label[AuxiliarObjetosDestacados.size()];

        for (int i = 0; i < AuxiliarObjetosDestacados.size(); i++) {

            ImagenesArrayDestacados[i] = new ImageView(new Image(AuxiliarObjetosDestacados.get(i).getImagenDestacados()));
            ImagenesArrayDestacados[i].setFitHeight(300);
            ImagenesArrayDestacados[i].setFitWidth(300);

            NombreArrayDestacados[i] = new Label("Nombre : " + AuxiliarObjetosDestacados.get(i).getNombreDestacados());
            NombreArrayDestacados[i].setTextFill(Color.YELLOW);
            NombreArrayDestacados[i].setFont(Font.font("Segoe Script", FontWeight.BOLD, 15));
            NombreArrayDestacados[i].setEffect(new Bloom());

            NacionalidadArrayDeportistas[i] = new Label("Nacionalidad : " + AuxiliarObjetosDestacados.get(i).getNacionalidadDestacados());
            NacionalidadArrayDeportistas[i].setTextFill(Color.LIME);
            NacionalidadArrayDeportistas[i].setFont(Font.font("Segoe Script", FontWeight.BOLD, 15));
            NacionalidadArrayDeportistas[i].setEffect(new Bloom());

            PerfilArrayDeportistas[i] = new Label("Perfil : " + AuxiliarObjetosDestacados.get(i).getPerfilDestacados());
            PerfilArrayDeportistas[i].setTextFill(Color.DODGERBLUE);
            PerfilArrayDeportistas[i].setFont(Font.font("Segoe Script", FontWeight.BOLD, 15));
            PerfilArrayDeportistas[i].setEffect(new Bloom());

            LogroArrayDeportistas[i] = new Label("Logro : " + AuxiliarObjetosDestacados.get(i).getLogrosDestacados());
            LogroArrayDeportistas[i].setTextFill(Color.RED);
            LogroArrayDeportistas[i].setFont(Font.font("Segoe Script", FontWeight.BOLD, 15));
            LogroArrayDeportistas[i].setEffect(new Bloom());

            MedallasArrayDeportistas[i] = new Label(
                    "Oro : " + AuxiliarObjetosDestacados.get(i).getOroDestacados()
                    + "   Plata : " + AuxiliarObjetosDestacados.get(i).getPlataDestacados()
                    + "   Bronce : " + AuxiliarObjetosDestacados.get(i).getBronceDestacados());
            MedallasArrayDeportistas[i].setTextFill(Color.GOLD);
            MedallasArrayDeportistas[i].setFont(Font.font("Segoe Script", FontWeight.BOLD, 15));
            MedallasArrayDeportistas[i].setEffect(new Bloom());

        }

        for (int i = page; i < page + itemsPerPage(); i++) {
            // box.getChildren().addAll(d[pageIndex]);
            box.getChildren().addAll(
                    ImagenesArrayDestacados[pageIndex],
                    NombreArrayDestacados[pageIndex],
                    NacionalidadArrayDeportistas[pageIndex],
                    PerfilArrayDeportistas[pageIndex],
                    LogroArrayDeportistas[pageIndex],
                    MedallasArrayDeportistas[pageIndex]);

        }
        return box;
    }

    private void ingresarDeportista() throws URISyntaxException {
    //objectstream para crear el archivo de objetos por primera Vez, Crea el encabezado.
        ObjectOutputStream PrimerObjetoStream = null;

    //objectstream para agregar objetos a un archivo ya creado, no crea el encabezado
        AgregarObject StreamAgregarObjetos = null;

    //Ruta donde esta/crea el archivo de Estudiantes.
        Path rutaArchivo = Paths.get("").toAbsolutePath().resolve("src\\olimpicos\\Datos\\Deportista.obj");

        if (!TNombre.getText().equals("") && !CDeportes.getValue().equals("") && !rutaImagen.equals("")) {

        //creacion del objeto deportista y agregarlo a el archivo Deportista.obj
            Deportista e = new Deportista(TNombre.getText(), CDeportes.getValue(), rutaImagen);

            try {

            //si no existe el archivo de objetos usar el PrimerObjetoStream para guardar el primer objeto                
            //si ya existe debemos usar el AgregarObejtoStream para agregar el objeto al archivo   
                if (Files.exists(rutaArchivo)) {//agregamos el objeto al archivo existente                    

                    StreamAgregarObjetos = new AgregarObject(new FileOutputStream(rutaArchivo.toString(), true));
                    StreamAgregarObjetos.writeObject(e);
                    StreamAgregarObjetos.flush();//escribir de memoria a disco.

                } else {

                    //Creamos el archivo y agregamos el primer objeto al archivo                    
                    PrimerObjetoStream = new ObjectOutputStream(new FileOutputStream(rutaArchivo.toString()));
                    PrimerObjetoStream.writeObject(e);
                    PrimerObjetoStream.flush();//escribir de memoria a disco.

                }
                // lresultado.setStyle("-fx-text-fill: green");
                // lresultado.setText("Estudiante registrado correctamente!!");
                TNombre.setText("");
                CDeportes.getValue().equals(null);
                rutaImagen = "";

            } catch (IOException ex) {


            } finally {

                try {

                    if (PrimerObjetoStream != null) {
                        PrimerObjetoStream.close();
                    }
                    if (StreamAgregarObjetos != null) {
                        StreamAgregarObjetos.close();
                    }

                } catch (IOException ex) {

                    Logger.getLogger(OlimpicoSS_1.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        } else {
            System.out.println("falta informacion");
        }
    }

    private void Agragar_Array_Deportista() {//Buscar el deportista                

        AuxiliarObjetosDeportistas.clear();

        Path rutaArchivo2 = Paths.get("").toAbsolutePath().resolve("src\\olimpicos\\Datos\\Deportista.obj");

            if (!Files.exists(rutaArchivo2)) {
            
                System.out.println("no se ha registrado ningun Deportista");
            
            } else {
            
        ObjectInputStream entrada = null;
        boolean encontrado = false;

            try {

                entrada = new ObjectInputStream(new FileInputStream(rutaArchivo2.toString()));
        
                do {
                
                    Deportista D = (Deportista) entrada.readObject();
                    AuxiliarObjetosDeportistas.add(D);

                } while (!encontrado);

            } //cuando termina de recorrer todos los objetos del archivo se lanza la excepción EOFException
            catch (EOFException excepcionFinDeArchivo) {// mostrar mensaje al llegar al fin del archivo                

              //  System.out.println("Fin de archivo..." + excepcionFinDeArchivo.toString());

            } catch (ClassNotFoundException ex) {
          
            //    System.out.println("Clase no encontrada..." + ex.toString());
            
            } catch (IOException ex) {
          
            //    System.out.println("Error de archivo..." + ex.toString());
           
            } catch (Exception e) {
            
                System.out.println("Error general..." + e.toString());
            
            } finally {
            
                try {
                    entrada.close();
                } catch (IOException ex) {
                
                    System.out.println("Error general..." + ex.toString());
                }
            }
        }
    }

    private void ingresarPais() throws URISyntaxException {

    //objectstream para crear el archivo de objetos por primera Vez, Crea el encabezado.
        ObjectOutputStream PrimerObjetoStream = null;

    //objectstream para agregar objetos a un archivo ya creado, no crea el encabezado
        AgregarObject StreamAgregarObjetos = null;

    //Ruta donde esta/crea el archivo de Estudiantes.
        Path rutaArchivo = Paths.get("").toAbsolutePath().resolve("src\\olimpicos\\Datos\\Paises.obj");

        if (!TNombrePais.getText().equals("")) {

            //CREAMOS UN Estudiante Y LO GUARDAMOS EN EL ARCHIVO Estudiantes.obj
            CPaises2 e = new CPaises2(TNombrePais.getText(), 0, 0, 0);

            // Paises.getItems().add(e.NombrePais);
            try {

                //si no existe el archivo de objetos usar el PrimerObjetoStream para guardar el primer objeto                
                //si ya existe debemos usar el AgregarObejtoStream para agregar el objeto al archivo   
                if (Files.exists(rutaArchivo)) {//agregamos el objeto al archivo existente                    

                    StreamAgregarObjetos = new AgregarObject(new FileOutputStream(rutaArchivo.toString(), true));
                    StreamAgregarObjetos.writeObject(e);
                    StreamAgregarObjetos.flush();//escribir de memoria a disco.

                } else {

                    //Creamos el archivo y agregamos el primer objeto al archivo                    
                    PrimerObjetoStream = new ObjectOutputStream(new FileOutputStream(rutaArchivo.toString()));
                    PrimerObjetoStream.writeObject(e);
                    PrimerObjetoStream.flush();//escribir de memoria a disco.

                }
                // lresultado.setStyle("-fx-text-fill: green");
                // lresultado.setText("Estudiante registrado correctamente!!");
                TNombrePais.setText("");

            } catch (IOException ex) {

                System.out.println("Error en el archivo: Empleados.obj - Error:" + ex.toString());

            } finally {

                try {

                    if (PrimerObjetoStream != null) {
                        PrimerObjetoStream.close();
                    }
                    if (StreamAgregarObjetos != null) {
                        StreamAgregarObjetos.close();
                    }

                } catch (IOException ex) {

                    Logger.getLogger(OlimpicoSS_1.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        } else {
            // lresultado.setStyle("-fx-text-fill: red");
            // lresultado.setText("Ingrese toda la información !!");        
        }
        //    seleccionUsuario = true;        
    }

    private void Agregar_Paises_ChoiceBox() {//Buscar el estudiante seleccionado y mostar la info.                

        Paises.getItems().clear();

        Path rutaArchivo2 = Paths.get("").toAbsolutePath().resolve("src\\olimpicos\\Datos\\Paises.obj");
        if (!Files.exists(rutaArchivo2)) {
    
            JOptionPane.showMessageDialog( null , "No se registra ningun Pais " , " - Error al Validar - " , JOptionPane.ERROR_MESSAGE );    
        
        } else {
            ObjectInputStream entrada = null;
            boolean encontrado = false;

            try {

                entrada = new ObjectInputStream(new FileInputStream(rutaArchivo2.toString()));
                do {
                    CPaises2 Cpais = (CPaises2) entrada.readObject();
                    Paises.getItems().add(Cpais.NombrePais);

                } while (!encontrado);

            } //cuando termina de recorrer todos los objetos del archivo se lanza la excepción EOFException
            catch (EOFException excepcionFinDeArchivo) {// mostrar mensaje al llegar al fin del archivo                
            //    System.out.println("Fin de archivo..." + excepcionFinDeArchivo.toString());
            } catch (ClassNotFoundException ex) {
            //    System.out.println("Clase no encontrada..." + ex.toString());
            } catch (IOException ex) {
            //    System.out.println("Error de archivo..." + ex.toString());
            } catch (Exception e) {
            //     System.out.println("Error general..." + e.toString());
            } finally {
                try {
                    entrada.close();
            } catch (IOException ex) {
            //        System.out.println("Error general..." + ex.toString());
                }
            }
        }
    }

    private void Agregar_Paises_Array() {//Buscar el estudiante seleccionado y mostar la info.                

        AuxiliarObjetos.clear();

        Path rutaArchivo2 = Paths.get("").toAbsolutePath().resolve("src\\olimpicos\\Datos\\Paises.obj");
        if (!Files.exists(rutaArchivo2)) {
            
            JOptionPane.showMessageDialog( null , "No se registra ningun Pais " , " - Error al Validar - " , JOptionPane.ERROR_MESSAGE );    
        
        } else {
            ObjectInputStream entrada = null;
            boolean encontrado = false;

            try {

                entrada = new ObjectInputStream(new FileInputStream(rutaArchivo2.toString()));
                do {
                    CPaises2 Cpais = (CPaises2) entrada.readObject();
                    AuxiliarObjetos.add(Cpais);

                } while (!encontrado);

            } //cuando termina de recorrer todos los objetos del archivo se lanza la excepción EOFException
            catch (EOFException excepcionFinDeArchivo) {// mostrar mensaje al llegar al fin del archivo                
                //System.out.println("Fin de archivo..." + excepcionFinDeArchivo.toString());
            } catch (ClassNotFoundException ex) {
                //System.out.println("Clase no encontrada..." + ex.toString());
            } catch (IOException ex) {
                //System.out.println("Error de archivo..." + ex.toString());
            } catch (Exception e) {
                //System.out.println("Error general..." + e.toString());
            } finally {
            
            try {
                    entrada.close();
            } catch (IOException ex) {
            //        System.out.println("Error general..." + ex.toString());
                }
            }
        }
    }

    private void Agregar_Medalla_Pais() throws URISyntaxException {
        try {

            ObjectOutputStream PrimerObjetoStream = null;

        //objectstream para agregar objetos a un archivo ya creado
            AgregarObject StreamAgregarObjetos = null;
        
        //Ruta donde esta/crea el archivo de Estudiantes.
            Path rutaArchivo = Paths.get("").toAbsolutePath().resolve("src\\olimpicos\\Datos\\Paises.obj");

            if (!Paises.getValue().equalsIgnoreCase(null) && !Medallas.getValue().equalsIgnoreCase(null)) {

                for (int i = 0; i < AuxiliarObjetos.size(); i++) {

                //if para obtener el indice de pais introducido en la array
                    if (Paises.getValue().equalsIgnoreCase(AuxiliarObjetos.get(i).NombrePais)) {

                        if (Medallas.getValue().equalsIgnoreCase("Oro")) {
                            CPaises2 SustituirPais = new CPaises2(
                                    AuxiliarObjetos.get(i).NombrePais,
                                    AuxiliarObjetos.get(i).MOro + 1,
                                    AuxiliarObjetos.get(i).MPlata,
                                    AuxiliarObjetos.get(i).MBronce);

                            //reemplazar el objeto en la array
                            AuxiliarObjetos.set(i, SustituirPais);

                        } else if (Medallas.getValue().equalsIgnoreCase("Plata")) {

                            CPaises2 SustituirPais = new CPaises2(
                                    AuxiliarObjetos.get(i).NombrePais,
                                    AuxiliarObjetos.get(i).MOro,
                                    AuxiliarObjetos.get(i).MPlata + 1,
                                    AuxiliarObjetos.get(i).MBronce);

                            //reemplazar el objeto en la array
                            AuxiliarObjetos.set(i, SustituirPais);

                        } else if (Medallas.getValue().equalsIgnoreCase("Bronce")) {

                            CPaises2 SustituirPais = new CPaises2(
                                    AuxiliarObjetos.get(i).NombrePais,
                                    AuxiliarObjetos.get(i).MOro,
                                    AuxiliarObjetos.get(i).MPlata,
                                    AuxiliarObjetos.get(i).MBronce + 1);

                            //reemplazar el objeto en la array                          
                            AuxiliarObjetos.set(i, SustituirPais);

                        }

                       // System.out.println("el paise seleccionado es " + AuxiliarObjetos.get(i).NombrePais + " oros " + AuxiliarObjetos.get(i).MOro);
                    }
                }
                //System.out.println("pais y medalla fueron seleccionadas");
            }
            if (Files.exists(rutaArchivo)) {//agregamos el objeto al archivo existente                    

                PrimerObjetoStream = new ObjectOutputStream(new FileOutputStream(rutaArchivo.toString()));

                for (int i = 0; i < AuxiliarObjetos.size(); i++) {
                    PrimerObjetoStream.writeObject(AuxiliarObjetos.get(i));
                }

                PrimerObjetoStream.flush();

            }

            if (PrimerObjetoStream != null) {
                PrimerObjetoStream.close();
            }

            Paises.getSelectionModel().clearSelection();
            Medallas.getSelectionModel().clearSelection();

        } catch (Exception d) {
            
           // System.out.println("no has registrado la informacion completa de las medallas");
    
        JOptionPane.showMessageDialog( null , "no has registrado la informacion completa de las medallas" , " - Error al Validar - " , JOptionPane.ERROR_MESSAGE );

        }

    }

    private void IngresarDestacados() throws URISyntaxException {

        //objectstream para crear el archivo de objetos por primera Vez, Crea el encabezado.
        ObjectOutputStream PrimerObjetoStream = null;

        //objectstream para agregar objetos a un archivo ya creado, no crea el encabezado
        AgregarObject StreamAgregarObjetos = null;

        //Ruta donde esta/crea el archivo de Estudiantes.
        Path rutaArchivo = Paths.get("").toAbsolutePath().resolve("src\\olimpicos\\Datos\\Destacados.obj");

        try {

            if (!TNombreDestacados.getText().equals("")
                    && !rutaImagenDestacados.equals("")
                    && !TNacionalidadDestacados.getText().equals("")
                    && !AreaPerfilDestacados.getText().equals("")
                    && !AreaLogroDestacados.getText().equals("")
                    && !OroDestacados.getValue().equals(false)
                    && !PlataDestacados.getValue().equals(false)
                    && !BronceDestacados.getValue().equals(false)) {

                //creacion del objeto deportista y agregarlo a el archivo Deportista.obj
                CDestacado Des = new CDestacado(
                        TNombreDestacados.getText(),
                        rutaImagenDestacados,
                        TNacionalidadDestacados.getText(),
                        AreaPerfilDestacados.getText(),
                        AreaLogroDestacados.getText(),
                        OroDestacados.getValue().toString(),
                        PlataDestacados.getValue().toString(),
                        BronceDestacados.getValue().toString());

                try {

                    //si no existe el archivo de objetos usar el PrimerObjetoStream para guardar el primer objeto                
                    //si ya existe debemos usar el AgregarObejtoStream para agregar el objeto al archivo   
                    if (Files.exists(rutaArchivo)) {//agregamos el objeto al archivo existente                    

                        StreamAgregarObjetos = new AgregarObject(new FileOutputStream(rutaArchivo.toString(), true));
                        StreamAgregarObjetos.writeObject(Des);
                        StreamAgregarObjetos.flush();//escribir de memoria a disco.

                    } else {

                        //Creamos el archivo y agregamos el primer objeto al archivo                    
                        PrimerObjetoStream = new ObjectOutputStream(new FileOutputStream(rutaArchivo.toString()));
                        PrimerObjetoStream.writeObject(Des);
                        PrimerObjetoStream.flush();//escribir de memoria a disco.

                    }

                } catch (IOException ex) {

                    System.out.println("Error en el archivo: Empleados.obj - Error:" + ex.toString());

                } finally {

                    try {

                        if (PrimerObjetoStream != null) {
                            PrimerObjetoStream.close();
                        }
                        if (StreamAgregarObjetos != null) {
                            StreamAgregarObjetos.close();
                        }

                    } catch (IOException ex) {

                        Logger.getLogger(OlimpicoSS_1.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }

            } else {

                JOptionPane.showMessageDialog( null , " falta informacion en la creacion del famoso " , " - Error al Validar - " , JOptionPane.ERROR_MESSAGE );

            }

        } catch (Exception ed) {

             JOptionPane.showMessageDialog( null , " falta informacion en la creacion del famoso " , " - Error al Validar - " , JOptionPane.ERROR_MESSAGE );
    
        }

    }

    private void Agregar_Destacados_Array() {//Buscar el estudiante seleccionado y mostar la info.                

        AuxiliarObjetosDestacados.clear();

        Path rutaArchivo2 = Paths.get("").toAbsolutePath().resolve("src\\olimpicos\\Datos\\Destacados.obj");

        if (!Files.exists(rutaArchivo2)) {
            
            JOptionPane.showMessageDialog( null , "No se registra ningun Destacado " , " - Error al Validar - " , JOptionPane.ERROR_MESSAGE );    

        } else {
            ObjectInputStream entrada = null;
            boolean encontrado = false;

            try {

                entrada = new ObjectInputStream(new FileInputStream(rutaArchivo2.toString()));
                do {
                    CDestacado CDes = (CDestacado) entrada.readObject();
                    AuxiliarObjetosDestacados.add(CDes);

                } while (!encontrado);

            } //cuando termina de recorrer todos los objetos del archivo se lanza la excepción EOFException
            catch (EOFException excepcionFinDeArchivo) {// mostrar mensaje al llegar al fin del archivo                
                //System.out.println("Fin de archivo..." + excepcionFinDeArchivo.toString());
            } catch (ClassNotFoundException ex) {
               // System.out.println("Clase no encontrada..." + ex.toString());
            } catch (IOException ex) {
               // System.out.println("Error de archivo..." + ex.toString());
            } catch (Exception e) {
               // System.out.println("Error general..." + e.toString());
            } finally {
                try {
                    entrada.close();
                } catch (IOException ex) {
                 //   System.out.println("Error general..." + ex.toString());
                }
            }
        }
    }

    private void IngresarDestacadoColombiano() throws URISyntaxException {

        //objectstream para crear el archivo de objetos por primera Vez, Crea el encabezado.
        ObjectOutputStream PrimerObjetoStream = null;

        //objectstream para agregar objetos a un archivo ya creado, no crea el encabezado
        AgregarObject StreamAgregarObjetos = null;

        //Ruta donde esta/crea el archivo de Estudiantes.
        Path rutaArchivo = Paths.get("").toAbsolutePath().resolve("src\\olimpicos\\Datos\\DestacadosColombianos.obj");

        try {

            if (!TNombreDestacadosColombiano.getText().equals("")
                    && !rutaImagenDestacadosColombiano.equals("")
                    && !AreaPerfilDestacadosColombiano.getText().equals("")
                    && !AreaLogroDestacadosColombiano.getText().equals("")
                    && !TDeporteDestacadosColombiano.getText().equals("")
                    && !TOlimpicoDestacadosColombiano.getText().equals("")
                    && !MedallasDestacadoColombia.getValue().equals(false)) {

                System.out.println("Prueba Creacion de Colombianos Ganadores");

                //creacion del objeto CDestacadoColombiano y agregarlo a el archivo DestacadosColombianos.obj
                CDestacadoColombiano DesCol = new CDestacadoColombiano(
                        TNombreDestacadosColombiano.getText(),
                        rutaImagenDestacadosColombiano,
                        AreaPerfilDestacadosColombiano.getText(),
                        AreaLogroDestacadosColombiano.getText(),
                        TOlimpicoDestacadosColombiano.getText(),
                        MedallasDestacadoColombia.getValue(),
                        TDeporteDestacadosColombiano.getText());

                try {

                    //si no existe el archivo de objetos usar el PrimerObjetoStream para guardar el primer objeto                
                    //si ya existe debemos usar el AgregarObejtoStream para agregar el objeto al archivo   
                    if (Files.exists(rutaArchivo)) {//agregamos el objeto al archivo existente                    

                        StreamAgregarObjetos = new AgregarObject(new FileOutputStream(rutaArchivo.toString(), true));
                        StreamAgregarObjetos.writeObject(DesCol);
                        StreamAgregarObjetos.flush();//escribir de memoria a disco.

                    } else {

                        //Creamos el archivo y agregamos el primer objeto al archivo                    
                        PrimerObjetoStream = new ObjectOutputStream(new FileOutputStream(rutaArchivo.toString()));
                        PrimerObjetoStream.writeObject(DesCol);
                        PrimerObjetoStream.flush();//escribir de memoria a disco.

                    }

                } catch (IOException ex) {

                    System.out.println("Error en el archivo: DestacadosColombia.obj - Error:" + ex.toString());

                } finally {

                    try {

                        if (PrimerObjetoStream != null) {
                            PrimerObjetoStream.close();
                        }
                        if (StreamAgregarObjetos != null) {
                            StreamAgregarObjetos.close();
                        }

                    } catch (IOException ex) {

                        Logger.getLogger(OlimpicoSS_1.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }

            } else {
             JOptionPane.showMessageDialog( null , "Falta Informacion del Destacado Colombiano " , " - Error al Validar - " , JOptionPane.ERROR_MESSAGE );    
            }

        } catch (Exception ed) {

            JOptionPane.showMessageDialog( null , "Falta Informacion del Destacado Colombiano " , " - Error al Validar - " , JOptionPane.ERROR_MESSAGE );    

        }

    }

    private void Agregar_Destacados_Colombianos_Array() {

        AuxiliarObjetosDestacadosColombianos.clear();

        Path rutaArchivo2 = Paths.get("").toAbsolutePath().resolve("src\\olimpicos\\Datos\\DestacadosColombianos.obj");

        if (!Files.exists(rutaArchivo2)) {

            JOptionPane.showMessageDialog( null , "No se registra ningun Destacado Colombiano " , " - Error al Validar - " , JOptionPane.ERROR_MESSAGE );    
       
        } else {
            ObjectInputStream entrada = null;
            boolean encontrado = false;

            try {

                entrada = new ObjectInputStream(new FileInputStream(rutaArchivo2.toString()));
                do {
                    CDestacadoColombiano CDesCol = (CDestacadoColombiano) entrada.readObject();
                    AuxiliarObjetosDestacadosColombianos.add(CDesCol);

                } while (!encontrado);

            } //cuando termina de recorrer todos los objetos del archivo se lanza la excepción EOFException
            catch (EOFException excepcionFinDeArchivo) {// mostrar mensaje al llegar al fin del archivo                
               //  System.out.println("Fin de archivo..." + excepcionFinDeArchivo.toString());
            } catch (ClassNotFoundException ex) {
               // System.out.println("Clase no encontrada..." + ex.toString());
            } catch (IOException ex) {
               // System.out.println("Error de archivo..." + ex.toString());
            } catch (Exception e) {
               // System.out.println("Error general..." + e.toString());
            } finally {
                try {
                    entrada.close();
                } catch (IOException ex) {
                 //   System.out.println("Error general..." + ex.toString());
                }
            }
        }
    }

    public VBox CreatePageDestacadosColombianos(int pageIndex) {

        VBox box = new VBox(5);
        int page = pageIndex * itemsPerPage();

        Label[] NombreArrayDestacados = new Label[AuxiliarObjetosDestacadosColombianos.size()];
        Label[] PerfilArrayDeportistas = new Label[AuxiliarObjetosDestacadosColombianos.size()];
        Label[] LogroArrayDeportistas = new Label[AuxiliarObjetosDestacadosColombianos.size()];
        Label[] OlimpicosArrayDeportistas = new Label[AuxiliarObjetosDestacadosColombianos.size()];
        Label[] MedallaArrayDeportistas = new Label[AuxiliarObjetosDestacadosColombianos.size()];
        Label[] DeporteArrayDeportistas = new Label[AuxiliarObjetosDestacadosColombianos.size()];

        ImageView[] ImagenesArrayDestacados = new ImageView[AuxiliarObjetosDestacadosColombianos.size()];

        for (int i = 0; i < AuxiliarObjetosDestacadosColombianos.size(); i++) {

            ImagenesArrayDestacados[i] = new ImageView(new Image(AuxiliarObjetosDestacadosColombianos.get(i).getImagenDestacadosColombiano()));
            ImagenesArrayDestacados[i].setFitHeight(300);
            ImagenesArrayDestacados[i].setFitWidth(300);

            NombreArrayDestacados[i] = new Label("Nombre : " + AuxiliarObjetosDestacadosColombianos.get(i).getNombreDestacadosColombiano());
            NombreArrayDestacados[i].setTextFill(Color.GOLD);
            NombreArrayDestacados[i].setFont(Font.font("Segoe Script", FontWeight.BOLD, 15));
            NombreArrayDestacados[i].setEffect(new Bloom());

            PerfilArrayDeportistas[i] = new Label("Perfil : " + AuxiliarObjetosDestacadosColombianos.get(i).getPerfilDestacadosColombiano());
            PerfilArrayDeportistas[i].setTextFill(Color.GOLD);
            PerfilArrayDeportistas[i].setFont(Font.font("Segoe Script", FontWeight.BOLD, 15));
            PerfilArrayDeportistas[i].setEffect(new Bloom());

            LogroArrayDeportistas[i] = new Label("Logro : " + AuxiliarObjetosDestacadosColombianos.get(i).getLogrosDestacadosColombiano());
            LogroArrayDeportistas[i].setTextFill(Color.GOLD);
            LogroArrayDeportistas[i].setFont(Font.font("Segoe Script", FontWeight.BOLD, 15));
            LogroArrayDeportistas[i].setEffect(new Bloom());

            OlimpicosArrayDeportistas[i] = new Label("Olimpicos : " + AuxiliarObjetosDestacadosColombianos.get(i).getAñoMedallaDestacadosColombiano());
            OlimpicosArrayDeportistas[i].setTextFill(Color.BLUE);
            OlimpicosArrayDeportistas[i].setFont(Font.font("Segoe Script", FontWeight.BOLD, 15));
            OlimpicosArrayDeportistas[i].setEffect(new Bloom());

            MedallaArrayDeportistas[i] = new Label("Medalla Ganada : " + AuxiliarObjetosDestacadosColombianos.get(i).getAñoMedallaDestacadosColombiano());
            MedallaArrayDeportistas[i].setTextFill(Color.BLUE);
            MedallaArrayDeportistas[i].setFont(Font.font("Segoe Script", FontWeight.BOLD, 10));
            MedallaArrayDeportistas[i].setEffect(new Bloom());

            DeporteArrayDeportistas[i] = new Label("Competicion : " + AuxiliarObjetosDestacadosColombianos.get(i).getDeporteDestacadosColombiano());
            DeporteArrayDeportistas[i].setTextFill(Color.RED);
            DeporteArrayDeportistas[i].setFont(Font.font("Segoe Script", FontWeight.BOLD, 10));
            DeporteArrayDeportistas[i].setEffect(new Bloom());

        }

        for (int i = page; i < page + itemsPerPage(); i++) {
            // box.getChildren().addAll(d[pageIndex]);
            box.getChildren().addAll(
                    ImagenesArrayDestacados[pageIndex],
                    NombreArrayDestacados[pageIndex],
                    PerfilArrayDeportistas[pageIndex],
                    LogroArrayDeportistas[pageIndex],
                    OlimpicosArrayDeportistas[pageIndex],
                    MedallaArrayDeportistas[pageIndex],
                    DeporteArrayDeportistas[pageIndex]);

            System.out.println("Pagina " + pageIndex);
        }
        return box;
    }

//____________________    
}
