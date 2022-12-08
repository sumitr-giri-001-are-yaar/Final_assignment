package com.store.util;

import java.util.List;

import com.store.exception.ProductCustomException;
import com.store.model.Category;
import com.store.model.Product;

public class ProductValidation {

	// method to check for duplicate product code
	public static void checkDuplicateProductCode(String itemCode,
			List<Product> productList) throws ProductCustomException {
		Product checkProduct = new Product(itemCode);
		for (Product product : productList) {
			if (product.equals(checkProduct)) {
				throw new ProductCustomException("Duplicate product found!!!");
			}
		}
	}

	// method to validate buying price
	public static void validateBuyingPrice(double buyingPrice)
			throws ProductCustomException {
		if (buyingPrice <= 0) {
			throw new ProductCustomException(
					"Buying price has to be greater than 0!!!");
		}
	}

	// method to validate buying price
	public static void validateSellingPrice(double sellingPrice,
			double buyingPrice) throws ProductCustomException {
		if (sellingPrice < buyingPrice) {
			throw new ProductCustomException(
					"Selling price can't be less than buying price!!!");
		}
	}

	// method to validate product category
	public static Category validateCategory(int categoryChoice)
			throws ProductCustomException {
		if (categoryChoice > 0 && categoryChoice <= 4) {
			if (categoryChoice == 1) {
				return Category.CAMERA;
			} else if (categoryChoice == 2) {
				return Category.LAPTOP;
			} else if (categoryChoice == 3) {
				return Category.WATCH;
			} else {
				return Category.MOBILEPHONE;
			}
		}
		throw new ProductCustomException("Not a valid product category!!!");
	}

	// method to validate Tax category
	public static double validateTax(int taxChoice) throws ProductCustomException {
			if(taxChoice > 0 && taxChoice <= 5) {
				if(taxChoice == 1) {
					return 5;
				} else if(taxChoice == 2) {
					return 10;
				} else if(taxChoice == 3) {
					return 15;
				} else if(taxChoice == 4) {
					return 18;
				} else {
					return 28;
				}
			}
			throw new ProductCustomException("Not a valid Tax category!!!");
		}
}
