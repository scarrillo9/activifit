package gui_package;

import javax.swing.*;
import javax.swing.border.Border;

import action_package.*;
import chart_package.Chart;
import chart_package.HeartChart;
import chart_package.StepChart;
import chart_package.tempChart;
import user_package.UserDatabase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.Calendar;

public class Screen {

	/* Resources  */
	/* Resolutions */
	// Resolution taken from an iPhone5 320x568 pixels
	public final static int rx = 320; // horizontal resolution
	public final static int ry = 568; // vertical resolution

	/* Colors */
	public static Color red_alizarin = new Color(231, 76, 60);
	public static Color green_emerald = new Color(46, 204, 113);
	public static Color blue_peterriver = new Color(52, 152, 219);
	public static Color blue_belizehole = new Color(41, 128, 185);
	public static Color purple_amethyst = new Color(155, 89, 182);
	public static Color gray_concrete = new Color(149, 165, 166);
	public static Color black_midnight = new Color(44, 62, 80);
	public static Color white_clouds = new Color(236, 240, 241);
	
	/* Global Variables */
	public final static UserDatabase globalDatabase = new UserDatabase();
	public static DataGenerator globalGen = null; //empty until called
	
	/* Time Variables */
	static Calendar rightNow = Calendar.getInstance();
	static int hour = rightNow.get(Calendar.HOUR_OF_DAY);
	public static int CURRENT_TIME = hour;
	
	/** Main Method */
	public static void main(String args[]) {
		// Test each screen individually
			
		// screen_login();
		// screen_register();
		// screen_home();
		// screen_heart();
		// screen_temperature();
		// screen_steps();
		// screen_sleep();
		// screen_share();
		// screen_logout();
		// screen_about();
		// screen_help();
		
	}// end main

	/** Methods */
	/* --Screens-- */
	// constructor
	public Screen() {
		screen_login();
	}

	/** Login Screen */
	public static void screen_login() {
		Border border_default = BorderFactory.createLineBorder(Color.BLACK);
		JFrame F = new JFrame("Log-In");

		// Panel (BOX LAYOUT)
		JPanel listP = new JPanel();
		listP.setLayout(new BoxLayout(listP, BoxLayout.PAGE_AXIS));

		// Panel bottom about us (BOX LAYOUT) ----Method?----
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		// Create Buttons
		JButton button_about = new JButton("About Us");
		JButton button_help = new JButton("Help");
		// Add to panel with glue between two buttons
		panel.add(button_about);
		panel.add(Box.createHorizontalGlue());
		panel.add(button_help);
		panel.setOpaque(false);
		// ----Method?---- Panel bottom about us (BOX LAYOUT)

		// Login Text Field
		JTextField t_login = new JTextField(20);
		t_login.setBorder(border_default); // login text field
		t_login.setMaximumSize(new Dimension(200, 20)); // login dimension

		// Labels
		JLabel l_sign = new JLabel("Sign In");
		l_sign.setFont(new Font(null, Font.BOLD, 20));
		l_sign.setForeground(black_midnight);
		JLabel l_enter = new JLabel("(enter your email)");
		l_enter.setForeground(gray_concrete);

		// Buttons
		JButton button_accept = new JButton("DONE");

		// Add components and span.
		listP.add(Box.createRigidArea(new Dimension(0, ry / 4)));
		listP.add(l_sign); // Sign in label
		listP.add(Box.createRigidArea(new Dimension(0, 20)));
		listP.add(t_login); // Text Field
		listP.add(Box.createRigidArea(new Dimension(0, 6)));
		listP.add(l_enter); // Enter your email
		listP.add(Box.createRigidArea(new Dimension(0, 50)));
		listP.add(button_accept);

		// Align every component (-.-)
		l_sign.setAlignmentX(Component.CENTER_ALIGNMENT);
		t_login.setAlignmentX(Component.CENTER_ALIGNMENT);
		l_enter.setAlignmentX(Component.CENTER_ALIGNMENT);
		button_accept.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Actions
		LoginActionListener action = new LoginActionListener(t_login, F);
		button_accept.addActionListener(action);
		button_about.addActionListener(new LoginAboutUsActionListener(F));
		button_help.addActionListener(new LoginHelpActionListener(F));
		// Change Color Action
		t_login.addMouseListener(new LoginMouseAdapter(t_login));
		
		// Frames
		F.add(CustomJPanels.bannerPanel(), BorderLayout.NORTH);
		F.add(listP); // First row
		F.add(panel, BorderLayout.SOUTH);
		viewFrame(F);
	}// End sign-in screen

	/** Screen Register */
	public static void screen_register() {
		Border border_default = BorderFactory.createLineBorder(Color.BLACK);
		JFrame F = new JFrame("Register");

		String[] labels = { "Name: ", "Age: ", "Weight: ", "Height: ", "Active Id: ", "Email: " };
		int numPairs = labels.length;

		// Create an array of JTextFields 
		JTextField[] t_array = new JTextField[numPairs];
		
		// Container Panel (Box Layout)
		JPanel containerP = new JPanel();
		containerP.setLayout(new BoxLayout(containerP, BoxLayout.PAGE_AXIS));
				
		// Create form panel (Spring Layout)
		JPanel springP = new JPanel(new SpringLayout());
		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			springP.add(l);
			t_array[i] = new JTextField(20); //new field
			l.setLabelFor(t_array[i]);
			springP.add(t_array[i]);
			t_array[i].setBorder(border_default); //Border color
 			t_array[i].setMaximumSize(new Dimension(300, 6)); //Max border
			t_array[i].addMouseListener(new LoginMouseAdapter(t_array[i])); //Action
			if(i == numPairs-1){ //Change email label
				t_array[i].setText(LoginActionListener.returnValidEmail() );
			}
		}
		// makeGrid(panel, nCols, nRows, init x, init y, xpad, ypad)
		SpringUtilities.makeGrid(springP, numPairs, 2, 6, 6, 6, 20);

		// Bottom panels
		JPanel bottomP = new JPanel(); // Will use 2 rows
		bottomP.setLayout(new BoxLayout(bottomP, BoxLayout.Y_AXIS)); // Vertical
		
		// Create buttons
		JButton button_accept = new JButton("Accept");
		JButton button_back = new JButton("BACK");
		JButton button_help = new JButton("HELP");

		// Navigation panel for last row
		JPanel navP = new JPanel(); // Will have back and help buttons
		navP.setLayout(new BoxLayout(navP, BoxLayout.X_AXIS)); // Horizontal

		// Add springP to ContainerP (for spacing options)
		containerP.add(Box.createRigidArea(new Dimension(0, 35))); //Add space
		containerP.add(springP); //Add form panel
		containerP.add(Box.createRigidArea(new Dimension(0, 35)));
		
		// Add buttons, properties and spaces to Bottom panel
		bottomP.add(button_accept);
		button_accept.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomP.add(Box.createRigidArea(new Dimension(0, 50))); // rigid area to
		// Add NavPanel 	
		navP.add(button_back);
		navP.add(Box.createHorizontalGlue());
		navP.add(button_help);
		// Add last row panel to bottom panel
		bottomP.add(navP); 
		
		// Actions
		RegisterAcceptActionListener action = new RegisterAcceptActionListener(t_array, F);
		button_accept.addActionListener(action);
		button_back.addActionListener(new RegisterBackActionListener(F));
		button_help.addActionListener(new RegisterHelpActionListener(F));
		
		// Set panels to the Frame
		F.add(CustomJPanels.bannerPanel(), BorderLayout.NORTH);
		F.add(containerP, BorderLayout.EAST);
		F.add(bottomP, BorderLayout.SOUTH);
		viewFrame(F);
	}// end screen_register

	/** HomePage Screen */
	public static void screen_home() {
		JFrame F = new JFrame("Homepage");
		// Panel for information (BOX LAYOUT)
		JPanel P = new JPanel();
		P.setLayout(new BoxLayout(P, BoxLayout.PAGE_AXIS));
		P.add(Box.createRigidArea(new Dimension(0, 50)));
		// Panel for Buttons (FLOW LAYOUT)
		JPanel buttonP = new JPanel();
		buttonP.setLayout(new FlowLayout());
		
		// Fetch from database
		String currentUser = globalDatabase.getCurrentUser().getName();
		
		//Labels
		String[] labels = { "Hello "+currentUser, "Time: "+CURRENT_TIME+" Hours", "Today's Daily Feed"
				, "THIS WILL BE A GRAPH(hopefully)", };
		for (int i = 0; i < labels.length; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			P.add(l);
			l.setAlignmentX(Component.CENTER_ALIGNMENT);
			// if statement to span everything BUT last component
			if (i < labels.length - 1) {
				P.add(Box.createRigidArea(new Dimension(0, 33)));
			} else {
				P.add(Box.createRigidArea(new Dimension(0, 10)));
			}
		}
		// Actions
		HomeMenuActionListener action = new HomeMenuActionListener(F);
		
		// Buttons
		String[] buttons = { "HEART", "STEPS", "TEMP", "SLEEP", "SHARE" };
		for (int i = 0; i < buttons.length; i++) {
			JButton b = new JButton(buttons[i]);
			b.setMargin(new java.awt.Insets(1, 6, 1, 6)); // Format
			action = new HomeMenuActionListener(F, i);
			b.addActionListener(action);
			buttonP.add(b);
		}
		// Graph Image
		JLabel graph = new JLabel();
		graph.setIcon(insertWebIconScaled("http://i.imgur.com/i6svYaH.png", 200, 200));
		graph.setAlignmentX(Component.CENTER_ALIGNMENT);
		P.add(graph);

		// add panels to frame
		F.add(CustomJPanels.bannerPanel(), BorderLayout.NORTH);
		F.add(P, BorderLayout.CENTER);
		F.add(buttonP, BorderLayout.SOUTH);
		viewFrame(F); // displays the frame
	}// end Screen

	/* Heart Rate Screen */
	public static void screen_heart() {
		JFrame F = new JFrame("Heartbeat");
		JPanel background = CustomJPanels.backgroundPanel("http://i.imgur.com/fMzXlWC.png", 3);
		
		//Activity Panel
		JPanel banner = CustomJPanels.activityBannerPanel("Heart Rate", black_midnight);
		//Display Panel
		JPanel displayP = new JPanel();
		displayP.setLayout(new BoxLayout(displayP, BoxLayout.X_AXIS));
		JButton button_week = new JButton("Change to Week");
		JButton button_month = new JButton("Change to Month");
		displayP.add(button_week);
		displayP.add(Box.createHorizontalGlue());
		displayP.add(button_month);
		displayP.setOpaque(true);

		// Local Variables from global generator
		int restHB = globalGen.heart.getRestHR();
		String status = globalGen.heart.getRestHeartStatus(restHB);
		
		// Labels
		JLabel c1 = new JLabel("Current Rate: "+restHB); 
		JLabel c2 = new JLabel("Health State: "+status); 
		
		background.add(banner);
		background.add(Box.createRigidArea(new Dimension(0, ry / 6)));
		background.add(c1);
		c1.setAlignmentX(Component.CENTER_ALIGNMENT);
		background.add(Box.createRigidArea(new Dimension(0, 20)));
		c2.setAlignmentX(Component.CENTER_ALIGNMENT);
		background.add(c2);
		background.add(Box.createRigidArea(new Dimension(0, 200)));
		//Display Panel --Week/Month--
		background.add(displayP);
		
		//Actions
		WeekHeartActionListener action = new WeekHeartActionListener(F);
		button_week.addActionListener(action);
		MonthHeartActionListener action2 = new MonthHeartActionListener(F);
		button_month.addActionListener(action2);
		
		F.add(background, BorderLayout.CENTER);
		F.add(CustomJPanels.navPanel(F), BorderLayout.SOUTH);
		viewFrame(F);
	}

	/** Temperature Screen */
	public static void screen_temperature() {
		JFrame F = new JFrame("Temperature");
		JPanel background = CustomJPanels.backgroundPanel("http://i.imgur.com/vZ0rtrH.png", 3);

		// Activity Banner
		JPanel banner = CustomJPanels.activityBannerPanel("Body Temperature", red_alizarin);
		// Display Panel
		JPanel displayP = new JPanel();
		displayP.setLayout(new BoxLayout(displayP, BoxLayout.X_AXIS));
		JButton button_week = new JButton("Change to Week");
		JButton button_month = new JButton("Change to Month");
		displayP.add(button_week);
		displayP.add(Box.createHorizontalGlue());
		displayP.add(button_month);
		displayP.setOpaque(true);

		// Local Variables from global generator
		double currentTemp = globalGen.temp.getCurrentTemperature(CURRENT_TIME);
		String status = globalGen.temp.getTempStatus(currentTemp);
		
		// Labels
		JLabel current_temp = new JLabel();
		current_temp.setText(String.format("<html>Current Temperature:<br>%.1f�C</html>", currentTemp, SwingConstants.CENTER));
		JLabel current_temp_state = new JLabel("<html>Body Temperature State:<br>"+status+"</html>", SwingConstants.CENTER); 

		background.add(banner);
		background.add(Box.createRigidArea(new Dimension(0, 50)));
		background.add(current_temp);
		//current_temp.setAlignmentX(Component.CENTER_ALIGNMENT);
		background.add(Box.createRigidArea(new Dimension(0, 75)));
		background.add(current_temp_state);
		current_temp_state.setAlignmentX(Component.CENTER_ALIGNMENT);
		background.add(Box.createRigidArea(new Dimension(0, 100)));
		background.add(displayP);

		// Actions
		WeekTempActionListener action = new WeekTempActionListener(F);
		button_week.addActionListener(action);
		MonthTempActionListener action2 = new MonthTempActionListener(F);
		button_month.addActionListener(action2);

		F.add(background, BorderLayout.CENTER);
		F.add(CustomJPanels.navPanel(F), BorderLayout.SOUTH);
		viewFrame(F);
	}

	/* Steps Screen */
	public static void screen_steps() {
		JFrame F = new JFrame("Steps");
		JPanel background = CustomJPanels.backgroundPanel("http://i.imgur.com/rFeyEtS.png", 3);
		
		// Activity Banner Panel
		JPanel banner = CustomJPanels.activityBannerPanel("Steps", purple_amethyst);
		// Display Panel
		JPanel displayP = new JPanel();
		displayP.setLayout(new BoxLayout(displayP, BoxLayout.X_AXIS));
		JButton button_week = new JButton("Change to Week");
		JButton button_month = new JButton("Change to Month");
		displayP.add(button_week);
		displayP.add(Box.createHorizontalGlue());
		displayP.add(button_month);
		displayP.setOpaque(true);

		// Local Variables from global generator
		DecimalFormat df = new DecimalFormat("#.##");
		double currentDistance = globalGen.steps.getCurrentDistance(CURRENT_TIME);
		int currentSteps = (int) globalGen.steps.getCurrentSteps(currentDistance);
		
		// Labels
		JLabel current_steps = new JLabel("<html>Current Number of Steps: <br>"+currentSteps+"</html>"); 
		JLabel current_steps_state = new JLabel("<html>Current Distance Traveled:<br>"+df.format(currentDistance)+"</html>"); 

		background.add(banner);
		background.add(Box.createRigidArea(new Dimension(0, 50)));
		background.add(current_steps);
		current_steps.setAlignmentX(Component.CENTER_ALIGNMENT);
		background.add(Box.createRigidArea(new Dimension(0, 75)));
		background.add(current_steps_state);
		current_steps_state.setAlignmentX(Component.CENTER_ALIGNMENT);
		background.add(Box.createRigidArea(new Dimension(0, 100)));
		background.add(displayP);

		// Actions
		WeekStepsActionListener action = new WeekStepsActionListener(F);
		button_week.addActionListener(action);
		MonthStepsActionListener action2 = new MonthStepsActionListener(F);
		button_month.addActionListener(action2);

		F.add(background, BorderLayout.CENTER);
		F.add(CustomJPanels.navPanel(F), BorderLayout.SOUTH);
		viewFrame(F);
	}

	/* Sleep Screen */
	public static void screen_sleep() {
		JFrame F = new JFrame("Sleep");

		JPanel background = CustomJPanels.backgroundPanel("http://i.imgur.com/1BxhCZb.jpg", 2);

		// Local Variables from global generator
		int totalSleep= globalGen.sleep.getTotalSleep();
		double cycles =  globalGen.sleep.getTotalCycles();
		
		
		// Labels
		JLabel label_totaltime = new JLabel("Total Time:   "+totalSleep);
		JLabel label_cycles = new JLabel("Total Cycles: "+cycles);
		
		

		// Activity Banner Panel
		JPanel banner = CustomJPanels.activityBannerPanel("Sleep", white_clouds);
		// Display Panel
		JPanel displayP = new JPanel();
//		displayP.setLayout(new BoxLayout(displayP, BoxLayout.X_AXIS));
//		JButton button_week = new JButton("Change to Week");
//		JButton button_month = new JButton("Change to Month");
//		displayP.add(button_week);
//		displayP.add(Box.createHorizontalGlue());
//		displayP.add(button_month);
//		displayP.setOpaque(false);

		background.add(banner);
		background.add(Box.createRigidArea(new Dimension(0, 50)));
		
		background.add(Box.createRigidArea(new Dimension(0, 75)));

//		background.add(rem);
//		background.add(light);
//		background.add(deep);
		// current_temp_state.setAlignmentX(Component.CENTER_ALIGNMENT);
		background.add(Box.createRigidArea(new Dimension(0, 100)));
//		background.add(displayP);

		// Actions
//		WeekSleepActionListener action = new WeekSleepActionListener(F);
//		button_week.addActionListener(action);
//		MonthSleepsActionListener action2 = new MonthSleepsActionListener(F);
//		button_month.addActionListener(action2);

		F.add(background, BorderLayout.CENTER);
		F.add(CustomJPanels.navPanel(F), BorderLayout.SOUTH);
		viewFrame(F);
	}

	/* Custom View Screen */
	public static void screen_customView(int opt){
		JFrame F = new JFrame("Custom View");
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		// Buttons
		JButton button_back = new JButton("BACK");
		
		//Graph Panel
		/** OPT =>  
		 * Heart Day Week Month 0,1,2
		 * Steps Week Month 3,4
		 * Temp  Week Month 5,6
		 * Sleep Week Month 7,8
		 * Extras:
		 * Heart All xx*/
		
		JPanel graph = new JPanel();
		
		
		switch (opt){
		case 0: //Day Graph Heart
			graph = HeartChart.drawChart(4);
			break;
		case 1: //Weekly Graph Heart
			graph = HeartChart.drawChart(2);
			break;
		case 2: //Monthly Graph Heart
			graph = HeartChart.drawChart(1); 
			break;
		case 3: //Weekly Graph Steps
			graph = StepChart.drawChart(2);
			break;
		case 4: //Monthly Graph Steps
			graph = StepChart.drawChart(4); //<- intentionally
			break;
		case 5: //Weekly Graph Temperature
			graph = tempChart.drawChart(2);
			break; //<-Never forger a break OK? THANKS
		case 6: //Month
			graph = tempChart.drawChart(1);
			break;
		case 7: //Week Sleep (2)
			break;
		case 8: //Month Sleep (1)
		}//end switch
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            //Chart.heartChart(4); //run something, ask the professor why
            HeartChart.drawChart(4);
            }
        });
		
		//Add Components to container
		container.add(graph);
		container.add(Box.createVerticalGlue()); // add glue
		container.add(button_back);
		button_back.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Actions
		HomeMenuActionListener action = new HomeMenuActionListener(F, 0);;
		if(opt <=2 ) action = new HomeMenuActionListener(F, 0); //hb
		else if (opt == 3 || opt == 4) action = new HomeMenuActionListener(F, 1); 
		else if (opt == 5 || opt == 6) action = new HomeMenuActionListener(F, 2);
		else if (opt == 7 || opt == 8) action = new HomeMenuActionListener(F, 3);
		button_back.addActionListener(action);
		
		F.add(container, BorderLayout.CENTER);
		viewFrame(F);
	}
	
	/* Share Screen */
	public static void screen_share() {
		// Icons URLs
		String url_f = ("http://icons.iconarchive.com/icons/mysitemyway/blue-jeans-social-media/128/facebook-icon.png"); // Facebook
		String url_s = ("http://imavex.vo.llnwd.net/o18/clients/imavex/images/Services-ClubWorksite/service-icon-share.png"); // share

		JFrame F = new JFrame("Share");
		// Panels (I'm getting good at this)
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Vertical
																			// Container
		JPanel postP = new JPanel();
		postP.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel iconsP = new JPanel();
		iconsP.setLayout(new BoxLayout(iconsP, BoxLayout.X_AXIS));
		JPanel sharing_iconP = new JPanel();
		sharing_iconP.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel backP = new JPanel();
		backP.setLayout(new FlowLayout(FlowLayout.LEADING));

		// Labels and icons
		JLabel label1 = new JLabel("POST TO...");
		postP.add(label1);

		// Add icons to panel #FROM HERE
		JLabel icon_f = getWebIconScaledLabel(url_f, 50, 50);
		JLabel icon_t = getWebIconScaledLabel(url_f, 50, 45);
		JLabel icon_m = getWebIconScaledLabel(url_f, 50, 50);

		iconsP.add(icon_f);
		iconsP.add(icon_t);
		iconsP.add(icon_m);
		// #TO HERE. change to make a loop
		JLabel icon_s = getWebIconScaledLabel(url_s, 50, 50);
		JLabel sharing = new JLabel("Sharing...");
		sharing_iconP.add(icon_s);
		sharing_iconP.add(sharing);

		JButton button_back = new JButton("BACK");
		backP.add(button_back);

		// Add Panels to container
		container.add(postP); // add post panel
		container.add(iconsP);// add icons panel
		iconsP.add(Box.createVerticalGlue()); // add glue
		container.add(sharing_iconP);// add sharing icon
		
		// Actions
		NavPanelBackActionListener action = new NavPanelBackActionListener(F);
		button_back.addActionListener(action);

		F.add(container, BorderLayout.CENTER);
		F.add(backP, BorderLayout.SOUTH);
		viewFrame(F);
	}// end screen_share

	/* About US Screen */
	public static void screen_about() {
		JFrame F = new JFrame("About Us");

		// Panel (BOX LAYOUT)
		JPanel mainP = new JPanel();
		mainP.setLayout(new BoxLayout(mainP, BoxLayout.PAGE_AXIS));

		// Information Panel (BOX LAYOUT) //Can be changed to flow
		JPanel infoP = new JPanel();
		mainP.setLayout(new BoxLayout(mainP, BoxLayout.Y_AXIS));
		// Labels
		JLabel l_about = new JLabel("About US");
		l_about.setFont(new Font(null, Font.BOLD, 20));
		l_about.setForeground(black_midnight);
		JLabel l_info = new JLabel("Information about the company. We are cool guys. We like Pizza.");
		infoP.add(l_about); // Header
		infoP.add(Box.createRigidArea(new Dimension(0, 50)));
		infoP.add(l_info); // paragraph
		l_about.setAlignmentX(Component.CENTER_ALIGNMENT);
		l_info.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoP.setMaximumSize(new Dimension(rx, ry / 3));
		infoP.setOpaque(false);

		// Panel pictures (Box Layout) --Method?--
		JPanel picturesP = new JPanel();
		picturesP.setLayout(new BoxLayout(picturesP, BoxLayout.X_AXIS)); // Horizontal
		String[] pics = { "Sergio", "Stef", "Javi" };
		for (int i = 0; i < pics.length; i++) {
			JLabel l = new JLabel(pics[i]);
			picturesP.add(l);
			picturesP.add(Box.createHorizontalGlue());
			l.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		picturesP.setOpaque(false);

		JPanel buttonP = new JPanel();
		buttonP.setLayout(new BoxLayout(buttonP, BoxLayout.X_AXIS));
		// Create Buttons
		JButton button_accept = new JButton("OK");
		buttonP.add(button_accept);
		button_accept.setAlignmentX(Component.CENTER_ALIGNMENT);

		mainP.add(infoP);
		mainP.add(picturesP);
		mainP.add(Box.createRigidArea(new Dimension(0, 250)));
		mainP.add(buttonP);
		
		// mainP.setBackground(gray_concrete);
		
		//Actions
		AboutUsActionListener action = new AboutUsActionListener(F);
		button_accept.addActionListener(action);

		F.add(mainP);
		viewFrame(F);
	}

	/* Help Screen */
	public static void screen_help() {
		JFrame F = new JFrame("Help");

		// Panel (BOX LAYOUT)
		JPanel mainP = new JPanel();
		mainP.setLayout(new BoxLayout(mainP, BoxLayout.PAGE_AXIS));

		// Information Panel (BOX LAYOUT) //Can be changed to flow
		JPanel infoP = new JPanel();
		mainP.setLayout(new BoxLayout(mainP, BoxLayout.Y_AXIS));
		// Labels
		JLabel l_about = new JLabel("Help");
		l_about.setFont(new Font(null, Font.BOLD, 20));
		l_about.setForeground(black_midnight);
		JLabel l_help = new JLabel("FAQ. HELP. OUI MON AMIE.");

		infoP.add(l_about); // Header
		infoP.add(Box.createRigidArea(new Dimension(0, 50)));
		infoP.add(l_help); // paragraph

		l_about.setAlignmentX(Component.CENTER_ALIGNMENT);
		l_help.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel buttonP = new JPanel();
		buttonP.setLayout(new BoxLayout(buttonP, BoxLayout.X_AXIS));
		// Create Buttons
		JButton button_accept = new JButton("Thank You");
		buttonP.add(button_accept);
		button_accept.setAlignmentX(Component.CENTER_ALIGNMENT);

		mainP.add(infoP);
		mainP.add(buttonP);

		// mainP.setBackground(gray_concrete);
		//Actions
		HelpActionListener action = new HelpActionListener(F);
		button_accept.addActionListener(action);
		
		F.add(mainP);
		viewFrame(F);
	}

	/* Logout Screen */
	public static void screen_logout() {
		JFrame F = new JFrame("Logout");

		JPanel thank_youP = new JPanel();
		thank_youP.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel dialog = new JLabel("THANK YOU FOR \nUSING");
		thank_youP.add(dialog);
		// thank_youP.add(Box.createRigidArea(new Dimension(0, 200))); // rigid
		// area to last row

		JButton button_ok = new JButton("OK");

		F.add(thank_youP, BorderLayout.NORTH);
		F.add(CustomJPanels.bannerPanel(), BorderLayout.CENTER);
		F.add(button_ok, BorderLayout.SOUTH);
		viewFrame(F);
	}

	/* --Frame Properties-- */
	/* View Frame */
	public static void viewFrame(JFrame F) {
		F.setSize(rx, ry);
		F.setVisible(true);
		F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/* --Custom Panels-- */
	//Moved to class -> CustomJPanels

	/* --Custom JLabels-- */
	/* Icon from web w/custom size */
	public static JLabel getWebIconScaledLabel(String web_address, int width, int height) {
		ImageUtilities buffer = new ImageUtilities(); // create instance
		buffer.readImgFromWeb(web_address); // read from url
		Image img = buffer.getImage(); // pull img
		// Create a Label
		img = buffer.getScaledImage(img, width, height);
		JLabel label = new JLabel(new ImageIcon(img));

		return label;
	}

	/* --Image Related-- */
	// Insert and scale image from local path
	public static ImageIcon insertIconScaled(String path, int x, int y) {
		ImageUtilities img = new ImageUtilities();
		img.readFromSource(path);
		img.setImage(img.getScaledImage(img.getImage(), x, y));
		ImageIcon icon = new ImageIcon(img.getImage());
		return icon;
	}

	// Insert and scale image from web (EITHER USE THIS OR
	// getWebIconScaledLabel)
	public static ImageIcon insertWebIconScaled(String web_address, int x, int y) {
		ImageUtilities img = new ImageUtilities(); // create instance
		img.readImgFromWeb(web_address); // read from url
		img.setImage(img.getScaledImage(img.getImage(), x, y));
		ImageIcon icon = new ImageIcon(img.getImage());
		return icon;
	}

	/* --Extra Methods-- */
	public static boolean isValidEmailAddress(String email) {
		// From
		// http://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
	
	
}