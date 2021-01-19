package intercom.InvisteCustomers;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.management.JMRuntimeException;

import java.io.*;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CustomersList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URL url;
		String sCurrentLine = null;
		JSONParser parser = new JSONParser();
		int i = 0;
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		try {
			url = new URL("https://s3.amazonaws.com/intercom-take-home-test/customers.txt");

			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(url.openStream()));

			while ((sCurrentLine = bufferReader.readLine()) != null) {

				JSONObject json = (JSONObject) new JSONParser().parse(sCurrentLine);
				long user_id = (Long) json.get("user_id");
				String name = (String) json.get("name");
				String latitude = (String) json.get("latitude");
				String longitude = (String) json.get("longitude");

				int customerId = (int) user_id;
				String customerName = name;
				double latitude2 = Double.parseDouble(latitude);
				double longitude2 = Double.parseDouble(longitude);
				 i = calculateDistance(customerId, customerName, latitude2, longitude2);
				 if (!(i == 0)) {

						map.put(customerId, customerName);

					}
				 
			}
			for (Map.Entry<Integer, String> e : map.entrySet()) {
				writeCustomerList(e.getKey(), e.getValue());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeCustomerList(int customerId, String customerName) {

		try {
			FileWriter fw = new FileWriter("InviteCustomerList.txt", true);
			BufferedWriter out = new BufferedWriter(fw);
			out.write("User Id : " + customerId + "  Customer Name : " + customerName + "\n");
			out.close();
		} catch (Exception e) {
			System.err.println("Failed to create file:  " + e.getMessage());
		}

	}

	public static int calculateDistance(int customerId, String customerName, double latitude2, double longitude2) {

		double latitude1 = 53.339428;
		double longitude1 = -6.257664;

		latitude1 = Math.toRadians(latitude1);
		longitude1 = Math.toRadians(longitude1);
		latitude2 = Math.toRadians(latitude2);
		longitude2 = Math.toRadians(longitude2);

		double dlon = longitude2 - longitude1;
		double dlat = latitude2 - latitude1;
		double a = Math.pow(Math.sin(dlat / 2), 2)
				+ Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(dlon / 2), 2);

		double c = 2 * Math.asin(Math.sqrt(a));

		double r = 6371;

		double k = (c * r);
		

		if (k < 100) { 
			
		return customerId;
		}else {
			return 0;
		}

	}

}
