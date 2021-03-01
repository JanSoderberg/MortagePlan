package MortagePlan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MortagePlan {

	private ArrayList<Mortage> mortages;

	public MortagePlan() {
		mortages = new ArrayList<Mortage>();
	}
	
	public ArrayList<Mortage> getMortages()
	{
		return mortages;
	}

	public void readProspects(String inputFilePath) {
		File file = new File(inputFilePath);

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));

			// Skip first line
			br.readLine();

			String st;
			while ((st = br.readLine()) != null) {

				st.trim();
				if (st.isEmpty())
					continue;

				String[] columns = st.split(",");
				if (columns.length < 4)
					continue;

				String customer = "";
				int currIndex = 0;

				// Handle the case where the name contains a comma.
				// It is required that such strings are surrounded
				// by quotation marks. Otherwise consider the row to
				// be ill-formatted and skip it.
				if (st.charAt(0) == '\"') {
					String[] quoteSplit = st.split("\"");
					customer = quoteSplit[1];

					int commasInsideQuotes = customer.split(",").length - 1;
					currIndex += commasInsideQuotes + 1;
				} else if (columns.length >= 5) {
					// Ill-formatted row.
					continue;
				} else {
					customer = columns[currIndex++];
				}

				// In a real-world setting I would probably use
				// some other data type than float for monetary values.
				float totalLoan = Float.parseFloat(columns[currIndex++]);
				float interest = Float.parseFloat(columns[currIndex++]);
				int years = Integer.parseInt(columns[currIndex++]);

				Mortage newMortage = new Mortage(customer, totalLoan, interest, years);
				mortages.add(newMortage);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeProspects(String outputFileName) {
		File outputFile = new File(outputFileName);

		try {
			outputFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false));
			
			DecimalFormat df = new DecimalFormat("0.00");

			for (int i = 0; i < mortages.size(); ++i) {
				Mortage mortage = mortages.get(i);

				String outputString = "Prospect " + (i + 1) + ": ";
				outputString += mortage.getCustomer();
				outputString += " wants to borrow " + df.format(mortage.getTotalLoan()) + "€";
				outputString += " for a period of " + mortage.getYears() + " years";
				outputString += " and pay " + df.format(mortage.getMonthlyPayment()) + "€ each month.";
				writer.write(outputString);
				writer.newLine();
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		if (args.length < 2) {
			System.out.println("Usage: java MortagePlane [input file] [output file]");
			return;
		}

		MortagePlan mortagePlan = new MortagePlan();
		mortagePlan.readProspects(args[0]);
		mortagePlan.writeProspects(args[1]);
	}
}
