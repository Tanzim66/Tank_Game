import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Rectangle;

public class Tankz extends JFrame {
  // private JLabel lbl[][] = new JLabel[10][10];       // number of buttons - can be changed
   private Container c;
   private MyPanel mainPanel = new MyPanel();


   public Tankz()   {
      super( "Tankz" );
	
      //actionBtn1.setIcon(new ImageIcon("C:\\Users\\Tanzim\\Desktop\\Tej final proj\\star.png"));

      c = getContentPane();
      c.setLayout( new BorderLayout() );

      // create and add buttons
     

	  c.add( mainPanel, BorderLayout.CENTER  );
      setSize( 1280, 768 );                          //size of the window, can be changed
      setVisible(true);
      setResizable(false);
   }  
  
   public static void main( String args[] ) {
      Tankz app = new Tankz();

      app.addWindowListener(
         new WindowAdapter() {
            public void windowClosing( WindowEvent e )
            {
               System.exit( 0 );
            }
         }
      );
   } 
}
 class MyPanel extends JPanel implements ActionListener, KeyListener {
	 //variables - they are all global
	int count = 3, count2, count3 = 0, index = 0, tileCount = 0, changeCount, restart = 0, destroyCount, destroyCount2, escapeCount = 0, player2count = 0, count4;
	int count5, changeCount2;

	int play1lives = 200, play2lives = 200;

	String winner = "";
	 	 
	 //Wall arrays:
	int testArray [][] = new int [13][20];
	Rectangle wall [];
	Rectangle whiteTile [];

	 //Player (Blue):
	int bulletX = 0, bulletY = 0, y = 0, x = 0;

	//Player (Red):
	int bulletX2 = 1130, bulletY2 = 700, player2X=1130, player2Y=700;
	 
	int rowArr [];
	int colArr [];
	int rowArr2[];
	int colArr2[];
	 
	boolean blueTrigger = false, placeBlock = false, redTrigger = false, placeBlock2 = false;
	
	Rectangle back, front, up, down, bullet, middle;
	Rectangle play1, play2;
	Rectangle back2, front2, up2, down2, bullet2, middle2;
	 
	ImageIcon rightpic = new ImageIcon("tankright.png");
	ImageIcon uppic = new ImageIcon("tankup.png");
	ImageIcon downpic = new ImageIcon("tankdown.png");
	ImageIcon leftpic = new ImageIcon("tankleft.png");

	ImageIcon rightpic2 = new ImageIcon("tank2right.png");
	ImageIcon uppic2 = new ImageIcon("tank2up.png");
	ImageIcon downpic2 = new ImageIcon("tank2down.png");
	ImageIcon leftpic2 = new ImageIcon("tank2left.png");
	ImageIcon menueimg = new ImageIcon("menuepage.jpg");
	

	 //Blocks/Tiles:
	ImageIcon pic = new ImageIcon("block1.png");
	ImageIcon pic2 = new ImageIcon("block2.png");
     
    CardLayout cards;
	
	JPanel  menuCard, gameCard, escapeCard, winnerCard, controlCard;


	Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
	
	JButton testButton = new JButton("Play");
	JButton actionBtn1= new JButton("Start");
   	JButton restartJ = new JButton("Restart");
   	JButton controls = new JButton("Controls");
   	JButton backMenue = new JButton("Back to Menue");
   	JButton resumeGame = new JButton("Resume");

	private Timer myTimer= new Timer( 30, this );
	 
	public  MyPanel() { 			//initial all the variables
	  

		//Button Colours
	  actionBtn1.setBackground(Color.black);
	  actionBtn1.setForeground(Color.white);
	  restartJ.setBackground(Color.black);
	  restartJ.setForeground(Color.white);
	  testButton.setBackground(Color.white);
	  testButton.setForeground(Color.black);
	  testButton.setContentAreaFilled(false);
	  controls.setBackground(Color.white);
	  controls.setForeground(Color.black);
	  controls.setContentAreaFilled(false);
	  resumeGame.setBackground(Color.black);
	  resumeGame.setForeground(Color.white);
	  backMenue.setBackground(Color.black);
	  backMenue.setForeground(Color.white);
          
           // Constructor: set background color to white set up listeners to respond to mouse actions
        
        setBackground(new Color(0,0,0));				 
		addKeyListener(this);	
        cards=new CardLayout();
		this.setLayout(cards);

        menuCard = new JPanel(){
		  
		  public void paintComponent(Graphics gr){  // painting
		      super.paintComponent(gr);
				setBackground(new Color(0,0,0));
    	 		testButton.setBounds(525, 50, 200, 45);
    	 		controls.setBounds(525, 100, 200, 45);
    	 		gr.drawImage(menueimg.getImage(),0,0, null);
		  }
		};
        
        gameCard = new JPanel(){
           public void paintComponent(Graphics gr){  // painting
		      	super.paintComponent(gr);
		      	setBackground(new Color(0,0,0));

    	 		actionBtn1.setBounds(1155, 310, 120, 50);
    	 		restartJ.setBounds(1155, 360, 120, 50);
				
				for (int i=0; i<testArray.length; i++){
				 for (int k=0; k<testArray[0].length; k++){
					 if (testArray [i][k] == 2)
						gr.drawImage(pic2.getImage(),k*58, i*58, null );
				 	}
				}
				gr.setColor(Color.RED);
				gr.fillRect(bulletX+10,bulletY+12,5,5);
				gr.fillRect(bulletX2+10,bulletY2+12,5,5);
				
				//Player 1:
				if (count==2)
					gr.drawImage(uppic.getImage(),x, y, null );
				else if (count==0) {
					gr.drawImage(rightpic.getImage(),x, y, null );
				}
				else if (count==1) {
					gr.drawImage(downpic.getImage(),x, y, null );
				}
				else if (count==3) {
					gr.drawImage(leftpic.getImage(),x, y, null );
				}
				//Player2:
				if (player2count==2)
					gr.drawImage(uppic2.getImage(),player2X, player2Y, null );
				else if (player2count==0) {
					gr.drawImage(rightpic2.getImage(),player2X, player2Y, null );
				}
				else if (player2count==1) {
					gr.drawImage(downpic2.getImage(),player2X, player2Y, null );
				}
				else if (player2count==3) {
					gr.drawImage(leftpic2.getImage(),player2X, player2Y, null );
				}
				for (int i=0; i<testArray.length; i++){
				 for (int k=0; k<testArray[0].length; k++){
					 if (testArray [i][k] == 1)
						gr.drawImage(pic.getImage(),k*58, i*58, null );
					
				 }
				}
				//gr.drawString("Lives: ", 877, 15);

				gr.setColor(Color.WHITE);
				gr.fillRect(1195,100+(200-play1lives),29,200-(200-play1lives));
				gr.drawRect(1195,100,29,200);
				gr.fillRect(1195,420,29,200-(200-play2lives));
				gr.drawRect(1195,420,29,200);
				gr.setColor(Color.BLUE);
				gr.drawString("Blocks", 1190, 45);
				gr.drawString(""+destroyCount, 1205, 65);
				gr.setColor(Color.RED);
				gr.drawString(""+destroyCount2, 1205, 660);
				gr.drawString("Blocks", 1190, 680);
			}	
		};

		escapeCard = new JPanel(){ 
			public void paintComponent(Graphics gr){  // painting
		      super.paintComponent(gr);
		      setBackground(new Color(0,0,0));
		      backMenue.setBounds(525, 350, 200, 45);
		      resumeGame.setBounds(525, 400, 200, 45);
		  }
        };

       winnerCard = new JPanel(){ 
			public void paintComponent(Graphics gr){  // painting
		      super.paintComponent(gr);
		      setBackground(new Color(0,0,0));
		      gr.drawString(winner, 600, 45);
		      backMenue.setBounds(525, 350, 200, 45);
		  }
        };

		controlCard = new JPanel(){ 
			public void paintComponent(Graphics gr){  // painting
		      super.paintComponent(gr);
		      setBackground(new Color(0,0,0));
		      gr.drawString(winner, 600, 45);

		  }
        };
		//gameCard = new JPanel(){

		//}

		menuCard.setLayout(new FlowLayout());
		menuCard.add(testButton);
		menuCard.add(controls);

		escapeCard.setLayout(new FlowLayout());
		winnerCard.setLayout(new FlowLayout());

		escapeCard.add(backMenue);
		escapeCard.add(resumeGame);

		winnerCard.add(backMenue);

		gameCard.add(restartJ);
		gameCard.add(actionBtn1);

     	for (int i=0; i<testArray.length; i++){
		 	for (int k=0; k<testArray[0].length; k++){
				testArray [i][k] = (int)((Math.random()*2)+1);
				System.out.print(testArray [i][k]+" ");
			}
			System.out.println(" ");
		} 
	testArray [0][0] = 2;	
	testArray [1][0] = 1;
	testArray [0][1] = 1;
	testArray [1][1] = 1; 
	testArray [11][19] = 1;
	testArray [11][18] = 1;
	testArray [12][18] = 1;
	testArray [12][19] = 2;
	
	testButton.addActionListener(this);
	controls.addActionListener(this);
	actionBtn1.addActionListener(this);
	restartJ.addActionListener(this);
	backMenue.addActionListener(this);
	resumeGame.addActionListener(this);

	actionBtn1.addKeyListener(this);
	restartJ.addKeyListener(this);
	addKeyListener(this);

      add(menuCard, "ManuName");
  	  add(gameCard, "GameName");
  	  add(escapeCard, "EscapeName");
	  add(winnerCard, "WinName");
	
	}



    public void keyPressed( KeyEvent ev ) {
		System.out.println(ev.getKeyCode() );
		if (ev.getKeyCode()==68){
			count = 0;
			//Move right key: D
		}
		if (ev.getKeyCode()==83){			
			count = 1;
			//Move down key: S
		}
		if (ev.getKeyCode()==87){			
			count = 2;
			//Move up key: W
		}
		if (ev.getKeyCode()==65){
			count = 3;
			//Move left key: A
		}
		if (count3 == 0) {
			if (ev.getKeyCode()==80){
				blueTrigger = true;
				count3 = 20;
				//Shoot key: P
			}
		}
		if (destroyCount>0) {
			
			if (ev.getKeyCode()==79){
				if	(destroyCount>0){
					placeBlock = true;
				//Place blocks key: O
				}
			}
		}
		if (ev.getKeyCode()==39){
			player2count = 0;
		}
		if (ev.getKeyCode()==40){
			player2count = 1;
		}
		if (ev.getKeyCode()==38){
			player2count = 2;
		}
		if (ev.getKeyCode()==37){
			player2count = 3;
		}
		if (escapeCount==1) {
			if (ev.getKeyCode()==27){
				cards.show(this, "EscapeName");
				repaint();
			//Place blocks key: O
			}
		}
		
		if (count4 == 0) {
			if (ev.getKeyCode()==109){
				redTrigger = true;
				count4 = 20;
				//Shoot key: -
			}
		}
		if (ev.getKeyCode()==106){
			if (destroyCount2>0){
				placeBlock2 = true;
			}
		}		
	}
    public void keyReleased( KeyEvent ev ){
	
	}
    public void keyTyped( KeyEvent e ){}
	
    public void actionPerformed( ActionEvent e ){ 

        if (e.getSource()==myTimer){
		// timer events
    	
        if (restart==1) {
		    x= 0;
		    y = 0;
			player2X=1130;
			player2Y=715;
		    for (int i=0; i<testArray.length; i++){
				for (int k=0; k<testArray[0].length; k++)
					testArray [i][k] = (int)((Math.random()*2)+1);
				} 
			testArray [0][0] = 2;	
			testArray [1][0] = 1;
			testArray [0][1] = 1;
			testArray [1][1] = 1; 
			testArray [11][19] = 1;
			testArray [11][18] = 1;
			testArray [12][18] = 1;
			testArray [12][19] = 2;

			destroyCount = 0;
			destroyCount2 = 0;

			play1lives = 200;
			play2lives = 200;
        }
        restart = 0;

   		//Bullet reload timer:
   		if (0<count3) {
   			count3--;
   		}
		if (0<count4) {
   			count4--;
   		}

   		index = 0;
   		tileCount = 0;

   		for (int i=0; i<testArray.length; i++){
			for (int k=0; k<testArray[0].length; k++){
				if (testArray [i][k] == 1) {
					index++;
					}
				if (testArray [i][k] == 2) {
					tileCount++;
					}
				}
			} 

		wall = new Rectangle[index];
		whiteTile = new Rectangle[tileCount];

		rowArr = new int [index];
		colArr = new int [index];
		rowArr2 = new int [tileCount];
		colArr2 = new int [tileCount];
		
		index = 0;
		tileCount = 0;
		    
		for (int i=0; i<testArray.length; i++){
			for (int k=0; k<testArray[0].length; k++){
				if (testArray [i][k] == 1) {
					wall [index] = new Rectangle(k*58,i*58,59,59);
					rowArr [index] = i;
					colArr [index] = k;
					index++;
				   }
				 if (testArray [i][k] == 2) {
					whiteTile [tileCount] = new Rectangle(k*58,i*58,59,59);
					rowArr2 [tileCount] = i;
					colArr2 [tileCount] = k;
					tileCount++;
				   }
				}
			}


			//Player blocks:
        	back = new Rectangle(x,y+6,10,17);
        	front = new Rectangle(x+19,y+5,10,17);
        	up = new Rectangle(x+6,y,15,10);
        	down = new Rectangle(x+6,y+19,15,10);
        	middle = new Rectangle(x+10,y+12,5,5);

        	play1 = new Rectangle(x,y,29,29);
			
			//Player 2 blocks:
			back2 = new Rectangle(player2X,player2Y+6,10,17);
        	front2 = new Rectangle(player2X+19,player2Y+5,10,17);
        	up2 = new Rectangle(player2X+7,player2Y,15,10);
        	down2 = new Rectangle(player2X+7,player2Y+19,15,10);
        	middle2 = new Rectangle(player2X,player2Y+12,5,5);

        	play2 = new Rectangle(player2X,player2Y,29,29);

        	
        	boolean right = true, left = true, upp = true, downn = true;
			
			boolean right2 = true, left2 = true, upp2 = true, downn2 = true;

        	//Collision detection:
        	for (int i=0; i<wall.length; i++) {
        		if (front.intersects(wall[i]))
        			right = false;
        		if (back.intersects(wall[i]))
        			left = false;
        		if (up.intersects(wall[i]))
        			upp = false;
        		if (down.intersects(wall[i]))
        			downn = false;
				if (front2.intersects(wall[i]))
        			right2 = false;
        		if (back2.intersects(wall[i]))
        			left2 = false;
        		if (up2.intersects(wall[i]))
        			upp2 = false;
        		if (down2.intersects(wall[i]))
        			downn2 = false;
        	}
		
			if (front.intersects(play2))
				right = false;
			if (down.intersects(play2))
				downn = false;
			if (up.intersects(play2))
				upp = false;
			if (back.intersects(play2))
				left = false;

			if (front2.intersects(play1))
				right2 = false;
			if (down2.intersects(play1))
				downn2 = false;
			if (up2.intersects(play1))
				upp2 = false;
			if (back2.intersects(play1))
				left2 = false;

        	//Detecting player position:
        	for (int i=0; i<whiteTile.length; i++) {
        		if (middle.intersects(whiteTile[i]))
        			changeCount = i;
				if (middle2.intersects(whiteTile[i]))
					changeCount2 = i;
        	}

        	//Placing blocks:
        	if (placeBlock) {
        		if (count == 0 && colArr2[changeCount]<19&&testArray[rowArr2[changeCount]][colArr2[changeCount]+1]!=1) {
        			testArray[rowArr2[changeCount]][colArr2[changeCount]+1]=1;
        			destroyCount--;
        		}
        		if (count == 1 && rowArr2[changeCount]<12&&testArray[rowArr2[changeCount]+1][colArr2[changeCount]]!=1) {
        			testArray[rowArr2[changeCount]+1][colArr2[changeCount]]=1;
        			destroyCount--;
        		}
        		if (count == 2 && rowArr2[changeCount]>0&&testArray[rowArr2[changeCount]-1][colArr2[changeCount]]!=1) {
        			testArray[rowArr2[changeCount]-1][colArr2[changeCount]]=1;
        			destroyCount--;
        		}
        		if (count == 3 && colArr2[changeCount]>0&&testArray[rowArr2[changeCount]][colArr2[changeCount]-1]!=1) {
        			testArray[rowArr2[changeCount]][colArr2[changeCount]-1]=1;
        			destroyCount--;
        		}
        	}
        	placeBlock = false;
			
			if (placeBlock2) {
        		if (player2count == 0 && colArr2[changeCount2]<19&&testArray[rowArr2[changeCount2]][colArr2[changeCount2]+1]!=1) {
        			testArray[rowArr2[changeCount2]][colArr2[changeCount2]+1]=1;
					destroyCount2--;
        		}
        		if (player2count == 1 && rowArr2[changeCount2]<12&&testArray[rowArr2[changeCount2]+1][colArr2[changeCount2]]!=1) {
        			testArray[rowArr2[changeCount2]+1][colArr2[changeCount2]]=1;
					destroyCount2--;
        		}
        		if (player2count == 2 && rowArr2[changeCount2]>0&&testArray[rowArr2[changeCount2]-1][colArr2[changeCount2]]!=1) {
        			testArray[rowArr2[changeCount2]-1][colArr2[changeCount2]]=1;
					destroyCount2--;
        		}
        		if (player2count == 3 && colArr2[changeCount2]>0&&testArray[rowArr2[changeCount2]][colArr2[changeCount2]-1]!=1) {
        			testArray[rowArr2[changeCount2]][colArr2[changeCount2]-1]=1;
					destroyCount2--;
        		}
        	}
        	placeBlock2 = false;

        	//Player actions:
        	if (x<1130&&count==0&&right)
        		x+=5;
        	if (y<705&&count==1&&downn)
				y+=5;
			if (y>0&&count==2&&upp)
				y-=5;
			if (x>0&&count==3&&left)
				x-=5;
			
			//Player 2 actions:
			if (player2X<1130&&player2count==0&&right2)
        		player2X+=5;
        	if (player2Y<705&&player2count==1&&downn2)
				player2Y+=5;
			if (player2Y>0&&player2count==2&&upp2)
				player2Y-=5;
			if (player2X>0&&player2count==3&&left2)
				player2X-=5;

			bullet = new Rectangle (bulletX+10,bulletY+12,5,5);
			bullet2 = new Rectangle (bulletX2+10,bulletY2+12,5,5);

			//Bullet actions:
			if (count2==0&&blueTrigger)
        		bulletX+=12;
        	
        	if (count2==1&&blueTrigger)
				bulletY+=12;
			
			if (count2==2&&blueTrigger)
				bulletY-=12;
			
			if (count2==3&&blueTrigger)
				bulletX-=12;

			if (bulletX>1130||bulletY>705||bulletY<0||bulletX<0)
        		blueTrigger = false;
			
			if (blueTrigger==false) {
				bulletX = x;
				bulletY = y;
				count2 = count;
			}
			
			if (count5==0&&redTrigger)
        		bulletX2+=12;
        	
        	if (count5==1&&redTrigger)
				bulletY2+=12;
			
			if (count5==2&&redTrigger)
				bulletY2-=12;
			
			if (count5==3&&redTrigger)
				bulletX2-=12;

			if (bulletX2>1130||bulletY2>705||bulletY2<0||bulletX2<0)
        		redTrigger = false;
			
			if (redTrigger==false) {
				bulletX2 = player2X;
				bulletY2 = player2Y;
				count5 = player2count;
			}
			
			if (bullet.intersects(play2)) {
				play2lives-=10;
				blueTrigger = false;
			}
			if (bullet2.intersects(play1)) {
				play1lives-=10;
				redTrigger = false;
			}

			//Destroy blocks:
			for (int i=0; i<wall.length; i++) {
        		if (bullet.intersects(wall[i])){
        			blueTrigger = false;
        			testArray [rowArr[i]][colArr[i]]=2;
        			destroyCount++;
        		}
				if (bullet2.intersects(wall[i])) {
					redTrigger = false;
        			testArray [rowArr[i]][colArr[i]]=2;
        			destroyCount2++;
				}
        	}
        	
			
        	//if (lives==0) {
        	//	System.exit(0);
        	//}
        	if (play1lives==0||play2lives==0) {
        		if (play1lives==0) {
        			winner = "Red Wins!";
        		}
        		else if (play2lives==0) {
        			winner = "Blue Wins!";
        		}
        		restart = 1;
        		cards.show(this, "WinName");
        		repaint();
        	}


        repaint();		
		}
		else{
			JButton b= (JButton)e.getSource();	   
			if (b.getText().equals("Start")){
				 //   code for button aaaa event goes here.....................
			    	myTimer.start();
			}
			else if (b.getText().equals("Restart")){
			   //   code for button Exit goes here.....................
				restart = 1;
			}
			else if (b.getText().equals("Play")){
				cards.show(this, "GameName");
				repaint();
				escapeCount = 1;
			}
			else if (b.getText().equals("Back to Menue")){
				cards.show(this, "ManuName");
				escapeCount = 0;
				myTimer.stop();
				repaint();
			}
			else if (b.getText().equals("Resume")){
				cards.show(this, "GameName");
				myTimer.start();
				repaint();
			}

		}	   
    }// end actionPerformed

}