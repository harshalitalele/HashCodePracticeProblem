import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/*To Do:

Fetch all valid slices with minimum requirement of L elements
Check overlapping slices for the best ones
Append untouched cells to whichever slice possible with H related requirement
Export solution*/

public class PizzaSlices {

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("D:\\problem statement\\a_example.in");
			BufferedReader br = new BufferedReader(fr);
			String[] rclh = null;

			String sCurrentLine;
			int r = 0, c = 0, l = 0, h = 0, rowCnt = 0;
			Pizza pizzaObj = null;

			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);

				if(rowCnt == 0) {
					rclh = sCurrentLine.split(" ");
					r = new Integer(rclh[0]);
					c = new Integer(rclh[1]);
					l = new Integer(rclh[2]);
					h = new Integer(rclh[3]);
					pizzaObj = new Pizza(r, c);
					pizzaObj.h = h;
					pizzaObj.l = l;
				} else {
					for(int j = 0; j < c; j++) {
						pizzaObj.pizzaContent[rowCnt-1][j] = sCurrentLine.charAt(j);
					}					
					
				}
				rowCnt++;
			}
			
			//pizzaObj.display();
			/*
			 * boolean isValid = pizzaObj.isValidSlice(1, 3, 1, 4);
			 * System.out.println("validity: " + isValid);
			 */
			
			//fetch all valid slices out of pizza even though they are overlapping
			int cellNum = 0,
				cellCnt = r * c;
			ArrayList<Slice> allSlices = new ArrayList<Slice>();
			while(cellNum <= cellCnt) {
				int rnum = cellNum / c,
					cnum = cellNum % c;
				pizzaObj.getAllSlices(rnum, cnum);
				cellNum++;
			}
			
			pizzaObj.showMap();
			
			//get rid of overlapping slices or cells
			
			//include all untouched cells to all the valid slices possible
			
			//output r1,c1,r2,c2 of the possible slices
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
