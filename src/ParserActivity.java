import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ParserActivity extends JApplet {

	/**
	 * @param args
	 */
	
	 JPanel gui ;
	 JFrame guiFrame ;
	 JTextField urlLabel;
	 JTextField outLabel;
	 String link;
	 JTextArea  textArea;
	public static void main(String[] args) {

		new ParserActivity();
	}

	public ParserActivity() {
		 JFrame guiFrame = new JFrame();
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Parser");
		guiFrame.setSize(450, 300);
		guiFrame.setLocationRelativeTo(null); 
//		
//		
//		btnParse= new Button("Parse");
//		
//		textField = new JTextField();
//		textField.setSize(200, 50);
//		textField.setText("test");
//		textField.setMargin( new Insets(10, 10, 10, 10));
//		guiFrame.add(textField,BorderLayout.NORTH);
//		guiFrame.add(btnParse,BorderLayout.SOUTH);
//		guiFrame.setVisible(true); 
		 JPanel gui = new JPanel(new BorderLayout(2,2));

	        JPanel labelFields = new JPanel(new BorderLayout(2,2));
	        labelFields.setBorder(new TitledBorder(""));

	        JPanel labels = new JPanel(new GridLayout(0,1,1,1));
	        labels.setBorder(new TitledBorder(""));
	        JPanel fields = new JPanel(new GridLayout(0,1,1,1));
	        fields.setBorder(new TitledBorder(""));

	            labels.add(new JLabel("URL :" ));
	            // if these were of different size, it would be necessary to
	            // constrain them using another panel
	            urlLabel=new JTextField(25);
	            fields.add(urlLabel);
	            
	            labels.add(new JLabel("Out Put :" ));
	            // if these were of different size, it would be necessary to
	            // constrain them using another panel
	            outLabel=new JTextField(25);
	            fields.add(outLabel);

	        labelFields.add(labels, BorderLayout.CENTER);
	        labelFields.add(fields, BorderLayout.EAST);

	        JPanel guiCenter = new JPanel(new BorderLayout(2,2));
	        guiCenter.setBorder(new TitledBorder(""));
	        JPanel buttonConstrain = new JPanel(new FlowLayout(FlowLayout.CENTER));
	        buttonConstrain.setBorder(new TitledBorder(""));
	        JButton btn = new JButton("Parser");
	        btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
//					System.out.print("");
//					JOptionPane.showMessageDialog(gui, "Eggs are not supposed to be green.");
					processParser();
				}
			});
	        buttonConstrain.add( btn );
	        
	       
	        guiCenter.add( buttonConstrain, BorderLayout.NORTH );
	        textArea= new JTextArea(5,30);
	        guiCenter.add(new JScrollPane(textArea));
	        gui.add(labelFields, BorderLayout.NORTH);
	        gui.add(guiCenter, BorderLayout.CENTER);
	        guiFrame.add(gui);
			guiFrame.setVisible(true); 
			
//	        JOptionPane.showMessageDialog(null, gui);
	}
	
	private void processParser(){
		String url=urlLabel.getText();
		String out = outLabel.getText();
		if(url==null||out==null
				||url.trim().length()==0||out.trim().length()==0
				){
			JOptionPane.showMessageDialog(gui, "Invalid input!");
			return;
		}		
		try{
			
			textArea.setText(sendGet());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
		private String sendGet() throws Exception {
			String titleTAG ="<title>";
			String imgTAG="class=\"ck_image\"";
			String linkTAG=".mp3";
			String desTAG="class=\"txt-head\"";
			String url = urlLabel.getText().trim();
	 
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 
			// optional default is GET
			con.setRequestMethod("GET");
	 
			//add request header
	 
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream(),"UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				if(inputLine.contains(desTAG)||inputLine.contains(titleTAG)||inputLine.contains(imgTAG)||inputLine.contains(linkTAG)){
					System.out.println(inputLine);
					link=inputLine;
					response.append(inputLine.trim()+"\n");

				}
			}
			in.close();
	 
			//print result
			System.out.println(response.toString());

			return response.toString();
	 
		}
			
	
		private void parseData(){
			
			String title ="<title>";
			
		}
}
