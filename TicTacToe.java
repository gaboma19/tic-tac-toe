import javax.swing.JFrame;
import java.awt.*;
import java.util.*;

public class TicTacToe extends JFrame{

	static XCanvas theCanvas;
	static int width = 800, height = 600;
	static String[][] board = new String[3][3];
	
	public static void main(String[] args) {

		new TicTacToe();

	}

	TicTacToe() {
		super();

		setSize(width, height);

		theCanvas = new XCanvas();
		add(theCanvas, "Center");

		setVisible(true);
	} 

	@SuppressWarnings("serial")
	class XCanvas extends Canvas
	{		
		
		// these data members specify the location of the tic-tac-toe grid

		int cellWidth = 100;
		int lineWidth = 5;
		int upperLeftX = 50;
		int upperLeftY = 50;

		// data members for running tests

		boolean testMode = false;
		String testLocation = "a1";
		String testType = "O"; // "O" or "X"

		// data members

		String location, type;

		XCanvas()
		{
			setSize(width, height);

			upperLeftX = width / 2 - 3 * cellWidth / 2;
			upperLeftY = height / 2 - 3 * cellWidth / 2;
		}

		public void update(Graphics g)
		{
			paint(g);
		}

		public void paint(Graphics g)
		{
			//clearTheCanvas(g);

			if ( testMode )
			{
				drawTestGraphics(g);
			}
			else
			{
				drawGraphics(g);
			}
		}

		void clearTheCanvas(Graphics g)
		{
			g.setColor(Color.white);

			g.fillRect(0, 0, width, height);
		}

		void drawGrid(Graphics g)
		{
			g.setColor( Color.black );

			g.fillRect(upperLeftX + cellWidth, upperLeftY, lineWidth, 3 * cellWidth);
			g.fillRect(upperLeftX + 2 * cellWidth, upperLeftY, lineWidth, 3 * cellWidth);

			g.fillRect(upperLeftX, upperLeftY + cellWidth, 3 * cellWidth, lineWidth);
			g.fillRect(upperLeftX, upperLeftY + 2 * cellWidth, 3 * cellWidth, lineWidth);

		}

		private void drawO(Graphics g, String location)
		{
			Point pt = locationToPixel(location);

			if ( pt != null )
			{
				int od = 3 * cellWidth / 4;
				int id = 5 * cellWidth / 8;
				int offset = cellWidth / 8;

				g.setColor( Color.red );
				g.fillOval(pt.x + offset, pt.y + offset, od, od);

				offset = 3 * cellWidth / 16;

				g.setColor( Color.white );
				g.fillOval(pt.x + offset, pt.y + offset, id, id);
			}
		}

		private void drawX(Graphics g, String location)
		{
			Point pt = locationToPixel(location);

			if ( pt != null )
			{
				int offset = cellWidth / 8;

				g.setColor( Color.black );

				int xpointsL[] =
				{
						pt.x + offset,
						pt.x + 3 * cellWidth / 4 + offset,
						pt.x + 3 * cellWidth / 4 + 100 * lineWidth / 71 + offset,
						pt.x + 100 * lineWidth / 71 + offset,
						pt.x + offset
				};

				int xpointsR[] =
				{
						pt.x + cellWidth - offset - 100 * lineWidth / 71,
						pt.x + offset,
						pt.x + offset + 100 * lineWidth / 71,
						pt.x + cellWidth - offset,
						pt.x + cellWidth - offset - 100 * lineWidth / 71
				};

				int ypoints[] =
				{
						pt.y + offset,
						pt.y + 3 * cellWidth / 4 + offset,
						pt.y + 3 * cellWidth / 4 + offset,
						pt.y + offset,
						pt.y + offset
				};

				int npoints = 5;

				g.fillPolygon(xpointsL, ypoints, npoints);
				g.fillPolygon(xpointsR, ypoints, npoints);
			}
		}

		private Point locationToPixel(String location)
		{
			Point pt = new Point(0,0);
			char[] ch = location.toCharArray();

			switch ( ch[0] )
			{
			case 'a':

				pt.x = upperLeftX;

				break;

			case 'b':

				pt.x = upperLeftX + cellWidth;

				break;

			case 'c':

				pt.x = upperLeftX + 2 * cellWidth;

				break;

			default:

				pt = null;
				break;
			}

			if ( pt != null )
			{

				switch ( ch[1] )
				{
				case '1':

					pt.y = upperLeftY;

					break;

				case '2':

					pt.y = upperLeftY + cellWidth;

					break;

				case '3':

					pt.y = upperLeftY + 2 * cellWidth;

					break;

				default:

					pt = null;
					break;
				}
			}

			return pt;
		}

		public void drawSomeText (Graphics g)
		{
			Font f = new Font("TimesRoman",Font.BOLD,18);
			g.setFont(f);

			g.setColor(Color.blue);

			g.drawString("Tic-Tac-Toe", 50, 50 );
		}

		public void drawGraphics( Graphics g )
		{
			drawGrid(g);

			// draw the state of the board

			Scanner keyboard = new Scanner(System.in);
			System.out.println("Type: ");
			String type = keyboard.nextLine();
			System.out.println("Location: ");
			String location = keyboard.nextLine();
			theCanvas.move(location, type);

			if (type.equals("O")) {
				drawO(g, location);
			} else {
				drawX(g, location);
			}

		}

		public void drawTestGraphics( Graphics g )
		{
			drawGrid(g);

			if ( testType.equals("O") )
			{
				drawO(g, testLocation);
			}
			else
			{
				drawX(g, testLocation);
			}
		}

		public void runTest(String location, String type)
		{
			this.testLocation = location;
			this.testType = type;

			repaint();
		}

		public void move(String l, String t) {
			location = l;
			type = t;

			System.out.println(location);

			if (location == "a1") {
				System.out.println("here");
				board[0][0] = type;
			} else if (location == "a2") {
				board[0][1] = type;
			} else if (location == "a3") {
				board[0][2] = type;
			} else if (location == "b1") {
				board[1][0] = type;
			} else if (location == "b2") {
				board[1][1] = type;
			} else if (location == "b3") {
				board[1][2] = type;
			} else if (location == "c1") {
				board[2][0] = type;
			} else if (location == "c2") {
				board[2][1] = type;
			} else {
				board[2][2] = type;
			}

			System.out.println(board[0][0]);

			repaint();
		}
	} 

}
