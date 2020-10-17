package com.example.Transaction.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Transaction.DAO.CartDAO;
import com.example.Transaction.DAO.CartItemDAO;
import com.example.Transaction.DAO.InvoiceDAO;
import com.example.Transaction.DAO.InvoiceDetailDAO;
import com.example.Transaction.Entity.Cart;
import com.example.Transaction.Entity.CartItem;
import com.example.Transaction.Entity.Invoice;
import com.example.Transaction.Entity.InvoiceDetail;
import com.example.Transaction.Helper.RequestService;
import com.example.Transaction.Model.BuyerModel;
import com.example.Transaction.Model.CartItemDTO;
import com.example.Transaction.Model.ProductModel;
import com.example.Transaction.Model.SellerModel;

@Transactional
@Service
public class TransactionService {
	
	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private CartItemDAO cartItemDAO;

	@Autowired
	private InvoiceDAO invoiceDAO;

	@Autowired
	private InvoiceDetailDAO invoiceDetailDAO;
	
	public Cart getbyId(Integer id) {
		return cartDAO.getById(id);
	}
	
	public Cart viewCart(Integer id) {
		
		return  cartDAO.getByBuyerId(id);
	}
	
	public List<Invoice> ViewInvoice(Integer id){
		return invoiceDAO.getByBuyerId(id);
	}
	
	
	public void process(Integer buyerId) throws Exception {
		Cart cart = cartDAO.getByBuyerId(buyerId);
		if(cart != null || cart.getCartItem().size() > 0) {
		BuyerModel buyer = RequestService.doGetBuyer(buyerId);
		BigDecimal totalPrice = new BigDecimal(0);
		Invoice invoice =new Invoice();
		invoice.setBuyerId(buyerId);
		invoice.setBuyerName(buyer.getName());
		invoice.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		invoice.setPrcsDate(new Timestamp(System.currentTimeMillis()));
		invoiceDAO.add(invoice);
		for(CartItem cartItem:cart.getCartItem()) {
			InvoiceDetail invoiceDetail = new InvoiceDetail();
			BigDecimal pricePerItem = new BigDecimal(0);
			ProductModel product = RequestService.doGetProduct(cartItem.getProductId());
			SellerModel seller = RequestService.doGetSeller(product.getSellerId());
			invoiceDetail.setInv(invoice);
			if(product != null) {
				invoiceDetail.setProduct_id(product.getId());
				invoiceDetail.setProductName(product.getName());
				invoiceDetail.setPrice(product.getPrice());
				pricePerItem= invoiceDetail.getPrice().multiply(new BigDecimal(cartItem.getQty()));
			}
			if(seller!= null) {
				invoiceDetail.setSeller_id(seller.getId());
				invoiceDetail.setSellerName(seller.getName());
			}
			invoiceDetail.setQty(cartItem.getQty());
			invoiceDetail.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			totalPrice= totalPrice.add(pricePerItem);
			invoiceDetailDAO.add(invoiceDetail);
		}
		invoice.setTotPrice(totalPrice);
		invoiceDAO.edit(invoice);
		cartDAO.delete(cart.getId());
		}else {
			throw new Exception("tidak ada item di cart");
		}
	}
	
	public void addToCart(CartItemDTO cartItemDTO) throws Exception {
		try {
			Integer buyerId = cartItemDTO.getBuyerId();
			List<CartItem> cartItemList = cartItemDTO.getCartItemList();
			Cart cart = cartDAO.getByBuyerId(buyerId);
			if(cart != null) {
				addCartDetail(cartItemList, cart);
			}else {
				if(RequestService.doCheckBuyer(buyerId)) {
					cart = new Cart();
					cart.setBuyerId(buyerId);
					cart.setCreatedAt(new Timestamp(System.currentTimeMillis()));
					cart = cartDAO.add(cart);
					addCartDetail(cartItemList, cart);
				}else {
					throw new Exception("buyer not Found");
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
	}
	
	public void removeFromCart(CartItemDTO cartItemDTO) throws Exception {
		try {
			Integer buyerId = cartItemDTO.getBuyerId();
			List<CartItem> cartItemList = cartItemDTO.getCartItemList();
			removeCartDetail(cartItemList);
		} catch (Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
	}
	
	private void addCartDetail(List<CartItem> cartItemList,Cart cart) {
		for(CartItem cartItem:cartItemList) {
			cartItem.setId(null);
			cartItem.setCart(cart);
			cartItem.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			cartItemDAO.add(cartItem);
		}
	}
	
	private void removeCartDetail(List<CartItem> cartItemList) {
		for(CartItem cartItem:cartItemList) {
			cartItemDAO.delete(cartItem.getId());
		}
	}

}
