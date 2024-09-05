package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

import javafx.geometry.Point2D;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class Main extends Application {
	private static final String IMAGE_PATH = "C:\\Users\\iFix\\Downloads\\projpic1.jpg";
	static ArrayList<College> Collages = new ArrayList<>();
	static HashMap<String, College> Coll = new HashMap<>();
	static HashMap<String, Double> res = new HashMap<>();
	static PriorityQueue<College> PQ = new PriorityQueue<>();
	static String[] strr = new String[53];
	static ArrayList<String> paintpath = new ArrayList<>();
	static String path = "";
	static String pathrev = "";
	static String strr1 = "";
	static String strr2 = "";

	@Override
	public void start(Stage primaryStage) throws Exception {

		GridPane gr = new GridPane();
		gr.setAlignment(Pos.TOP_LEFT);
		gr.setHgap(5.5);
		gr.setVgap(5.5);

		readFile();

		HBox hb1 = new HBox();
		hb1.setSpacing(15);

		Label l1 = new Label("Source");
		l1.setFont(new Font("Arial", 20));
		l1.setTextFill(Color.BLUEVIOLET);
		ComboBox<String> cb = new ComboBox<>();

		cb.getItems().addAll(strr);
		cb.setPromptText("source");

		hb1.getChildren().addAll(l1, cb);

		HBox hb2 = new HBox();
		hb2.setSpacing(15);

		Label l2 = new Label("Target");
		l2.setFont(new Font("Arial", 20));
		l2.setTextFill(Color.BLUEVIOLET);
		ComboBox<String> cb1 = new ComboBox<>();
		cb1.getItems().addAll(strr);
		cb1.setPromptText("target");

		hb2.getChildren().addAll(l2, cb1);

		Button btn = new Button("Run");
		btn.setFont(new Font("Arial", 20));
		btn.setTextFill(Color.BLUEVIOLET);

		Label l3 = new Label("Path");
		l3.setFont(new Font("Arial", 20));
		l3.setTextFill(Color.BLUEVIOLET);
		TextArea ta = new TextArea();

		Label l4 = new Label("Distance");
		l4.setFont(new Font("Arial", 20));
		l4.setTextFill(Color.BLUEVIOLET);

		TextField txt = new TextField();
		
		

		cb.setOnAction(e -> {
			strr1 += cb.getSelectionModel().getSelectedItem();

			cb1.setOnAction(f -> {
				strr2 += cb1.getSelectionModel().getSelectedItem();
				String str1 = strr1;
				String str2 = strr2;
				College source = Coll.get(str1);
				College target = Coll.get(str2);
				if(str1.equals(str2))
					ta.appendText("source = target");
				else {
				// System.out.println(target.getName());
					for (College col : Collages) {
						if (col.name.equals(source.getName())) {
							col.setCost(0);
						} else
							col.setToMaxInt();
						PQ.add(col);
					}
					int count = 0;
					while (!PQ.isEmpty() && PQ.contains(target)) {
						College uknownMin = PQ.poll();
						// System.out.println(uknownMin.getCost());
						// System.out.println(uknownMin.getName());
						LinkedList<Edge> adjacentsList = uknownMin.getEdges();
						for (Edge eg : adjacentsList) {
							College coll = eg.getAdj();
							if (PQ.contains(coll)) {
								if ((uknownMin.getCost() + eg.getDistance()) < coll.getCost()) {
									PQ.remove(coll);
									coll.setCost(uknownMin.getCost() + eg.getDistance());
									coll.setPrev(uknownMin);
									PQ.add(coll);
									System.out.println(coll.getCost() + "=======" + coll.getName());
									// System.out.println(coll.getPrev().getName());
								}
							}
						}

					}
					double result = target.getCost();
					int counter = 0;
					if (target.getPrev() != null) {

						while (target != null) {
							paintpath.add(target.getName());
							path = path + target.getName() + " ";
							target = target.getPrev();
						}
						String[] st = path.split(" ");
						for(int i = st.length -1; i >= 0; i--) {
							if(i == 0)
							    pathrev += st[i] ;
							else
							    pathrev += st[i] + "->";
							 
						}

						
					}else {
						pathrev = "no path";
						result = 0.0;
						
					}
					
					double re = result;
					
					btn.setOnAction(d -> {
						ta.appendText(pathrev);
						txt.setText(re + "");

					});
						
				}

		});
			
			

	});
		
		Image image = new Image(IMAGE_PATH);
		ImageView imageView = new ImageView(image);

		Canvas canvas = new Canvas(image.getWidth(), image.getHeight());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLUEVIOLET); // Set the line color
		gc.setLineWidth(3); // Set the line thickness

		Pane pane = new Pane();
		pane.setPrefSize(image.getWidth(), image.getHeight());

		// pane.getChildren().add(imageView);

		StackPane root = new StackPane();
		root.getChildren().addAll(imageView, canvas);

		pane.getChildren().add(root);
		
		//Scene scene1 = new Scene(pane, 800, 600);
		Button p = new Button("show path");
		p.setFont(new Font("Arial", 20));
		p.setTextFill(Color.BLUEVIOLET);
		
		p.setOnAction(e -> {


			for (int i = 0; i < paintpath.size() - 1; i++) {

				gc.strokeLine(Coll.get(paintpath.get(i)).getX() , Coll.get(paintpath.get(i)).getY(),
						Coll.get(paintpath.get(i + 1)).getX(), Coll.get(paintpath.get(i + 1)).getY());
			}

			
			
			for(College col : Collages) {
				
				Tooltip tooltip = new Tooltip(col.getName());

				// Customize the display duration of the tooltip
				tooltip.setShowDelay(javafx.util.Duration.ZERO);
				tooltip.setHideDelay(javafx.util.Duration.INDEFINITE);
				Circle c = new Circle(Coll.get(col.getName()).getX(),Coll.get(col.getName()).getY(), 3.5);
				if(col.getName() .equals(paintpath.get(paintpath.size()-1))) {
					c.setFill(Color.CYAN);
				    c.setStroke(Color.CYAN);
				} else { 
					c.setFill(Color.BLACK);
				   c.setStroke(Color.BLACK);
				}   
			
				
				pane.getChildren().add(c);
				Tooltip.install(c, tooltip);
				
				c.setOnMouseEntered(o -> {
					tooltip.show(c, Coll.get(paintpath.get(0)).getX(), Coll.get(paintpath.get(0)).getY());
				});

	       
				c.setOnMouseExited(event -> {
					tooltip.hide();
				});
				
				
				
			}

		});
		
        Button bt = new Button();
        bt.setOnAction(e ->{
        	cb.getSelectionModel().clearSelection();
        	cb1.getSelectionModel().clearSelection();
        	ta.clear();
        	txt.clear();
            paintpath.clear();
            pathrev = "";
            strr1 ="";
            strr2 = "";
            for(int i = 0; i<strr.length; i++) {
            	strr[i] = null;
            }
            Coll.clear();
            Collages.clear();
            PQ.clear();
            path = "";
             
             gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
             
             try {
				readFile();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
        	
        });

	
		

		VBox vb = new VBox();
		vb.setSpacing(15);
		vb.getChildren().addAll(hb1, hb2, btn, l3, ta, l4, txt, p ,bt );

		gr.add(vb, 0, 0);
		gr.add(pane, 2, 0);

		Scene scene = new Scene(gr, 1000, 700);

		primaryStage.setScene(scene);
		primaryStage.setTitle("project algo 3");
		primaryStage.show();

	}

	public static ArrayList<College> readFile() throws Exception {
		String readLine = "";
		int numberOfCollage;
		int numberOfEdeg;
		float x;
		float y;
		File fofo = new File("C:\\Users\\iFix\\Downloads\\proj3 (1).txt");
		Scanner input = new Scanner(fofo);
		readLine = input.nextLine();
		String[] str = readLine.split(" ");
		numberOfCollage = Integer.parseInt(str[0]);
		numberOfEdeg = Integer.parseInt(str[1]);
		int counter = 0;
		for (int i = 0; i < numberOfCollage; i++) {
			readLine = input.nextLine();
			// System.out.println(readLine);
			String[] str1 = readLine.split(" ");
			x = Float.parseFloat(str1[1]);
			y = Float.parseFloat(str1[2]);
			College c1 = new College(str1[0], x, y);
			Collages.add(c1);
			Coll.putIfAbsent(str1[0], c1);
			strr[i] = str1[0];
		}

		for (int i = 0; i < numberOfEdeg; i++) {
			readLine = input.nextLine();
			String[] str2 = readLine.split(" ");
			String collage1 = str2[0];
			// System.out.println(collage1);
			String collage2 = str2[1];
			double distance = Double.parseDouble(str2[2]);
			// System.out.println(distance);
			College collage = Coll.get(collage1);
			// System.out.println(collage.getName());
			College college2 = Coll.get(collage2);
			College adjacentCollage = Coll.get(collage2);
			College adjacentCollage2 = Coll.get(collage1);
			collage.addAdjacentCollage(adjacentCollage, distance);
			college2.addAdjacentCollage(adjacentCollage2, distance);

		}
		return Collages;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
