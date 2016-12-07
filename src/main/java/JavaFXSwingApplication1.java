/**
 * Created by Leo on 2016/12/6.
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.nativeapi.NativeWebcamDriver;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

/**
 *
 * @author Leo
 */
public class JavaFXSwingApplication1 extends JApplet {


    public static Boolean isInitJacob=true;
    private static String[] msgString=new String[9];
    private static String picPath=null;
    private static String videoPath=null;

    private static final int JFXPANEL_WIDTH_INT = 300;
    private static final int JFXPANEL_HEIGHT_INT = 250;
    private static JFXPanel fxContainer;
    private static JFXPanel contaner1;
    private static JFXPanel contaner2;
    private static JFXPanel contaner3;

    private FlowPane topPane;
    private ComboBox topChoiceCam;
    private String cameraListPromptText = "Choose Camera";
    private static Webcam webCam = null;

    private FlowPane bottomCameraControlPane;
    private Button btnVideoAndMsg;
    private Button btnPicAndMsg;
//    private Button btnUploadFile;
    private Button btnAllThing;

    private HBox rightMessageAllBox;
    private VBox TextMessageBox;
    private static VBox imageVBox;
    private HBox nameHBox;
    private HBox sexHBox;
    private HBox nationHBox;
    private HBox birthdayHBox;
    private HBox addressHBox;
    private HBox IDHBox;
    private HBox departmentHBox;
    private HBox startDateHBox;
    private HBox endDateHBox;
    private TextField nameLeft;
    private static TextArea nameRight;
    private TextField sexLeft;
    private static TextArea sexRight;
    private TextField nationLeft;
    private static TextArea nationRight;
    private TextField birthdayLeft;
    private static TextArea birthdayRight;
    private TextField addressLeft;
    private static TextArea addressRight;
    private TextField IDLeft;
    private static TextArea IDRight;
    private TextField departmentLeft;
    private static TextArea departmentRight;
    private TextField startDateLeft;
    private static TextArea startDateRight;
    private TextField endDateLeft;
    private static TextArea endDateRight;

    private ImageView imageNull;
    private static ImageView imagePerson;
    private static Button btnCheck;
    private static Button btnClear;

    private static String name;
    private static String sex;
    private static String nation;
    private static String birthday;
    private static String address;
    private static String ID;
    private static String department;
    private static String startDate;
    private static String endDate;
    private static String path=null;
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        Webcam.setDriver(new NativeWebcamDriver());


        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                } catch (Exception e) {
                }
                 if (webCam==null){
                       webCam=Webcam.getDefault();
                 }
                WebcamPanel panel = new WebcamPanel(webCam);
                panel.setFPSDisplayed(true);
                panel.setDisplayDebugInfo(true);
                panel.setImageSizeDisplayed(true);
                panel.setSize(new Dimension(webCam.getViewSize()));
                System.out.println(webCam.getViewSize());
                JFrame frame = new JFrame("JavaFX 2 in Swing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JApplet applet = new JavaFXSwingApplication1();
                applet.init();

                frame.setContentPane(applet.getContentPane());

                frame.pack();
                frame.setSize(new Dimension(webCam.getViewSize().width+360,webCam.getViewSize().height+100));
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.add(panel);
                applet.start();
            }
        });
    }

    @Override
    public void init() {
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(360, 377));
        add(fxContainer, BorderLayout.EAST);
        // create JavaFX scene
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                createSceneRight();
            }
        });
        contaner1 = new JFXPanel();
        contaner1.setPreferredSize(new Dimension(50, 50));
        add(contaner1, BorderLayout.SOUTH);
        // create JavaFX scene
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                createSceneBottom();
            }
        });
        contaner2 = new JFXPanel();
        contaner2.setPreferredSize(new Dimension(50, 50));
        add(contaner2, BorderLayout.NORTH);
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                createSceneTop();
            }
        });


    }

    private void createSceneRight() {
//
        rightMessageAllBox=new HBox();
        rightMessageAllBox.setPrefHeight(377);
        rightMessageAllBox.setPrefWidth(353);
        rightMessageAllBox.setAlignment(Pos.CENTER);
        //文字信息
        TextMessageBox=new VBox();
        TextMessageBox.setPrefWidth(223);
        TextMessageBox.setPrefHeight(377);
        TextMessageBox.setAlignment(Pos.TOP_LEFT);
        //姓名栏
        nameHBox=new HBox();
        nameHBox.setAlignment(Pos.TOP_LEFT);
        nameHBox.setPrefWidth(223);
        nameHBox.setPrefHeight(32);
        nameLeft=new TextField();
        nameLeft.setAlignment(Pos.CENTER_RIGHT);
        nameLeft.setPrefHeight(23);
        nameLeft.setPrefWidth(77);
        nameLeft.setText("姓名：");
        nameLeft.setEditable(false);
        nameLeft.setFont(Font.font(12));
        nameHBox.getChildren().add(nameLeft);
        nameRight=new TextArea();
        nameRight.setMinWidth(USE_PREF_SIZE);
        nameRight.setMinHeight(USE_PREF_SIZE);
        nameRight.setPrefWidth(146);
        nameRight.setPrefHeight(23);
        nameRight.setMaxWidth(USE_PREF_SIZE);
        nameRight.setMaxHeight(USE_PREF_SIZE);
        nameRight.setEditable(false);
        nameRight.setText(name);

        nameHBox.getChildren().add(nameRight);
        TextMessageBox.getChildren().add(nameHBox);
//性别栏
        sexHBox=new HBox();
        sexHBox.setAlignment(Pos.TOP_LEFT);
        sexHBox.setPrefWidth(223);
        sexHBox.setPrefHeight(32);
        sexLeft=new TextField();
        sexLeft.setAlignment(Pos.CENTER_RIGHT);
        sexLeft.setPrefHeight(23);
        sexLeft.setPrefWidth(77);
        sexLeft.setText("性别：");
        sexLeft.setEditable(false);
        sexLeft.setFont(Font.font(12));
        sexHBox.getChildren().add(sexLeft);
        sexRight=new TextArea();
        sexRight.setMinWidth(USE_PREF_SIZE);
        sexRight.setMinHeight(USE_PREF_SIZE);
        sexRight.setPrefWidth(146);
        sexRight.setPrefHeight(23);
        sexRight.setMaxWidth(USE_PREF_SIZE);
        sexRight.setMaxHeight(USE_PREF_SIZE);
        sexRight.setEditable(false);
        sexRight.setText(sex);
        sexHBox.getChildren().add(sexRight);
        TextMessageBox.getChildren().add(sexHBox);
        //民族栏
        nationHBox=new HBox();
        nationHBox.setAlignment(Pos.TOP_LEFT);
        nationHBox.setPrefWidth(223);
        nationHBox.setPrefHeight(32);
        nationLeft=new TextField();
        nationLeft.setAlignment(Pos.CENTER_RIGHT);
        nationLeft.setPrefHeight(23);
        nationLeft.setPrefWidth(77);
        nationLeft.setText("民族：");
        nationLeft.setEditable(false);
        nationLeft.setFont(Font.font(12));
        nationHBox.getChildren().add(nationLeft);
        nationRight=new TextArea();
        nationRight.setMinWidth(USE_PREF_SIZE);
        nationRight.setMinHeight(USE_PREF_SIZE);
        nationRight.setPrefWidth(146);
        nationRight.setPrefHeight(23);
        nationRight.setMaxWidth(USE_PREF_SIZE);
        nationRight.setMaxHeight(USE_PREF_SIZE);
        nationRight.setEditable(false);
        nationRight.setText(nation);
        nationHBox.getChildren().add(nationRight);
        TextMessageBox.getChildren().add(nationHBox);
        //住址栏，需注意文字太多换行的问题
        addressHBox=new HBox();
        addressHBox.setAlignment(Pos.TOP_LEFT);
        addressHBox.setPrefWidth(223);
        addressHBox.setPrefHeight(64);
        addressLeft=new TextField();
        addressLeft.setAlignment(Pos.CENTER_RIGHT);
        addressLeft.setPrefHeight(23);
        addressLeft.setPrefWidth(77);
        addressLeft.setText("住址：");
        addressLeft.setEditable(false);
        addressLeft.setFont(Font.font(12));
        addressHBox.getChildren().add(addressLeft);
        addressRight=new TextArea();
        addressRight.setPrefWidth(146);
        addressRight.setPrefHeight(64);
        addressRight.setEditable(false);
        addressRight.setWrapText(true);
        addressRight.setText(address);
        addressHBox.getChildren().add(addressRight);
        TextMessageBox.getChildren().add(addressHBox);
        //身份证号栏
        IDHBox=new HBox();
        IDHBox.setAlignment(Pos.TOP_LEFT);
        IDHBox.setPrefWidth(223);
        IDHBox.setPrefHeight(32);
        IDLeft=new TextField();
        IDLeft.setAlignment(Pos.CENTER_RIGHT);
        IDLeft.setPrefHeight(23);
        IDLeft.setPrefWidth(77);
        IDLeft.setText("身份证号：");
        IDLeft.setEditable(false);
        IDLeft.setFont(Font.font(12));
        IDHBox.getChildren().add(IDLeft);
        IDRight=new TextArea();
        IDRight.setMinWidth(USE_PREF_SIZE);
        IDRight.setMinHeight(USE_PREF_SIZE);
        IDRight.setPrefWidth(146);
        IDRight.setPrefHeight(23);
        IDRight.setMaxWidth(USE_PREF_SIZE);
        IDRight.setMaxHeight(USE_PREF_SIZE);
        IDRight.setEditable(false);
        IDRight.setText(ID);
        IDHBox.getChildren().add(IDRight);
        TextMessageBox.getChildren().add(IDHBox);
        //签发机关栏
        departmentHBox=new HBox();
        departmentHBox.setAlignment(Pos.TOP_LEFT);
        departmentHBox.setPrefWidth(223);
        departmentHBox.setPrefHeight(32);
        departmentLeft=new TextField();
        departmentLeft.setAlignment(Pos.CENTER_RIGHT);
        departmentLeft.setPrefHeight(23);
        departmentLeft.setPrefWidth(77);
        departmentLeft.setText("签发机关：");
        departmentLeft.setEditable(false);
        departmentLeft.setFont(Font.font(12));
        departmentHBox.getChildren().add(departmentLeft);
        departmentRight=new TextArea();
        departmentRight.setMinWidth(USE_PREF_SIZE);
        departmentRight.setMinHeight(USE_PREF_SIZE);
        departmentRight.setPrefWidth(146);
        departmentRight.setPrefHeight(23);
        departmentRight.setMaxWidth(USE_PREF_SIZE);
        departmentRight.setMaxHeight(USE_PREF_SIZE);
        departmentRight.setEditable(false);
        departmentRight.setText(department);
        departmentHBox.getChildren().add(departmentRight);
        TextMessageBox.getChildren().add(departmentHBox);
        //发证日期栏
        startDateHBox=new HBox();
        startDateHBox.setAlignment(Pos.TOP_LEFT);
        startDateHBox.setPrefWidth(223);
        startDateHBox.setPrefHeight(32);
        startDateLeft=new TextField();
        startDateLeft.setAlignment(Pos.CENTER_RIGHT);
        startDateLeft.setPrefHeight(23);
        startDateLeft.setPrefWidth(77);
        startDateLeft.setText("发证日期：");
        startDateLeft.setEditable(false);
        startDateLeft.setFont(Font.font(12));
        startDateHBox.getChildren().add(startDateLeft);
        startDateRight=new TextArea();
        startDateRight.setMinWidth(USE_PREF_SIZE);
        startDateRight.setMinHeight(USE_PREF_SIZE);
        startDateRight.setPrefWidth(146);
        startDateRight.setPrefHeight(23);
        startDateRight.setMaxWidth(USE_PREF_SIZE);
        startDateRight.setMaxHeight(USE_PREF_SIZE);
        startDateRight.setEditable(false);
        startDateRight.setText(startDate);
        startDateHBox.getChildren().add(startDateRight);
        TextMessageBox.getChildren().add(startDateHBox);
        //有效期栏
        endDateHBox=new HBox();
        endDateHBox.setAlignment(Pos.TOP_LEFT);
        endDateHBox.setPrefWidth(223);
        endDateHBox.setPrefHeight(32);
        endDateLeft=new TextField();
        endDateLeft.setAlignment(Pos.CENTER_RIGHT);
        endDateLeft.setPrefHeight(23);
        endDateLeft.setPrefWidth(77);
        endDateLeft.setText("有效期：");
        endDateLeft.setEditable(false);
        endDateLeft.setFont(Font.font(12));
        endDateHBox.getChildren().add(endDateLeft);
        endDateRight=new TextArea();
        endDateRight.setMinWidth(USE_PREF_SIZE);
        endDateRight.setMinHeight(USE_PREF_SIZE);
        endDateRight.setPrefWidth(146);
        endDateRight.setPrefHeight(23);
        endDateRight.setMaxWidth(USE_PREF_SIZE);
        endDateRight.setMaxHeight(USE_PREF_SIZE);
        endDateRight.setEditable(false);
        endDateRight.setText(endDate);
        endDateHBox.getChildren().add(endDateRight);
        TextMessageBox.getChildren().add(endDateHBox);
        //添加左信息栏
        rightMessageAllBox.getChildren().add(TextMessageBox);
//添加右照片栏
        imageVBox=new VBox();
        //!
        imageVBox.setAlignment(Pos.TOP_LEFT);
        imageVBox.setPrefWidth(124);
        imageVBox.setPrefHeight(305);

        //空图，为了空出空间
        imageNull=new ImageView();
        imageNull.setVisible(false);
        imageNull.setFitWidth(121);
        imageNull.setFitHeight(30);
        imageVBox.getChildren().add(imageNull);

        //照片
        imagePerson=new ImageView();
        imagePerson.setFitHeight(150);
        imagePerson.setFitWidth(122);
        //  imagePerson.setImage(image);

        imageVBox.getChildren().add(imagePerson);
      //  btnCheckControls();
       // btnClearControls();
        rightMessageAllBox.getChildren().add(imageVBox);
        fxContainer.setScene(new Scene(rightMessageAllBox));
    }
    private void createSceneBottom() {

        bottomCameraControlPane = new FlowPane();
        bottomCameraControlPane.setOrientation(Orientation.HORIZONTAL);
        bottomCameraControlPane.setAlignment(Pos.CENTER);
        bottomCameraControlPane.setHgap(20);
        bottomCameraControlPane.setVgap(10);
        bottomCameraControlPane.setPrefHeight(40);
        createBottomBtn();
        contaner1.setScene(new Scene(bottomCameraControlPane));
    }

    private void createBottomBtn() {
        btnVideoAndMsg=new Button();
        btnVideoAndMsg.setText("录像+身份证");
        btnVideoAndMsg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initJacob();
                try {

                    inputDate();
                    new CreateVideo("VideoAndMsg",webCam);
                    videoPath="E:\\opuputVideoAndMsg.mp4";
                    new UploadFile(msgString,videoPath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (InterruptedException e) {
                    System.out.println("warning");
                }


            }
        });
        bottomCameraControlPane.getChildren().add(btnVideoAndMsg);
        btnPicAndMsg= new Button();
        btnPicAndMsg.setText("截图+身份证");
        btnPicAndMsg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initJacob();
                try {

                    inputDate();
                    new GetPic(webCam,"catchPicAndMsg");
                    picPath="E:\\catchPicAndMsg.jpg";
                    new UploadFile(msgString,picPath);
                } catch (IOException e) {
                    System.out.println("null");
                }

            }
        });
        bottomCameraControlPane.getChildren().add(btnPicAndMsg);
//        btnUploadFile=new Button();
//        btnUploadFile.setText("上传");
//        btnUploadFile.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                try {
//                    new UploadFile(msgString,picPath,videoPath);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        btnUploadFile.setDisable(true);
//        bottomCameraControlPane.getChildren().add(btnUploadFile);
        btnAllThing=new Button();
        btnAllThing.setText("一键上传");
        btnAllThing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              initJacob();
                try {
                    inputDate();
                    new  CreateVideo("ALL",webCam);
                    videoPath="E:\\outputALL.mp4";
                    new GetPic(webCam,"catchPic");
                    picPath="E:\\catchPic.jpg";
                    new UploadFile(msgString,picPath,videoPath);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        bottomCameraControlPane.getChildren().add(btnAllThing);
    }

    private void createSceneTop() {

        topPane = new FlowPane();
        topPane.setAlignment(Pos.CENTER);
        topPane.setHgap(20);
        topPane.setOrientation(Orientation.HORIZONTAL);
        topPane.setPrefHeight(40);
        createTopPanel();
        contaner2.setScene(new Scene(topPane));
    }
    private void btnClearControls() {
        btnClear=new Button();
        btnClear.setText("清除");
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Task<Void> task=new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try {
                            Platform.runLater(new Runnable() {
                                public void run() {
                                    //clearMsg();
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
                new Thread(task).start();
            }
        });
        imageVBox.getChildren().add(btnClear);
    }

//    private void clearMsg() {
//        nameRight.setText("");
//        sexRight.setText("");
//        nationRight.setText("");
//        addressRight.setText("");
//        IDRight.setText("");
//        departmentRight.setText("");
//        startDateRight.setText("");
//        endDateRight.setText("");
//        imagePerson.setImage(null);
//        name=null;
//        sex=null;
//        nation=null;
//        address=null;
//        ID=null;
//        department=null;
//        startDate=null;
//        endDate=null;
//        path=null;
//
//        //btnUploadFile.setDisable(true);
//
//    }
    private static void btnCheckControls() {
        btnCheck=new Button();
        btnCheck.setText("查询");
        btnCheck.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Task<Void> task=new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        try {

                            Platform.runLater(new Runnable() {
                                public void run() {
                                    try {
                                        //初始化硬件并导入数据
                                        initJacob();
                                        inputDate();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }catch (Exception e){
                        }
                        return   null;
                    }
                };
                //执行线程
                new Thread(task).start();

            }
        });
        imageVBox.getChildren().add(btnCheck);
    }
    private static void initJacob() {
        ActiveXComponent com = new ActiveXComponent("CLSID:0F55CC69-97EF-42A9-B63D-D1831CB2B3B9");
        Dispatch disp = (Dispatch) com.getObject();
        int ret = Dispatch.call(disp, "getCardInfo", new Variant("E:\\GET\\person.bmp")).getInt();
        if (ret != 0) {
            System.out.println("打开设备失败");
            JLabel warningLabel=new JLabel("设备未打开！");
            ImageIcon image = new ImageIcon("1.jpg");
            warningLabel.setIcon(image);
            JFrame frame=new JFrame();
//            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setSize(new Dimension(300,200));
            frame.setVisible(true);
            frame.add(warningLabel);
        } else {
            System.out.println("打开设备成功");
            name = Dispatch.call(disp, "Name").getString().trim();
            sex = Dispatch.call(disp, "Sex").getString().trim();
            nation = Dispatch.call(disp, "Nation").getString().trim();
            birthday = Dispatch.call(disp, "Birthday").getString().trim();
            address = Dispatch.call(disp, "Address").getString().trim();
            ID = Dispatch.call(disp, "ID").getString().trim();
            department = Dispatch.call(disp, "Department").getString().trim();
            startDate = Dispatch.call(disp, "StartDate").getString().trim();
            endDate = Dispatch.call(disp, "EndDate").getString().trim();
            path="E:\\GET\\person.bmp";
            msgString[0]=name;
            msgString[1]=sex;
            msgString[2]=nation;
            msgString[3]=birthday;
            msgString[4]=address;
            msgString[5]=ID;
            msgString[6]=department;
            msgString[7]=startDate;
            msgString[8]=endDate;
        }


    }
    private static void inputDate() throws FileNotFoundException {
        nameRight.setText(name);
        sexRight.setText(sex);
        nationRight.setText(nation);
        addressRight.setText(address);
        IDRight.setText(ID);
        departmentRight.setText(department);
        startDateRight.setText(startDate);
        endDateRight.setText(endDate);
        InputStream in=new FileInputStream(new File(path));
        Image image=new Image(in);
        imagePerson.setImage(image);
    }
    private void createTopPanel() {

        int webCamCounter = 0;
        Label lbInfoLabel = new Label("选择摄像头");
        ObservableList<WebCamInfo> options = FXCollections.observableArrayList(
        );

        topPane.getChildren().add(lbInfoLabel);
        for (Webcam webcam : Webcam.getWebcams()) {
            WebCamInfo webCamInfo = new WebCamInfo();
            webCamInfo.setWebCamIndex(webCamCounter);
            webCamInfo.setWebCamName(webcam.getName());
            options.add(webCamInfo);
            webCamCounter++;
        }
        ComboBox<WebCamInfo> cameraOptions = new ComboBox<WebCamInfo>();
        cameraOptions.setItems(options);
        cameraOptions.setPromptText(cameraListPromptText);
        cameraOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WebCamInfo>() {

            @Override
            public void changed(ObservableValue<? extends WebCamInfo> arg0, WebCamInfo arg1, WebCamInfo arg2) {
                if (arg2 != null) {

                    System.out.println("WebCam Index: " + arg2.getWebCamIndex() + ": WebCam Name:" + arg2.getWebCamName());
                    initializeWebCam(arg2.getWebCamIndex());
                }
            }
        });
        topPane.getChildren().add(cameraOptions);
    }
    class WebCamInfo {
        private String webCamName;
        private int webCamIndex;

        public String getWebCamName() {
            return webCamName;
        }

        public void setWebCamName(String webCamName) {
            this.webCamName = webCamName;
        }

        public int getWebCamIndex() {
            return webCamIndex;
        }

        public void setWebCamIndex(int webCamIndex) {
            this.webCamIndex = webCamIndex;
        }

        @Override
        public String toString() {
            return webCamName;
        }

    }
    //!!!!!!!尚未清楚是否真的可以选择，现在只是出现选择画面而已。需更多一步测试
    protected void initializeWebCam(final int webCamIndex) {

        Task<Void> webCamTask = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                if (webCam != null) {
                  //  disposeWebCamCamera();
                    webCam = Webcam.getWebcams().get(webCamIndex);
                    webCam.open();
                } else {
                    webCam = Webcam.getWebcams().get(webCamIndex);
                    webCam.open();
                }

              //  startWebCamStream();
                return null;
            }
        };

        Thread webCamThread = new Thread(webCamTask);
        webCamThread.setDaemon(true);
        webCamThread.start();
      //  bottomCameraControlPane.setDisable(false);
     //   btnCamreaStart.setDisable(true);
    }
}

