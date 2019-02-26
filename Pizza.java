import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pizza {
	char[][] pizzaContent;
	int l;
	int h;
	int r;
	int c;
	Map<String, ArrayList<String>> cellSlices = new HashMap<>();
	
	Pizza(int r, int c){
		this.r = r;
		this.c = c;
		this.pizzaContent = new char[r][c];
	}
	
	void display() {
		for(int i = 0; i < pizzaContent.length; i++) {
			char[] pizzaRow = pizzaContent[i];
			for(int j = 0; j < pizzaRow.length; j++) {
				System.out.print(pizzaRow[j]);
			}
			System.out.println("");
		}
	}
	
	void showMap() {
		for(String key : cellSlices.keySet()) {
			if(key.charAt(0) == '0') {
				System.out.print(key + ": ");
				for(String val : cellSlices.get(key)) {
					System.out.print("(" + val + ")");
				}
				System.out.println("");
			}			
		}
	}
	
	boolean isValidSlice(int r1, int c1, int r2, int c2) {
		boolean isValid = false;
		int tcnt = 0;
		int mcnt = 0;
		
		int totalCells = (r2 - r1 + 1) * (c2 - c1 + 1);
		if(r2 >=  r || c2 >= c || totalCells > h || totalCells < 2 * l) {
			return isValid;
		}
		
		for(int i = r1; i <= r2; i++) {
			for(int j = c1; j <= c2; j++) {
				if(pizzaContent[i][j] == 'T') {
					tcnt++;
				} else {
					mcnt++;
				}
				if(tcnt >= l && mcnt >= l) {
					return true;
				}
			}
		}
		return isValid;
	}

	void getSlicesForLen(int r1, int c1, int len) {
		//ArrayList<Slice> slices = new ArrayList<Slice>();
		int sliceLen = len;
		while(sliceLen >= 1) {
			if(len % sliceLen == 0) {
				int sliceHt = len / sliceLen;
				boolean isValid = isValidSlice(r1, c1, r1 + sliceLen - 1, c1 + sliceHt - 1);
				if(isValid) {
					Slice newTempSlice = new Slice(r1, c1, r1 + sliceLen - 1, c1 + sliceHt - 1);
					String sliceStart = r1 + "," + c1;
					String sliceEnd = (r1 + sliceLen - 1) + "," + (c1 + sliceHt - 1);
					
					if(!cellSlices.containsKey(sliceStart)) {
						cellSlices.put(sliceStart.toString(), new ArrayList<>());	
					}
					if(!cellSlices.containsKey(sliceEnd)) {
						cellSlices.put(sliceEnd.toString(), new ArrayList<>());						
					}
					cellSlices.get(sliceStart).add(sliceEnd);
					cellSlices.get(sliceEnd).add(sliceStart);
					
					//slices.add(newTempSlice);
					//newTempSlice.display();
				}
			}
			sliceLen--;
		}
		//return slices;
	}
	
	void getAllSlices(int r1, int c1) {
		//ArrayList<Slice> slices = new ArrayList<Slice>();
		for(int i = 2*l; i <= h; i++) {
			getSlicesForLen(r1, c1, i);
		}
		//return slices;
	}
	
}
