package com.sjsu.market.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sjsu.market.beans.PaymentInfo;
import com.sjsu.market.beans.Product;
import com.sjsu.market.beans.User;
import com.sjsu.marketplace.process.MarketProcess;
import com.sjsu.marketplace.process.ProductProcess;

@Path("/market")
public class MarketPlaceController {
	@GET
	@Path("/productlist")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts(@QueryParam("userId") String userId) {
		System.out.println("in get params " + userId);
		MarketProcess marketProcess = new MarketProcess();
		List<Product> productList = marketProcess.getProductList();
		if (null != productList && !productList.isEmpty()) {
			return Response.status(200).entity(productList).build();
		}
		//return Response.status(400).entity("Something went wrong!!").build();
		return Response.status(200).entity(productList).build();
	}

	@POST
	@Path("/addtocart")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addToCart(@FormParam("userId") int userId, @FormParam("quantity") int quantity, @FormParam("itemId") String itemId, @FormParam("itemPrice") float itemPrice) {
		boolean isSuccess = new MarketProcess().addToCart(userId, quantity, itemId, itemPrice);
		if (isSuccess) {
			return Response.status(200).entity(new User(1, "Amol", "amolmane")).build();
		}
		return Response.status(400).entity(new User(1, "Amol", "amolmane")).build();
	}

	@GET
	@Path("/cartitems")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCartItems(@QueryParam("userId") int userId) {
		System.out.println("in cart items" + userId);
		MarketProcess marketProcess = new MarketProcess();
		List<Product> productList = marketProcess.cartItemsList(userId);
		if (null != productList && !productList.isEmpty()) {
			return Response.status(200).entity(productList).build();
		}
		return Response.status(400).entity("Something went wrong!!").build();
	}
	
	@GET
	@Path("/history")
	@Produces(MediaType.APPLICATION_JSON)
	public Response gethistoryItems(@QueryParam("userId") int userId) {
		System.out.println("in cart items" + userId);
		MarketProcess marketProcess = new MarketProcess();
		List<Product> productList = marketProcess.historyItemsList(userId);
		if (null != productList && !productList.isEmpty()) {
			return Response.status(200).entity(productList).build();
		}
		return Response.status(400).entity("Something went wrong!!").build();
	}

	/*
	 * @POST
	 * 
	 * @Path("/placeorder")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response
	 * placeOrder(@FormParam("userId") int userId, @FormParam("ccNumber") long
	 * ccNumber, @FormParam("csvNumber") int csvNumber,
	 * @FormParam("totalAmount") float totalAmount){
	 * System.out.println("in get order"); PaymentInfo paymentInfo = new
	 * PaymentInfo(userId, ccNumber, csvNumber, totalAmount); MarketProcess
	 * marketProcess = new MarketProcess();
	 * marketProcess.placeOrder(paymentInfo); return
	 * Response.status(200).entity(new User(1, "Amol", "amolmane")).build(); }
	 */
	@POST
	@Path("/placeorder")
	@Produces(MediaType.APPLICATION_JSON)
	public Response makePay(@FormParam("userId") int userId, @FormParam("ccNumber") long ccNumber, @FormParam("csvNumber") int csvNumber, @FormParam("totalAmount") float totalAmount) {
		System.out.println("in get order " + userId + " " + ccNumber + " " + csvNumber + " " + totalAmount);
		MarketProcess marketProcess = new MarketProcess();
		PaymentInfo paymentInfo = new PaymentInfo(userId, ccNumber, csvNumber, totalAmount);
		marketProcess.placeOrder(paymentInfo);
		return Response.status(200).entity(new User(1, "Amol", "amolmane")).build();
	}

	@GET
	@Path("/cartpage")
	@Produces(MediaType.APPLICATION_JSON)
	// public Response cartPage(@QueryParam("userId") int userId,
	// @QueryParam("ccNumber") long ccNumber, @QueryParam("csvNumber") int
	// csvNumber, @QueryParam("totalAmount") float totalPayment) {
	public Response cartPage() {
		System.out.println("in cart page nav");
		return Response.status(200).entity(new User(1, "Amol", "amolmane")).build();
	}

	@GET
	@Path("/paymentpage")
	@Produces(MediaType.APPLICATION_JSON)
	public Response paymentPage(@QueryParam("userId") int userId, @QueryParam("totalAmount") float totalAmount) {
		return Response.status(200).entity(new PaymentInfo(userId, totalAmount)).build();
	}

	@POST
	@Path("/addItem")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addItem(@FormParam("productName") String productName, @FormParam("productCount") int productCount, @FormParam("productCategory") String productCategory,
			@FormParam("productPrice") Float productPrice, @FormParam("productDesc") String productDesc) {
		System.out.println("product add" + productName + " " + productDesc + " " + productPrice + " " + productCategory);
		Product product = new Product(productName, productCount, productPrice, productDesc, productCategory);
		ProductProcess productProcess = new ProductProcess();
		boolean isAdded = productProcess.addProduct(product);
		if (isAdded) {
			return Response.status(200).entity(product).build();
		}
		return Response.status(400).entity("").build();
	}
}
