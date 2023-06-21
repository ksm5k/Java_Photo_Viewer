import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.awt.BorderLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PhotoViewer extends JFrame implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PhotographContainer imageAlbum;
	JLabel Photo;
	JButton Next;
	JButton Previous;
	JRadioButton One, Two, Three, Four, Five = new JRadioButton();
	JLabel imageLabel1, imageLabel2, imageLabel3, imageLabel4, imageLabel5, imageLabel6;
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panelT = new JPanel();
	JPanel controls = new JPanel();
	JPanel prevNext = new JPanel();
	JPanel sorters = new JPanel();
	Container pane = new Container();
	int index;
	int currentPosition = 0;

	
	public static void main(String[] args) {
		PhotoViewer myViewer = new PhotoViewer();
		// relative path for PCs:
		String imageDirectory =
		"images\\";
		Photograph p1 =
		new Photograph("Casual", imageDirectory + "gray shirt.jpg", "2015-07-15", 3);
		Photograph p2 =
		new Photograph("Day in NYC", imageDirectory + "funky pants.jpg", "2015-04-17", 4);
		Photograph p3 =
		new Photograph("Summer look", imageDirectory + "i like this.jpg", "2015-03-20", 5);
		Photograph p4 =
		new Photograph("Indie feels", imageDirectory + "pls.jpg", "2015-02-20", 1);
		Photograph p5 =
		new Photograph("Do I wanna know?", imageDirectory + "soo cute.jpg", "2015-06-30", 2);
		// four more photographs like the line above
		myViewer.imageAlbum = new PhotoLibrary("Test Library", 1);
		myViewer.imageAlbum.addPhoto(p1);
		myViewer.imageAlbum.addPhoto(p2);
		myViewer.imageAlbum.addPhoto(p3);
		myViewer.imageAlbum.addPhoto(p4);
		myViewer.imageAlbum.addPhoto(p5);
		// four more photographs added like the line above
		Collections.sort(myViewer.imageAlbum.photos); // i imported collections, sorted -> sorts by date automatically
		javax.swing.SwingUtilities.invokeLater(() -> myViewer.createAndShowGUI() ); // think this needs editing

}

		/**
		 * method to create and display the graphic user interface
		 */
		private void createAndShowGUI() {
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.addComponentsToPane(this.getContentPane());
			this.pack();
			this.setVisible(true);
		}

		
		
		
		/**
		 * @param pane
		 */
		public void addComponentsToPane(Container pane) {
			// panels for images and buttons
			panelT = new JPanel(new BorderLayout());
			panel = new JPanel();
			panel.setLayout(new FlowLayout());
			
			controls = new JPanel();
	        controls.setLayout(new FlowLayout());
	        
	        prevNext = new JPanel();
	        prevNext.setLayout(new BorderLayout());
	        
	        sorters = new JPanel();
	        sorters.setLayout(new FlowLayout());
	        
	        index = 0;
	        
	        
	        /**
	         * Class created to implement an ActionListener for the purpose of detecting the click of the Sort By Caption button
	         *
	         */
	        /**
	         * @author Kristen Shae
	         *
	         */
	        class SortListener implements ActionListener {
	        	/* (non-Javadoc)
	        	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	        	 * @param takes in an action, which is the clicking of the Sort By Caption radio button
	        	 * Sorts the photos by caption
	        	 */
	        	public void actionPerformed(ActionEvent e) {
	        		String command = e.getActionCommand();
	        		if (command.equals("sbc")){
	        			Collections.sort(imageAlbum.photos, new CompareByCaption());
        				updateThumbnail(imageLabel1, 0);
        				updateCaption(imageLabel1, 0);
        				updateThumbnail(imageLabel2, 1);
        				updateCaption(imageLabel2, 1);
        				updateThumbnail(imageLabel3, 2);
        				updateCaption(imageLabel3, 2);
        				updateThumbnail(imageLabel4, 3);
        				updateCaption(imageLabel4, 3);
        				updateThumbnail(imageLabel5, 4);
        				updateCaption(imageLabel5, 4);
	        	
        				
        				try {
        					BufferedImage bi = ImageIO.read(imageAlbum.getPhotos().get(index).getImageFile());
        					imageLabel6.setIcon(new ImageIcon(bi.getScaledInstance(400, 400, Image.SCALE_DEFAULT)));
        					// set rating
        				} catch (IOException exc) {
        					imageLabel6.setText("Error loading the image");
        				}
	        		
	        		}
	        	}
	        }
	        
	        /**
	         * Class created to implement an ActionListener for the purpose of detecting the click of the Sort By Rating button
	         *
	         */
	        class SortListener2 implements ActionListener {
	        	/* (non-Javadoc)
	        	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	        	 * @param takes in an action, which is the clicking of the Sort By Rating radio button
	        	 * Sorts the photos by rating
	        	 */
	        	public void actionPerformed(ActionEvent e) {
	        		String command = e.getActionCommand();
	        		if (command.equals("sbr")) {
	        			Collections.sort(imageAlbum.photos, new CompareByRating());
        				updateThumbnail(imageLabel1, 0);
        				updateCaption(imageLabel1, 0);
        				updateThumbnail(imageLabel2, 1);
        				updateCaption(imageLabel2, 1);
        				updateThumbnail(imageLabel3, 2);
        				updateCaption(imageLabel3, 2);
        				updateThumbnail(imageLabel4, 3);
        				updateCaption(imageLabel4, 3);
        				updateThumbnail(imageLabel5, 4);
        				updateCaption(imageLabel5, 4);
	        	
        				currentPosition = 0;
        				try {
        					BufferedImage bi = ImageIO.read(imageAlbum.getPhotos().get(index).getImageFile());
        					imageLabel6.setIcon(new ImageIcon(bi.getScaledInstance(400, 400, Image.SCALE_DEFAULT)));
        					// set rating
        				} catch (IOException exc) {
        					imageLabel6.setText("Error loading the image");
        				}
	        		}
	        	}
	        }
	        
	        /**
	         * Class created to implement ActionListener for the purpose of detecting the click of the Sort By Date button
	         *
	         */
	        class SortListener3 implements ActionListener {
	        	/* (non-Javadoc)
	        	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	        	 * @param takes in an action, which is the clicking of the Sort By Date radio button
	        	 * Sorts the photos by date
	        	 */
	        	public void actionPerformed(ActionEvent e) {
	        		String command = e.getActionCommand();
	        		if (command.equals("sbd")) {
	        			Collections.sort(imageAlbum.photos);
        				updateThumbnail(imageLabel1, 0);
        				updateCaption(imageLabel1, 0);
        				updateThumbnail(imageLabel2, 1);
        				updateCaption(imageLabel2, 1);
        				updateThumbnail(imageLabel3, 2);
        				updateCaption(imageLabel3, 2);
        				updateThumbnail(imageLabel4, 3);
        				updateCaption(imageLabel4, 3);
        				updateThumbnail(imageLabel5, 4);
        				updateCaption(imageLabel5, 4);
        				
        				currentPosition = 0;
        				try {
        					BufferedImage bi = ImageIO.read(imageAlbum.getPhotos().get(index).getImageFile());
        					imageLabel6.setIcon(new ImageIcon(bi.getScaledInstance(400, 400, Image.SCALE_DEFAULT)));
        					// set rating
        				} catch (IOException exc) {
        					imageLabel6.setText("Error loading the image");
        				}
	        		}
	        	}
	        }	
	        
	        class ButtonListener implements ActionListener {
	        	int index = 0;
	        	public void actionPerformed(ActionEvent e) {
	        		String command = e.getActionCommand();
	        		
	        		// next
	        		if (command.equals("next")) {
	        			if (index != 4){
	        				index += 1;
	        				currentPosition +=1;
	    					try {
	    						File imageFile6 = new File(imageAlbum.getPhotos().get(index).getFilename());
	    						BufferedImage defaultPhoto6 = ImageIO.read(imageFile6);
	    						ImageIcon thisIcon6 = new ImageIcon(defaultPhoto6.getScaledInstance(300, 300, Image.SCALE_DEFAULT));
	    						imageLabel6.setIcon(thisIcon6);

	    					} catch (IOException exc){
	    						imageLabel6.setText("Error loading default or album is empty");
	    					} 
	        			}
	        			else {
	        				index = 0;
	        				currentPosition =0;
	    					try {
	    						File imageFile6 = new File(imageAlbum.getPhotos().get(index).getFilename());
	    						BufferedImage defaultPhoto6 = ImageIO.read(imageFile6);
	    						ImageIcon thisIcon6 = new ImageIcon(defaultPhoto6.getScaledInstance(300, 300, Image.SCALE_DEFAULT));
	    						imageLabel6.setIcon(thisIcon6);

	    					} catch (IOException exc){
	    						imageLabel6.setText("Error loading default or album is empty");
	    					} 
	        			}
	        		}
	        		
	        		// previous
	        		if (command.equals("previous")) {
	        			if (index != 0) {
	        				index -= 1;
	        				currentPosition -= 1;
	    					try {
	    						File imageFile6 = new File(imageAlbum.getPhotos().get(index).getFilename());
	    						BufferedImage defaultPhoto6 = ImageIO.read(imageFile6);
	    						ImageIcon thisIcon6 = new ImageIcon(defaultPhoto6.getScaledInstance(300, 300, Image.SCALE_DEFAULT));
	    						imageLabel6.setIcon(thisIcon6);

	    					} catch (IOException exc){
	    						imageLabel6.setText("Error loading default or album is empty");
	    					} 
	        			}
	        			else {
	        				index = 4;
	        				currentPosition = 4;
	    					try {
	    						File imageFile6 = new File(imageAlbum.getPhotos().get(index).getFilename());
	    						BufferedImage defaultPhoto6 = ImageIO.read(imageFile6);
	    						ImageIcon thisIcon6 = new ImageIcon(defaultPhoto6.getScaledInstance(300, 300, Image.SCALE_DEFAULT));
	    						imageLabel6.setIcon(thisIcon6);

	    					} catch (IOException exc){
	    						imageLabel6.setText("Error loading default or album is empty");
	    					} 
	        			}
	        	
	        		}
	        	}
	        } 
	        {
	        

    		// rating radio button actions and results
	        class RatingRB implements ActionListener{
	        	public void actionPerformed(ActionEvent e) {
	        		String command = e.getActionCommand();
	        		if (command.equals("1")) {
	        			index = currentPosition;
	        			updateRating(currentPosition, 1);
	        		}
	        		if (command.equals("2")) {
	        			index = currentPosition;
	        			updateRating(currentPosition, 2);
	        		}
	        		if (command.equals("3")) {
	        			index = currentPosition;
	        			updateRating(currentPosition, 3);
	        		}
	        		if (command.equals("4")) {
	        			index = currentPosition;
	        			updateRating(currentPosition, 4);
	        		}
	        		if (command.equals("5")) {
	        			index = currentPosition;
	        			updateRating(currentPosition, 5);
	        		}
	        	}
	        }
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
			
			// five thumbnails
	        
	        // first thumbnail
	        imageLabel1 = new JLabel(); // make a new one for each
			try {
				File imageFile = new File(imageAlbum.getPhotos().get(0).getFilename());
				BufferedImage defaultPhoto = ImageIO.read(imageFile);
				ImageIcon thisIcon = new ImageIcon(defaultPhoto.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
				imageLabel1.setIcon(thisIcon);
				imageLabel1.setText("Caption: " + imageAlbum.getPhotos().get(0).getCaption() + "\n Date: " + imageAlbum.getPhotos().get(0).getDateTaken() + "\n Rating: " + imageAlbum.getPhotos().get(0).getRating());
			    currentPosition = 0;
			} catch (IOException e){
				imageLabel1.setText("Error loading default or album is empty");
			}
			

			//add to panel
			panel.add(imageLabel1);
			setVisible(true);
			
			// addmouselistener
			
			imageLabel1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						File imageFile6 = new File(imageAlbum.getPhotos().get(0).getFilename());
						BufferedImage defaultPhoto6 = ImageIO.read(imageFile6);
						ImageIcon thisIcon6 = new ImageIcon(defaultPhoto6.getScaledInstance(300, 300, Image.SCALE_DEFAULT));
						imageLabel6.setIcon(thisIcon6);

					} catch (IOException exc){
						imageLabel6.setText("Error loading default or album is empty");
					} 
				}
			});
			
			
			// second thumbnail
			imageLabel2 = new JLabel();
			try {
				File imageFile2 = new File(imageAlbum.getPhotos().get(1).getFilename());
				BufferedImage defaultPhoto2 = ImageIO.read(imageFile2);
				ImageIcon thisIcon2 = new ImageIcon(defaultPhoto2.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
				imageLabel2.setIcon(thisIcon2);
				imageLabel2.setText("Caption: " + imageAlbum.getPhotos().get(1).getCaption() + "\n Date: " + imageAlbum.getPhotos().get(1).getDateTaken() + "\n Rating: " + imageAlbum.getPhotos().get(1).getRating());
			    currentPosition = 1;
			} catch (IOException e){
				imageLabel2.setText("Error loading default or album is empty");
			}
			
			// add to panel
			panel.add(imageLabel2);
			setVisible(true);
			
			// mouse listener
			imageLabel2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						File imageFile6 = new File(imageAlbum.getPhotos().get(1).getFilename());
						BufferedImage defaultPhoto6 = ImageIO.read(imageFile6);
						ImageIcon thisIcon6 = new ImageIcon(defaultPhoto6.getScaledInstance(300, 300, Image.SCALE_DEFAULT));
						imageLabel6.setIcon(thisIcon6);

					} catch (IOException exc){
						imageLabel6.setText("Error loading default or album is empty");
					} 
				}
			});
			
			// third thumbnail
			imageLabel3 = new JLabel(); 
			try {
				File imageFile3 = new File(imageAlbum.getPhotos().get(2).getFilename());
				BufferedImage defaultPhoto3 = ImageIO.read(imageFile3);
				ImageIcon thisIcon3 = new ImageIcon(defaultPhoto3.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
				imageLabel3.setIcon(thisIcon3);
				imageLabel3.setText("Caption: " + imageAlbum.getPhotos().get(2).getCaption() + "\n Date: " + imageAlbum.getPhotos().get(2).getDateTaken() + "\n Rating: " + imageAlbum.getPhotos().get(2).getRating());
				currentPosition = 2;
			} catch (IOException e){
				imageLabel3.setText("Error loading default or album is empty");
			}
			panel.add(imageLabel3);
			setVisible(true);
			
			// imageLabel3 mouseListener
			imageLabel3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						File imageFile6 = new File(imageAlbum.getPhotos().get(2).getFilename());
						BufferedImage defaultPhoto6 = ImageIO.read(imageFile6);
						ImageIcon thisIcon6 = new ImageIcon(defaultPhoto6.getScaledInstance(300, 300, Image.SCALE_DEFAULT));
						imageLabel6.setIcon(thisIcon6);

					} catch (IOException exc){
						imageLabel6.setText("Error loading default or album is empty");
					} 
				}
			});
			
			// fourth thumbnail
			imageLabel4 = new JLabel(); 
			try {
				File imageFile4 = new File(imageAlbum.getPhotos().get(3).getFilename());
				BufferedImage defaultPhoto4 = ImageIO.read(imageFile4);
				ImageIcon thisIcon4 = new ImageIcon(defaultPhoto4.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
				imageLabel4.setIcon(thisIcon4);
				imageLabel4.setText("Caption: " + imageAlbum.getPhotos().get(3).getCaption() + "\n Date: " + imageAlbum.getPhotos().get(3).getDateTaken() + "\n Rating: " + imageAlbum.getPhotos().get(3).getRating());
				currentPosition = 3;
			} catch (IOException e){
				imageLabel4.setText("Error loading default or album is empty");
			}
			
			//add to panel
			panel.add(imageLabel4);
			setVisible(true);
			
			// mouse listener
			imageLabel4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						File imageFile6 = new File(imageAlbum.getPhotos().get(3).getFilename());
						BufferedImage defaultPhoto6 = ImageIO.read(imageFile6);
						ImageIcon thisIcon6 = new ImageIcon(defaultPhoto6.getScaledInstance(300, 300, Image.SCALE_DEFAULT));
						imageLabel6.setIcon(thisIcon6);
					} catch (IOException exc){
						imageLabel6.setText("Error loading default or album is empty");
					} 
				}
			});
			
			imageLabel5 = new JLabel(); 
			try {
				File imageFile5 = new File(imageAlbum.getPhotos().get(4).getFilename());
				BufferedImage defaultPhoto5 = ImageIO.read(imageFile5);
				ImageIcon thisIcon5 = new ImageIcon(defaultPhoto5.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
				imageLabel5.setIcon(thisIcon5);
				imageLabel5.setText("Caption: " + imageAlbum.getPhotos().get(4).getCaption() + "\n Date: " + imageAlbum.getPhotos().get(4).getDateTaken() + "\n Rating: " + imageAlbum.getPhotos().get(4).getRating());
				currentPosition = 4;
			} catch (IOException e){
				imageLabel5.setText("Error loading default or album is empty");
			}
			
			//add to panel
			panel.add(imageLabel5);
			setVisible(true);
			
			// mouse listener
			imageLabel5.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						File imageFile6 = new File(imageAlbum.getPhotos().get(4).getFilename());
						BufferedImage defaultPhoto6 = ImageIO.read(imageFile6);
						ImageIcon thisIcon6 = new ImageIcon(defaultPhoto6.getScaledInstance(300, 300, Image.SCALE_DEFAULT));
						imageLabel6.setIcon(thisIcon6);

					} catch (IOException exc){
						imageLabel6.setText("Error loading default or album is empty");
					} 
				}
			});
		
			// big picture 
			this.panel2 = new JPanel();
			panel2.setLayout(new FlowLayout());
			
			this.imageLabel6 = new JLabel();
			try {
				File imageFile6 = new File(imageAlbum.getPhotos().get(index).getFilename());
				BufferedImage defaultPhoto6 = ImageIO.read(imageFile6);
				ImageIcon thisIcon6 = new ImageIcon(defaultPhoto6.getScaledInstance(400, 400, Image.SCALE_DEFAULT));
				imageLabel6.setIcon(thisIcon6);

			} catch (IOException e){
				imageLabel6.setText("Error loading default or album is empty");
			}
			panel2.add(imageLabel6);
			setVisible(true);
			
			// rating buttons and their action listeners
			
			JRadioButton One = new JRadioButton("1");
			One.setActionCommand("1"); // make this change the actual rating of the photo when u write ur action listener class
			One.addActionListener(new RatingRB());
			JRadioButton Two = new JRadioButton("2");
			Two.setActionCommand("2");
			Two.addActionListener(new RatingRB());
			JRadioButton Three = new JRadioButton("3");
			Three.setActionCommand("3");
			Three.addActionListener(new RatingRB());
			JRadioButton Four = new JRadioButton("4");
			Four.setActionCommand("4");
			Four.addActionListener(new RatingRB());
			JRadioButton Five = new JRadioButton("5");
			Five.setActionCommand("5");
			Five.addActionListener(new RatingRB());
			
			// rating button group
			ButtonGroup group = new ButtonGroup();
			group.add(One);
			group.add(Two);
			group.add(Three);
			group.add(Four);
			group.add(Five);
			controls.add(One);
			controls.add(Two);
			controls.add(Three);
			controls.add(Four);
			controls.add(Five);
			
			// previous and next buttons
			
			JButton Previous = new JButton("Previous");
			Previous.setActionCommand("previous"); // make this move the photos
			Previous.addActionListener(new ButtonListener());
			JButton Next = new JButton("Next");
			Next.setActionCommand("next");
			Next.addActionListener(new ButtonListener());
			prevNext.add(Previous, BorderLayout.WEST);
			prevNext.add(Next, BorderLayout.EAST);
			
			// sort by radiobuttons
			JRadioButton sortByCaption = new JRadioButton("Sort By Caption");
			sortByCaption.setActionCommand("sbc"); // make this sort a new way
			sortByCaption.addActionListener(new SortListener());
			JRadioButton sortByRating = new JRadioButton("Sort By Rating");
			sortByRating.setActionCommand("sbr");
			sortByRating.addActionListener(new SortListener2());
			JRadioButton sortByDate = new JRadioButton("Sort By Date");
			sortByDate.setActionCommand("sbd");
			sortByDate.addActionListener(new SortListener3());
			
			// sorting button group
			ButtonGroup sortGroup = new ButtonGroup();
			sortGroup.add(sortByCaption);
			sortGroup.add(sortByDate);
			sortGroup.add(sortByRating);
			
			sorters.add(sortByCaption);
			sorters.add(sortByDate);
			sorters.add(sortByRating);
			
			// creating the actual panel and adding it to the pane
			panelT.add(panel, BorderLayout.SOUTH);
			panelT.add(panel2, BorderLayout.CENTER);
			panelT.add(controls, BorderLayout.NORTH);
			panelT.add(prevNext, BorderLayout.WEST);
			panelT.add(sorters, BorderLayout.EAST);
			pane.add(panelT);}
			

		 
		}


		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


/**
 * @param thumbnail
 * @param index
 * method updates the image thumbnail, used when images are sorted
 */
public void updateThumbnail(JLabel thumbnail, int index) {
	try {
		BufferedImage bi = ImageIO.read(imageAlbum.getPhotos().get(index).getImageFile());
		thumbnail.setIcon(new ImageIcon(bi.getScaledInstance(100, 75, Image.SCALE_DEFAULT)));
	} catch (IOException e) {
		thumbnail.setText("Error loading the image");
	}
}

/**
 * @param thumbnail
 * @param index
 * method used for updating the caption, used within the sorting method for sort by caption
 */
public void updateCaption(JLabel thumbnail, int index) {
		thumbnail.setText("Caption: " + imageAlbum.getPhotos().get(index).getCaption() + "\n Date: " + imageAlbum.getPhotos().get(index).getDateTaken() + "\n Rating: " + imageAlbum.getPhotos().get(index).getRating());
}

/**
 * @param index
 * @param ratingB
 * used for updating the rating, used within the sorting method for sort by rating
 */
public void updateRating(int index, int ratingB) {
	if (index == 0) {
		imageAlbum.photos.get(index).setRating(ratingB);
		imageLabel1.setText("Caption: " + imageAlbum.getPhotos().get(index).getCaption() + "\n Date: " + imageAlbum.getPhotos().get(index).getDateTaken() + "\n Rating: " + imageAlbum.getPhotos().get(index).getRating());
	}
	if (index == 1) {
		imageAlbum.photos.get(index).setRating(ratingB);
		imageLabel2.setText("Caption: " + imageAlbum.getPhotos().get(index).getCaption() + "\n Date: " + imageAlbum.getPhotos().get(index).getDateTaken() + "\n Rating: " + imageAlbum.getPhotos().get(index).getRating());
	}
	if (index == 2) {
		imageAlbum.photos.get(index).setRating(ratingB);
		imageLabel3.setText("Caption: " + imageAlbum.getPhotos().get(index).getCaption() + "\n Date: " + imageAlbum.getPhotos().get(index).getDateTaken() + "\n Rating: " + imageAlbum.getPhotos().get(index).getRating());
	}
	if (index == 3) {
		imageAlbum.photos.get(index).setRating(ratingB);
		imageLabel4.setText("Caption: " + imageAlbum.getPhotos().get(index).getCaption() + "\n Date: " + imageAlbum.getPhotos().get(index).getDateTaken() + "\n Rating: " + imageAlbum.getPhotos().get(index).getRating());
	}
	if (index == 4) {
		imageAlbum.photos.get(index).setRating(ratingB);
		imageLabel5.setText("Caption: " + imageAlbum.getPhotos().get(index).getCaption() + "\n Date: " + imageAlbum.getPhotos().get(index).getDateTaken() + "\n Rating: " + imageAlbum.getPhotos().get(index).getRating());
	}
}
}
