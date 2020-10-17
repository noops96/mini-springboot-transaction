package com.example.Transaction.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Transaction.Entity.Cart;
import com.example.Transaction.Model.CartItemDTO;
import com.example.Transaction.Service.TransactionService;

@RequestMapping({ "transaction" })
@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping(value = "/add-to-cart")
	public ResponseEntity<?> AddToCart(@RequestBody CartItemDTO cartItemDTO) {
		try {
			transactionService.addToCart(cartItemDTO);
			return ResponseEntity.ok("ok");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/remove-from-cart")
	public ResponseEntity<?> RemoveFromCart(@RequestBody CartItemDTO cartItemDTO) {
		try {
			transactionService.removeFromCart(cartItemDTO);
			return ResponseEntity.ok("ok");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/view-cart",params = {"id"})
	public ResponseEntity<?> ViewCart(int id) {
		return ResponseEntity.ok(transactionService.viewCart(id));
	}
	
	@GetMapping(value = "/view-invoice",params = {"id"})
	public ResponseEntity<?> ViewInvoice(int id) {
		return ResponseEntity.ok(transactionService.ViewInvoice(id));
	}
	
	@PostMapping(value = "/process",params = {"buyerId"})
	public ResponseEntity<?> process(int buyerId) {
		try {
			transactionService.process(buyerId);
			return ResponseEntity.ok("ok");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
}