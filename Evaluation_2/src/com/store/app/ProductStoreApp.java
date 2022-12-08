package com.store.app;

import static com.store.util.ProductUtil.*;
import static com.store.util.ProductValidation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import com.store.model.Category;
import com.store.model.Product;

public class ProductStoreApp {

	public static void main(String[] args) {
		try(Scanner scan = new Scanner(System.in);
				BufferedReader brRead = new BufferedReader(new InputStreamReader(System.in))) {
			
			// loading the pre-loaded list
			List<Product> productList = loadExistingProductList();
			
			System.out.println("WELCOME TO CARTANA STORE!!!");
			
			boolean loop = true;
			
			while(loop) {
				System.out.println("Press 1 to add a new product");
				System.out.println("Press 2 to list products by category");
				System.out.println("Press 3 to search a product by item code or its name");
				System.out.println("Press 4 to list all the products");
				System.out.println("Press 10 to exit");
				System.out.println("Enter your choice: ");
				int choice = scan.nextInt();
				
				switch (choice) {
				case 1:
					// prompting user for inputs
					System.out.println("Enter the product code: ");
					String productCode = brRead.readLine();
					// check for duplicate product code
					checkDuplicateProductCode(productCode, productList);
					
					System.out.println("Enter product name: ");
					String productName = brRead.readLine();
					
					System.out.println("Enter the buying price: ");
					double buyingPrice = scan.nextDouble();
					// validate buying price
					validateBuyingPrice(buyingPrice);
					
					System.out.println("Enter the selling price: ");
					double sellingPrice = scan.nextDouble();
					// validate selling price
					validateSellingPrice(sellingPrice, buyingPrice);
					
					System.out.println("Enter product category: ");
					System.out.println("Press 1 for CAMERA, 2 for LAPTOP, 3 for WATCH, 4 for MOBILEPHONE");
					int categoryChoice = scan.nextInt();
					Category checkCategory = validateCategory(categoryChoice);
					
					System.out.println("Enter tax input");
					System.out.println("Press 1 for 5%, 2 for 10%, 3 for 15%, 4 for 18%, 5 for 28%");
					int taxChoice = scan.nextInt();
					double tax = validateTax(taxChoice);
					
					System.out.println("Enter quantity: ");
					int quantity = scan.nextInt();
					
					// adding product to the list
					productList.add(new Product(productCode, productName, buyingPrice, sellingPrice, checkCategory, tax, quantity));
					break;

				case 2:
					System.out.println("Enter the category you need to search for: ");
					System.out.println("Press 1 for CAMERA, 2 for LAPTOP, 3 for WATCH, 4 for MOBILEPHONE");
					int categorySearchChoice = scan.nextInt();
					Category searchCategory = validateCategory(categorySearchChoice);
					// category search counter
					int categorySearchCount = 0;
					
					System.out.println("======================================================================================================");
					System.out.println(String.format("%90s", "Product Details"));
					System.out.println("======================================================================================================");
					System.out.println(String.format("%-10s%10s%35s%20s%15s%28s%20s",
							"Code", "Name",
							"Buying Price", "SellingPrice",
							"Tax", "Category",
							"Quantity"));
					System.out.println("======================================================================================================");
					
					for (Product product : productList) {
						if(product.getCategory().equals(searchCategory)) {
							categorySearchCount++;
							displayProductDetails(product);
						} 
					}
					// if there's no product in the given category
					if(categorySearchCount == 0) {
						System.err.println("There's no product in the selected category!!!");
						System.err.println("Please add the product in the category first!!!");
					}
					break;
					
				case 3:
					System.out.println("Enter product name or product code that you want to search: ");
					String search = brRead.readLine().toUpperCase();
					// product search counter
					int productSearchCount = 0;
					
					System.out.println("======================================================================================================");
					System.out.println(String.format("%90s", "Product Details"));
					System.out.println("======================================================================================================");
					System.out.println(String.format("%-10s%10s%35s%20s%15s%28s%20s",
							"Code", "Name",
							"Buying Price", "SellingPrice",
							"Tax", "Category",
							"Quantity"));
					System.out.println("======================================================================================================");
					
					for (Product product : productList) {
						if(product.getItemCode().equals(search) || product.getItemName().toUpperCase().equals(search)) {
							productSearchCount++;
							displayProductDetails(product);
						}
					}
					System.out.println("======================================================================================================");
					
					// if product is not found
					if(productSearchCount == 0) {
						System.err.println("The product with the input name/code doesn't exist!!!");
						System.err.println("Please add the product in the database first!!!");
					}
					break;
					
				case 4:
					System.out.println("======================================================================================================");
					System.out.println(String.format("%90s", "Product Details"));
					System.out.println("======================================================================================================");
					System.out.println(String.format("%-10s%10s%35s%20s%15s%28s%20s",
							"Code", "Name",
							"Buying Price", "SellingPrice",
							"Tax", "Category",
							"Quantity"));
					System.out.println("======================================================================================================");
					for (Product product : productList) {
						displayProductDetails(product);
					}
					System.out.println("======================================================================================================");
					break;
					
				case 10:
					loop = false;
					break;
					
				default:
					System.out.println("Not a valid choice");
					break;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
