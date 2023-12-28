package com.suraj.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.suraj.model.Order;
import com.suraj.model.Product;
import com.suraj.util.DBConnection;

public class DAO {	
	static Scanner sc = new Scanner(System.in);
	
	public static void registerProduct() throws SQLException {
		System.out.println("Enter no. of products you want to register!!!");
		int noOfProductsToRegiser = sc.nextInt();
		
		Connection conn= DBConnection.getConnection();
		int count = 1;
		int increValue = 1000;
		for(int i=0; i<noOfProductsToRegiser; i++) {
		
		Product product = new Product();
		product.setProductID(increValue++);
		System.out.println("Enter the product name: "); product.setProductName(sc.next());
		System.out.println("Enter product descriptin: "); product.setProductDescription(sc.next());
		System.out.println("Enter product price: "); product.setProductPrice(sc.nextDouble());
		System.out.println("Enter product available quantity: "); product.setProductAvailableQuantity(sc.nextInt());
		
		String SQL = "insert into producttable values (?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, product.getProductID());
			ps.setString(2, product.getProductName());
			ps.setString(3, product.getProductDescription());
			ps.setDouble(4, product.getProductPrice());
			ps.setInt(5, product.getProductAvailableQuantity());
			
			ps.executeUpdate();
			System.out.println(count + " product registered  succsessfully.");
			count++;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		}
		conn.close();
	}

	public static void searchProductByName() throws SQLException {
		System.out.println("Enter the product you want to search: ");
		String pName = sc.next();
		
		Connection conn= DBConnection.getConnection();
		String SQL ="select * from producttable where productName=?";
		PreparedStatement ps = conn.prepareStatement(SQL);
		ps.setString(1, pName);
		ResultSet rs= ps.executeQuery();
		
		while(rs.next()) {
			System.out.println("Product ID: " + rs.getInt("productID"));
			System.out.println("Product Name: " + rs.getString("productName"));
			System.out.println("Product Description: " + rs.getString("productDescription"));
			System.out.println("Prouduct Price: " + rs.getString("price"));
			System.out.println("Product Available Quantity: " + rs.getInt("quantityAvailable"));
			System.out.println("---------------------------------------------------------------");
		}
	 conn.close();
	}

	public static void placeOrder() throws SQLException {
		System.out.println("Enter the name of the product you want to order!!!");
		String pName = sc.next();
		
		double discount1 = 0.0; 
	
		//Checking if the product is available or not
		Connection conn= DBConnection.getConnection();
		String SQL = "select * from producttable where productName=?";
		PreparedStatement ps = conn.prepareStatement(SQL);
		ps.setString(1, pName);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			
			int qtyAvailable = rs.getInt("quantityAvailable");
				
				if(qtyAvailable > 0) {
					System.out.println("Enter the quantity to order");
					int orderQuantity = sc.nextInt();
						if(orderQuantity<=qtyAvailable) {
							
							//Setting the orderId by creating a new object for Order Class.
								Order o = new Order();
									o.setOrderID(Order.id++);
							
							//Calculating total price by applying discount!
									double price = rs.getDouble("price");
									double totalprice = orderQuantity * price;
									
									if(totalprice>10000) {
										discount1 = totalprice - (0.1 * totalprice);
										totalprice = totalprice - discount1;
									 }
									
							//Inserting into ordertable
							String insertIntoSQL = "insert into ordertable VALUES (?,?,?,?,?,?)";
							PreparedStatement prepareStatement = conn.prepareStatement(insertIntoSQL);
							prepareStatement.setInt(1, o.getOrderID());
							prepareStatement.setString(2, pName);
							prepareStatement.setInt(3, orderQuantity);
							prepareStatement.setDouble(4, price);
							prepareStatement.setDouble(5, totalprice);
							prepareStatement.setDouble(6, discount1);
							prepareStatement.executeUpdate();
									
							//Updating the producttable
							
							int updatedQty = qtyAvailable - orderQuantity;
							String updateQtySQL = "update producttable set quantityAvailable = ? where productName = ? ";
							PreparedStatement pss = conn.prepareStatement(updateQtySQL);
							pss.setInt(1, updatedQty);
							pss.setString(2, pName);
							pss.executeUpdate();
							
						}else {
							System.out.println("Insufficient quantity available!");
							return;
						}
					
				}else {
					System.out.println("Product out of stock!!");
				}
			
			
		} else {
			System.out.println("The product not found!!");
		}
		conn.close();
		
	}
	

}
